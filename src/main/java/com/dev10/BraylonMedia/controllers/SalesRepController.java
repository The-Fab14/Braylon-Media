package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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
    VisitService visits;
    
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
            User user = users.getUserFromSession();
            model.addAttribute("user", user);
            model.addAttribute("lookup", lookup.findAll());
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);
            return "edit_user";
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
    public String displaySalesRep(Model model)
    {
        List<User> userList = users.findAll();
        userList.sort(Comparator.comparing(User::getUserId));
        model.addAttribute("users", userList);
        model.addAttribute("clients", clients.findAll());
        
        List<Visit> visitList = visits.getAllVisits();
        List<Integer> userIdFreq = new ArrayList<>();
        List<Integer> visitFreq = new ArrayList<>();
        for(Visit visit: visitList) 
        {
            userIdFreq.add(visit.getUser().getUserId());
        }
        for(User user : userList) 
        {
            int freq = Collections.frequency(userIdFreq, user.getUserId());
            visitFreq.add(freq);
        }
        model.addAttribute("visitCount", visitFreq);
        
        violations.clear();
        customViolations.clear();
        return "sales_rep_display";
    }
}