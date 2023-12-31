package com.bway.springproject.controller;

import javax.servlet.http.HttpSession;

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
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session) {
		
		User usr = userService.login(user.getUsername(), user.getPassword());
		if(usr != null) {
			session.setAttribute("validuser", usr);
			session.setMaxInactiveInterval(300);
			//model.addAttribute("u",usr.getFname());
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
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();//session kill
		return "LoginForm";
	}
	
	@GetMapping("/profile")
	public String getProfile() {
		
		return "Profile";
	}
	
}
