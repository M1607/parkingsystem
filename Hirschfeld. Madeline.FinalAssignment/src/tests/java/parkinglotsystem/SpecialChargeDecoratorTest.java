/**
 * SpecialChargeStrategyTest class tests the main functionalites,
 * including calculation of charges based on the fixed fee and days parked.
 * No discount is applied.
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.tests.java.parkinglotsystem;

import src.main.java.parking.customerData.*;
import src.main.java.parking.parkingCharges.*;
import src.main.java.parking.parkingLotData.*;
import src.main.java.parking.transactionManager.ParkingPermit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SpecialChargeDecoratorTest {

    private EntryTime twoDayEntryTime;
    private Car discountCar;
    private ParkingPermit discountPermit;

    @Before
    public void setUp() {
        new ParkingLot(ParkingLotType.LOTA);
        twoDayEntryTime = new EntryTime() {
            @Override
            public long getTotalHours() {
                return 25;
            }
        };

        discountCar = new Car();
        discountCar.setType(CarType.COMPACT);
        discountPermit = new ParkingPermit("MH786T", discountCar, null, null);
    }

    @Test
    public void testCalculateChargeWithNoDiscountApplied() {
        BaseParkingCharge baseCharge = new BaseParkingDecorator(ParkingLotType.LOTA);
        SpecialChargeDecorator strategy = new SpecialChargeDecorator(baseCharge);

        Money result = strategy.calculateCharge(twoDayEntryTime, discountPermit);

        Money expectedCharge = new Money(100);
        assertEquals(expectedCharge, result);
    }
}