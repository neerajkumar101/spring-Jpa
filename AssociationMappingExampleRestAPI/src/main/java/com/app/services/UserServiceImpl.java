package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.repo.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User saveUser(User cstmr) {
		User customer = userRepo.save(cstmr);
		return customer;
	}

	@Override
	public User findById(Long userId) throws Exception {
		User user = userRepo.findOne(userId);
		if(user == null){
			throw new Exception("No User Found For the given id: " + userId.toString());
		}
		return user;
	}

	@Override
	public void deleteUser(Long userId) {
		userRepo.delete(userId);
	}

}
