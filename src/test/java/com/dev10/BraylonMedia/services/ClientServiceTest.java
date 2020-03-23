/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.repositories.ClientRepository;
import com.dev10.BraylonMedia.repositories.StateRepository;
import com.dev10.BraylonMedia.repositories.UserRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Garrett Becker, Sydney Mason
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClientServiceTest {
    
    @Autowired
    ClientRepository cr;
    
    @Autowired
    StateRepository sr;
    
    @Autowired
    UserRepository ur;
    
    public ClientServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Client> allClients = cr.findAll();
        for (Client c : allClients) {
            cr.delete(c);
        }
        
        List<User> allUsers = ur.findAll();
        for (User u : allUsers) {
            ur.delete(u);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findById method, of class ClientService.
     */
    @Test
    public void testFindById() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient = new Client();
        newClient.setClientId(1);
        newClient.setContactFirstName("First");
        newClient.setContactLastName("Last");
        newClient.setCompanyName("Company");
        newClient.setAptUnit("Apt");
        newClient.setStreetAddress("Street");
        newClient.setCity("City");
        newClient.setState(newState);
        newClient.setZip("12345");
        newClient.setUser(newUser);
        newClient.setEmailAddress("email");
        newClient.setPhoneNumber("1234567890");
        cr.save(newClient);
        
        List<Client> allClients = cr.findAll();
        int id = 0;
        for (Client c : allClients) {
            id = c.getClientId();
        }
        
        Client fromRepo = new Client();
        fromRepo = cr.findById(id).orElse(null);
        
        assertNotNull(fromRepo);
        assertEquals(fromRepo.getClientId(), id);
    }

    /**
     * Test of findAll method, of class ClientService.
     */
    @Test
    public void testFindAll() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient1 = new Client();
        newClient1.setClientId(1);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip("12345");
        newClient1.setUser(newUser);
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(2);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip("12345");
        newClient2.setUser(newUser);
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        List<Client> clients = cr.findAll();
        assertEquals(clients.size(), 2);
    }

    /**
     * Test of save method, of class ClientService.
     */
    @Test
    public void testSave() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient1 = new Client();
        newClient1.setClientId(1);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip("12345");
        newClient1.setUser(newUser);
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        List<Client> clients = cr.findAll();
        assertEquals(clients.size(), 1);
    }

    /**
     * Test of deleteById method, of class ClientService.
     */
    @Test
    public void testDeleteById() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient = new Client();
        newClient.setClientId(1);
        newClient.setContactFirstName("First");
        newClient.setContactLastName("Last");
        newClient.setCompanyName("Company");
        newClient.setAptUnit("Apt");
        newClient.setStreetAddress("Street");
        newClient.setCity("City");
        newClient.setState(newState);
        newClient.setZip("12345");
        newClient.setUser(newUser);
        newClient.setEmailAddress("email");
        newClient.setPhoneNumber("1234567890");
        cr.save(newClient);
        
        List<Client> allClients = cr.findAll();
        int id = 0;
        for (Client c : allClients) {
            id = c.getClientId();
        }
        
        Client fromRepo = new Client();
        fromRepo = cr.findById(id).orElse(null);
        assertNotNull(fromRepo);
        
        cr.deleteById(id);
        fromRepo = cr.findById(id).orElse(null);
        assertNull(fromRepo);
    }

    /**
     * Test of count method, of class ClientService.
     */
    @Test
    public void testCount() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient1 = new Client();
        newClient1.setClientId(1);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip("12345");
        newClient1.setUser(newUser);
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(2);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip("12345");
        newClient2.setUser(newUser);
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        long numClients = cr.count();
        assertEquals(numClients, 2);
    }

    /**
     * Test of existsById method, of class ClientService.
     */
    @Test
    public void testExistsById() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);
        
        Client newClient = new Client();
        newClient.setClientId(1);
        newClient.setContactFirstName("First");
        newClient.setContactLastName("Last");
        newClient.setCompanyName("Company");
        newClient.setAptUnit("Apt");
        newClient.setStreetAddress("Street");
        newClient.setCity("City");
        newClient.setState(newState);
        newClient.setZip("12345");
        newClient.setUser(newUser);
        newClient.setEmailAddress("email");
        newClient.setPhoneNumber("1234567890");
        cr.save(newClient);
        
        List<Client> allClients = cr.findAll();
        int id = 0;
        for (Client c : allClients) {
            id = c.getClientId();
        }
        
        boolean exists = cr.existsById(id);
        assertTrue(exists);
    }

    /**
     * Test of findAllByUserId method, of class ClientService.
     */
    //Still need to fix
    @Test
    public void testFindAllByUserId() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(2);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip("12345");
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(3);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip("12345");
        newClient2.setUser(newUser1);
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        List<User> allUsers = ur.findAll();
        int userId = 0;
        for (User u : allUsers) {
            userId = u.getUserId();
        }
        
        List<Client> clientsByUser = cr.findAllByUserId(userId);
        assertEquals(clientsByUser.size(), 2);
    }
}
