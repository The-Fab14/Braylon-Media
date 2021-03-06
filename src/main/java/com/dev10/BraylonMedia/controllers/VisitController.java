package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
    public String displayVisits(Model model, String clientIds, String userIds, String month)
    {
        User user = userService.getUserFromSession();
        List<Visit> visits = visitService.getAllVisits();
        Integer user_id = null;
        Integer client_id = null;
        boolean userLive = false;
        boolean clientLive = false;
        boolean monthLive = false;
        try
        {
            client_id = Integer.parseInt(clientIds);
            clientLive = true;
            Client uClient = clientService.findById(client_id);
            model.addAttribute("uClient", uClient);
        }
        catch(NumberFormatException e)
        {
            
        }
        try
        {
            user_id = Integer.parseInt(userIds);
            User cUser = userService.findById(user_id);
            model.addAttribute("cUser", cUser);
            userLive = true;
        }
        catch(NumberFormatException e)
        {
            
        }
        model.addAttribute("userLive", userLive);
        model.addAttribute("clientLive", clientLive);
        if(!user.getUserRole().equals("ROLE_ADMIN"))
        {
            user_id = user.getUserId();
        }
        List<User> users = userService.findAll();
        List<Client> clients = clientService.findAll();
        if(user_id != null)
        {
            visits = visitService.getVisitsByUserId(user_id);
        }
        if(client_id != null)
        {
            visits = visitService.getVisitsByClientId(client_id);
        }
        if(user_id != null && client_id != null)
        {
            visits = visitService.findAllByClientAndUser(client_id, user_id);
        }
        if(month != null)
        {
            List<Visit> newVisits = new ArrayList<>();
            for(Visit v : visits)
            {
                if(v.getDateVisited().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()).equalsIgnoreCase(month))
                {
                    newVisits.add(v);
                }
            }
            monthLive = true;
            model.addAttribute("cMonth", month);
            visits.clear();
            visits = newVisits;
        }
        model.addAttribute("monthLive", monthLive);
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
        User user = userService.getUserFromSession();
        model.addAttribute("currentUser", user);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "add_visit";
    }
    
    @PostMapping("/add_visit")
    public String addVisit(String dateVisited, String userId,
        String visitNotes, String clientId) 
    {
        LocalDate dateVisitedParse = null;
        int userIdParse = 0;
        int clientIdParse = 0;
        try {
            dateVisitedParse = LocalDate.parse(dateVisited);
            userIdParse = Integer.parseInt(userId);
            clientIdParse = Integer.parseInt(clientId);
        } catch(NumberFormatException | DateTimeParseException e) {
            //Something bad happened!
        }
        
        Visit visit = new Visit();
        visit.setDateVisited(dateVisitedParse);
        visit.setUser(userService.findById(userIdParse));
        visit.setVisitNotes(visitNotes);
        visit.setClient(clientService.findById(clientIdParse));
        visitService.addVisit(visit);
        return "redirect:/visit";
    }
    
    @GetMapping("/edit_visit")
    public String displayEditVisit(Model model, Integer visitId)
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
    public String editVisit(String dateVisited, String userId,
        String visitNotes, String clientId, int visitId)
    {
        LocalDate dateVisitedParse = null;
        int userIdParse = 0;
        int clientIdParse = 0;
        try {
            dateVisitedParse = LocalDate.parse(dateVisited);
            userIdParse = Integer.parseInt(userId);
            clientIdParse = Integer.parseInt(clientId);
        } catch(NumberFormatException | DateTimeParseException e) {
            //Something bad happened!
        }
        
        Visit visit = new Visit();
        visit.setDateVisited(dateVisitedParse);
        visit.setUser(userService.findById(userIdParse));
        visit.setVisitNotes(visitNotes);
        visit.setClient(clientService.findById(clientIdParse));
        visit.setVisitId(visitId);
        visitService.editVisit(visit);
        return "redirect:/visit";
    }
}