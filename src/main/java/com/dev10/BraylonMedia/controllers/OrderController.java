package com.dev10.BraylonMedia.controllers;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.Product;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.services.ClientService;
import com.dev10.BraylonMedia.services.OrderService;
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

    @GetMapping("/add_new_order")
    public String displayAddOrder(Model model) {
        model.addAttribute("customViolations", customViolations);
        return "add_new_order";
    }

    @PostMapping("/add_new_order")
    public String addCustomer(HttpServletRequest request, Model model, String dateSubmitted,
            String dateInstalled, String dateCompleted, String orderTotal,
            String orderStatus, String orderComments, String clientId, String productId, String productQuantity) {

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

        User userId = userService.getUserFromSession();
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
        return "redirect:/home";
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
        List<Order> orderList = orderService.getOrdersByUserId(user.getUserId());

        for (Order orderItem : orderList) {
            List<Product> productListWithinOrder = orderItem.getProducts();
            for (Product productItem : productListWithinOrder) {
                int quantity = orderService.getOrderProductQuantity(orderItem.getOrderId(), productItem.getProductId());
                productItem.setOrderProductQuantity(quantity);
            }
        }
        model.addAttribute("orders", orderList);
        violations.clear();
        return "orders";
    }

}
