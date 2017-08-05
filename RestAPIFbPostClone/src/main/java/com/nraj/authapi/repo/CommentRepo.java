package com.nraj.authapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nraj.authapi.entity.Comment;
import com.nraj.authapi.entity.Post;

@Repository("commentRepo")
public interface CommentRepo extends JpaRepository<Comment, Long> {
	
	@Query("FROM Comment c WHERE c.post = ? ORDER BY c.commentId ASC")
	public List<Comment> findCommentsByPostId(Post post);
}
