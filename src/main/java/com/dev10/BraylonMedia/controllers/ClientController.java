package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.LookupService;
import javax.servlet.http.HttpServletRequest;
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
public class ClientController 
{
    @Autowired
    ClientService clients;
    
    @Autowired
    LookupService lookUp;
    
    @GetMapping("/add_customer")
    public String displayAddCustomer(Model model)
    {
        return "add_customer";
    }
    
    @PostMapping("/add_customer")
    public String addCustomer(HttpServletRequest request, Model model, String companyName, 
            String firstName, String lastName, String streetAddress, 
            String aptUnit, String city, String stateId, int zip, String phone, String email)
    {
        String userId = request.getUserPrincipal().getName();
        Client client = new Client();
        client.setCompanyName(companyName);
        client.setContactFirstName(firstName);
        client.setContactLastName(lastName);
        client.setStreetAddress(streetAddress);
        client.setAptUnit(aptUnit);
        client.setCity(city);
        State state = lookUp.findById(stateId);
        client.setState(state);
        client.setZip(zip);
        client.setEmailAddress(email);
        client.setPhoneNumber(phone);
        clients.save(client);
        return "redirect:/home";
    }
    
    @GetMapping("/edit_customer/{clientId}")
    public String displayEditCustomer(Model model, @PathVariable int clientId)
    {
        Client client = clients.findById(clientId);
        model.addAttribute("client", client);
        return "edit_customer";
    }
    
    @PostMapping("/edit_customer")
    public String editCustomer(HttpServletRequest request, Model model, String companyName, 
            String firstName, String lastName, String streetAddress, 
            String aptUnit, String city, String stateId, int zip, String phone, String email,
            int clientId)
    {
        Client client = new Client();
        client.setCompanyName(companyName);
        client.setContactFirstName(firstName);
        client.setContactLastName(lastName);
        client.setStreetAddress(streetAddress);
        client.setAptUnit(aptUnit);
        client.setCity(city);
        State state = lookUp.findById(stateId);
        client.setState(state);
        client.setZip(zip);
        client.setEmailAddress(email);
        client.setPhoneNumber(phone);
        client.setClientId(clientId);
        clients.save(client);
        return "redirect:/orders";
    }
}
