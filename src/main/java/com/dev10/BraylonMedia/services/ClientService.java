/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Client;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface ClientService {
    
    // CRUD for Client objects
    /**
     * Find Client by clientId
     * @param clientId
     * @return 
     */
    Client findById(int clientId);

    /**
     * Find all Client objects
     * @return 
     */
    List<Client> findAll();

    /**
     * Save Client
     * @param client
     * @return 
     */
    Client save(Client client);

    /**
     * Delete Client by clientId
     * @param clientId
     */
    void deleteById(int clientId);

    /**
     * Count Client objects
     * @return 
     */
    long count();

    /**
     * Check if Client exists by clientId
     * @param clientId
     * @return 
     */
    boolean existsById(int clientId);
    
    /**
     * Find all Client objects by userId
     * @param userId
     * @return 
     */
    List<Client> findAllByUserId(int userId);
    
}
