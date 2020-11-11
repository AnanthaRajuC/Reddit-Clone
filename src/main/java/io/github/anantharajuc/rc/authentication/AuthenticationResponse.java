package io.github.anantharajuc.rc.authentication;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse 
{
	private String authenticationToken;
	private String refreshToken;
	private Instant expiresAt;
	private String username;
}
