package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.Product;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.OrderService;
import com.dev10.BraylonMedia.services.ProductService;
import com.dev10.BraylonMedia.services.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController {

    Set<ConstraintViolation<User>> violations = new HashSet<>();
    Set<String> customViolations = new HashSet<>();    

    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;

    @GetMapping("/add_new_order")
    public String displayAddOrder(Model model) {
        User user = userService.getUserFromSession();
        model.addAttribute("customViolations", customViolations);
        model.addAttribute("clients", clientService.findAllByUserId(user.getUserId()));
        model.addAttribute("allClients", clientService.findAll());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("users", userService.findAll());
        return "add_new_order";
    }

    @PostMapping("/add_new_order")
    public String addCustomer(HttpServletRequest request, Model model, String dateSubmitted,
            String dateInstalled, String dateCompleted, String orderTotal,
            String orderStatus, String orderComments, String clientId, String productId, String productQuantity, String userId) {

        LocalDate dateSubmittedLocalDate;
        LocalDate dateInstalledLocalDate;
        LocalDate dateCompletedLocalDate;
        BigDecimal orderTotalBigDecimal;
        int clientIdInt;
        int productIdInt;
        int productQuantityInt;

        try {
            dateSubmittedLocalDate = LocalDate.parse(dateSubmitted);
        } catch (Exception e) {
            customViolations.add("Date submitted format is incorrect.");
            return "redirect:/add_new_order";
        }

        try {
            dateInstalledLocalDate = LocalDate.parse(dateInstalled);
        } catch (Exception e) {
            customViolations.add("Date installed format is incorrect.");
            return "redirect:/add_new_order";
        }

        try {
            dateCompletedLocalDate = LocalDate.parse(dateCompleted);
        } catch (Exception e) {
            customViolations.add("Date completed format is incorrect.");
            return "redirect:/add_new_order";
        }

        try {
            orderTotalBigDecimal = new BigDecimal(orderTotal);
        } catch (Exception e) {
            customViolations.add("Order total format is incorrect.");
            return "redirect:/add_new_order";
        }

        try {
            clientIdInt = Integer.parseInt(clientId);
        } catch (Exception e) {
            customViolations.add("Client Id format is incorrect.");
            return "redirect:/add_new_order";
        }

        try {
            productIdInt = Integer.parseInt(productId);
        } catch (Exception e) {
            customViolations.add("Product Id format is incorrect.");
            return "redirect:/add_new_order";
        }
        
        try {
            productQuantityInt = Integer.parseInt(productQuantity);
        } catch (Exception e) {
            customViolations.add("Product quantity format is incorrect.");
            return "redirect:/add_new_order";
        }
        if(userId != null)
        {
            Client curr = clientService.findById(clientIdInt);
            if(curr.getUser().getUserId() != Integer.parseInt(userId))
            {
                Client newClient = new Client();
                newClient.setCompanyName(curr.getCompanyName());
                newClient.setContactFirstName(curr.getContactFirstName());
                newClient.setContactLastName(curr.getContactLastName());
                newClient.setAptUnit(curr.getAptUnit());
                newClient.setCity(curr.getCity());
                newClient.setEmailAddress(curr.getEmailAddress());
                newClient.setPhoneNumber(curr.getPhoneNumber());
                newClient.setState(curr.getState());
                newClient.setStreetAddress(curr.getStreetAddress());
                newClient.setZip(curr.getZip());
                newClient.setUser(userService.findById(Integer.parseInt(userId)));
                newClient = clientService.save(newClient);
                clientIdInt = newClient.getClientId();
            }
        }
        User user = userService.getUserFromSession();
        Order order = new Order();
        order.setDateSubmitted(dateSubmittedLocalDate);
        order.setDateInstalled(dateInstalledLocalDate);
        order.setDateCompleted(dateCompletedLocalDate);
        order.setOrderTotal(orderTotalBigDecimal);
        order.setOrderStatus(orderStatus);
        order.setOrderComments(orderComments);

        Client client = new Client();
        client.setClientId(clientIdInt);
        order.setClient(client);

        List<Product> productList = new ArrayList<>();

        Product product = new Product();
        product.setProductId(productIdInt);
        productList.add(product);
        order.setProducts(productList);

        order = orderService.addOrder(order);
        
        orderService.saveOrderProductQuantity(order.getOrderId(), productIdInt, productQuantityInt);
        
        customViolations.clear();
        return "redirect:/orders";
    }

    @GetMapping("/edit_order")
    public String displayEditOrder(String orderId, Model model) {
        int orderIdInt = Integer.parseInt(orderId);
        User user = userService.getUserFromSession();
        if (user.getUserRole().equals("ROLE_USER")) {
            List<Order> orderList = orderService.getOrdersByUserId(user.getUserId());

            for (Order item : orderList) {
                if (item.getOrderId() == orderIdInt) {
                    Order orderToDisplay = orderService.getOrder(item.getOrderId());
                    List<Product> orderToDisplayProductList = orderToDisplay.getProducts();
                    
                    for(Product productToDisplay: orderToDisplayProductList) {
                        int quantity = orderService.getOrderProductQuantity(item.getOrderId(), productToDisplay.getProductId());
                        productToDisplay.setOrderProductQuantity(quantity);
                    }
                    
                    model.addAttribute("orders", orderToDisplay);
                    violations.clear();
                    return "edit_order";
                }
            }
        } else if (user.getUserRole().equals("ROLE_ADMIN")) {
            model.addAttribute("orders", orderService.getOrder(orderIdInt));
            violations.clear();
            return "edit_order";
        }

        return "redirect:/orders";

    }
    
    @PostMapping("/edit_order")
    public String editOrder()
    {
        return "redirect:/orders";
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
        boolean userLive = false;
        boolean clientLive = false;
        boolean orderLive = false;
        try
        {
            orderId = Integer.parseInt(orderIds);
            orderLive = true;
            model.addAttribute("cOrder", orderService.getOrder(orderId));
        }
        catch(NumberFormatException e)
        {
            
        }
        try
        {
            clientId = Integer.parseInt(clientIds);
            clientLive = true;
            model.addAttribute("uClient", clientService.findById(clientId));
        }
        catch(NumberFormatException e)
        {
            
        }
        try
        {
            userId = Integer.parseInt(userIds);
            userLive = true;
            model.addAttribute("cUser", userService.findById(userId));
        }
        catch(NumberFormatException e)
        {
            
        }
        model.addAttribute("clientLive", clientLive);
        model.addAttribute("userLive", userLive);
        model.addAttribute("orderLive", orderLive);
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