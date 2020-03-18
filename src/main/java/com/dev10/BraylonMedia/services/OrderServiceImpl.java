package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev10.BraylonMedia.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    OrderRepository orderRepo;

    @Override
    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order getOrder(int id) {
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersByClientId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Order> getOrdersByCompany(String company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order editOrder(int origOrderId, Order editedOrder) {
        Order origOrder = orderRepo.findById(origOrderId).orElse(null);
        origOrder.setDateSubmitted(editedOrder.getDateSubmitted());
        origOrder.setClient(editedOrder.getClient());
        origOrder.setProducts(editedOrder.getProducts());
        origOrder.setOrderComments(editedOrder.getOrderComments());
        origOrder.setOrderTotal(editedOrder.getOrderTotal());
        origOrder.setOrderStatus(editedOrder.getOrderStatus());
        
        if(origOrder.getOrderStatus().equals("installed")) {
            origOrder.setDateInstalled(editedOrder.getDateInstalled());
        } else if (origOrder.getOrderStatus().equals("completed")) {
            origOrder.setDateCompleted(editedOrder.getDateCompleted());
        }
        
        return origOrder;
    }

}