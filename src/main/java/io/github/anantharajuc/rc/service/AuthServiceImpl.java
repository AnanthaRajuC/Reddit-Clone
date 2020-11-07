package io.github.anantharajuc.rc.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.dto.UserDTO;
import io.github.anantharajuc.rc.model.NotificationEmail;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.model.VerificationToken;
import io.github.anantharajuc.rc.repository.UserRepository;
import io.github.anantharajuc.rc.repository.VerificationTokenRepository;

@Service
public class AuthServiceImpl implements AuthService
{
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailServiceImpl mailServiceImpl;
	
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
	public void signup(UserDTO userDto) 
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
        
        verificationTokenRepository.save(verificationToken);
        
		return token;
	}
}
