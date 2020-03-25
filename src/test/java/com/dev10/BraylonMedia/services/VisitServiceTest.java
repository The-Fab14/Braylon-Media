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
import java.time.LocalDate;
import java.util.ArrayList;
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
 * @author Garrett Becker
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class VisitServiceTest {
    
    @Autowired
    ClientRepository cr;
    
    @Autowired
    OrderRepository or;
    
    @Autowired
    StateRepository sr;
    
    @Autowired
    UserRepository ur;
    
    @Autowired
    VisitRepository vr;
    
    public VisitServiceTest() {
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
     * Test of addVisit method, of class VisitService.
     */
    @Test
    public void testAddVisit() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        List<Visit> allVisits = vr.findAll();
        int id = 0;
        for (Visit v : allVisits) {
            id = v.getVisitId();
        }
        
        Visit fromRepo = vr.findById(id).orElse(null);
        assertNotNull(fromRepo);
        assertEquals(fromRepo.getVisitId(), id);
    }

    /**
     * Test of getVisit method, of class VisitService.
     */
    @Test
    public void testGetVisit() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        List<Visit> allVisits = vr.findAll();
        int id = 0;
        for (Visit v : allVisits) {
            id = v.getVisitId();
        }
        
        Visit fromRepo = vr.findById(id).orElse(null);
        assertNotNull(fromRepo);
    }

    /**
     * Test of getAllVisits method, of class VisitService.
     */
    @Test
    public void testGetAllVisits() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        List<Visit> allVisits = vr.findAll();
        assertEquals(allVisits.size(), 1);
        
        Visit newVisit2 = new Visit();
        newVisit2.setVisitId(0);
        newVisit2.setDateVisited(LocalDate.now());
        newVisit2.setUser(newUser1);
        newVisit2.setVisitNotes("Notes");
        newVisit2.setClient(newClient1);
        vr.save(newVisit2);
        
        allVisits = vr.findAll();
        assertEquals(allVisits.size(), 2);
        
        Visit newVisit3 = new Visit();
        newVisit3.setVisitId(0);
        newVisit3.setDateVisited(LocalDate.now());
        newVisit3.setUser(newUser1);
        newVisit3.setVisitNotes("Notes");
        newVisit3.setClient(newClient1);
        vr.save(newVisit3);
        
        allVisits = vr.findAll();
        assertEquals(allVisits.size(), 3);
    }

    /**
     * Test of getVisitsByClientId method, of class VisitService.
     */
    @Test
    public void testGetVisitsByClientId() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(0);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip(12345);
        newClient2.setUser(newUser1);
        newClient2.setEmailAddress("email@email.com");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        Visit newVisit2 = new Visit();
        newVisit2.setVisitId(0);
        newVisit2.setDateVisited(LocalDate.now());
        newVisit2.setUser(newUser1);
        newVisit2.setVisitNotes("Notes");
        newVisit2.setClient(newClient2);
        vr.save(newVisit2);
        
        Visit newVisit3 = new Visit();
        newVisit3.setVisitId(0);
        newVisit3.setDateVisited(LocalDate.now());
        newVisit3.setUser(newUser1);
        newVisit3.setVisitNotes("Notes");
        newVisit3.setClient(newClient2);
        vr.save(newVisit3);
        
        List<Client> clientsInDB = cr.findAll();
        Client c1 = clientsInDB.get(0);
        int clientID1 = c1.getClientId();
        Client c2 = clientsInDB.get(1);
        int clientID2 = c2.getClientId();
        
        List<Visit> allVisits = vr.findAll();
        List<Visit> visitsId1 = new ArrayList<>();
        List<Visit> visitsId2 = new ArrayList<>();
        for (Visit v : allVisits) {
            Client c = v.getClient();
            
            if (c.getClientId() == clientID1) {
                visitsId1.add(v);
            }
            
            if (c.getClientId() == clientID2) {
                visitsId2.add(v);
            }
        }
        
        assertEquals(visitsId1.size(), 1);
        assertEquals(visitsId2.size(), 2);
    }

    /**
     * Test of getVisitsByUserId method, of class VisitService.
     */
    @Test
    public void testGetVisitsByUserId() {
        State newState = new State();
        newState = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState);
        ur.save(newUser1);
        
        User newUser2 = new User();
        newUser2.setUserId(0);
        newUser2.setFirstName("First");
        newUser2.setLastName("Last");
        newUser2.setEmailAddress("email@email.com");
        newUser2.setUserRole("Role");
        newUser2.setUserPassword("Password");
        newUser2.setDidPasswordChange(false);
        newUser2.setState(newState);
        ur.save(newUser2);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(0);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip(12345);
        newClient2.setUser(newUser2);
        newClient2.setEmailAddress("email@email.com");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        Visit newVisit2 = new Visit();
        newVisit2.setVisitId(0);
        newVisit2.setDateVisited(LocalDate.now());
        newVisit2.setUser(newUser2);
        newVisit2.setVisitNotes("Notes");
        newVisit2.setClient(newClient2);
        vr.save(newVisit2);
        
        Visit newVisit3 = new Visit();
        newVisit3.setVisitId(0);
        newVisit3.setDateVisited(LocalDate.now());
        newVisit3.setUser(newUser2);
        newVisit3.setVisitNotes("Notes");
        newVisit3.setClient(newClient2);
        vr.save(newVisit3);
        
        List<User> usersInDB = ur.findAll();
        User u1 = usersInDB.get(0);
        int userID1 = u1.getUserId();
        User u2 = usersInDB.get(1);
        int userID2 = u2.getUserId();
        
        List<Visit> allVisits = vr.findAll();
        List<Visit> visitsId1 = new ArrayList<>();
        List<Visit> visitsId2 = new ArrayList<>();
        for (Visit v : allVisits) {
            User u = v.getUser();
            
            if (u.getUserId() == userID1) {
                visitsId1.add(v);
            }
            
            if (u.getUserId() == userID2) {
                visitsId2.add(v);
            }
        }
        
        assertEquals(visitsId1.size(), 1);
        assertEquals(visitsId2.size(), 2);
    }

    /**
     * Test of getVisitsByStateId method, of class VisitService.
     */
    @Test
    public void testGetVisitsByStateId() {
        State newState1 = new State();
        newState1 = sr.findById("NC").orElse(null);
        
        State newState2 = new State();
        newState2 = sr.findById("AL").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState1);
        ur.save(newUser1);
        
        User newUser2 = new User();
        newUser2.setUserId(0);
        newUser2.setFirstName("First");
        newUser2.setLastName("Last");
        newUser2.setEmailAddress("email@email.com");
        newUser2.setUserRole("Role");
        newUser2.setUserPassword("Password");
        newUser2.setDidPasswordChange(false);
        newUser2.setState(newState2);
        ur.save(newUser2);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState1);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(0);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("Company");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState2);
        newClient2.setZip(12345);
        newClient2.setUser(newUser2);
        newClient2.setEmailAddress("email@email.com");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        Visit newVisit2 = new Visit();
        newVisit2.setVisitId(0);
        newVisit2.setDateVisited(LocalDate.now());
        newVisit2.setUser(newUser2);
        newVisit2.setVisitNotes("Notes");
        newVisit2.setClient(newClient2);
        vr.save(newVisit2);
        
        Visit newVisit3 = new Visit();
        newVisit3.setVisitId(0);
        newVisit3.setDateVisited(LocalDate.now());
        newVisit3.setUser(newUser2);
        newVisit3.setVisitNotes("Notes");
        newVisit3.setClient(newClient2);
        vr.save(newVisit3);
        
        String state1 = "NC";
        String state2 = "AL";
        
        List<Visit> allVisits = vr.findAll();
        List<Visit> visitsState1 = new ArrayList<>();
        List<Visit> visitsState2 = new ArrayList<>();
        for (Visit v : allVisits) {
            User u = v.getUser();
            State s = u.getState();
            
            if (s.getStateId().equals(state1)) {
                visitsState1.add(v);
            }
            
            if (s.getStateId().equals(state2)) {
                visitsState2.add(v);
            }
        }
        
        assertEquals(visitsState1.size(), 1);
        assertEquals(visitsState2.size(), 2);
    }

    /**
     * Test of editVisit method, of class VisitService.
     */
    @Test
    public void testEditVisit() {
        State newState1 = new State();
        newState1 = sr.findById("NC").orElse(null);
        
        User newUser1 = new User();
        newUser1.setUserId(0);
        newUser1.setFirstName("First");
        newUser1.setLastName("Last");
        newUser1.setEmailAddress("email@email.com");
        newUser1.setUserRole("Role");
        newUser1.setUserPassword("Password");
        newUser1.setDidPasswordChange(false);
        newUser1.setState(newState1);
        ur.save(newUser1);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("Company");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState1);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email@email.com");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Visit newVisit1 = new Visit();
        newVisit1.setVisitId(0);
        newVisit1.setDateVisited(LocalDate.now());
        newVisit1.setUser(newUser1);
        newVisit1.setVisitNotes("Notes");
        newVisit1.setClient(newClient1);
        vr.save(newVisit1);
        
        List<Visit> allVisits = vr.findAll();
        int id = 0;
        for (Visit v : allVisits) {
            id = v.getVisitId();
        }
        
        Visit fromRepo = vr.findById(id).orElse(null);
        assertEquals(fromRepo.getVisitId(), id);
        
        fromRepo.setVisitNotes("New Notes");
        vr.save(fromRepo);
        
        Visit fromRepoEdited = vr.findById(id).orElse(null);
        assertEquals(fromRepo.getVisitId(), fromRepoEdited.getVisitId());
    }
    
}
