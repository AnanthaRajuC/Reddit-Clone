package io.github.anantharajuc.rc.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse 
{
	private String authenticationToken;
	private String username;
}
