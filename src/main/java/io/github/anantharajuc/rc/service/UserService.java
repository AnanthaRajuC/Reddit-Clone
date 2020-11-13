package io.github.anantharajuc.rc.service;

import java.util.List;

import io.github.anantharajuc.rc.security.user.model.User;

public interface UserService 
{
	List<User> findAllUsers();
}
