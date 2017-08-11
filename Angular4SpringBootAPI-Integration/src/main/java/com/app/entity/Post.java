package com.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="post_tbl")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	protected Long postId; 
	
	@Column(name="post_text")
	private String postText;
	
	private Long likeCount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User sharingUser;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="shared_by",
		joinColumns=@JoinColumn(name="post_id"),
		inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> sharedBy = new ArrayList<User>();

	public Long getPostId() {
		return postId;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	public Long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSharingUser() {
		return sharingUser;
	}

	public void setSharingUser(User sharingUser) {
		this.sharingUser = sharingUser;
	}

	public List<User> getSharedBy() {
		return sharedBy;
	}
	
	@Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals( postId, post.postId ) &&
                Objects.equals( postText, post.postText ) &&
                Objects.equals( likeCount, post.likeCount );
    }

    @Override
    public int hashCode() {
        return Objects.hash( postId, postText, likeCount );
    }

}
