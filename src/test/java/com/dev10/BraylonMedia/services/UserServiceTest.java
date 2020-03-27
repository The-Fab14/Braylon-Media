package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.entities.Visit;
import com.dev10.BraylonMedia.repositories.ClientRepository;
import com.dev10.BraylonMedia.repositories.OrderRepository;
import com.dev10.BraylonMedia.repositories.StateRepository;
import com.dev10.BraylonMedia.repositories.UserRepository;
import com.dev10.BraylonMedia.repositories.VisitRepository;
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
 * @author Eric Fass
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    ClientRepository cr;

    @Autowired
    StateRepository sr;

    @Autowired
    UserRepository ur;

    @Autowired
    OrderRepository or;
    
    @Autowired
    VisitRepository vr;

    public UserServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Visit> allVisits = vr.findAll();
        for (Visit v : allVisits) {
            vr.delete(v);
        }
        
        List<Order> allOrders = or.findAll();
        for (Order o : allOrders) {
            or.delete(o);
        }

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
     * Test of findById method, of class UserService.
     */
    @Test
    public void testFindById() {
        State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        List<User> allUsers = ur.findAll();
        int id = 0;
        for (User u : allUsers) {
            id = u.getUserId();
        }

        assertNotNull(newUser);
        assertEquals(newUser.getUserId(), id);
    }

    /**
     * Test of findAll method, of class UserService.
     */
    @Test
    public void testFindAll() {
        State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        User newUser2 = new User();
        newUser2.setUserId(1);
        newUser2.setFirstName("Test");
        newUser2.setLastName("User");
        newUser2.setEmailAddress("testEmail@service.com");
        newUser2.setUserRole("Role2");
        newUser2.setUserPassword("Password2");
        newUser2.setDidPasswordChange(false);
        newUser2.setState(newState);
        ur.save(newUser2);

        List<User> allUsers = ur.findAll();
        assertEquals(allUsers.size(), 2);

    }

    /**
     * Test of save method, of class UserService.
     */
    @Test
    public void testSave() {
        State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        List<User> users = ur.findAll();
        assertEquals(users.size(), 1);
    }

    /**
     * Test of deleteById method, of class UserService.
     */
    @Test
    public void testDeleteById() {

        State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        List<User> allUsers = ur.findAll();
        int id = 0;
        for (User u : allUsers) {
            id = u.getUserId();
        }

        User fromRepo = new User();
        fromRepo = ur.findById(id).orElse(null);
        assertNotNull(fromRepo);

        ur.deleteById(id);
        fromRepo = ur.findById(id).orElse(null);
        assertNull(fromRepo);
    }

    /**
     * Test of count method, of class UserService.
     */
    @Test
    public void testCount() {

         State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        User newUser2 = new User();
        newUser2.setUserId(1);
        newUser2.setFirstName("Test");
        newUser2.setLastName("User");
        newUser2.setEmailAddress("testEmail@service.com");
        newUser2.setUserRole("Role2");
        newUser2.setUserPassword("Password2");
        newUser2.setDidPasswordChange(false);
        newUser2.setState(newState);
        ur.save(newUser2);

        long numUsers = ur.count();
        assertEquals(numUsers, 2);
    }

    /**
     * Test of existsById method, of class UserService.
     */
    @Test
    public void testExistsById() {

        State newState = new State();
        newState = sr.findById("NY").orElse(null);

        User newUser = new User();
        newUser.setUserId(0);
        newUser.setFirstName("First");
        newUser.setLastName("Last");
        newUser.setEmailAddress("email@service.com");
        newUser.setUserRole("Role");
        newUser.setUserPassword("Password");
        newUser.setDidPasswordChange(false);
        newUser.setState(newState);
        ur.save(newUser);

        List<User> allUsers = ur.findAll();
        int id = 0;
        for (User u : allUsers) {
            id = u.getUserId();
        }

        boolean exists = ur.existsById(id);
        assertTrue(exists);
    }

}