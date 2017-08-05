package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.models.User;
import com.services.UserCrudService;

@RestController
@RequestMapping("/api/v1")
public class MainController {
	
	@Autowired
	public UserCrudService userCrudService;
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public Map<String, User> insert(@RequestBody User user){
		System.out.println("user object recieved: " + user);
		User usr = userCrudService.saveIt(user);
		System.out.println("user object saved: " + usr);
		Map<String, User> map = new HashMap<String, User>();
		map.put("Saved_User", usr);
		return map;
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public Map<String, User> getAll(){
		Map<String, User> map = new HashMap<String, User>();
		List<User> users = new ArrayList<>();
		users = userCrudService.listAll();
		for(User user : users){
			map.put("User Data", user);
		}
		return map;
		
	}
}
