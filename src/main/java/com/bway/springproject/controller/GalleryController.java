package com.bway.springproject.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {

	@GetMapping("/gallery")
	public String getGallery(Model model, HttpSession session) {
		
		if(session.getAttribute("validuser") ==null) {
			return "LoginForm";
		}
		
		String[] imgName = new File("src/main/resources/static/images").list();
		model.addAttribute("imgNameList", imgName);
		return "GalleryForm";
	}
}
