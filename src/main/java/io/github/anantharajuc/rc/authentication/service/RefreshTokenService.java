package io.github.anantharajuc.rc.authentication.service;

import io.github.anantharajuc.rc.authentication.model.RefreshToken;

public interface RefreshTokenService 
{
	RefreshToken generateRefreshToken(String username);
	
	void validateRefreshToken(String token);
	
	void deleteByToken(String token, String username);
}
