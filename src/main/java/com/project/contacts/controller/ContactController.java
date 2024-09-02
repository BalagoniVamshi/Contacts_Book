package com.project.contacts.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.contacts.entities.Contacts;
import com.project.contacts.services.ContactsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {
	
	private ContactsService contactService;
	
	public ContactController(ContactsService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("/contacts")
	public String addContact(Model model) {
		Contacts contact = new Contacts();
		model.addAttribute("contacts",contact);
		return"AddContacts";
	}
	
	@PostMapping("/addContacts")
	public String addContacts(Contacts contact, HttpServletRequest request,Model model) throws Exception{
		HttpSession session = request.getSession(false);
		Integer userId = (Integer)session.getAttribute("userId");
		boolean contactSaved = contactService.addContact(contact, userId);
		
		if(contactSaved) {
			model.addAttribute("smsg","Contact Saved");
		}
		else {
			model.addAttribute("emsg","Failed to Save Contact");
		}
		model.addAttribute("contacts", new Contacts());
		return "AddContacts";
	}
	
	@GetMapping("/viewContacts")
	public String viewAllContacts(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<Contacts> allContacts = contactService.getAllContacts(userId);
		model.addAttribute("contacts",allContacts);
		
		return "ViewContacts";
		
	}
	@GetMapping("/editContact")
	public String editContacts(@RequestParam("contId") Integer contId, Model model) {
	Contacts contactById = contactService.getContactByContId(contId);
	model.addAttribute("contacts",contactById);
	
	return "AddContacts";
	}
	
	@GetMapping("/deleteContact")
	public String delete(@RequestParam("contId") Integer contId,HttpServletRequest request,Model model) throws Exception {
		contactService.deleteContact(contId);
		
		HttpSession session = request.getSession(false);
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<Contacts> allContacts = contactService.getAllContacts(userId);
		model.addAttribute("contacts",allContacts);
		model.addAttribute("smsg","Contact Deleted Successfully");
		
		return "ViewContacts";
	}
}
