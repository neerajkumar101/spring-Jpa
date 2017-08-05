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
import com.nraj.authapi.entity.User;
import com.nraj.authapi.service.PostServiceInterface;
import com.nraj.authapi.service.UserServiceInterface;

@RestController
@RequestMapping("/authorized/api/v1")
public class PostController {
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired
	private UserServiceInterface userService;
	
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
	
	@PostMapping("/user/{userId}/post")
	public ResponseEntity<Void> addPost(@PathVariable("userId") Long userId, @RequestBody Post post, UriComponentsBuilder builder) {
		if(userId != new Long(0)){
			User user = userService.findUserById(userId);
			 HttpHeaders headers = new HttpHeaders();
			if(user != null){
				post.setUser(user);
		        Post savedPost = postService.savePost(post);
		        if (savedPost == null) {
		        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		        }
		        headers.setLocation(builder.path("/post/{postId}").buildAndExpand(post.getPostId()).toUri());
		        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			}else{
				return new ResponseEntity<Void>(headers, HttpStatus.UNAUTHORIZED);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable("userId") Long userId,@PathVariable("postId") Long postId, @RequestBody Post post) {
		System.out.println("inside post update controller handler method");
		Post foundPost = postService.findPostById(postId);
		if(userId != new Long(0) && foundPost != null){
			User user = userService.findUserById(userId);
			if(user != null){
				foundPost.setPostText(post.getPostText());
				post = postService.savePost(foundPost);
				return new ResponseEntity<Post>(post, HttpStatus.OK);
			}else{
				return new ResponseEntity<Post>(post, HttpStatus.UNAUTHORIZED);
			}
		}else{
			return new ResponseEntity<Post>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable("userId") Long userId, @PathVariable("id") Long postId) {
		Post post = postService.findPostById(postId);
		if(userId != new Long(0) || post != null){
			User user = userService.findUserById(userId);
			if(user != null){
				postService.deletePostById(postId);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}else{
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		}else{
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}	
	
} 