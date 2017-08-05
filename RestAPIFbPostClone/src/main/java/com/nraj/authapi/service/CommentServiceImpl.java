package com.nraj.authapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nraj.authapi.entity.Comment;
import com.nraj.authapi.entity.Post;
import com.nraj.authapi.repo.CommentRepo;
import com.nraj.authapi.repo.PostRepo;
import com.nraj.authapi.repo.UserRepo;

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
	public Comment saveComment(Comment comment) {
		comment.setCommentDate(new Date());
		comment.setCommentTime(new Date());
		comment.setCommentText(comment.getCommentText());
		comment.setPost(comment.getPost());
		comment.setUser(comment.getUser());
		return commentRepo.save(comment);
	}
	
	@Override
	public void deleteCommentById(Long commentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllCommentsByPostId(Long postId) {
		Post post = postRepo.findPostByPostId(postId);
		List<Comment> comments = commentRepo.findCommentsByPostId(post); 
		commentRepo.delete(comments);//delete all comments in the list
	}

	@Override
	public Comment addComment(String serializeContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findCommentById(Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllComments() {
		return (List<Comment>) commentRepo.findAll();
	}

}
