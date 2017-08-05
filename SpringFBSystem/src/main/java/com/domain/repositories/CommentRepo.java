package com.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.entities.Comment;
import com.domain.entities.Post;

@Repository("commentRepo")
public interface CommentRepo extends CrudRepository<Comment, Long> {
	
	@Query("FROM Comment c WHERE c.post = ? ORDER BY c.commentId ASC")
	public List<Comment> findCommentsByPostId(Post post);
}
