package com.nraj.authapi.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nraj.authapi.entity.Role;

@Repository("roleRepo")
public interface RoleRepo extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}
