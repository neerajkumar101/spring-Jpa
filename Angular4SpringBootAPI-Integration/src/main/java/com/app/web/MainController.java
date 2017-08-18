package com.app.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;

import com.app.entity.Credential;
import com.app.entity.Post;
import com.app.entity.User;
import com.app.services.CredentialServiceImpl;
import com.app.services.PostServiceImpl;
import com.app.services.UserServiceImpl;

import ch.qos.logback.core.net.SyslogOutputStream;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class MainController {

	@Autowired
	private CredentialServiceImpl crdntlService;
	
	@Autowired
	private UserServiceImpl usrService;
	
	@Autowired
	private PostServiceImpl postService;
	
	/*@PostMapping("/user")
	public ResponseEntity<User> adduser(@RequestBody @Valid User user, BindingResult bindingResult){
		User userExists = usrService.findUserByEmail((String) user.getEmail());
		User usr = null;
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
			return new ResponseEntity<User>(usr, HttpStatus.NOT_ACCEPTABLE);
		}
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<User>(usr, HttpStatus.BAD_REQUEST);
		} else {
			usr = usrService.saveUser(user);
			return new ResponseEntity<User>(usr, HttpStatus.OK);			
		}		
	}*/
	@PostMapping("/user")
	public ResponseEntity<Map<String, User>> adduser(@RequestBody @Valid User user, BindingResult bindingResult){
		User userExists = usrService.findUserByEmail((String) user.getEmail());
		User usr = null;
		Map<String, User> map = new HashMap<>();
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
			map.put("User with the same email is exixts: please chose another email address.", usr);
			return new ResponseEntity<Map<String, User>>(map, HttpStatus.NOT_ACCEPTABLE);
		}
		if (bindingResult.hasErrors()) {
			map.put("Please send correct data", usr);
			return new ResponseEntity<Map<String, User>>(map, HttpStatus.BAD_REQUEST);
		} else {
			usr = usrService.saveUser(user);
			map.put("Registered Successfully", usr);
			return new ResponseEntity<Map<String, User>>(map, HttpStatus.OK);		
		}		
	}
	
	@GetMapping("/getuserbypost/post/{postId}")
	public ResponseEntity<User> getUserByPost(@PathVariable("postId") Long postId){
		User user = postService.findUserByPostId(postId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/sharedBy/post/{postId}")
	public ResponseEntity<Set<User>> postSharedByWho(@PathVariable("postId") Long postId){
		Set<User> set = postService.postSharedBy(postId);
		return new ResponseEntity<Set<User>>(set, HttpStatus.OK);
	}
	
	@RequestMapping("/user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId){
		User user;
		try {
			user = usrService.findById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/credential")
	public ResponseEntity<Credential> doTransaction(@PathVariable("userId") Long userId, @RequestBody Credential credential) {
		User usr = null;
		Credential crdntl = null;
		
		if(userId != null || userId != new Long(0)){
			try {
				usr = usrService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				return new ResponseEntity<Credential>(crdntl, HttpStatus.BAD_REQUEST);
			}
		} 
		if(usr != null){
			crdntl = crdntlService.saveCredential(usr, credential);
			return new ResponseEntity<Credential>(crdntl, HttpStatus.OK);
		} else {
			return new ResponseEntity<Credential>(crdntl, HttpStatus.BAD_REQUEST);
		}
	} 

	@PutMapping(value="/user/{userId}/post")
	public ResponseEntity<Post> publishPost(final HttpServletResponse response, 
			@PathVariable("userId") Long userId, @RequestBody Post post) {
		User usr = null;
		Post postInside = null; 
		System.out.println("touch down");
		
	     
		if(userId != null || userId != new Long(0)){
			try {
				usr = usrService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				return new ResponseEntity<Post>(postInside, HttpStatus.BAD_REQUEST);
			}
		} 
		if(usr != null){
			postInside = postService.publishPost(usr, post);
			return new ResponseEntity<Post>(postInside, HttpStatus.OK);
		} else {
			return new ResponseEntity<Post>(post, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> list = postService.getAll();
		for(Post post : list){
			System.out.println("logging: " + post.getSharedBy());
		}
		return new ResponseEntity<List<Post>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
		User usr = null;
		
		if(userId != null || userId != new Long(0)){
			try {
				usr = usrService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				return new ResponseEntity<String>("userId cannotbe null", HttpStatus.BAD_REQUEST);
			}
		} 
		if(usr != null){
			usrService.deleteUser(userId); 
			return new ResponseEntity<String>("user deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("no user with given userId", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/user/{userId}/post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("userId") Long userId, 
				@PathVariable("postId") Long postId) {
		User usr = null;
		Post post = null; 
		
		if(userId != null || userId != new Long(0)){
			try {
				usr = usrService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				return new ResponseEntity<String>("userId cannotbe null", HttpStatus.BAD_REQUEST);
			}
		} 
		if(usr != null){
			post = postService.getPostById(postId);
			if(post != null){
				postService.deletePost(postId);
				return new ResponseEntity<String>("post is deleted successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("no post with given postId", HttpStatus.BAD_REQUEST);
			}
			
		} else {
			return new ResponseEntity<String>("no user with given userId", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/share/user/{userId}/post/{postId}")
	public ResponseEntity<Map<String, Post>> sharePost(@PathVariable("userId") Long userId, 
			@PathVariable("postId") Long postId){
		
		User usr = null;
		Post post = null; 
		Post sharedPost = null;
		Map<String, Post> map = new HashMap<String, Post>();
		
		if(userId != null || userId != new Long(0)){
			try {
				usr = usrService.findById(userId);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
				map.put("userId cannotbe null", sharedPost);
				return new ResponseEntity<Map<String, Post>>(map, HttpStatus.BAD_REQUEST);
			}
		} 
		if(usr != null){
			post = postService.getPostById(postId);
			if(post != null){
				sharedPost = postService.sharePost(usr, post);
				map.put("post is shared successfully", sharedPost);
				return new ResponseEntity<Map<String, Post>>(map, HttpStatus.OK);
			} else {
				map.put("no post with given postId", sharedPost);
				return new ResponseEntity<Map<String, Post>>(map, HttpStatus.BAD_REQUEST);
			}
			
		} else {
			map.put("no user with given userId", sharedPost);
			return new ResponseEntity<Map<String, Post>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
}
