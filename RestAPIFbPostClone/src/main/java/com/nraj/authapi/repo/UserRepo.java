package com.nraj.authapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nraj.authapi.entity.User;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Long> {
	@Query("FROM User u where u.email = ? ORDER BY u.userId DESC") 
	User findByEmail(String email);
	
	@Query("FROM User u where u.userId = ? ORDER BY u.userId DESC")
	User findById(Long id);
	
	@Query("FROM User u ORDER BY u.userId DESC")
	List<User> findAll();
}
