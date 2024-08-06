/**
 * DailyChargeStrategyTest class tests the main functionalites,
 * including calculation of charges based on parking lot, hours
 * parked, and discount based on car type.
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

public class DailyChargeDecoratorTest {

    private ParkingLot lotB;
    private EntryTime oneHourEntryTime;
    private Car noDiscountCar;
    private Car discountCar;
    private ParkingPermit noDiscountPermit;
    private ParkingPermit discountPermit;
    private BaseParkingCharge baseCharge;

    @Before
    public void setUp() {
        lotB = new ParkingLot(ParkingLotType.LOTB);
        oneHourEntryTime = new EntryTime() {
            @Override
            public long getTotalHours() {
                return 1;
            }
        };

        noDiscountCar = new Car();
        noDiscountCar.setType(CarType.SUV);
        noDiscountPermit = new ParkingPermit("97XY3", noDiscountCar, null, null);

        discountCar = new Car();
        discountCar.setType(CarType.ELECTRIC);
        discountPermit = new ParkingPermit("16XYU24", discountCar, null, null);

        baseCharge = new BaseParkingDecorator(lotB.getParkingLotType());
    }

    @Test
    public void testCalculateChargeWithoutDiscountOnWeekday() {
        DailyChargeDecorator strategy = new DailyChargeDecorator(baseCharge);

        Money result = strategy.calculateCharge(oneHourEntryTime, noDiscountPermit);

        Money expectedCharge = baseCharge.calculateCharge(oneHourEntryTime, noDiscountPermit).multiply(oneHourEntryTime.getTotalHours());
        assertEquals(expectedCharge, result);
    }

    @Test
    public void testCalculateChargeWithDiscountOnWeekday() {
        DailyChargeDecorator strategy = new DailyChargeDecorator(baseCharge);

        Money result = strategy.calculateCharge(oneHourEntryTime, discountPermit);

        Money baseChargeAmount = baseCharge.calculateCharge(oneHourEntryTime, discountPermit).multiply(oneHourEntryTime.getTotalHours());
        double discountRate = discountPermit.getCarTypeFromPermit().getDiscount() / 100.0;
        Money discountAmount = baseChargeAmount.multiply(discountRate);
        Money expectedCharge = baseChargeAmount.subtract(discountAmount);
        assertEquals(expectedCharge, result);
    }
}