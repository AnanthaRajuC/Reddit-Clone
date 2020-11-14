package io.github.anantharajuc.rc.security.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.anantharajuc.rc.security.user.model.User;
import io.github.anantharajuc.rc.security.user.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RedditUserServiceImpl implements RedditUserService
{
	private final UserRepository userRepository;
	
	@Override
	public List<User> findAllUsers() 
	{
		return userRepository.findAll();
	}
}
