/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositories;

import com.dev10.BraylonMedia.entities.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author diego
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query(value = "SELECT op.quantity FROM crm_order_product op WHERE op.order_id = ?1 AND op.product_id = ?2", nativeQuery = true)
    int findOrderProductQuantity(int orderId, int productId);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE crm_order_product op SET op.quantity = ?3 WHERE op.order_id = ?1 AND op.product_id = ?2", nativeQuery = true)
    void saveOrderProductQuantity(int orderId, int productId, int quantity);
    
    @Query(value = "SELECT * FROM crm_order ot INNER JOIN "
            + "crm_client ct ON ct.client_id = ot.client_id INNER JOIN "
            + "crm_user ut ON ct.user_id = ut.user_id WHERE "
            + "ut.user_id = ?1", nativeQuery = true)
    List<Order> getOrdersByUserId(int userId);
    
    @Query(value = "SELECT * FROM crm_order WHERE client_id = ?1", nativeQuery = true)
    List<Order> getOrdersByClientId(int userId);
}
