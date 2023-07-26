package com.bway.springproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Department;
import com.bway.springproject.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/add")
	public String getDepartment(HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		return "DepartmentForm";
	}
	
	@PostMapping("/add")
	public String postDept(@ModelAttribute Department dept, HttpSession session) {
		
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		deptService.addDept(dept);
		return  "DepartmentForm";
	}
	@GetMapping("/list")
	public String getAll(Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		
		model.addAttribute("deptList", deptService.getAllDept());
		return "DepartmentListForm";
	}
	@GetMapping("/edit")
	public String edit(@RequestParam int id, Model model, HttpSession session) {
		
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("deptObject", deptService.getDeptById(id));
		return "DepartmentEditForm";
	}
	
	@GetMapping("/view")
	public String view(@RequestParam int id, Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("deptObject", deptService.getDeptById(id));
		return "DepartmentViewForm";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Department dept, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		deptService.addDept(dept);
		return "redirect:/department/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id, HttpSession session) {
		
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		
		deptService.deleteDept(id);
		return "redirect:/department/list";
	}
}
