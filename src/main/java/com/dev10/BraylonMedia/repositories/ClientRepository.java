/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositories;

import com.dev10.BraylonMedia.entities.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    @Query(value = "SELECT c.* FROM crm_client c INNER JOIN crm_user u ON c.user_id = u.user_id WHERE c.user_id = ?1", nativeQuery = true)
    List<Client> findAllByUserId(int userId);
}
