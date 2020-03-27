package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import com.dev10.BraylonMedia.services.OrderService;
import com.dev10.BraylonMedia.services.ProductService;
import com.dev10.BraylonMedia.services.UserService;
import com.dev10.BraylonMedia.services.VisitService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
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
    public String displayHome(Model model) 
    {
        User user = userService.getUserFromSession();
        //find number of visits this month
        List<Visit> visits = visitService.getVisitsByUserId(user.getUserId());
        int userVisits = 0;
        for(Visit visit : visits)
        {
            if(visit.getDateVisited().getMonth().equals(LocalDate.now().getMonth()) && visit.getDateVisited().getYear() == LocalDate.now().getYear())
            {
                userVisits++;
            }
        }
        model.addAttribute("userVisits", userVisits);
        //find top client
        List<Order> orders = orderService.getOrdersByUserId(user.getUserId());
        Map<Client, List<Order>> groupedByClient = orders.stream()
                .collect(Collectors.groupingBy(Order::getClient));
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        Client userTopClient = new Client();
        for(Client c : groupedByClient.keySet())
        {
            sum = BigDecimal.ZERO;
            for(Order o : groupedByClient.get(c))
            {
                sum.add(o.getOrderTotal());
            }
            if(sum.compareTo(max) >= 0)
            {
                max = sum;
                userTopClient = c;
            }
        }
        model.addAttribute("userTopClient", userTopClient);
        //number of unsubmitted orders
        int unsubmitted = 0;
        for(Order o : orders)
        {
            if(o.getOrderStatus().equals("Quote") || o.getOrderStatus().equals("Pending"))
            {
                unsubmitted++;
            }
        }
        model.addAttribute("unsubmitted", unsubmitted);
        //number of sales this month
        BigDecimal userSalesMonth = BigDecimal.ZERO;
        for(Order o : orders)
        {
            if(o.getDateSubmitted().getMonth().equals(LocalDate.now().getMonth()) && o.getDateSubmitted().getMonth().equals(LocalDate.now().getYear()))
            {
                userSalesMonth.add(o.getOrderTotal());
            }
        }
        model.addAttribute("userSalesMonth", userSalesMonth);
        
        //total visits for all this month
        List<Visit> visitsAll = visitService.getAllVisits();
        int allVisits = 0;
        for(Visit visit : visitsAll)
        {
            if(visit.getDateVisited().getMonth().equals(LocalDate.now().getMonth()) && visit.getDateVisited().getYear() == LocalDate.now().getYear())
            {
                allVisits++;
            }
        }
        model.addAttribute("allVisits", allVisits);
        
        //top customer (company wide)
        orders = orderService.getAllOrders();
        Map<String, List<Order>> groupedByCompanyName = orders.stream()
                .collect(Collectors.groupingBy(
                order -> order.getClient().getCompanyName(),
                        Collectors.toList()));
        sum = BigDecimal.ZERO;
        max = BigDecimal.ZERO;
        String topCompany = "";
        for(String comp : groupedByCompanyName.keySet())
        {
            sum = BigDecimal.ZERO;
            for(Order o : groupedByCompanyName.get(comp))
            {
                sum.add(o.getOrderTotal());
            }
            if(sum.compareTo(max) >= 0)
            {
                topCompany = comp;
                max = sum;
            }
        }
        model.addAttribute("topCompany", topCompany);
        //top contact 
        Map<Client, List<Order>> groupedByClientAll = orders.stream()
                .collect(Collectors.groupingBy(Order::getClient));
        sum = BigDecimal.ZERO;
        max = BigDecimal.ZERO;
        Client topClient = new Client();
        for(Client c : groupedByClientAll.keySet())
        {
            sum = BigDecimal.ZERO;
            for(Order o : groupedByClientAll.get(c))
            {
                sum.add(o.getOrderTotal());
            }
            if(sum.compareTo(max) >= 0)
            {
                max = sum;
                topClient = c;
            }
        }
        model.addAttribute("topClient", topClient);
        //number of incomplete orders
        int incompletes = 0;
        for(Order o : orders)
        {
            if(o.getOrderStatus().equals("Submitted") || o.getOrderStatus().equals("Approved"))
            {
                incompletes++;
            }
        }
        model.addAttribute("incompletes", incompletes);
        //number of sales this month (all)
        BigDecimal allSalesMonth = BigDecimal.ZERO;
        for(Order o : orders)
        {
            if(o.getDateSubmitted().getMonth().equals(LocalDate.now().getMonth()) && o.getDateSubmitted().getMonth().equals(LocalDate.now().getYear()))
            {
                allSalesMonth.add(o.getOrderTotal());
            }
        }
        model.addAttribute("allSalesMonth", allSalesMonth);
        //top sales rep by visits
        List<User> users = userService.findAll();
        int maxVisits = 0;
        User topUser = new User();
        for(User u : users)
        {
            if(visitService.getVisitsByUserId(u.getUserId()).size() >= maxVisits)
            {
                maxVisits = visitService.getVisitsByUserId(u.getUserId()).size();
                topUser = u;
            }
        }
        model.addAttribute("topUserVisits", topUser);
        //top sales rep (# clients)
        users = userService.findAll();
        int maxClients = 0;
        for(User u : users)
        {
            if(clientService.findAllByUserId(u.getUserId()).size() >= maxClients)
            {
                maxClients = clientService.findAllByUserId(u.getUserId()).size();
                topUser = u;
            }
        }
        model.addAttribute("topUserClients", topUser);
        //top sales rep (sales)
        users = userService.findAll();
        sum = BigDecimal.ZERO;
        max = BigDecimal.ZERO;
        for(User u : users)
        {
            sum = BigDecimal.ZERO;
            List<Order> userOrders = orderService.getOrdersByUserId(u.getUserId());
            for(Order o : userOrders)
            {
                if(!o.getOrderStatus().equals("Quoted") && !o.getOrderStatus().equals("Pending"))
                {
                    sum.add(o.getOrderTotal());
                }
            }
            if(sum.compareTo(max) >= 0)
            {
                max = sum;
                topUser = u;
            }
        }
        model.addAttribute("topUserSales", topUser);
        
        // determine if maintenance page shows or not
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
        String timeNow = ZonedDateTime.now(ZoneId.of("America/Chicago")).format(formatter);
        LocalTime startTime = LocalTime.parse("00:00:00", formatter);
        LocalTime endTime = LocalTime.parse("06:00:00", formatter);
        LocalTime checkTime = LocalTime.parse(timeNow, formatter);

        boolean isInBetween = false;

        if (startTime.compareTo(checkTime) <= 0 && endTime.compareTo(checkTime) >= 0) 
        {
            isInBetween = true;
        }

        if (isInBetween) 
        {
            SecurityContextHolder.getContext().setAuthentication(null);
            return "redirect:/maintenance";
        } else 
        {
            return "home";
        }
    }

    // load maintenance page
    @GetMapping("/maintenance")
    public String displayMaintenance(Model model) 
    {
        return "maintenance";
    }
}