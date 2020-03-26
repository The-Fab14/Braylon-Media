package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.Product;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.OrderService;
import com.dev10.BraylonMedia.services.UserService;
import java.util.HashSet;
import java.util.List;
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
public class OrderController {

    Set<ConstraintViolation<User>> violations = new HashSet<>();

    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;

    @GetMapping("/add_new_order")
    public String displayAddOrder() {
        return "add_new_order";
    }

    @GetMapping("/edit_order")
    public String displayEditOrder(Integer orderId, Model model) {
        User user = userService.getUserFromSession();
        if (user.getUserRole().equals("ROLE_USER")) {
            List<Order> orderList = orderService.getOrdersByUserId(user.getUserId());

            for (Order item : orderList) {
                if (item.getOrderId() == orderId) {
                    model.addAttribute("orders", orderService.getOrder(orderId));
                    violations.clear();
                    return "edit_order";
                } else {
                    // add violation here
                    model.addAttribute("orders", null);
                    return "edit_order";
                }
            }
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            model.addAttribute("orders", orderService.getOrder(orderId));
            violations.clear();
            return "edit_order";
        }

        return "edit_order";

    }

    @GetMapping("/orders")
    public String displayOrders(Model model, String orderIds, String clientIds, String userIds) 
    {
        User user = userService.getUserFromSession();
        List<Order> orderList = orderService.getAllOrders();
        List<User> users = userService.findAll();
        List<Client> clients = clientService.findAll();
        Integer orderId = null;
        Integer clientId = null;
        Integer userId = null;
        try
        {
            orderId = Integer.parseInt(orderIds);
        }
        catch(NumberFormatException e)
        {
            
        }
        try
        {
            clientId = Integer.parseInt(clientIds);
        }
        catch(NumberFormatException e)
        {
            
        }
        try
        {
            userId = Integer.parseInt(userIds);
        }
        catch(NumberFormatException e)
        {
            
        }
        //limits list if user isn't an admin to only their stuff
        if(!user.getUserRole().equals("ROLE_ADMIN"))
        {
            users.clear();
            users.add(user);
            clients.clear();
            clients = clientService.findAllByUserId(user.getUserId());
            orderList.clear();
            orderList = orderService.getOrdersByUserId(user.getUserId());
            userId = user.getUserId();
            model.addAttribute("ordersAll", orderService.getOrdersByUserId(user.getUserId()));
        }
        else
        {
            model.addAttribute("ordersAll", orderService.getAllOrders());
        }
        //if user selected an orderId
        if(orderId != null)
        {
            orderList.clear();
            orderList.add(orderService.getOrder(orderId));
        }
        
        //if user selected a user
        if(userId != null)
        {
            orderList.clear();
            orderList = orderService.getOrdersByUserId(userId);
        }
        
        //if user selected a client
        if(clientId != null)
        {
            orderList.clear();
            orderList = orderService.getOrdersByClientId(clientId);
        }
        
        //if user selected user and client
        if(userId != null && clientId != null)
        {
            orderList.clear();
            List<Order> orders = orderService.getOrdersByUserId(userId);
            for(Order order : orders)
            {
                if(order.getClient().equals(clientService.findById(clientId)))
                {
                    orderList.add(order);
                }
            }
        }
        
        for(Order orderItem : orderList) 
        {
            List<Product> productListWithinOrder = orderItem.getProducts();
            for(Product productItem : productListWithinOrder) 
            {
                int quantity = orderService.getOrderProductQuantity(orderItem.getOrderId(), productItem.getProductId());
                productItem.setOrderProductQuantity(quantity);
            }
        }
        
        model.addAttribute("users", users);
        model.addAttribute("clients", clients);
        model.addAttribute("orders", orderList);
        violations.clear();
        return "orders";
    }

}