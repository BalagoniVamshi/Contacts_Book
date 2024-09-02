package com.project.contacts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.contacts.entities.Contacts;
import com.project.contacts.entities.UserContacts;
import com.project.contacts.services.UserContactService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserContactController {
	
	private UserContactService userService;
	
	public UserContactController(UserContactService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("users",new UserContacts());
		return "index";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		UserContacts user = new UserContacts();
		model.addAttribute("users",user);
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(UserContacts users,Model model) {
		UserContacts email = userService.findByEmail(users.getEmail());
		if(email != null) {
			model.addAttribute("emsg","Duplicate Email");
			return "register";
		}
		
		boolean registered = userService.register(users);
		if(registered) {
			model.addAttribute("smsg","Registered Successfully");
			model.addAttribute("users",new UserContacts());
		}
		else {
			model.addAttribute("emsg","Failed to Register");
		}
		return "register";
	}
	
	@PostMapping("/login")
	public String login(UserContacts users,HttpServletRequest request,Model model) {
		UserContacts cred = userService.login(users.getEmail(), users.getPassword());
		if(cred == null ) {
			model.addAttribute("emsg","Invalid Credentials");
			return "index";
		}
		
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", cred.getUserId());
		List<Contacts> contactsList = userService.contactsList(cred.getUserId());
		model.addAttribute("contacts",contactsList);
		return "ViewContacts";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
}
