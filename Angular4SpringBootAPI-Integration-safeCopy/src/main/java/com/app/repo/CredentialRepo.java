package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entity.Credential;

@Repository
public interface CredentialRepo extends JpaRepository<Credential, Long> {
	@Query("FROM Credential cred WHERE cred.credentialUser.userId = ? ")
	public Credential findCredentialByUserId(long userId);
	
}
