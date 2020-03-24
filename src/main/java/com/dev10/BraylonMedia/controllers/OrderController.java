package com.dev10.BraylonMedia.controllers;

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
    public String displayOrders(Model model) {
        User user = userService.getUserFromSession();
        List<Order> orderList = orderService.getOrdersByUserId(user.getUserId());
        
        for(Order orderItem : orderList) {
            List<Product> productListWithinOrder = orderItem.getProducts();
            for(Product productItem : productListWithinOrder) {
                int quantity = orderService.getOrderProductQuantity(orderItem.getOrderId(), productItem.getProductId());
                productItem.setOrderProductQuantity(quantity);
            }
        }
        
        model.addAttribute("orders", orderList);
        violations.clear();
        return "orders";
    }

}