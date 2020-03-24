package com.dev10.BraylonMedia.controllers;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.repositories.ClientRepository;
import com.dev10.BraylonMedia.repositories.OrderRepository;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Lindsay Gen10
 * @date Mar 18, 2020
 */
@Controller
public class OrderController 
{
    Set<ConstraintViolation<User>> violations = new HashSet<>();
    
    @Autowired
    OrderRepository orders;
    
    @Autowired
    ClientRepository clients;
    
    
    @GetMapping("/add_new_order")
    public String displayAddOrder()
    {
        return "add_new_order";
    }
    
    @GetMapping("/edit_order")
    public String displayEditOrder(Integer orderId, Model model)
    {
        model.addAttribute("orders", orders.findAll());;
        violations.clear();
        return "edit_order";
    }
    
    
    @GetMapping("/orders")
    public String displayOrders(Model model)
    {
        model.addAttribute("orders", orders.findAll());;
        violations.clear();
        return "orders";
    }
    
    
}