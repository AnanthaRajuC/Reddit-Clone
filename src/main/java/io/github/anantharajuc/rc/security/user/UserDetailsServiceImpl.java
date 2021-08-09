package io.github.anantharajuc.rc.security.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.anantharajuc.rc.security.user.model.User;
import io.github.anantharajuc.rc.security.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final UserRepository userRepository;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
	{
		log.info("-----> loadUserByUsername  : "+username);
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
		
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
 
		return new org.springframework.security.core.userdetails.User(userDetailsImpl.getUsername(), 
																	  userDetailsImpl.getPassword(), 
																	  userDetailsImpl.isEnabled(), 
																	  userDetailsImpl.isAccountNonExpired(), 
																	  userDetailsImpl.isCredentialsNonExpired(), 
																	  userDetailsImpl.isAccountNonLocked(), 
																	  userDetailsImpl.getAuthorities());
	}
}
