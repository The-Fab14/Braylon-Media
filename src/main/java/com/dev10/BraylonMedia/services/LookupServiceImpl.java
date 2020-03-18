/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.data.StateRepository;
import com.dev10.BraylonMedia.entities.State;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class LookupServiceImpl implements LookupService {
    
    @Autowired
    StateRepository stateRepository;

    @Override
    public State findById(String stateId) {
        return stateRepository.findById(stateId).orElse(null);
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void deleteById(String stateId) {
        stateRepository.deleteById(stateId);
    }

    @Override
    public long count() {
        return stateRepository.count();
    }

    @Override
    public boolean existsById(String stateId) {
        return stateRepository.existsById(stateId);
    }
    
}
