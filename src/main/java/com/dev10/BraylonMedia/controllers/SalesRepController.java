package com.dev10.BraylonMedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class SalesRepController 
{
    @GetMapping("/add_sales_rep")
    public String displayAddUser()
    {
        return "add_sales_rep";
    }
    
    @GetMapping("/edit_user")
    public String displayEditUser()
    {
        return "edit_user";
    }
    
    @GetMapping("/sales_rep_display")
    public String displaySalesRep()
    {
        return "sales_rep_display";
    }
}
