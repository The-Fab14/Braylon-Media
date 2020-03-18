package com.dev10.BraylonMedia.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.Id;

@Entity
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;
	
	@Column(nullable = false)
	String productName;
	
	@Column(nullable = false)
	double price;
	
	@ManyToMany(mappedBy = "order")
    private List<Order> orders;
}
