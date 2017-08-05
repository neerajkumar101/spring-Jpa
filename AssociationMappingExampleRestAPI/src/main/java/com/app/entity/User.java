package com.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user_tbl")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	protected long userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="address")
	private String address;

	//user is target entity
	@OneToOne(mappedBy="credentialUser", cascade = {CascadeType.DETACH, CascadeType.REMOVE}, 
			orphanRemoval = true, fetch = FetchType.LAZY)
	private Credential credential;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user", orphanRemoval=true, cascade = CascadeType.ALL)
//	@JoinColumn(name="post_id")
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.REMOVE}, mappedBy="sharedBy")
	private List<Post> sharedPosts = new ArrayList<>();
	
	public long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Credential getCrendial() {
		return credential;
	}

	public void setCrendial(Credential crendial) {
		this.credential = crendial;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	public List<Post> getSharedPosts() {
		return sharedPosts;
	}
	
	public void addPost(Post post) {
        posts.add( post );
        post.setUser( this );
    }

    public void removePost(Post post) {
    	posts.remove( post );
    	post.setUser( null );
    }
    
    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        User user = (User) o;
        return Objects.equals( userId, user.userId );
    }

    @Override
    public int hashCode() {
        return Objects.hash( userId );
    }

}