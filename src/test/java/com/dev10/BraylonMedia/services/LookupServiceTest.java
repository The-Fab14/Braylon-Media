/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.State;
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
public class LookupServiceTest {
    
    public LookupServiceTest() {
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
     * Test of findById method, of class LookupService.
     */
    @Test
    public void testFindById() {
    }

    /**
     * Test of findAll method, of class LookupService.
     */
    @Test
    public void testFindAll() {
    }

    /**
     * Test of save method, of class LookupService.
     */
    @Test
    public void testSave() {
    }

    /**
     * Test of deleteById method, of class LookupService.
     */
    @Test
    public void testDeleteById() {
    }

    /**
     * Test of count method, of class LookupService.
     */
    @Test
    public void testCount() {
    }

    /**
     * Test of existsById method, of class LookupService.
     */
    @Test
    public void testExistsById() {
    }

    public class LookupServiceImpl implements LookupService {

        public State findById(String stateId) {
            return null;
        }

        public List<State> findAll() {
            return null;
        }

        public State save(State state) {
            return null;
        }

        public void deleteById(String stateId) {
        }

        public long count() {
            return 0L;
        }

        public boolean existsById(String stateId) {
            return false;
        }
    }
    
}
