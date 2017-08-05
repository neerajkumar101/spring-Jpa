package com.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.models.User;

@Repository
public interface UserCrudRepositoryBackedByJpaRepository extends JpaRepository<User, Serializable> {
	
}
