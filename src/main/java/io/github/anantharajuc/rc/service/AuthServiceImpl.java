package io.github.anantharajuc.rc.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.dto.AuthenticationResponse;
import io.github.anantharajuc.rc.dto.UserLoginRequestDTO;
import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import io.github.anantharajuc.rc.exceptions.SpringRedditException;
import io.github.anantharajuc.rc.model.NotificationEmail;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.model.VerificationToken;
import io.github.anantharajuc.rc.repository.UserRepository;
import io.github.anantharajuc.rc.repository.VerificationTokenRepository;
import io.github.anantharajuc.rc.security.JwtProvider;

@Service
public class AuthServiceImpl implements AuthService
{
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailServiceImpl mailServiceImpl;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, VerificationTokenRepository verificationTokenRepository, MailServiceImpl mailServiceImpl) 
	{
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailServiceImpl = mailServiceImpl;
	}
	
	@Value("${mail.subject}")
	private String mailSubject;
	
	@Value("${mail.body}")
	private String mailBody;
	
	@Override
	@Transactional
	public void signup(UserSignupRequestDTO userDto) 
	{
		User user;
		
		user = modelMapper.map(userDto, User.class); 
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword())); 
		user.setEnabled(false); 
		
		userRepository.save(user);
		
		String token = generateVerificationToken(user);
		
		mailServiceImpl.sendMail(new NotificationEmail(mailSubject, user.getEmail(), mailBody+token));
	}

	@Override
	public String generateVerificationToken(User user) 
	{
		String token = UUID.randomUUID().toString();
		
		VerificationToken verificationToken = new VerificationToken();
		
		verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plus(30, ChronoUnit.SECONDS));

        verificationTokenRepository.save(verificationToken);
        
		return token;
	}
 
	@Override
	public String verifyAccount(String token) 
	{
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		
		verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token!"));
		
		if(verificationToken.isPresent() && Instant.now().isBefore(verificationToken.get().getExpiryDate()))
		{
			fetchUserAndEnable(verificationToken.get());
		}
		else
		{
			return "token expired!";
		}
		return "Account Activated Successfully"; 
	}

	@Override
	@Transactional
	public void fetchUserAndEnable(VerificationToken verificationToken) 
	{
		String username = verificationToken.getUser().getUsername();
		
		User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User does not exist"));
	
		user.setEnabled(true);
		
		userRepository.save(user);
	}

	@Override
	public AuthenticationResponse login(UserLoginRequestDTO userLoginRequestDTO) 
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		return new AuthenticationResponse(token, userLoginRequestDTO.getUsername());
	}
}
