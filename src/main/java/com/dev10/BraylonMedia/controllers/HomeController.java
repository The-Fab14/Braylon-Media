package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import com.dev10.BraylonMedia.services.OrderService;
import com.dev10.BraylonMedia.services.ProductService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */
@Controller
public class HomeController {

    @Autowired
    ClientService clientService;

    @Autowired
    LookupService lookupService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    VisitService visitService;

    // load home page if not in maintenance window
    @GetMapping({"/", "/home"})
    public String displayHome(Model model) {

        // determine if maintenance page shows or not
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
        String timeNow = ZonedDateTime.now(ZoneId.of("America/Chicago")).format(formatter);
//        String timeNow = "00:00:00";
        LocalTime startTime = LocalTime.parse("00:00:00", formatter);
        LocalTime endTime = LocalTime.parse("06:00:00", formatter);
        LocalTime checkTime = LocalTime.parse(timeNow, formatter);

        boolean isInBetween = false;

        if (startTime.compareTo(checkTime) <= 0 && endTime.compareTo(checkTime) >= 0) {
            isInBetween = true;
        }

        if (isInBetween) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return "redirect:/maintenance";
        } else {
            return "home";
        }
    }

    // load maintenance page
    @GetMapping("/maintenance")
    public String displayMaintenance(Model model) {
        return "maintenance";
    }
}