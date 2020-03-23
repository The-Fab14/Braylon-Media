package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Product;
import com.dev10.BraylonMedia.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Garrett Becker
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    
    @Autowired
    ProductRepository pr;
    
    public ProductServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Product> allProducts = pr.findAll();
        for (Product p : allProducts) {
            pr.delete(p);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class ProductService.
     */
    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setProductId(0);
        product1.setProductName("Name1");
        product1.setPrice(BigDecimal.ONE);
        pr.save(product1);
        
        Product product2 = new Product();
        product2.setProductId(0);
        product2.setProductName("Name1");
        product2.setPrice(BigDecimal.ONE);
        pr.save(product2);
        
        List<Product> allProducts = pr.findAll();
        assertEquals(allProducts.size(), 2);
    }
    
}
