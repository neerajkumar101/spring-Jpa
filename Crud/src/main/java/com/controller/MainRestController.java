package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.domain.User;
import com.service.CrudService;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {
	
	@Autowired
	public CrudService service;
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
	public Map<String, User> insert(@RequestBody User user){
		System.out.println(user);
		Map<String, User> map = new HashMap<>();
		User usr = service.saveAndFlush(user);
		map.put("user_name", usr);
		System.out.println(map);
		return map;		
	}
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public Map<String, List<User>> read(){
		Map<String, List<User>> map = new HashMap<>();
		ArrayList<User> userList = service.getAll();
		
		map.put("all_users", userList);
		return map;
		
	}
	
	@RequestMapping(value="/users",method=RequestMethod.PUT)
	public String updateUsr(@RequestBody User user){
		System.out.println(user);
		
		User usr = service.update(user.getId(), user.getName(), user.getEmail());
		if(usr == null){
			return "Success";
		} else{
			return "failure";
		}
				
				
	}
	@RequestMapping(value="/users",method=RequestMethod.DELETE)
	public Map<String, String> deleteUser(@RequestParam int id){
		System.out.println(id);
		service.delete(id);
		
		Map<String, String> map = new HashMap<>();
		map.put("user_name", "user deleted");
		return map;
	}
}
