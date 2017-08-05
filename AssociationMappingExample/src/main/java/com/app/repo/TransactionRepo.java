package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long>{

}
