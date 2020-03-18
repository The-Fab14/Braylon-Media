package com.dev10.BraylonMedia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */

@Controller
public class LoginController 
{
    @GetMapping("/login")
    public String displayLogin()
    {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(String userId, String password)
    {
        
        return "redirect:home";
    }
}
