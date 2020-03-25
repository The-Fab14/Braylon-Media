package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class VisitController 
{
    Set<ConstraintViolation<User>> violations = new HashSet<>();
    
    @Autowired
    VisitService visitService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ClientService clientService;
    
    @GetMapping("/visit")
    public String displayVisits(Model model)
    {
        User user = userService.getUserFromSession();
        List<Visit> visits = visitService.getVisitsByUserId(user.getUserId());
        List<User> users = userService.findAll();
        List<Client> clients = clientService.findAll();
        model.addAttribute("visits", visits);
        model.addAttribute("users", users);
        model.addAttribute("clients", clients);
        List<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        model.addAttribute("months", months);
        return "visit_display";
    }
    
    @GetMapping("/add_visit")
    public String displayAddVisit(Model model)
    {
        return "add_visit";
    }
    
    @PostMapping("/add_visit")
    public String addVisit(Visit visit) 
    {
        visitService.addVisit(visit);
        return "redirect:/visit";
    }
    
    @GetMapping("/edit_visit/{visitId}")
    public String displayEditVisit(Model model, @PathVariable int visitId)
    {
        Visit visit = visitService.getVisit(visitId);
        model.addAttribute("visit", visit);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        List<Client> clients = clientService.findAllByUserId(visit.getUser().getUserId());
        model.addAttribute("clients", clients);
        return "edit_visit";
    }
    
    @PostMapping("/edit_visit")
    public String editVisit(Visit visit)
    {
        visitService.editVisit(visit);
        return "redirect:/edit_visit?visitId=" + visit.getVisitId();
    }
    
}