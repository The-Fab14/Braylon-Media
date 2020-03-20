package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class OrderController 
{
    @Autowired
    OrderService orders;
    
    @GetMapping("/add_new_order")
    public String displayAddOrder(Model model)
    {
        return "add_new_order";
    }
    
    @PostMapping("/add_new_order")
    public String addOrder()
    {
        
        return "redirect:/orders";
    }
    
    @GetMapping("/edit_order")
    public String displayEditOrder(Model model)
    {
        return "edit_order";
    }
    
    @GetMapping("/orders")
    public String displayOrders(Model model)
    {
        return "orders";
    }
}
