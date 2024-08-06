/**
 * The CustomerTest class tests customer creation, adding a car, and converting
 * customer information into a string.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.customerData.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CustomerTest {

    private Customer customer;
    private Car car;
    private Address address;

    @Before
    public void setUp() {
        address = new Address.AddressBuilder("897 Galena Rd.", "Silverton", "CO", "87654")
                .build();
        customer = new Customer.CustomerBuilder("Maddie", "Hirsch", address)
                .phoneNumber("877-1140")
                .build();
        car = new Car();
    }

    @Test
    public void testCustomerCreation() {
        assertNotNull("Customer should not be null", customer);
        assertEquals("Customer's first name should be Maddie", "Maddie", customer.getFirstName());
        assertEquals("Customer's last name should be Hirsch", "Hirsch", customer.getLastName());
        assertEquals("Customer's phone number should be 877-1140", "877-1140", customer.getPhoneNumber());
        assertEquals("Customer's address should be set", address, customer.getAddress());
    }

    @Test
    public void testAddCar() {
        customer.addCar(car);
        assertEquals("Customer should have 1 car", 1, customer.getCars().size());
        assertTrue("Customer's car list should contain the car", customer.getCars().contains(car));
    }

    @Test
    public void testCustomerToString() {
        String customerInfo = customer.toString();
        assertNotNull("toString should not return null", customerInfo);
        assertTrue("toString should contain the first name", customerInfo.contains(customer.getFirstName()));
        assertTrue("toString should contain the last name", customerInfo.contains(customer.getLastName()));
    }
}