/**
 * The ParkingOfficeTest class test the methods in the ParkingOffice class,
 * including 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 8, 2023)
 */

package src.tests.java.parkinglotsystem;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.customerData.*;
import src.main.java.parking.management.*;

public class ParkingOfficeTest {
    private ParkingOffice parkingOffice;

    @Before
    public void setUp() {
        parkingOffice = new ParkingOffice();
    }

    @Test
    public void testRegisterCustomer() {
        Customer customer = new Customer.CustomerBuilder("Maddie", "Hirschfeld",
                new Address.AddressBuilder("8953 Pine St.", "Telluride", "CO", "81435")
                        .streetAddress2("Unit 5")
                        .build())
                .phoneNumber("317-555-7653")
                .build();

        parkingOffice.registerCustomer(customer);
        assertEquals(customer, parkingOffice.getCustomer(customer.getCustomerId()));
    }

    @Test
    public void testRemoveCustomer() {
        Customer customer = new Customer.CustomerBuilder("Toby", "Flenderson",
                new Address.AddressBuilder("895 Maple St.", "Scranton", "PA", "16543")
                        .streetAddress2("Unit 4")
                        .build())
                .phoneNumber("555-987-6543")
                .build();

        parkingOffice.registerCustomer(customer);

        Customer removedCustomer = parkingOffice.removeCustomer(customer);
        assertNull(parkingOffice.getCustomer(customer.getCustomerId()));
        assertEquals(customer, removedCustomer);
    }
}