package com.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.entities.Role;

@Repository("roleRepo")
public interface RoleRepo extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}
