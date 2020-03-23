package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.repositories.ClientRepository;
import com.dev10.BraylonMedia.repositories.UserRepository;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
public class SalesRepController 
{
    Set<ConstraintViolation<User>> violations = new HashSet<>();
    
    @Autowired
    UserRepository users;
    
    @Autowired
    ClientRepository clients;
    
    @GetMapping("/add_sales_rep")
    public String displayAddUser()
    {
        return "add_sales_rep";
    }
    
    @PostMapping("/add_sales_rep")
    public String displayAddUser(User user)
    {   
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);
        
        if (violations.isEmpty()) {
            users.save(user);
            return "redirect:/sales_rep_display";
        } else {
            return "redirect:/add_sales_rep";
        }
    }
    
    @PostMapping("/edit_user")
    public String displayEditUser(User user)
    {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);
        
        if (violations.isEmpty()) {
            users.save(user);
            return "redirect:/sales_rep_display";
        }
        return "redirect:/sales_rep_display";
    }
    
    @GetMapping("/sales_rep_display")
    public String displaySalesRep(Model model)
    {
        model.addAttribute("users", users.findAll());
        model.addAttribute("clients", clients.findAll());
        violations.clear();
        return "sales_rep_display";
    }
}
