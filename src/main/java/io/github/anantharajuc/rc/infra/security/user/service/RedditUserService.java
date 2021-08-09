package io.github.anantharajuc.rc.infra.security.user.service;

import java.util.List;

import io.github.anantharajuc.rc.infra.security.user.model.User;

public interface RedditUserService 
{
	List<User> findAllUsers();
}
