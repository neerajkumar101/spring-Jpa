package com.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.domain.entities.Comment;
import com.domain.entities.Post;
import com.domain.entities.User;
import com.domain.repositories.CommentRepo;
import com.domain.repositories.PostRepo;
import com.domain.repositories.UserRepo;
import com.service.CommentServiceInterface;
import com.service.PostServiceInterface;
import com.service.UserServiceInterface;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String root(){
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public  String index(){
		return "/index";
	}
		
	@RequestMapping("/login")
	public String login(){
		return "/login";
	}
	
		
	@RequestMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		return "/login";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView signup(){
		ModelAndView modelAndView = new ModelAndView();
		
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	
/*	
	@RequestMapping(value="/user/index", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Post post = new Post();
		Comment comment = new Comment();
		ArrayList<Post> posts = (ArrayList<Post>) postService.findPostsByUserId(user);
		if(!posts.isEmpty()){
			modelAndView.addObject("user_posts", posts);
		}
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
		
		post.setUser(user);
		
		modelAndView.addObject("post_ob", post);
		
		comment.setPost(post);
		comment.setUser(user);
		
		modelAndView.addObject("comment", comment);
		modelAndView.setViewName("user/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/profile", method = RequestMethod.GET)
	public ModelAndView profile(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Post post = new Post();
		post.setUser(user);
//		Comment comment = new Comment(); 
//		comment.setPost(post);
//		comment.setUser(user);
		
		List<User> allUsers = (ArrayList<User>) userRepo.findAll();
		ArrayList<Post> posts = (ArrayList<Post>) postService.findPostsByUserId(user);
		ArrayList<List<Comment>> comments = new ArrayList<>();
		
		for(Post eachPost : posts){
			comments.add((List<Comment>) commentRepo.findCommentsByPostId(eachPost));
		}
		if(!posts.isEmpty()){
			modelAndView.addObject("all_posts", posts);
			modelAndView.addObject("total_posts", posts.size());
		}
		if(!comments.isEmpty()){
			modelAndView.addObject("all_comments", comments);
		}
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
		modelAndView.addObject("post_ob", post);
		modelAndView.addObject("current_user", user);
//		modelAndView.addObject("comment", comment);
		modelAndView.addObject("all_users", allUsers);
		modelAndView.setViewName("user/profile");
		return modelAndView;
	}
	
	@RequestMapping(value = "/user/profile", method = RequestMethod.POST)
	public ModelAndView createUserComment(@ModelAttribute("post_ob") Post post_ob, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(post_ob.getUser().getUserId());
		Post post = new Post();
		post.setUser(user);
		if(post_ob.getLikeCount() == null){
			post_ob.setLikeCount(new Long(0));
		} else {
			post_ob.setLikeCount(post_ob.getLikeCount() + 1);
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("error");
		} else {
			post_ob.setUser(user);
			postService.savePost(post_ob);
		}
		ArrayList<Post> posts = (ArrayList<Post>) postService.findPostsByUserId(user);
		ArrayList<List<Comment>> comments = new ArrayList<>();
		for(Post eachPost : posts){
			comments.add((List<Comment>) commentRepo.findCommentsByPostId(eachPost));
		}
		if(!posts.isEmpty()){
			modelAndView.addObject("all_posts", posts);
			modelAndView.addObject("total_posts", posts.size());
		}
		if(!comments.isEmpty()){
			modelAndView.addObject("all_comments", comments);
		}
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
		modelAndView.addObject("post_ob", post);
		modelAndView.addObject("current_user", user);	
		modelAndView.setViewName("user/profile");
		return modelAndView;
	}
	
	@RequestMapping(value="/user/all_posts", method=RequestMethod.GET)
	public ModelAndView getAllPosts(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Post post = new Post(); 
		post.setUser(user);
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setUser(user);
		ArrayList<List<Comment>> comments = new ArrayList<>();
		ArrayList<Post> posts = (ArrayList<Post>) postRepo.findAll();
		if(!posts.isEmpty()){
			modelAndView.addObject("all_posts", posts);
			modelAndView.addObject("total_posts", posts.size());
			for(Post eachPost : posts){
				comments.add((List<Comment>) commentRepo.findCommentsByPostId(eachPost));
			}
			if(!comments.isEmpty()){
				modelAndView.addObject("all_comments", comments);
			}
		}
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
		modelAndView.addObject("comment", comment);
		modelAndView.addObject("current_user", user);
		modelAndView.setViewName("user/all_posts");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public ModelAndView error(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("exception_error/error");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/comment/add_comment", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> createPostComment(@RequestParam String comment){
		Map<String, String> map = new HashMap<>();
		Map<String, String> innerMap = new HashMap<String, String>();
		Comment commentOb = new Comment();
		System.out.println(comment);
		String[] fields = comment.split("&");
		for(String str : fields){
			String[] keyValue = str.split("=");
			innerMap.put(keyValue[0], keyValue[1]);
		}
		System.out.println(innerMap);
		String decodedCommenntText = null;
		String escapedCommentText = null;
		
		escapedCommentText = HtmlUtils.htmlEscape(innerMap.get("commentText"));
		try {
			decodedCommenntText = java.net.URLDecoder.decode(escapedCommentText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		commentOb.setCommentText(decodedCommenntText);
		Long postId = Long.parseLong(innerMap.get("post"));
		Post post = postRepo.findPostById(postId);
		commentOb.setPost(post);
		System.out.println("post object here: " + post);
		Long userId = Long.parseLong(innerMap.get("user"));
		User user = userRepo.findById(userId);
		commentOb.setUser(user);
		System.out.println("before sending the commentOb to be saved: " + commentOb);
		commService.saveComment(commentOb);
		
		map.put("postId", commentOb.getPost().getPostId().toString());
		map.put("userFullName", commentOb.getUser().getName() + ' ' + commentOb.getUser().getLastName());
		map.put("commentText", commentOb.getCommentText());
		return map;
	}
	
	@RequestMapping(value="/ajax/like_ajax", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> likeAjax(@RequestParam String json ){
		Map<String, String> map = new HashMap<>();
		Long longPostId = Long.parseLong(json);
		Post post = postRepo.findPostsByPostId(longPostId);
		Long success = postService.doLike(longPostId, post.getLikeCount());
		post = postRepo.findPostsByPostId(longPostId);
		if(success > 0){
			map.put("requestStatus", post.getLikeCount().toString());
			map.put("requestPostId", longPostId.toString());
		} else {
			map.put("requestStatus", new Long(0).toString());
		}
		return map;
	}
	
	@RequestMapping(value="/ajax/share_ajax", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> shareAjax(@RequestParam String postedData ){
		Map<String, String> map = new HashMap<>();
		Map<String, String> innerMap = new HashMap<String, String>(); 
		String[] fields = postedData.split("&");
		for(String str : fields){
			String[] keyValue = str.split("=");
			innerMap.put(keyValue[0], keyValue[1]); 
		}  
		System.out.println(innerMap);
//		Long userId = Long.parseLong(innerMap.get("userId"));
		String userNamesToShareWith = innerMap.get("toShare");
		String[] names = userNamesToShareWith.split(",");
		
		String escapedString = HtmlUtils.htmlEscape(innerMap.get("aboutShare"));
		String decodedString = null;
		String aboutShare = null;
		String toShare = null;
		try {
			decodedString = java.net.URLDecoder.decode(escapedString, "UTF-8");
			aboutShare = decodedString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		System.out.println(aboutShare);
		escapedString = HtmlUtils.htmlEscape(innerMap.get("toShare"));
		try {
			decodedString = java.net.URLDecoder.decode(escapedString, "UTF-8");
			toShare = decodedString;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		System.out.println(toShare);
		
		
		Long postId = Long.parseLong(innerMap.get("postId"));
		
		Post post = postRepo.findPostsByPostId(postId);
		
		User user = post.getUser();
//		postService.doShare(post, user, aboutShare); 
		map.put("shareStatus", "succesfully shared");
		return map;
		
	}

	@RequestMapping(value="/ajax/delete_ajax", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteAjax(@RequestParam String postId){
		Map<String, String> map = new HashMap<>();
		Long longPostId = Long.parseLong(postId);
		
		postService.deletePost(longPostId);
		
		map.put("deleteRequestStatus", "");
		
		return map;
	}
*/
}
