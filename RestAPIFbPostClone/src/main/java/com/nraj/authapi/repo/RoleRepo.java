package com.nraj.authapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nraj.authapi.entity.Role;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}
