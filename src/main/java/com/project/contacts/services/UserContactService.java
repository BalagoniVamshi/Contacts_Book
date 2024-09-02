package com.project.contacts.services;

import java.util.List;

import com.project.contacts.entities.Contacts;
import com.project.contacts.entities.UserContacts;

public interface UserContactService {
	
	public boolean register(UserContacts user);
	
	public UserContacts findByEmail(String email);
	
	public UserContacts login(String email, String password);
	
	public List<Contacts> contactsList(Integer userId);

}
