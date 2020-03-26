/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.config.SecurityUtils;
import com.dev10.BraylonMedia.repositories.UserRepository;
import com.dev10.BraylonMedia.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder encoder;

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public boolean existsById(int userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User getUserFromSession() {
        String username = SecurityUtils.getUserName();
        int userId = Integer.parseInt(username);
        User user = findById(userId);
        return user;
    }
    
    @Override
    public boolean defaultPasswordChanged(User user) {
        String oldPassword = encoder.encode("jerkstore");
        boolean matching = encoder.matches(user.getUserPassword(), oldPassword);
        return matching;
    }

    @Override
    public User findUserByClientId(int clientId) {
        return userRepository.findUserByClientId(clientId);
    }

}
