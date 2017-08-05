package com.domain.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinTable;

@Entity
@Table(name="TBL_USER")
public class User {
	
	private long id;
	
	private String userName;
	
	private String fisrtName;
	
	private String lastName;
	
	private String email;
	
	private
	@OneToMany
	@JoinTable(joinColumns=@JoinColumn(name="User_id"), inverseJoinColumns=@JoinColumn(name="post_id"))
	private List<Post> posts = new LinkedList<Post>();
}
