package io.github.anantharajuc.rc.service;

import io.github.anantharajuc.rc.dto.UserDTO;
import io.github.anantharajuc.rc.model.User;

public interface AuthService 
{
	void signup(UserDTO userDto);
	
	String generateVerificationToken(User user);
}
