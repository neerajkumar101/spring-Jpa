package com.nraj.authapi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nraj.authapi.entity.Comment;
import com.nraj.authapi.service.CommentServiceInterface;

@RestController
@RequestMapping("/authorized/api/v1")
public class CommentController {
	@Autowired
	private CommentServiceInterface commService;
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
		Comment	comment = commService.findCommentById(commentId);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> list = commService.findAllComments();
		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/comment")
	public ResponseEntity<Void> addComment(@RequestBody Comment comment, UriComponentsBuilder builder) {
        Comment savedComment = commService.saveComment(comment);
        if (savedComment == null) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/comment/{commentId}").buildAndExpand(comment.getCommentId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/comment")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
		commService.saveComment(comment);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
		commService.deleteCommentById(commentId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
} 