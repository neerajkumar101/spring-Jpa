package com.nraj.authapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nraj.authapi.entity.User;
import com.nraj.authapi.service.UserServiceInterface;

@RestController
public class UserSignUpLoginController {
	@Autowired
	private UserServiceInterface userService;
	
	@PostMapping("/signup")
	public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) {
        User savedUser = userService.saveUser(user);
        if (savedUser == null) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{userId}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
}
