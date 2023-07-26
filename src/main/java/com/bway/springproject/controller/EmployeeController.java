package com.bway.springproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.Employee;
import com.bway.springproject.service.DepartmentService;
import com.bway.springproject.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private DepartmentService deptService;
	@GetMapping("/add")
	public String getEmployee(Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("dptList", deptService.getAllDept());
		return "EmployeeForm";
	}
	
	@PostMapping("/add")
	public String postEmployee(@ModelAttribute Employee employee, HttpSession session) {
		
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		
		empService.addEmp(employee);
		return "redirect:/employee/add";
	}
	
	@GetMapping("/list")
	public String empList(Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("empList", empService.getAllEmps());
		return "EmployeeListForm";
	}
	
	@GetMapping("/edit")
	public String editEmp(@RequestParam Long id, Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("dptList", deptService.getAllDept());
		model.addAttribute("empModel", empService.getEmpById(id));
		return "EmployeeEditForm";
	}
	
	@GetMapping("/view")
	public String viewEmp(@RequestParam Long id, Model model, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		model.addAttribute("dptList", deptService.getAllDept());
		model.addAttribute("empModel", empService.getEmpById(id));
		return "EmployeeViewForm";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Employee employee, HttpSession session) {
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		empService.updateEmp(employee);
		return "redirect:/employee/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long id, Employee employee, HttpSession session ) {
		
		if(session.getAttribute("validuser") == null) {
			return "LoginForm";
		}
		
		empService.deleteEmp(id);
		return "redirect:/employee/list";
	}
}


