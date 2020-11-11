package io.github.anantharajuc.rc.authentication.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.rc.authentication.AuthenticationResponse;
import io.github.anantharajuc.rc.authentication.UserLoginRequestDTO;
import io.github.anantharajuc.rc.authentication.model.RefreshToken;
import io.github.anantharajuc.rc.authentication.model.VerificationToken;
import io.github.anantharajuc.rc.authentication.model.VerificationTokenEnum;
import io.github.anantharajuc.rc.authentication.repository.UserRepository;
import io.github.anantharajuc.rc.authentication.repository.VerificationTokenRepository;
import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import io.github.anantharajuc.rc.email.EmailServiceImpl;
import io.github.anantharajuc.rc.email.Email;
import io.github.anantharajuc.rc.exceptions.SpringRedditException;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.security.JwtProvider;

@Service
public class AuthenticationServiceImpl implements AuthenticationService
{
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailServiceImpl mailServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public AuthenticationServiceImpl(ModelMapper modelMapper, 
									 UserRepository userRepository, 
									 PasswordEncoder passwordEncoder, 
									 VerificationTokenRepository verificationTokenRepository, 
									 EmailServiceImpl mailServiceImpl,
									 RefreshTokenServiceImpl refreshTokenServiceImpl) 
	{
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.verificationTokenRepository = verificationTokenRepository;
		this.mailServiceImpl = mailServiceImpl;
		this.refreshTokenServiceImpl = refreshTokenServiceImpl;
	}
	
	@Value("${mail.subject}")
	private String mailSubject;
	
	@Value("${mail.body}")
	private String mailBody;
	
	@Value("${verification.token.validity}")
	private Long verificationTokenValidity;
	
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
		
		mailServiceImpl.sendMail(new Email(mailSubject, user.getEmail(), mailBody+token+" This link is valid for the next "+verificationTokenValidity+" minute."));
	}

	@Override
	public String generateVerificationToken(User user) 
	{
		String token = UUID.randomUUID().toString();
		
		VerificationToken verificationToken = new VerificationToken();
		
		verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plus(verificationTokenValidity, ChronoUnit.MINUTES));
        verificationToken.setStatus(VerificationTokenEnum.UNVERIFIED);
        
        verificationTokenRepository.save(verificationToken);
        
		return token;
	}
 
	@Override
	@Transactional
	public String verifyAccount(String token) 
	{
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		 
		if(verificationToken.isPresent() && verificationToken.get().getStatus().equals(VerificationTokenEnum.UNVERIFIED) &&Instant.now().isBefore(verificationToken.get().getExpiryDate()))
		{
			verificationToken.get().setStatus(VerificationTokenEnum.VERIFIED); 
			
			verificationTokenRepository.save(verificationToken.get());
			
			fetchUserAndEnable(verificationToken.get()); 
		}
		else if(verificationToken.isPresent() && verificationToken.get().getStatus().equals(VerificationTokenEnum.VERIFIED))
		{
			return "Account already verified.";
		}
		else if(!verificationToken.isPresent())
		{
			return "invalid verification token, please check the verification link.";
		}
		else
		{
			verificationTokenRepository.deleteById(verificationToken.get().getId()); 
			userRepository.deleteById(verificationToken.get().getId());
			
			return "token expired! Please register again.";
		}
		
		return "Account Activated Successfully. Login to the application to start using it."; 
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

		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenServiceImpl.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationTime()))
				.username(userLoginRequestDTO.getUsername())
				.build();
	}
	
	@Override
	public AuthenticationResponse refreshToken(RefreshToken refreshToken) 
	{
		refreshTokenServiceImpl.validateRefreshToken(refreshToken.getToken());

		String token = jwtProvider.generateTokenWithUserName(refreshToken.getUsername());
		
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshToken.getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationTime()))
				.username(refreshToken.getUsername())
				.build();
	}
}
