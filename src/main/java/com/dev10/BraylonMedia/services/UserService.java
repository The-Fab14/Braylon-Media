/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.User;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface UserService {
    
    // CRUD for User objects
    /**
     * Find User by userId
     * @param userId
     * @return 
     */
    User findById(int userId);

    /**
     * Find all User objects
     * @return 
     */
    List<User> findAll();

    /**
     * Save User
     * @param user
     * @return 
     */
    User save(User user);

    /**
     * Delete User by userId
     * @param userId
     */
    void deleteById(int userId);

    /**
     * Count User objects
     * @return 
     */
    long count();

    /**
     * Check if User exists by userId
     * @param userId
     * @return 
     */
    boolean existsById(int userId);
    
    /**
     * Get User object from session
     * @return 
     */
    User getUserFromSession();
    
     /**
     * Check that initial password was changed
     * @return 
     * @param user
     */
    boolean defaultPasswordChanged(User user);
    
     /**
     * Find sales rep by clientId
     * @param clientId
     * @return 
     */
    User findUserByClientId(int clientId);
    
     /**
     * Check if User email exists
     * @param newEmail
     * @param userId
     * @return 
     */
    boolean emailAddressExists(String newEmail, int userId);
    
     /**
     * Check if User email exists
     * @param newEmail
     * @return 
     */
    boolean emailAddressExists(String newEmail);
}