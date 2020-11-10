package io.github.anantharajuc.rc.authentication;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import io.github.anantharajuc.rc.model.User;
import io.github.anantharajuc.rc.model.VerificationToken;

public interface AuthenticationService 
{
	void signup(UserSignupRequestDTO userDto);
	
	void fetchUserAndEnable(VerificationToken verificationToken);
	
	String verifyAccount(@PathVariable String token);
	
	String generateVerificationToken(User user);
	
	AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO);
}
