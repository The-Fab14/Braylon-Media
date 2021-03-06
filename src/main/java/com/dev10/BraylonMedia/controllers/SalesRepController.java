package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
public class SalesRepController {

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
    public String displayAddUser(Model model) {
        customViolations.clear();

        User userLoggedIn = users.getUserFromSession();
        if (userLoggedIn.getUserRole().equals("ROLE_USER")) {
            customViolations.add("You are just a user. You must be an admin to do this!!!");
            model.addAttribute("customViolations", customViolations);
            return "custom_error";
        } else if (userLoggedIn.getUserRole().equals("ROLE_ADMIN")) {
            model.addAttribute("lookup", lookup.findAll());
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);
            return "add_sales_rep";
        } else {
            customViolations.add("You are not a user or an admin...how did you login into this site???");
            model.addAttribute("customViolations", customViolations);
            return "custom_error";
        }

    }

    @PostMapping("/add_sales_rep")
    public String displayAddUser(User user) {
        customViolations.clear();
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(user);

        User userLoggedIn = users.getUserFromSession();
        if (userLoggedIn.getUserRole().equals("ROLE_USER")) {
            return "redirect:/add_sales_rep";
        } else if (userLoggedIn.getUserRole().equals("ROLE_ADMIN")) {
            if (violations.isEmpty()) {
                user.setUserPassword(encoder.encode(user.getUserPassword()));
                users.save(user);
                return "redirect:/sales_rep_display";
            } else {
                return "redirect:/add_sales_rep";
            }
        } else {
            return "redirect:/add_sales_rep";
        }

    }

    @GetMapping("/edit_user")
    public String displayEditUser(Integer userId, Model model) {
        if (userId != null) {
            model.addAttribute("user", users.findById(userId));
            model.addAttribute("lookup", lookup.findAll());
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);
            return "edit_user";
        } else {
            customViolations.clear();
            User user = users.getUserFromSession();
            model.addAttribute("user", user);
            model.addAttribute("lookup", lookup.findAll());
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);
            return "edit_user";
        }
    }

    @PostMapping("/edit_user")
    public String editUser(User user) {
        customViolations.clear();
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
    public String displaySalesRep(Model model, Integer rep_id, Integer client_id) {
        customViolations.clear();

        User userLoggedIn = users.getUserFromSession();
        if (userLoggedIn.getUserRole().equals("ROLE_USER")) {
            customViolations.add("You are just a user. You must be an admin to do this!!!");
            model.addAttribute("customViolations", customViolations);
            return "custom_error";
        } else if (userLoggedIn.getUserRole().equals("ROLE_ADMIN")) {
            List<User> userList = users.findAll();
            userList.sort(Comparator.comparing(User::getUserId));
//        model.addAttribute("users", userList);
//        model.addAttribute("clients", clients.findAll());
            model.addAttribute("allUsers", users.findAll());
            List<User> sortedUsers = userList;
            boolean clientLive = false;
            boolean userLive = false;
            if (rep_id == null && client_id == null) {
                model.addAttribute("users", users.findAll());
                model.addAttribute("clients", clients.findAll());
            } else if (rep_id != null) {
                model.addAttribute("users", users.findById(rep_id));
                model.addAttribute("clients", clients.findAll());
                sortedUsers.clear();
                sortedUsers.add(users.findById(rep_id));
            } else {
                model.addAttribute("clients", clients.findAll());
                model.addAttribute("users", users.findUserByClientId(client_id));
                sortedUsers.clear();
                sortedUsers.add(users.findUserByClientId(client_id));
            }
            if(rep_id != null)
            {
                userLive = true;
            }
            if(client_id != null)
            {
                clientLive = true;
            }
            if(userLive)
            {
                model.addAttribute("cUser", users.findById(rep_id));
            }
            if(clientLive)
            {
                model.addAttribute("uClient", clients.findById(client_id));
            }
            model.addAttribute("userLive", userLive);
            model.addAttribute("clientLive", clientLive);
            //Map for visit count
            List<Visit> visitList = visits.getAllVisits();
            List<Integer> userIdFreq = new ArrayList<>();
            LinkedHashMap<User, Integer> visitMap = new LinkedHashMap<>();
            for (Visit visit : visitList) {
                if (visit.getDateVisited().getMonth().equals(LocalDate.now().getMonth()) && visit.getDateVisited().getYear() == LocalDate.now().getYear()) {
                    userIdFreq.add(visit.getUser().getUserId());
                }
            }
            userList.sort(Comparator.comparing(User::getUserId));
            for (User user : userList) {
                int freq = Collections.frequency(userIdFreq, user.getUserId());
                if (sortedUsers.contains(user)) {
                    visitMap.put(user, freq);
                }
            }
            model.addAttribute("visitMap", visitMap);
            model.addAttribute("errors", violations);
            model.addAttribute("customViolations", customViolations);

            violations.clear();
            customViolations.clear();
            return "sales_rep_display";
        } else {
            customViolations.add("You are not a user or an admin...how did you login into this site???");
            model.addAttribute("customViolations", customViolations);
            return "custom_error";
        }

    }
}