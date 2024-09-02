package com.project.contacts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.contacts.Repositoriess.ContactRepo;
import com.project.contacts.Repositoriess.UserContactRepo;
import com.project.contacts.entities.Contacts;
import com.project.contacts.entities.UserContacts;

@Service
public class ContactServiceImpl implements ContactsService{
	
	private UserContactRepo userRepo;
	private ContactRepo contactsRepo;
	
	public ContactServiceImpl(UserContactRepo userRepo, ContactRepo contactsRepo) {
		this.userRepo = userRepo;
		this.contactsRepo = contactsRepo;
	}

	@Override
	public boolean addContact(Contacts contact, Integer userId) throws Exception {
		UserContacts contacts = userRepo.findById(userId).orElse(null);
		if(contacts == null) {
			throw new Exception("User Not Found");
		}
		contact.setUserContacts(contacts);
		
		Contacts savedContact = contactsRepo.save(contact);
		if(savedContact.getContId() != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<Contacts> getAllContacts(Integer userId) {
		return contactsRepo.getContactsByUserId(userId);
	}

	@Override
	public Contacts getContactByContId(Integer contId) {
		return contactsRepo.findById(contId).orElse(null);
	}
	
	@Override
	public void deleteContact(Integer contId) throws Exception {
		if(contactsRepo.existsById(contId)) {
			contactsRepo.deleteById(contId);
		}
		else {
			throw new Exception("Contact With "+contId+" Not found");
		}
	}
}
