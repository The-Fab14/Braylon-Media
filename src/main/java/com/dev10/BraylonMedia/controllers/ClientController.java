package com.dev10.BraylonMedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class ClientController 
{
    @GetMapping("/add_customer")
    public String displayAddCustomer()
    {
        return "add_customer";
    }
    
    @GetMapping("/edit_customer")
    public String displayEditCustomer()
    {
        return "edit_customer";
    }
}
