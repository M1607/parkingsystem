/**
 * The RegisterCarCommandTest tests the command of
 * registering a car
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Properties;

import src.main.java.parking.management.*;

public class RegisterCarCommandTest {
    private ParkingOffice office;
    private RegisterCarCommand registerCarCommand;

    @Before
    public void setUp() {
        office = new ParkingOffice();
        registerCarCommand = new RegisterCarCommand(office);
    }



    @Test
    public void testExecute_RegisterCar_NoOwner() {
        Properties params = new Properties();
        params.setProperty("license", "9827DX");
        params.setProperty("type", "COMPACT");

        String result = registerCarCommand.execute(params);

        assertEquals("Parameter check failed: Owner of Car parameter is missing.", result);
    }
}