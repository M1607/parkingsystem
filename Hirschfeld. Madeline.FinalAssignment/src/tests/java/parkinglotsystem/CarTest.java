/**
 * The Customer class helps represent a customer entity and has methods that
 * provide customer related information.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.tests.java.parkinglotsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import src.main.java.parking.customerData.*;

public class CarTest {
        @Test
        public void testToString() {

        Car car = new Car();
        car.setLicense("9827X7");
        car.setType(CarType.COMPACT);
        car.setOwner("Maddie Hirschfeld");

        String expectedOutput = "License Number: 9827X7\n" +
                                "Type of Car: COMPACT\n" +
                                "Owner of Car: Maddie Hirschfeld\n";

        //Compare the result to expected result
        String actualOutput = car.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCarConstructorValidArguments() {
        Car car = new Car("9JH876", CarType.SUV, "Kelly Treinen");
        assertNotNull(car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCarConstructorInvalidLicense() {
        new Car("", CarType.COMPACT, "John Doe");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCarConstructorInvalidType() {
        new Car("5554RT", null, "Theo Edwards");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCarConstructorInvalidOwner() {
        new Car("996TH8", CarType.SUV, "");
    }

    @Test
    public void testFetchCarType() {
        Car car = new Car("8YHGT", CarType.COMPACT, "Maddie Tryon");
        CarType actualCarType = car.getType();
        
        assertEquals(CarType.COMPACT, actualCarType);
    }
}