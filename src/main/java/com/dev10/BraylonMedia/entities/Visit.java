package com.dev10.BraylonMedia.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
public class Visit 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int visitId;
	
	@Column(nullable = false)
	LocalDate dateVisited;
	
	@ManyToOne
    @JoinColumn(name = "userId", nullable = false)
	User user;
	
	@Column(nullable = true)
	@Size(max = 5000)
	String visitNotes;
	
	@ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
	Client client;
}
