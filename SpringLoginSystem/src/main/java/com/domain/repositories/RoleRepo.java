package com.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.Entities.Role;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}
