package io.github.anantharajuc.rc.api;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.rc.infra.security.user.model.User;
import io.github.anantharajuc.rc.infra.security.user.service.RedditUserServiceImpl;
import lombok.AllArgsConstructor;

/*
 * Reddit User Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@RestController
@RequestMapping(value="/user")
@AllArgsConstructor
public class UserController 
{
	private final RedditUserServiceImpl userServiceImpl;
	
	@GetMapping()
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<User> getAllSubreddits()
	{
		return userServiceImpl.findAllUsers();
	}
}
