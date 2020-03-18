package com.dev10.BraylonMedia.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity(name = "crm_order")
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
	@Size(max = 256)
	String orderStatus;
	
	@Column
	@Size(max = 5000)
	String orderComments;
	
	@ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
	Client client;
	
	@ManyToMany
    @JoinTable(name = "crm_order_product",
            joinColumns = {@JoinColumn(name = "orderId")},
            inverseJoinColumns = {@JoinColumn(name = "productId")})
    private List<Product> products;
}
