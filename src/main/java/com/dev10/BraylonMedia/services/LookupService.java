/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.State;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface LookupService {
    
    // CRUD for State objects
    /**
     * Find State by stateId
     * @param stateId
     * @return 
     */
    State findById(String stateId);

    /**
     * Find all State objects
     * @return 
     */
    List<State> findAll();

    /**
     * Save State
     * @param state
     * @return 
     */
    State save(State state);

    /**
     * Delete State by stateId
     * @param stateId
     */
    void deleteById(String stateId);

    /**
     * Count State objects
     * @return 
     */
    long count();

    /**
     * Check if State exists by stateId
     * @param stateId
     * @return 
     */
    boolean existsById(String stateId);
    
}
