package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.Customer;
import com.app.repo.CustomerRepo;

public class CustomerServiceImpl implements CustomerServiceInterface {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public Customer saveCustomer(Customer cstmr) {
		Customer customer = customerRepo.save(cstmr);
		return customer;
	}

}
