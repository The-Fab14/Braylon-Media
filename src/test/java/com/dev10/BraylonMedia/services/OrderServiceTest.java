package com.dev10.BraylonMedia.services;

import com.dev10.BraylonMedia.entities.Client;
import com.dev10.BraylonMedia.entities.Order;
import com.dev10.BraylonMedia.entities.State;
import com.dev10.BraylonMedia.entities.User;
import com.dev10.BraylonMedia.repositories.ClientRepository;
import com.dev10.BraylonMedia.repositories.OrderRepository;
import com.dev10.BraylonMedia.repositories.StateRepository;
import com.dev10.BraylonMedia.repositories.UserRepository;
import java.math.BigDecimal;
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
public class OrderServiceTest {
    
    @Autowired
    ClientRepository cr;
    
    @Autowired
    OrderRepository or;
    
    @Autowired
    StateRepository sr;
    
    @Autowired
    UserRepository ur;
    
    public OrderServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
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
     * Test of addOrder method, of class OrderService.
     */
    @Test
    public void testAddOrder() {
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
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        List<Order> allOrders = or.findAll();
        int id = 0;
        for (Order o : allOrders) {
            id = o.getOrderId();
        }
        
        Order fromRepo = or.findById(id).orElse(null);
        assertNotNull(fromRepo);
        
        assertEquals(fromRepo.getOrderId(), id);
        
    }

    /**
     * Test of getOrder method, of class OrderService.
     */
    @Test
    public void testGetOrder() {
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
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        List<Order> allOrders = or.findAll();
        int id = 0;
        for (Order o : allOrders) {
            id = o.getOrderId();
        }
        
        Order fromRepo = or.findById(id).orElse(null);
        assertNotNull(fromRepo);
    }

    /**
     * Test of getAllOrders method, of class OrderService.
     */
    @Test
    public void testGetAllOrders() {
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
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        Order newOrder2 = new Order();
        newOrder2.setOrderId(0);
        newOrder2.setDateSubmitted(LocalDate.now());
        newOrder2.setDateInstalled(LocalDate.now());
        newOrder2.setDateCompleted(LocalDate.now());
        newOrder2.setOrderTotal(BigDecimal.ZERO);
        newOrder2.setOrderStatus("Status");
        newOrder2.setOrderComments("Comments");
        newOrder2.setClient(newClient1);
        or.save(newOrder2);
        
        List<Order> allOrders = or.findAll();
        assertEquals(allOrders.size(), 2);
        
        Order newOrder3 = new Order();
        newOrder3.setOrderId(0);
        newOrder3.setDateSubmitted(LocalDate.now());
        newOrder3.setDateInstalled(LocalDate.now());
        newOrder3.setDateCompleted(LocalDate.now());
        newOrder3.setOrderTotal(BigDecimal.ZERO);
        newOrder3.setOrderStatus("Status");
        newOrder3.setOrderComments("Comments");
        newOrder3.setClient(newClient1);
        or.save(newOrder3);
        
        allOrders = or.findAll();
        assertEquals(allOrders.size(), 3);
    }

