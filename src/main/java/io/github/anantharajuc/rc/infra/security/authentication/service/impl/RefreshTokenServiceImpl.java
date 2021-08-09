package io.github.anantharajuc.rc.infra.security.authentication.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.rc.infra.exception.SpringRedditException;
import io.github.anantharajuc.rc.infra.security.authentication.model.RefreshToken;
import io.github.anantharajuc.rc.infra.security.authentication.repository.RefreshTokenRepository;
import io.github.anantharajuc.rc.infra.security.authentication.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Refresh Token Service Implementation
 * 
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 */
@Service
@AllArgsConstructor
@Transactional
@Log4j2
public class RefreshTokenServiceImpl implements RefreshTokenService
{
	private final RefreshTokenRepository refreshTokenRepository;
	
	@Override
	public RefreshToken generateRefreshToken(String stage, String username) 
	{
		RefreshToken refreshToken = new RefreshToken();
		
		if(stage.equalsIgnoreCase("LOGIN"))
		{
			log.info("LOGIN token generation");
			
			refreshToken.setToken(UUID.randomUUID().toString());
			refreshToken.setUsername(username); 
		}
		else if(stage.equalsIgnoreCase("POST_LOGIN"))
		{
			log.info("POST_LOGIN token generation");
			
			refreshToken = refreshTokenRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
			
			refreshToken.setToken(UUID.randomUUID().toString());
			refreshToken.setUsername(username); 
		}

		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public void validateRefreshToken(String token) 
	{
		log.info("Validating Refresh Token");
		
		refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
	}

	@Override
	public void deleteByToken(String token, String username) 
	{
		RefreshToken refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));

		if(refreshToken.getToken().equals(token) && refreshToken.getUsername().equals(username)) 
		{
			refreshTokenRepository.deleteByToken(token); 
			
			log.info("Token deletion successful.");
		}
		else
		{
			log.info("Token, Username mismatch!");
			
			throw new SpringRedditException("Token, Username mismatch!");
		}
	}
}
