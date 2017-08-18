package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="credential_tbl")
public class Credential {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected long credId;
	
	@Column(name="pan_card_no", unique=true)
	private String panCardNo;
	
	@Column(name="adhaar_card_no", unique=true)
	private String adhaarCardNo;
	
	@Column(name="voter_id_card_no", unique=true)
	private String voterIdCardNo;
	
	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="user_id") 
	@JsonBackReference
	private User credentialUser;

	public long getCredId() {
		return credId;
	}

	public String getPanCardNo() {
		return panCardNo;
	}

	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}

	public String getAdhaarCardNo() {
		return adhaarCardNo;
	}

	public void setAdhaarCardNo(String adhaarCardNo) {
		this.adhaarCardNo = adhaarCardNo;
	}

	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}

	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}

	public User getUser() {
		return credentialUser;
	}

	public void setUser(User user) {
		this.credentialUser = user;
	}

}
