/**
 * The CustomerServiceTest class tests the methods in the Customer Class, including
 * changeAddress, notifyCarRegistered, createCustomer, and addCar.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.customerData.*;
import src.main.java.parking.management.CustomerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CustomerServiceTest {

    private src.main.java.parking.customerData.Customer customer;
    private Address address;
    private Car car;

    @Before
    public void setUp() {
        address = new Address.AddressBuilder("5678 Oak St.", "Telluride", "CO", "81435")
                .streetAddress2("Unit 5")
                .build();
        
        customer = CustomerService.createCustomer("Maddie", "Hirsch", address, "317-877-1140");
        
        car = new Car();
    }

    @Test
    public void testCreateCustomer() {
        assertNotNull("Customer should not be null", customer);
        assertEquals("Expected first name to match", "Maddie", customer.getFirstName());
        assertEquals("Expected last name to match", "Hirsch", customer.getLastName());
        assertEquals("Expected phone number to match", "317-877-1140", customer.getPhoneNumber());
        assertEquals("Expected address to match", address, customer.getAddress());
    }

    @Test
    public void testChangeAddress() {
        // Create a new address
        Address newAddress = new Address.AddressBuilder("678 Pine Circle", "Telluride", "CO", "81435")
                .build();

        // Change the customer's address
        CustomerService.changeAddress(customer, newAddress);

        // Retrieve the updated address from the customer and verify it's been changed
        Address updatedAddress = customer.getAddress();
        assertNotNull("Address should not be null after changing", updatedAddress);
        assertEquals("Expected street address to match after changing", "678 Pine Circle", updatedAddress.getStreetAddress1());
    }

    @Test
    public void testNotifyCarRegistered() {
        String message = CustomerService.notifyCarRegistered(customer, car);
        String expectedMessage = customer.getFirstName() + " " + customer.getFirstName() + " registered car to their account.";
        assertEquals("Expected message to match", expectedMessage, message);
    }

    @Test
    public void testAddCar() {
        CustomerService.addCar(customer, car);
        assertEquals("Expected number of cars to be 1 after adding a car", 1, customer.getCars().size());
        assertEquals("Expected the added car to be the same as car", car, customer.getCars().get(0));
    }
}