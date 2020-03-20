package com.dev10.BraylonMedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class OrderController 
{
    @GetMapping("/add_new_order")
    public String displayAddOrder()
    {
        return "add_new_order";
    }
    
    @GetMapping("/edit_order")
    public String displayEditOrder()
    {
        return "edit_order";
    }
    
    @GetMapping("/orders")
    public String displayOrders()
    {
        return "orders";
    }
}
