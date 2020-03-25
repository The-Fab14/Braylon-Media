/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositories;

import com.dev10.BraylonMedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joe
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT c.user_id FROM crm_client c WHERE client_id = ?1", nativeQuery = true)
    User findUserByClientId(int clientId);
}