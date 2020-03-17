package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

@Entity
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	@Column(nullable = false)
	String firstName;
	
	@Column(nullable = false)
	String lastName;
	
	@Column(nullable = false)
	String emailAddress;
	
	@Column(nullable = false)
	String userRole;
	
	@Column(nullable = false)
	String password;
	
	@Column(nullable = false)
	boolean didPasswordChange;
	
	@ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
	State state;
	
}
