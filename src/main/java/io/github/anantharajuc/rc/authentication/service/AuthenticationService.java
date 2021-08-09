package io.github.anantharajuc.rc.authentication.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.github.anantharajuc.rc.authentication.AuthenticationResponse;
import io.github.anantharajuc.rc.authentication.UserLoginRequestDTO;
import io.github.anantharajuc.rc.authentication.UserSignupRequestDTO;
import io.github.anantharajuc.rc.authentication.model.RefreshToken;
import io.github.anantharajuc.rc.authentication.model.VerificationToken;
import io.github.anantharajuc.rc.security.user.model.User;

/**
 * Authentication Service
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface AuthenticationService 
{
	void signup(UserSignupRequestDTO userDto);
	
	void fetchUserAndEnable(VerificationToken verificationToken);
	
	String verifyAccount(@PathVariable String token);
	
	String generateVerificationToken(User user);
	
	AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO);
	
	AuthenticationResponse refreshToken(RefreshToken refreshToken);
}
