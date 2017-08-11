package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Credential;
import com.app.entity.User;
import com.app.repo.CredentialRepo;

@Service
public class CredentialServiceImpl implements CredentialServiceInterface {

	@Autowired
	private CredentialRepo credentialRepo;

	@Override
	public Credential saveCredential(User usr, Credential crndtl){
		Credential credential = credentialRepo.findCredentialByUserId(usr.getUserId());
		if(credential == null) {
			crndtl.setUser(usr);
			return credentialRepo.save(crndtl);
		} else {
			return null;
		} 
		
	}
	

}
