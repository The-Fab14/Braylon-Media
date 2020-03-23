package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.repositories.StateRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Kenji Kaenbyou, Garrett Becker
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LookupServiceTest {
    
    @Autowired
    StateRepository sr;
    
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
        String id = "NC";
        State state = sr.findById(id).orElse(null);
        assertNotNull(state);
        assertEquals(state.getStateId(), id);
    }

    /**
     * Test of findAll method, of class LookupService.
     */
    @Test
    public void testFindAll() {
        List<State> allStates = sr.findAll();
        assertEquals(allStates.size(), 52);
    }

    /**
     * Test of save method, of class LookupService.
     */
    @Test
    public void testSave() {
        State newState = new State();
        newState.setStateId("QP");
        newState.setStateName("New State");
        sr.save(newState);
        
        State fromRepo = sr.findById("QP").orElse(null);
        assertEquals(newState.getStateId(), fromRepo.getStateId());
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
    
}
