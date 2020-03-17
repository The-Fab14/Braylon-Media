package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

@Entity
public class Client 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int clientId;
	
	@Column(nullable = false)
	String contactFirstName;
	
	@Column(nullable = false)
	String contactLastName;
	
	@Column(nullable = false)
	String companyName;
	
	@Column(nullable = false)
	String streetAddress;
	
	@Column(nullable = false)
	int aptUnit;
	
	@Column(nullable = false)
	String city;
	
	@ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
	State state;
	
	@Column(nullable = false)
	int zip;
	
	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
	User user;
	
	@Column(nullable = false)
	String emailAddress;
	
	@Column(nullable = false)
	String phoneNumber;
}
