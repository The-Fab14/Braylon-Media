package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class HomeController 
{
    @GetMapping({"/", "/home"})
    public String displayHome(Model model, HttpServletRequest request)
    {
        String userId = request.getUserPrincipal().getName();
        //needs list of client objects associated with current user's id
        //list of all clients if role is admin
        model.addAttribute("userId", userId);
        //User user = function to get user(userId);
        //String role = user.getRole();
        //model.addAttribute("role", role);
        return "home";
    }
    
    @GetMapping("/clients/{userId}")
    public List<Client> getClients(@PathVariable String userId)
    {
        //return function to get clients(userId)
        return null;
    }
}
