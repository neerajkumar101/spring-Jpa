package com.service;

import java.util.List;

import com.domain.entities.Comment;
import com.domain.entities.Post;

public interface CommentServiceInterface {
	public List<Comment> findCommentsByPostId(Post postId);
	public void saveComment(Comment comment);
	public Comment addComment(String serializeContent);
	public void deleteCommentById(Long commentId);
	public void deleteAllCommentsByPostId(Long postId);
}
