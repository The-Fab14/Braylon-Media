/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Client;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author garrettbecker
 */
public class ClientServiceTest {
    
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
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findById method, of class ClientService.
     */
    @Test
    public void testFindById() {
    }

    /**
     * Test of findAll method, of class ClientService.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * Test of save method, of class ClientService.
     */
    @Test
    public void testSave() {
    }

    /**
     * Test of deleteById method, of class ClientService.
     */
    @Test
    public void testDeleteById() {
    }

    /**
     * Test of count method, of class ClientService.
     */
    @Test
    public void testCount() {
    }

    /**
     * Test of existsById method, of class ClientService.
     */
    @Test
    public void testExistsById() {
    }

    /**
     * Test of findAllByUserId method, of class ClientService.
     */
    @Test
    public void testFindAllByUserId() {
    }

    public class ClientServiceImpl implements ClientService {

        public Client findById(int clientId) {
            return null;
        }

        public List<Client> findAll() {
            return null;
        }

        public Client save(Client client) {
            return null;
        }

        public void deleteById(int clientId) {
        }

        public long count() {
            return 0L;
        }

        public boolean existsById(int clientId) {
            return false;
        }

        public List<Client> findAllByUserId(int userId) {
            return null;
        }
    }
    
}
