package com.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long txnId;
	
	@Column(name="txnDate")
	private Date date;
	
	@Column(name="total")
	private double total;
	
	@OneToOne(mappedBy="txn", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Customer customer;

	public long getTxnId() {
		return txnId;
	}

	public void setTxnId(long txnId) {
		this.txnId = txnId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
