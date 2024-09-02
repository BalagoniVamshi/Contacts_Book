package com.project.contacts.Repositoriess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.contacts.entities.UserContacts;

public interface UserContactRepo extends JpaRepository<UserContacts, Integer> {

	public UserContacts findByEmail(String email);
	
	public UserContacts findByEmailAndPassword(String email, String password);
	
}
