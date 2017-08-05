package com.repositories;

import java.util.ArrayList;
import java.util.List;

import com.models.User;

public interface BasicRepo {
	public User saveIt(User user);
	
	public ArrayList<User> saveAll(List<User> users);
	
	public User updateIt(long id, User user);
	
	public ArrayList<User> listAll();
	
	public User listOne(long id);
	
	public boolean deleteIt(long id);
}
