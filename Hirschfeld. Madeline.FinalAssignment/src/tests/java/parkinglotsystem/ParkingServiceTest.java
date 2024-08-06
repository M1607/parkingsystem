/**
 * The ParkingServiceTest class test the commands and
 * whether they are valid or invalid.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import src.main.java.server.*;
import src.main.java.parking.management.*;

public class ParkingServiceTest {

    private ParkingService parkingService;

    @Before
    public void setUp() {
        ParkingOffice parkingOffice = new ParkingOffice();
        parkingService = new ParkingService(parkingOffice, null);
    }

    @Test
    public void testPerformValidCommand() {
        String commandName = "customer_registration";
        String[] parameters = {
                "firstName=Maddie",
                "lastName=Hirschfeld",
                "customerId=1234",
                "streetAddress1=99 Columbine St.",
                "city=Durango",
                "state=CO",
                "zipCode=85514",
                "phoneNumber=317-777-7854"
        };
        String result = parkingService.performCommand(commandName, parameters);

        assertEquals("Success! Maddie Hirschfeld is registered.", result);
    }

    @Test
    public void testPerformInvalidCommand() {
        String commandName = "wrongCommand";
        String[] parameters = { "param=value" };

        String result = parkingService.performCommand(commandName, parameters);
        assertEquals("wrongCommand is not a valid command.", result);
    }

    @Test
    public void testPerformInvalidParameterFormat() {
        String commandName = "registerCar";
        String[] parameters = { "paramWithNoEqualsSign" };

    
        String result = parkingService.performCommand(commandName, parameters);
        assertEquals("registerCar is not a valid command.", result);
    }
}