package io.github.anantharajuc.rc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.security.user.model.User;
import io.github.anantharajuc.rc.service.UserServiceImpl;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/user")
@AllArgsConstructor
public class UserController 
{
	private final UserServiceImpl userServiceImpl;
	@GetMapping()
	public List<User> getAllSubreddits()
	{
		return userServiceImpl.findAllUsers();
	}
}
