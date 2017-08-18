package com.app.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_tbl")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	protected long userId;
	
	@Column(name="name")
	@NotEmpty(message = "*Please provide your name")
	@Length(min = 3, message = "*Name must have at least 3 characters")
	private String name;
	
	@Column(name="email", unique=true)
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name="address")
	@NotBlank
	private String address;

	@OneToOne(mappedBy="credentialUser", cascade = CascadeType.ALL)//fetch = FetchType.LAZY,
	@JsonManagedReference
	private Credential credential;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Post> posts = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="sharedBy", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Post> sharedPosts = new HashSet<>();
		
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
	
	public Set<Post> getSharedPosts() {
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
    
    public void addSharedPost(Post post){
    	sharedPosts.add(post);
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