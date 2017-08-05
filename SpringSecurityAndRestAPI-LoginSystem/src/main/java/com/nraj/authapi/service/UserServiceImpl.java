package com.nraj.authapi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nraj.authapi.entity.Role;
import com.nraj.authapi.entity.User;
import com.nraj.authapi.repo.RoleRepo;
import com.nraj.authapi.repo.UserRepo;

@Service("userService")
public class UserServiceImpl implements UserServiceInterface{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	@Override
	public User saveUser(User user) {
		//for encryption of passwords
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepo.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepo.save(user);
	}

	@Override
	public User findUserById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public User getActiveUser(String userName) {
		User activeUser = new User();
		short active = 1;
		List<?> list = entityManager.createQuery("SELECT u FROM User u WHERE userName=? and active=?")
				.setParameter(1, userName).setParameter(2, active).getResultList();
		if(!list.isEmpty()) {
			activeUser = (User)list.get(0);
		}
		return activeUser;
	}
	
}
