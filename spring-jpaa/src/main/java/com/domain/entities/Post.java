package com.domain.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	private Long postId; 
	
	@Column(name="post_text")
	private String postText;
	
	private Long likeCount;
	
	private Long shareCount;
	
	private Long commentCount;

	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinTable(name="user_post",
		joinColumns=@JoinColumn(name="post_id"), 
		inverseJoinColumns=@JoinColumn(name="user_id"))
	private User user;
	
	@OneToMany(mappedBy="post", orphanRemoval=true,
			cascade = {CascadeType.MERGE,CascadeType.REMOVE})
	private List<Comment> comments = new ArrayList<Comment>();

	@ManyToMany(mappedBy="sharedPost",cascade={CascadeType.MERGE,CascadeType.REMOVE})
	private List<User> sharedBy = new ArrayList<>();
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
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

	public Long getShareCount() {
		return shareCount;
	}

	public void setShareCount(Long shareCount) {
		this.shareCount = shareCount;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(List<User> sharedBy) {
		this.sharedBy = sharedBy;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
