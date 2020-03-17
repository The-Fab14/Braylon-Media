package com.dev10.BraylonMedia.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

@Entity
public class Order 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int orderId;
	
	@Column(nullable = false)
	LocalDate dateSubmitted;
	
	@Column
	LocalDate dateInstalled;
	
	@Column
	LocalDate dateCompleted;
	
	@Column(nullable = false)
	double orderTotal;
	
	@Column(nullable = false)
	String orderStatus;
	
	@Column
	String orderComments;
	
	@ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
	Client client;
}
