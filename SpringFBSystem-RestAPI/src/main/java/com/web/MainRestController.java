package com.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.domain.entities.User;
import com.domain.repositories.CommentRepo;
import com.domain.repositories.PostRepo;
import com.domain.repositories.UserRepo;
import com.service.CommentServiceInterface;
import com.service.PostServiceInterface;
import com.service.UserServiceInterface;

@RestController
@RequestMapping("/api/v1")
public class MainRestController {

	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private PostServiceInterface postService;
	
	@Autowired 
	private CommentServiceInterface commService;
	
	@Autowired
	PostRepo postRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, User> createNewUser(Model model, @Valid User user, BindingResult bindingResult) {
		Map<String, User> map = new HashMap<String, User>();
		
		User userExists = userService.findUserByEmail((String) user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "validation failed");
		} else {
			userService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			map.put("user", new User());
		}
		return map;
	}
}
