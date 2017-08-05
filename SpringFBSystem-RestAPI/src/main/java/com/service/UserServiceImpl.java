package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.entities.Role;
import com.domain.entities.User;
import com.domain.repositories.RoleRepo;
import com.domain.repositories.UserRepo;

@Service("userService")
public class UserServiceImpl implements UserServiceInterface{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	@Override
	public void saveUser(User user) {
		//for encryption of passwords
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepo.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(user);
	}

	@Override
	public User findUserById(Long id) {
		return userRepo.findById(id);
	}

/*	@Override
	public void sendFriendRequest(User requestUser) {
		List<User> friendRequests = new ArrayList<>();
		friendRequests.add(requestUser);
		requestUser.setFriendRequests(friendRequests);
		userRepo.save(requestUser);
	}

	@Override
	public void approveFriendRequest(User user) {
		// TODO Auto-generated method stub
		
	}*/

}
