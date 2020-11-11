package io.github.anantharajuc.rc.authentication.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.rc.authentication.model.RefreshToken;
import io.github.anantharajuc.rc.authentication.repository.RefreshTokenRepository;
import io.github.anantharajuc.rc.exceptions.SpringRedditException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService
{
	private final RefreshTokenRepository refreshTokenRepository;
	
	@Override
	public RefreshToken generateRefreshToken() 
	{
		 RefreshToken refreshToken = new RefreshToken(); 
		 
		 refreshToken.setToken(UUID.randomUUID().toString());
		 
		 return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public void validateRefreshToken(String token) 
	{
		refreshTokenRepository.findByToken(token).orElseThrow(() -> new SpringRedditException("Invalid Refresh Token"));
	}

	@Override
	public void deleteByToken(String token) 
	{
		refreshTokenRepository.deleteByToken(token); 
	}
}
