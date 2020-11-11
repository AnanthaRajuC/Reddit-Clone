package io.github.anantharajuc.rc.authentication.service;

import io.github.anantharajuc.rc.authentication.model.RefreshToken;

public interface RefreshTokenService 
{
	RefreshToken generateRefreshToken();
	
	void validateRefreshToken(String token);
	
	void deleteByToken(String token);
}
