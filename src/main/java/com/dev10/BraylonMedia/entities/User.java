package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;



@Entity(name = "crm_user")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	@Column(nullable = false)
	@Size(max = 256)
	String firstName;
	
	@Column(nullable = false)
	@Size(max = 256)
	String lastName;
	
	@Column(nullable = false)
	@Size(max = 256)
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
