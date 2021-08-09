package io.github.anantharajuc.rc.infra.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.infra.exception.SpringRedditException;
import io.github.anantharajuc.rc.service.AppServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;

@Service
public class JwtProvider 
{
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	private KeyStore keyStore;

	@PostConstruct
    public void init() 
	{
		appServiceImpl.loadApplicationSettings();
		
        try 
        {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/"+appServiceImpl.getKeystoreFileName());
            keyStore.load(resourceAsStream, appServiceImpl.getKeystorePassword().toCharArray());
        } 
        catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) 
        {
            throw new SpringRedditException("Exception occurred while loading keystore", e);
        }
    }

	private PrivateKey getPrivateKey() 
	{
        try 
        {
            return (PrivateKey) keyStore.getKey(appServiceImpl.getKeystoreAlias(), appServiceImpl.getKeystorePassword().toCharArray());
        } 
        catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) 
        {
            throw new SpringRedditException("Exception occured while retrieving public key from keystore", e);
        }
    }
	
	private PublicKey getPublickey() 
	{
        try 
        {
            return keyStore.getCertificate(appServiceImpl.getKeystoreAlias()).getPublicKey();
        } 
        catch (KeyStoreException e) 
        {
            throw new SpringRedditException("Exception occured while retrieving public key from keystore", e);
        }
    }
	
	public String generateToken(Authentication authentication)
	{
		User principal = (User) authentication.getPrincipal();
		
		return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(appServiceImpl.getJwtExpirationTime()))) 
                .compact();
	}
	
	public String generateTokenWithUserName(String username) 
	{
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(from(Instant.now()))
				.signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(appServiceImpl.getJwtExpirationTime())))
                .compact();
				
		
	}
	
	public boolean validateToken(String jwt) 
	{
        parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        
        return true;
    }
	
	public String getUsernameFromJwt(String token) 
	{
        Claims claims = parser()
			                .setSigningKey(getPublickey())
			                .parseClaimsJws(token)
			                .getBody();

        return claims.getSubject();
    }
}
