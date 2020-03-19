package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Product;
import com.dev10.BraylonMedia.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    ProductRepository productRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    
    
}