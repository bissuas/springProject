package com.bway.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String getLogin(){
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model) {
		
		User usr = userService.login(user.getUsername(), user.getPassword());
		if(usr != null) {
			model.addAttribute("u",user.getUsername());
			return "userHome";
		}
		model.addAttribute("message", "User not found!!");
		return "LoginForm";
	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {
		userService.signup(user);
		return "LoginForm";
	}
}
