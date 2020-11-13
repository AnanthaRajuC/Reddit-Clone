package io.github.anantharajuc.rc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.authentication.repository.UserRepository;
import io.github.anantharajuc.rc.security.user.model.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{
	private final UserRepository userRepository;
	
	@Override
	public List<User> findAllUsers() 
	{
		return userRepository.findAll();
	}
}
