package com.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.domain.Entities.User;
import com.service.UserServiceInterface;

@Controller
public class MainController {
	
	@Autowired
	private UserServiceInterface userService;
	
	@RequestMapping("/")
	public String root(){
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public  String index(){
		return "index";
	}
	
//	@RequestMapping("/user/index")
//	public String userIndex(){
//		return "/user/index";
//	}
		
	@RequestMapping("/login")
	public String login(){
		System.out.println("we hitted the controller here");
		return "/login";
	}
	
		
	@RequestMapping("/login-error")
	public String loginError(Model model){
		model.addAttribute("loginError", true);
		return "login";
	}
	
	//============================================================================================
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView signup(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		System.out.println(user);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("signup");
		return modelAndView;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		System.out.println(user);
		
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("signup");
		} else {
			System.out.println(user);
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("signup");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/user/index", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/index");
		return modelAndView;
	}
	
		
}
