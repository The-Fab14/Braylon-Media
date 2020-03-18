package com.dev10.BraylonMedia.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        //List<Client> clients = function to get clients;
        //model.addAttribute("clients", clients);
        return "home";
    }
}
