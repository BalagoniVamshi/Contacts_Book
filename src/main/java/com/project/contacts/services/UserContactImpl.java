package com.project.contacts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.contacts.Repositoriess.ContactRepo;
import com.project.contacts.Repositoriess.UserContactRepo;
import com.project.contacts.entities.Contacts;
import com.project.contacts.entities.UserContacts;

@Service
public class UserContactImpl implements UserContactService{

	private UserContactRepo userRepo;
	private ContactRepo contactsRepo;
	
	
	public UserContactImpl( UserContactRepo userRepo, ContactRepo contactsRepo) {
		this.userRepo = userRepo;
		this.contactsRepo = contactsRepo;
	}
	
	@Override
	public boolean register(UserContacts user) {
		UserContacts savedContact = userRepo.save(user);
		if(savedContact.getUserId() != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserContacts findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserContacts login(String email, String password) {
		UserContacts users = userRepo.findByEmail(email);
		if(users != null && users.getPassword().equals(password)) {
		
		return users;
		}
		return null;
	}

	@Override
	public List<Contacts> contactsList(Integer userId) {
		
		return contactsRepo.getContactsByUserId(userId);
	}

}
