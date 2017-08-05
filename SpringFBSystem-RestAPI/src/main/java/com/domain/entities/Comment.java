package com.domain.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="comment_id")
	private long commentId;
	
	@Column(name="comment_text")
	@Lob
	private String commentText;
	
	@Column(name="comment_date")
	@Temporal(TemporalType.DATE)
	private Date commentDate;
	
	@Column(name="comment_time")
	@Temporal(TemporalType.TIME)
	private Date commentTime;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(cascade={CascadeType.MERGE})
//	@JoinColumn(name="post_id")
	@JoinTable(name="post_comments",
		joinColumns=@JoinColumn(name="post_id"), 
		inverseJoinColumns=@JoinColumn(name="comment_id"))
	private Post post;
	
	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
}
