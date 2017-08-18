package com.app.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="post_tbl")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="post_id")
	protected Long postId; 
	
	@Column(name="post_text")
	@NotBlank
	private String postText;
	
	@Column(name="like_count")
	private Long likeCount;
	
	@Column(name="share_count")
	private Long shareCount;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
		
	@JsonIgnore
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="shared_posts",
			joinColumns=@JoinColumn(name="shared_post_id"),
			inverseJoinColumns=@JoinColumn(name="sharing_user_id"))
	private Set<User> sharedBy = new HashSet<>();

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

	public Long getShareCount() {
		return shareCount;
	}

	public void setShareCount(Long shareCount) {
		this.shareCount = shareCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getSharedBy() {
		return sharedBy;
	}
	
	public void addSharedBy(User user) {
//		this.sharedBy = sharedBy;
		this.sharedBy.add(user);
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
