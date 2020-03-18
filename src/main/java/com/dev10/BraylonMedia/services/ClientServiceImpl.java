/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.data.ClientRepository;
import com.dev10.BraylonMedia.entities.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class ClientServiceImpl implements ClientService {
    
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findById(int clientId) {
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(int clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public long count() {
        return clientRepository.count();
    }

    @Override
    public boolean existsById(int clientId) {
        return clientRepository.existsById(clientId);
    }

    @Override
    public List<Client> findAllByUserId(int userId) {
        return clientRepository.findAllByUserId(userId);
    }
    
}
