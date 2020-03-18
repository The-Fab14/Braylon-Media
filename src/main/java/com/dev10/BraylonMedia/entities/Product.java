package com.dev10.BraylonMedia.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int productId;

	@Column(nullable = false)
	String productName;

	@Column(nullable = false)
	double price;

	@ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
