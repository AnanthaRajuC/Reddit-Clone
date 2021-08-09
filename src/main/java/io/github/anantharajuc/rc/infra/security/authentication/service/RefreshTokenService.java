package io.github.anantharajuc.rc.infra.security.authentication.service;

import io.github.anantharajuc.rc.infra.security.authentication.model.RefreshToken;

/**
 * Refresh Token Service
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
public interface RefreshTokenService 
{
	RefreshToken generateRefreshToken(String stage, String username);
	
	void validateRefreshToken(String token);
	
	void deleteByToken(String token, String username);
}
