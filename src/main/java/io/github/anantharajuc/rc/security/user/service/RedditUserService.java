package io.github.anantharajuc.rc.security.user.service;

import java.util.List;

import io.github.anantharajuc.rc.security.user.model.User;

public interface RedditUserService 
{
	List<User> findAllUsers();
}
