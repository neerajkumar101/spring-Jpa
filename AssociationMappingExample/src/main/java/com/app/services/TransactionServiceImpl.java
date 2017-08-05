package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Transaction;
import com.app.repo.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionServiceInterface {

	@Autowired
	private TransactionRepo transactionRepo;
	
	@Override
	public Transaction saveTxn(Transaction txn) {
		Transaction transaction = transactionRepo.save(txn);
		return transaction;
	}

}
