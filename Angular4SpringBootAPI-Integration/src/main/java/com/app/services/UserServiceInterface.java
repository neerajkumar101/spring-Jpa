package com.app.services;

import com.app.entity.User;

public interface UserServiceInterface {
	public User saveUser(User cstmr);
	public void deleteUser(Long userId);
	public User findById(Long custId) throws Exception;
	public User findUserByEmail(String Email);
}
