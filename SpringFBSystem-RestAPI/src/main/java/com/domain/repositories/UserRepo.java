package com.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.entities.User;

@Repository("userRepo")
public interface UserRepo extends CrudRepository<User, Long> {
	@Query("FROM User u where u.email = ? ORDER BY u.userId DESC") 
	User findByEmail(String email);
	
	@Query("FROM User u where u.userId = ? ORDER BY u.userId DESC")
	User findById(Long id);
	
	@Query("FROM User u ORDER BY u.userId DESC")
	List<User> findAll();
}
