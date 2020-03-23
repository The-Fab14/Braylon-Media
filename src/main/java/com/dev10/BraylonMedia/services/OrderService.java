  
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Order;
import java.util.List;

public interface OrderService {
    
    Order addOrder(Order order);
    Order getOrder(int id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(int id);
    List<Order> getOrdersByClientId(int id);
    List<Order> getOrdersByCompany(String company);
    Order editOrder(int origOrderId, Order editedOrder);

}