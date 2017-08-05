package com.nraj.authapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nraj.authapi.entity.User;
import com.nraj.authapi.service.UserServiceInterface;

@RestController
@RequestMapping("/authorized/api/v1")
public class UserController {
	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
		User user = userService.findUserById(userId);
		if(userId !=null && user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else{
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/success/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		User user = userService.findUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
} 