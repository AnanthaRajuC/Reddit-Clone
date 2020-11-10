package io.github.anantharajuc.rc.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.dto.UserSignupRequestDTO;
import lombok.extern.log4j.Log4j2;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@Log4j2
public class AuthenticationController 
{
	@Autowired
	private AuthenticationServiceImpl authServiceImpl;

	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDTO userDto) 
	{
		authServiceImpl.signup(userDto);
		
        return ResponseEntity.status(OK).body("User Registration Successful! Activate your account by following the instructions in the verification email.");
    }
	
	@GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) 
	{
        return new ResponseEntity<>(authServiceImpl.verifyAccount(token), OK);
    } 
	
	@PostMapping("/login")
    public AuthenticationResponse login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) 
	{
		log.info("/api/auth/login controller");	
		return authServiceImpl.login(userLoginRequestDTO);
    }
} 
