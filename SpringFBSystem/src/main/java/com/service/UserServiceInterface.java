package com.service;

import com.domain.entities.User;

public interface UserServiceInterface {
	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public void saveUser(User user);
	/*public void sendFriendRequest(User user);
	public void approveFriendRequest(User user);*/
}
