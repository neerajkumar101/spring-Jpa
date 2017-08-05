package com.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.Entities.User;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Long> {
	 User findByEmail(String email);
}
