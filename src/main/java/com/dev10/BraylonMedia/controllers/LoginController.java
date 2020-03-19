package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.config.SecurityUtils;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */
@Controller
public class LoginController {
    
    @Autowired
    UserService userService;

    @GetMapping("/log_in")
    public String displayLogin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String username = SecurityUtils.getUserName();
            int userId = Integer.parseInt(username);

            User userEntity = userService.findById(userId);

            String role = userEntity.getUserRole();

            if (role.equals("ROLE_ADMIN") || role.equals("ROLE_USER")) {
                return "redirect:/home";
            } 

        }

        return "log_in";
    }
}