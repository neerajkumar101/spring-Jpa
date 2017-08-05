package com.service;

import com.domain.Entities.User;

public interface UserServiceInterface {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
