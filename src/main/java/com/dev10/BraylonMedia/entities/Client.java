package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity(name = "crm_client")
public class Client 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int clientId;
	
	@Column(nullable = false)
	@Size(max = 256)
	String contactFirstName;
	
	@Column(nullable = false)
	@Size(max = 256)
	String contactLastName;
	
	@Column(nullable = false)
	@Size(max = 256)
	String companyName;
	
	@Column(nullable = false)
	@Size(max = 256)
	String streetAddress;
	
	@Column(nullable = false)
	@Size(max = 256)
	String aptUnit;
	
	@Column(nullable = false)
	@Size(max = 256)
	String city;
	
	@ManyToOne
    @JoinColumn(name = "stateId", nullable = false)
	State state;
	
	@Column(nullable = false)
	@Size(max = 5)
	int zip;
	
	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
	User user;
	
	@Column(nullable = false)
	@Size(max = 256)
	String emailAddress;
	
	@Column(nullable = false)
	@Size(max = 10)
	String phoneNumber;
}
