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

import com.nraj.authapi.entity.Post;
import com.nraj.authapi.service.PostServiceInterface;

@RestController
@RequestMapping("/authorized/api/v1")
public class PostController {
	@Autowired
	private PostServiceInterface postService;
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> list = postService.findAllPosts();
		return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/post")
	public ResponseEntity<Void> addPost(@RequestBody Post post, UriComponentsBuilder builder) {
        Post savedPost = postService.savePost(post);
        if (savedPost == null) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/post/{postId}").buildAndExpand(post.getPostId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/post")
	public ResponseEntity<Post> updatePost(@RequestBody Post post) {
		postService.savePost(post);
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId) {
		postService.deletePostById(postId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
} 