/**
 * WeekendChargeStrategyTest class tests the main functionalites,
 * including calculation of charges based on parking lot, hours
 * parked, and discount based on car type.
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.tests.java.parkinglotsystem;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.customerData.*;
import src.main.java.parking.parkingCharges.*;
import src.main.java.parking.parkingLotData.*;
import src.main.java.parking.transactionManager.ParkingPermit;

public class WeekendChargeDecoratorTest {

    private ParkingLot lotA;
    private EntryTime oneHourEntryTime;
    private Car noDiscountCar;
    private ParkingPermit noDiscountPermit;

    @Before
    public void setUp() {
        lotA = new ParkingLot(ParkingLotType.LOTA);
        oneHourEntryTime = new EntryTime() {
            @Override
            public long getTotalHours() {
                return 2;
            }
        };

        noDiscountCar = new Car();
        noDiscountCar.setType(CarType.SUV);
        noDiscountPermit = new ParkingPermit("98HYT", noDiscountCar, null, null);

    }

    @Test
    public void testCalculateChargeWithoutDiscount() {
        BaseParkingCharge baseCharge = new BaseParkingDecorator(ParkingLotType.LOTA);
        WeekendChargeDecorator strategy = new WeekendChargeDecorator(baseCharge);

        Money result = strategy.calculateCharge(oneHourEntryTime, noDiscountPermit);

        Money expectedCharge = new Money(4 * lotA.getDailyRate(), 0);
        assertEquals(expectedCharge, result);
    }
}