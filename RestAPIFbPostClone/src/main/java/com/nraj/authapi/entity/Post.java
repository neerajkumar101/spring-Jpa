package com.nraj.authapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
	@JoinTable(name="user_post",
		joinColumns=@JoinColumn(name="post_id"), 
		inverseJoinColumns=@JoinColumn(name="user_id"))
//	@JsonBackReference
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="post", orphanRemoval=true,
			cascade = {CascadeType.MERGE,CascadeType.REMOVE})
//	@JsonManagedReference
	private List<Comment> comments = new ArrayList<Comment>();

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.MERGE,CascadeType.REMOVE})
	@JoinTable(name = "shared_posts", 
		joinColumns = @JoinColumn(name = "post_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
//	@JsonBackReference
	private User sharedBy;
	
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

//	public List<User> getSharedBy() {
//		return sharedBy;
//	}
//
//	public void setSharedBy(List<User> sharedBy) {
//		this.sharedBy = sharedBy;
//	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postText=" + postText + ", likeCount=" + likeCount + ", shareCount="
				+ shareCount + ", commentCount=" + commentCount + ", user=" + user + ", comments=" + comments
				+ ", sharedBy=" + sharedBy + "]";
	}
	
	
}