    /**
     * Test of getOrdersByUserId method, of class OrderService.
     */
    @Test
    public void testGetOrdersByUserId() {
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
        
        User newUser2 = new User();
        newUser2.setUserId(0);
        newUser2.setFirstName("First2");
        newUser2.setLastName("Last2");
        newUser2.setEmailAddress("email2");
        newUser2.setUserRole("Role2");
        newUser2.setUserPassword("Password2");
        newUser2.setDidPasswordChange(true);
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
        newClient1.setEmailAddress("email");
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
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        Order newOrder2 = new Order();
        newOrder2.setOrderId(0);
        newOrder2.setDateSubmitted(LocalDate.now());
        newOrder2.setDateInstalled(LocalDate.now());
        newOrder2.setDateCompleted(LocalDate.now());
        newOrder2.setOrderTotal(BigDecimal.ZERO);
        newOrder2.setOrderStatus("Status");
        newOrder2.setOrderComments("Comments");
        newOrder2.setClient(newClient2);
        or.save(newOrder2);
        
        Order newOrder3 = new Order();
        newOrder3.setOrderId(0);
        newOrder3.setDateSubmitted(LocalDate.now());
        newOrder3.setDateInstalled(LocalDate.now());
        newOrder3.setDateCompleted(LocalDate.now());
        newOrder3.setOrderTotal(BigDecimal.ZERO);
        newOrder3.setOrderStatus("Status");
        newOrder3.setOrderComments("Comments");
        newOrder3.setClient(newClient2);
        or.save(newOrder3);
                
        List<User> usersInDB = ur.findAll();
        User u1 = usersInDB.get(0);
        int userID1 = u1.getUserId();
        User u2 = usersInDB.get(1);
        int userID2 = u2.getUserId();
        
        List<Order> allOrders = or.findAll();
        List<Order> ordersId1 = new ArrayList<>();
        List<Order> ordersId2 = new ArrayList<>();
        for (Order o : allOrders) {
            Client c = o.getClient();
            User u = c.getUser();
            
            if (u.getUserId() == userID1) {
                ordersId1.add(o);
            }
            
            if (u.getUserId() == userID2) {
                ordersId2.add(o);
            }
        }
        
        assertEquals(ordersId1.size(), 1);
        assertEquals(ordersId2.size(), 2);
    }

    /**
     * Test of getOrdersByClientId method, of class OrderService.
     */
    @Test
    public void testGetOrdersByClientId() {
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
        
        User newUser2 = new User();
        newUser2.setUserId(0);
        newUser2.setFirstName("First2");
        newUser2.setLastName("Last2");
        newUser2.setEmailAddress("email2");
        newUser2.setUserRole("Role2");
        newUser2.setUserPassword("Password2");
        newUser2.setDidPasswordChange(true);
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
        newClient1.setEmailAddress("email");
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
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        Order newOrder2 = new Order();
        newOrder2.setOrderId(0);
        newOrder2.setDateSubmitted(LocalDate.now());
        newOrder2.setDateInstalled(LocalDate.now());
        newOrder2.setDateCompleted(LocalDate.now());
        newOrder2.setOrderTotal(BigDecimal.ZERO);
        newOrder2.setOrderStatus("Status");
        newOrder2.setOrderComments("Comments");
        newOrder2.setClient(newClient2);
        or.save(newOrder2);
        
        Order newOrder3 = new Order();
        newOrder3.setOrderId(0);
        newOrder3.setDateSubmitted(LocalDate.now());
        newOrder3.setDateInstalled(LocalDate.now());
        newOrder3.setDateCompleted(LocalDate.now());
        newOrder3.setOrderTotal(BigDecimal.ZERO);
        newOrder3.setOrderStatus("Status");
        newOrder3.setOrderComments("Comments");
        newOrder3.setClient(newClient2);
        or.save(newOrder3);
        
        List<Client> clientsInDB = cr.findAll();
        Client c1 = clientsInDB.get(0);
        int clientID1 = c1.getClientId();
        Client c2 = clientsInDB.get(1);
        int clientID2 = c2.getClientId();
        
        List<Order> allOrders = or.findAll();
        List<Order> ordersId1 = new ArrayList<>();
        List<Order> ordersId2 = new ArrayList<>();
        for (Order o : allOrders) {
            Client c = o.getClient();
            
            if (c.getClientId() == clientID1) {
                ordersId1.add(o);
            }
            
            if (c.getClientId() == clientID2) {
                ordersId2.add(o);
            }
        }
        
        assertEquals(ordersId1.size(), 1);
        assertEquals(ordersId2.size(), 2);
        
    }

