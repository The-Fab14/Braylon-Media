package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class State 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String stateId;
	
	@Column(nullable = false)
	String stateName;
}
