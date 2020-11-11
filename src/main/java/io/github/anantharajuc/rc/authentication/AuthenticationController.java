package io.github.anantharajuc.rc.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.api.ResourcePaths;
import io.github.anantharajuc.rc.authentication.model.RefreshToken;
import io.github.anantharajuc.rc.authentication.service.AuthenticationServiceImpl;
import io.github.anantharajuc.rc.authentication.service.RefreshTokenServiceImpl;
import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

@RestController
@RequestMapping(value=ResourcePaths.Authentication.V1.ROOT)
@Api(value="Authentication", tags="Authentication")
@AllArgsConstructor
public class AuthenticationController 
{
	private final AuthenticationServiceImpl authServiceImpl;
	private final RefreshTokenServiceImpl refreshTokenServiceImpl;

	@PostMapping(value=ResourcePaths.Authentication.V1.SIGNUP)
	@ApiOperation(httpMethod="POST", value="Signup for an account.", notes="Signup for an account.")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDTO userDto) 
	{
		authServiceImpl.signup(userDto);
		
        return ResponseEntity.status(OK).body("User Registration Successful! Activate your account by following the instructions in the verification email.");
    }
	
	@GetMapping(value=ResourcePaths.Authentication.V1.VERIFICATION)
	@ApiOperation(httpMethod="GET", value="Verify Account.", notes="Verify Account based on validity of the verification token.")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGIN)
	@ApiOperation(httpMethod="POST", value="Login.", notes="Login to an existing account.")
    public AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) 
	{
		return authServiceImpl.login(userLoginRequestDTO);
    }
	
	@PostMapping(value=ResourcePaths.Authentication.V1.REFRESH_TOKEN)
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshToken refreshToken) 
	{
		return authServiceImpl.refreshToken(refreshToken);
	}
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGOUT)
	public ResponseEntity<String> logout(@RequestBody RefreshToken refreshToken)
	{
		refreshTokenServiceImpl.deleteByToken(refreshToken.getToken());
		
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully.");
	}
} 