    /**
     * Test of getOrdersByCompany method, of class OrderService.
     */
    @Test
    public void testGetOrdersByCompany() {
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
        
        User newUser2 = new User();
        newUser2.setUserId(0);
        newUser2.setFirstName("First2");
        newUser2.setLastName("Last2");
        newUser2.setEmailAddress("email2");
        newUser2.setUserRole("Role2");
        newUser2.setUserPassword("Password2");
        newUser2.setDidPasswordChange(true);
        newUser2.setState(newState);
        ur.save(newUser2);
        
        Client newClient1 = new Client();
        newClient1.setClientId(0);
        newClient1.setContactFirstName("First");
        newClient1.setContactLastName("Last");
        newClient1.setCompanyName("CompanyOne");
        newClient1.setAptUnit("Apt");
        newClient1.setStreetAddress("Street");
        newClient1.setCity("City");
        newClient1.setState(newState);
        newClient1.setZip(12345);
        newClient1.setUser(newUser1);
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Client newClient2 = new Client();
        newClient2.setClientId(0);
        newClient2.setContactFirstName("First");
        newClient2.setContactLastName("Last");
        newClient2.setCompanyName("CompanyTwo");
        newClient2.setAptUnit("Apt");
        newClient2.setStreetAddress("Street");
        newClient2.setCity("City");
        newClient2.setState(newState);
        newClient2.setZip(12345);
        newClient2.setUser(newUser2);
        newClient2.setEmailAddress("email");
        newClient2.setPhoneNumber("1234567890");
        cr.save(newClient2);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        Order newOrder2 = new Order();
        newOrder2.setOrderId(0);
        newOrder2.setDateSubmitted(LocalDate.now());
        newOrder2.setDateInstalled(LocalDate.now());
        newOrder2.setDateCompleted(LocalDate.now());
        newOrder2.setOrderTotal(BigDecimal.ZERO);
        newOrder2.setOrderStatus("Status");
        newOrder2.setOrderComments("Comments");
        newOrder2.setClient(newClient2);
        or.save(newOrder2);
        
        Order newOrder3 = new Order();
        newOrder3.setOrderId(0);
        newOrder3.setDateSubmitted(LocalDate.now());
        newOrder3.setDateInstalled(LocalDate.now());
        newOrder3.setDateCompleted(LocalDate.now());
        newOrder3.setOrderTotal(BigDecimal.ZERO);
        newOrder3.setOrderStatus("Status");
        newOrder3.setOrderComments("Comments");
        newOrder3.setClient(newClient2);
        or.save(newOrder3);
        
        String comp1 = "CompanyOne";
        String comp2 = "CompanyTwo";
        
        List<Order> allOrders = or.findAll();
        List<Order> ordersComp1 = new ArrayList<>();
        List<Order> ordersComp2 = new ArrayList<>();
        for (Order o : allOrders) {
            Client c = o.getClient();
            String company = c.getCompanyName();
            
            if (company.equals(comp1)) {
                ordersComp1.add(o);
            }
            
            if (company.equals(comp2)) {
                ordersComp2.add(o);
            }
        }
        
        assertEquals(ordersComp1.size(), 1);
        assertEquals(ordersComp2.size(), 2);
        
    }

    /**
     * Test of editOrder method, of class OrderService.
     */
    @Test
    public void testEditOrder() {
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
        newClient1.setEmailAddress("email");
        newClient1.setPhoneNumber("1234567890");
        cr.save(newClient1);
        
        Order newOrder1 = new Order();
        newOrder1.setOrderId(0);
        newOrder1.setDateSubmitted(LocalDate.now());
        newOrder1.setDateInstalled(LocalDate.now());
        newOrder1.setDateCompleted(LocalDate.now());
        newOrder1.setOrderTotal(BigDecimal.ZERO);
        newOrder1.setOrderStatus("Status");
        newOrder1.setOrderComments("Comments");
        newOrder1.setClient(newClient1);
        or.save(newOrder1);
        
        List<Order> allOrders = or.findAll();
        int id = 0;
        for (Order o : allOrders) {
            id = o.getOrderId();
        }
        
        Order fromRepo = or.findById(id).orElse(null);
        assertNotNull(fromRepo);
        
        fromRepo.setOrderStatus("New Status");
        fromRepo.setOrderComments("New Comments");
        
        or.save(fromRepo);
        
        Order fromRepoEdited = or.findById(id).orElse(null);
        assertEquals(fromRepo.getOrderId(), fromRepoEdited.getOrderId());
    }
    
}
