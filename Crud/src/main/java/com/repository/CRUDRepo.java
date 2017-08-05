package com.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.User;

@Repository
public interface CRUDRepo extends JpaRepository<User, Serializable>{
	
}
