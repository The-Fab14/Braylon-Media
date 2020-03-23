package com.dev10.BraylonMedia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class State 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String stateId;
	
	@Column(nullable = false)
	private String stateName;
}
