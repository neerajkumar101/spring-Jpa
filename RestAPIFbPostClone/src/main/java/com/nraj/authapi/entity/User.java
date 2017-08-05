package com.nraj.authapi.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "email", unique=true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	
	@Column(name = "user_name")
	@NotEmpty(message = "*Please provide your name")
	private String userName;
	
//	@Column(name = "last_name")
//	@NotEmpty(message = "*Please provide your last name")
//	private String lastName;
		
	@Column(name = "active")
	private int active;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.REMOVE})
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	@JsonManagedReference
	private Set<Role> roles;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user", cascade = {CascadeType.MERGE,CascadeType.REMOVE})
//	@JsonManagedReference
	private List<Post> posts = new ArrayList<Post>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="sharedBy", cascade = {CascadeType.MERGE,CascadeType.REMOVE})
//	@JsonManagedReference
	private List<Post> sharedPost = new ArrayList<>();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public List<Post> getSharedPost() {
		return sharedPost;
	}
	public void setSharedPost(List<Post> sharedPost) {
		this.sharedPost = sharedPost;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userName=" + userName
				+ ", active=" + active + ", roles=" + roles + ", posts=" + posts + ", sharedPost=" + sharedPost + "]";
	}
	
	
	
}