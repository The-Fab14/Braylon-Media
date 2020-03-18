/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.data;

import com.dev10.BraylonMedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel Bart
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
