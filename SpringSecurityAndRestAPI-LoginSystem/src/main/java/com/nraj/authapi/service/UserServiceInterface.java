package com.nraj.authapi.service;

import com.nraj.authapi.entity.User;

public interface UserServiceInterface {
	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public User saveUser(User user);
	public User getActiveUser(String userName);
	/*public void sendFriendRequest(User user);
	public void approveFriendRequest(User user);*/
}
