package com.project.contacts.Repositoriess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.contacts.entities.Contacts;

public interface ContactRepo extends JpaRepository<Contacts, Integer>{
	
	public Contacts findByContId(Integer contId);
	
	@Query(value = "select * from Contacts where user_id=:userId",nativeQuery=true)
	public List<Contacts> getContactsByUserId(@Param("userId")Integer userId);
	

}
