package io.github.anantharajuc.rc.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.api.ResourcePaths;
import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value=ResourcePaths.Authentication.V1.ROOT)
public class AuthenticationController 
{
	@Autowired
	private AuthenticationServiceImpl authServiceImpl;

	@PostMapping(value=ResourcePaths.Authentication.V1.SIGNUP)
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDTO userDto) 
	{
		authServiceImpl.signup(userDto);
		
        return ResponseEntity.status(OK).body("User Registration Successful! Activate your account by following the instructions in the verification email.");
    }
	
	@GetMapping(value=ResourcePaths.Authentication.V1.VERIFICATION)
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping(value=ResourcePaths.Authentication.V1.LOGIN)
    public AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) 
	{
		return authServiceImpl.login(userLoginRequestDTO);
    }
} 
