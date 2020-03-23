/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositories;

import com.dev10.BraylonMedia.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    @Query(value = "SELECT op.quantity FROM crm_order_product op WHERE op.order_id = ?1 AND op.product_id = ?2", nativeQuery = true)
    int findOrderProductQuantity(int orderId, int productId);
}
