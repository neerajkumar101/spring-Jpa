package com.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.domain.entities.Comment;
import com.domain.entities.Post;
import com.domain.entities.User;
import com.domain.repositories.CommentRepo;
import com.domain.repositories.PostRepo;
import com.domain.repositories.UserRepo;

@Service("commService")
public class CommentServiceImpl implements CommentServiceInterface {

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<Comment> findCommentsByPostId(Post postId) {
		return commentRepo.findCommentsByPostId(postId);
	}

	@Override
	public void saveComment(Comment comment) {
		comment.setCommentDate(new Date());
		comment.setCommentTime(new Date());
		comment.setCommentText(comment.getCommentText());
		comment.setPost(comment.getPost());
		comment.setUser(comment.getUser());
		commentRepo.save(comment);
	}
	
	@Override
	public void deleteCommentById(Long commentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllCommentsByPostId(Long postId) {
		Post post = postRepo.findPostsByPostId(postId);
		List<Comment> comments = commentRepo.findCommentsByPostId(post); 
		commentRepo.delete(comments);//delete all comments in the list
	}

	@Override
	public Comment addComment(String serializeContent) {
		// TODO Auto-generated method stub
		return null;
	}

}
