package com.nraj.authapi.service;

import java.util.List;

import com.nraj.authapi.entity.Comment;
import com.nraj.authapi.entity.Post;

public interface CommentServiceInterface {
	public Comment findCommentById(Long commentId);
	public List<Comment> findAllComments();
	public List<Comment> findCommentsByPostId(Post postId);
	public Comment saveComment(Comment comment);
	public Comment addComment(String serializeContent);
	public void deleteCommentById(Long commentId);
	public void deleteAllCommentsByPostId(Long postId);
}
