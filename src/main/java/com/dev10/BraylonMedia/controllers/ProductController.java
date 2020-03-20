package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class ProductController 
{
 @Autowired
 ProductRepository products;
}
