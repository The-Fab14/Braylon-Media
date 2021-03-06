/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositories;

import com.dev10.BraylonMedia.entities.Visit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joe
 */
@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer>{
    @Query(value = "SELECT * FROM visit WHERE user_id = ?1", nativeQuery = true)
    List<Visit> getVisitsByUserId(int id);
    
    @Query(value = "SELECT * FROM visit WHERE client_id = ?1", nativeQuery = true)
    List<Visit> findAllByClientId(int id);
    
    @Query(value = "SELECT * FROM visit WHERE client_id = ?1 AND user_id = ?2", nativeQuery = true)
    List<Visit> findAllByClientAndUser(int clientId, int userId);
}