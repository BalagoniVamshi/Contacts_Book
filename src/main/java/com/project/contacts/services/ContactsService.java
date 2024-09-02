package com.project.contacts.services;

import java.util.List;

import com.project.contacts.entities.Contacts;

public interface ContactsService {
	
	public boolean addContact(Contacts contact,Integer userId) throws Exception;
	
	public List<Contacts> getAllContacts(Integer userId);
	
	public Contacts getContactByContId(Integer contId);
	
	public void deleteContact(Integer contId) throws Exception;

}
