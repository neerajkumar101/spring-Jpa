package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.User;
import com.repositories.BasicRepo;
import com.repositories.UserCrudRepositoryBackedByJpaRepository;

@Service
public class UserCrudService implements BasicRepo{
	
	@Autowired
	UserCrudRepositoryBackedByJpaRepository userCrudRepo;

	@Override
	public User saveIt(User user) {
		return (User) userCrudRepo.save(user);
	}

	@Override
	public ArrayList<User> saveAll(List<User> users) {
		
		return (ArrayList<User>) userCrudRepo.save(users);
	}

	@Override
	public User updateIt(long id, User user) {
		User usr = userCrudRepo.findOne(id);
		if(usr != null){
			return (User) userCrudRepo.save(user);
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<User> listAll() {
		return (ArrayList<User>) userCrudRepo.findAll();
	}

	@Override
	public User listOne(long id) {
		return userCrudRepo.findOne(id);
	}

	@Override
	public boolean deleteIt(long id) {
		User user = userCrudRepo.findOne(id);
		if( user != null){
			userCrudRepo.delete(user.getId());
			return true;
		} else {
			return false;
		}
		
	}

}
