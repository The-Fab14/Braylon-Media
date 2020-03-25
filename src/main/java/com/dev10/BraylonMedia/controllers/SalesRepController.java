package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import com.dev10.BraylonMedia.services.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    Set<String> customViolations = new HashSet<>();
    
    @Autowired
    UserService users;
    
    @Autowired
    ClientService clients;
    
    @Autowired
    LookupService lookup;
    
    @Autowired
    PasswordEncoder encoder;
    
    @GetMapping("/add_sales_rep")
    public String displayAddUser(Model model)
    {
        model.addAttribute("lookup", lookup.findAll());
        model.addAttribute("errors", violations);
        return "add_sales_rep";
    }
    
    @PostMapping("/add_sales_rep")
    public String displayAddUser(User user)
    {   
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);
        
        if (violations.isEmpty()) {
            user.setUserPassword(encoder.encode(user.getUserPassword()));
            users.save(user);
            return "redirect:/sales_rep_display";
        } else {
            return "redirect:/add_sales_rep";
        }
    }
    
    @GetMapping("/edit_user")
    public String displayEditUser(Integer userId, Model model)
    {
        if (userId != null) {
            model.addAttribute("user", users.findById(userId));
            model.addAttribute("lookup", lookup.findAll());
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);
            return "edit_user";
        } else {
            return "redirect:/sales_rep_display";
        }
    }
    
    @PostMapping("/edit_user")
    public String editUser(User user)
    {
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);
        
        if (violations.isEmpty() && !user.isDidPasswordChange() && !users.defaultPasswordChanged(user)) {
            user.setUserPassword(encoder.encode(user.getUserPassword()));
            user.setDidPasswordChange(true);
            users.save(user);
            return "redirect:/sales_rep_display";
        } else if (!user.isDidPasswordChange() && users.defaultPasswordChanged(user)) {
            customViolations.add("Initial password must be changed.");
            return "redirect:/edit_user?userId=" + user.getUserId();
        } else if (violations.isEmpty()) {
            users.save(user);
            return "redirect:/sales_rep_display";
        } else {
            return "redirect:/sales_rep_display";
        }
    }
    
    @GetMapping("/sales_rep_display")
    public String displaySalesRep(Model model, Integer rep_id, Integer client_id)
    {
        if (rep_id == null && client_id  == null) {
            model.addAttribute("allUsers", users.findAll());
            model.addAttribute("users", users.findAll());
            model.addAttribute("clients", clients.findAll());
        } else if (rep_id != null) {
            model.addAttribute("allUsers", users.findAll());
            model.addAttribute("users", users.findById(rep_id));
            model.addAttribute("clients", clients.findAll());
        } else {
            model.addAttribute("clients", clients.findAll());
            model.addAttribute("allUsers", users.findAll());
            model.addAttribute("users", users.findUserByClientId(client_id));
        }
        violations.clear();
        customViolations.clear();
        return "sales_rep_display";
    }
}