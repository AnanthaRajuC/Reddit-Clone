package io.github.anantharajuc.rc.service;

import org.springframework.web.bind.annotation.PathVariable;

import io.github.anantharajuc.rc.dto.UserDTO;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.model.VerificationToken;

public interface AuthService 
{
	void signup(UserDTO userDto);
	
	void fetchUserAndEnable(VerificationToken verificationToken);
	
	String verifyAccount(@PathVariable String token);
	
	String generateVerificationToken(User user);
}
