package com.nraj.authapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nraj.authapi.service.UserServiceInterface;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserServiceInterface userService;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		com.nraj.authapi.entity.User activeUser = userService.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRoles().toString());
		UserDetails userDetails = (UserDetails)new User(activeUser.getEmail(),
				activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}

