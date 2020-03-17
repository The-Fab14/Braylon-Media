package com.dev10.BraylonMedia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	String firstName;
	
	String lastName;
	
	String emailAddress;
	
	String userRole;
	
	String password;
	
	boolean didPasswordChange;
	
	int stateId;
	
}
