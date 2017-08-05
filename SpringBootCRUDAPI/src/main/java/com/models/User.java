package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author neeraj
 *
 */
@Entity
@Table(name="USER_TABLE")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_PASSWORD")
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column(name="JOINED_DATE")
	private Date JoinDate;
	
	@Column(name="PROFILE_PIC_PATH")
	private String profilePicPath;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_OF_BIRTH")
	private Date  dateOfBirth;
	
	@Embedded
	@Column(name="HOME_ADDRESS")
	@AttributeOverrides({
			@AttributeOverride(name = "STREET_NAME", column=@Column(name="HOME_STREET_NAME")),
			@AttributeOverride(name = "CITY_NAME", column=@Column(name="HOME_CITY_NAME")),
			@AttributeOverride(name = "STATE_NAME", column=@Column(name="HOME_STATE_NAME")),
			@AttributeOverride(name = "ZIP_CODE", column=@Column(name="HOME_ZIP_CODE"))
	})
	private Address homeAddress;
	
	@ElementCollection
	List<Address> otherAddressList = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getJoinDate() {
		return JoinDate;
	}

	public void setJoinDate(Date joinDate) {
		JoinDate = joinDate;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public List<Address> getOtherAddressList() {
		return otherAddressList;
	}

	public void setOtherAddressList(List<Address> otherAddressList) {
		this.otherAddressList = otherAddressList;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + ", JoinDate=" + JoinDate + ", profilePicPath="
				+ profilePicPath + ", dateOfBirth=" + dateOfBirth + ", homeAddress=" + homeAddress
				+ ", otherAddressList=" + otherAddressList + "]";
	}
}
