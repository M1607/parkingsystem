/**
 * The RegisterCustomerCommandTest tests the command of registering a customer.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.tests.java.parkinglotsystem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.Properties;

import src.main.java.parking.management.*;


public class RegisterCustomerCommandTest {
    private ParkingOffice parkingOffice;
    private RegisterCustomerCommand registerCustomerCommand;

    @Before
    public void setUp() {
        parkingOffice = new ParkingOffice();
        registerCustomerCommand = new RegisterCustomerCommand(parkingOffice);
    }

    @Test
    public void testExecute_RegisterCustomer() {
        Properties params = new Properties();
        params.setProperty("firstName", "Maddie");
        params.setProperty("lastName", "Hirschfeld");
        params.setProperty("customerId", "7686");
        params.setProperty("streetAddress1", "987 Pine Lane");
        params.setProperty("city", "Durango");
        params.setProperty("state", "CO");
        params.setProperty("zipCode", "85514");
        params.setProperty("phoneNumber", "317-999-4852");

        String result = registerCustomerCommand.execute(params);

        assertEquals("Success! Maddie Hirschfeld is registered.", result);
    }
}