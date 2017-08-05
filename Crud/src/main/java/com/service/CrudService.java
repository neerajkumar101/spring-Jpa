package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.User;
import com.repository.CRUDRepo;

@Service
@Transactional
public class CrudService {
	
	@Autowired
	CRUDRepo repo;
	
	/*@PostConstruct
	@Transactional
	public void populate(){
		User user = new User();
		
		user.setName("Neeraj");
		user.setEmail("Neeraj@mail.com");
		repo.save(user);
		
		user.setName("sooraj");
		user.setEmail("sooraj@mail.com");
		repo.save(user);
		
		user.setName("dheraj");
		user.setEmail("dheraj@mail.com");
		repo.save(user);
	}*/
	/*
	 * read of crud
	 */
	@Transactional
	public ArrayList<User> getAll(){
		return (ArrayList<User>) repo.findAll();		
	}
	
	/*
	 * update of crud
	 */
	@Transactional
	public User update(int id, String name, String email){
		User usr = null;
		
		if(repo.getOne(id) != null){
			usr = repo.getOne(id);
			usr.setName(name);
			usr.setEmail(email);
			repo.saveAndFlush(usr);
			return usr;
		} else {
			return null;
		}		
	}

	@Transactional
	public User saveAndFlush(User user){
		return repo.save(user);
	}
	
	/*
	 * delete of crud
	 */
	@Transactional
	public void delete(int id){
		repo.delete(id);		
	}
}
