/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dev10.BraylonMedia.entities.Visit;

/**
 *
 * @author joe
 */
@Repository
public interface VisitRepository extends JpaRepository <Visit, Integer> {
    
}
