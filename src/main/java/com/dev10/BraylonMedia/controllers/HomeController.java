package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.config.SecurityUtils;
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
        String timeNow = LocalTime.now().format(formatter);
//        String timeNow = "00:01:00";
        LocalTime startLocalTime = LocalTime.parse("00:00:00", formatter);
        LocalTime endLocalTime = LocalTime.parse("06:00:00", formatter);
        LocalTime checkLocalTime = LocalTime.parse(timeNow, formatter);

        boolean isInBetween = false;

        if (startLocalTime.compareTo(checkLocalTime) <= 0 && endLocalTime.compareTo(checkLocalTime) >= 0) {
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

    // AJAX call to load all states
    @ResponseBody
    @GetMapping("/load-states")
    public List<State> loadStates() {

        return lookupService.findAll();

    }

    // AJAX call to load all clients
    @ResponseBody
    @GetMapping("/load-clients")
    public List<Client> loadClients() {

        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = userService.findById(userId);

        List<Client> clients = new ArrayList<>();

        if (user.getUserRole().equals("ROLE_USER")) {
            clients = clientService.findAllByUserId(user.getUserId());
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            clients = clientService.findAll();
        }

        return clients;

    }

    // AJAX call to load one client with clientId taking into account user roles
    @ResponseBody
    @GetMapping("/load-client/{clientIdString}")
    public ResponseEntity<Client> loadClient(@PathVariable String clientIdString) {
        int clientId = Integer.parseInt(clientIdString);

        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = userService.findById(userId);

        Client client = null;

        if (user.getUserRole().equals("ROLE_USER")) {
            List<Client> clientList = clientService.findAllByUserId(user.getUserId());

            for (Client item : clientList) {
                if (item.getClientId() == clientId) {
                    client = item;
                }
            }
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            client = clientService.findById(clientId);
        }

        if (client == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(client);
    }

    // Create new client
    @ResponseBody
    @PostMapping("/create-client")
    public ResponseEntity createClient(@RequestBody Map<String, String> map) {

        String contactFirstName = map.get("contactFirstName");
        String contactLastName = map.get("contactLastName");
        String companyName = map.get("companyName");
        String streetAddress = map.get("streetAddress");
        String aptUnit = map.get("aptUnit");
        String city = map.get("city");

        String stateIDString = map.get("stateId");
        State state = lookupService.findById(stateIDString);

        String zip = map.get("zip");
        String emailAddress = map.get("emailAddress");
        String phoneNumber = map.get("phoneNumber");

        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = userService.findById(userId);

        Client client = new Client();

        client.setContactFirstName(contactFirstName);
        client.setContactLastName(contactLastName);
        client.setCompanyName(companyName);
        client.setStreetAddress(streetAddress);
        client.setAptUnit(aptUnit);
        client.setCity(city);
        client.setState(state);
        client.setZip(Integer.parseInt(zip));
        client.setEmailAddress(emailAddress);
        client.setPhoneNumber(phoneNumber);
        client.setUser(user);

        clientService.save(client);

        return new ResponseEntity("Successfully added new client.", HttpStatus.OK);

    }

    // Edit client
    @ResponseBody
    @PostMapping("/edit-client/{clientIdString}")
    public ResponseEntity editClient(@PathVariable String clientIdString, @RequestBody Map<String, String> map) {

        int clientId = Integer.parseInt(clientIdString);
        String contactFirstName = map.get("contactFirstName");
        String contactLastName = map.get("contactLastName");
        String companyName = map.get("companyName");
        String streetAddress = map.get("streetAddress");
        String aptUnit = map.get("aptUnit");
        String city = map.get("city");

        String stateIDString = map.get("stateId");
        State state = lookupService.findById(stateIDString);

        String zip = map.get("zip");
        String emailAddress = map.get("emailAddress");
        String phoneNumber = map.get("phoneNumber");

        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = userService.findById(userId);

        Client client = new Client();

        client.setClientId(clientId);
        client.setContactFirstName(contactFirstName);
        client.setContactLastName(contactLastName);
        client.setCompanyName(companyName);
        client.setStreetAddress(streetAddress);
        client.setAptUnit(aptUnit);
        client.setCity(city);
        client.setState(state);
        client.setZip(Integer.parseInt(zip));
        client.setEmailAddress(emailAddress);
        client.setPhoneNumber(phoneNumber);
//        client.setUser(user);

        if (user.getUserRole().equals("ROLE_USER")) {
            List<Client> clientList = clientService.findAllByUserId(user.getUserId());

            for (Client item : clientList) {
                if (item.getClientId() == clientId) {
                    clientService.save(client);
                    return new ResponseEntity("Successfully edited client.", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Could not edit client. Did you try to edit someone else's client???", HttpStatus.NOT_FOUND);
                }
            }
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            clientService.save(client);
            return new ResponseEntity("Successfully edited client.", HttpStatus.OK);
        }
        
        return new ResponseEntity("I don't know what happened.", HttpStatus.NOT_FOUND);

    }

    @ResponseBody
    @PostMapping("/delete-client/{clientIdString}")
    public ResponseEntity deleteBuyerAccount(@PathVariable String clientIdString) {
        
        int clientId = Integer.parseInt(clientIdString);

        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = userService.findById(userId);
        
        if (user.getUserRole().equals("ROLE_USER")) {
            List<Client> clientList = clientService.findAllByUserId(user.getUserId());

            for (Client item : clientList) {
                if (item.getClientId() == clientId) {
                    clientService.deleteById(clientId);
                    return new ResponseEntity("Successfully deleted client.", HttpStatus.OK);
                } else {
                    return new ResponseEntity("Could not delete client. Did you try to delete someone else's client???", HttpStatus.NOT_FOUND);
                }
            }
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            clientService.deleteById(clientId);
            return new ResponseEntity("Successfully deleted client.", HttpStatus.OK);
        }
        
        return new ResponseEntity("I don't know what happened.", HttpStatus.NOT_FOUND);

    }
}
