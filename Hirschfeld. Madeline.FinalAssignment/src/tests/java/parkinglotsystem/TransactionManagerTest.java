/**
 * 
 * The TransactionManagerTest verifies the functionaliy of TransactionManager.
 *
 * @author (Maddie Hirschfeld)
 * @version (October 15, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.customerData.*;
import src.main.java.parking.management.ParkingEvent;
import src.main.java.parking.parkingCharges.*;
import src.main.java.parking.parkingLotData.ParkingLotType;
import src.main.java.parking.transactionManager.*;


import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;

public class TransactionManagerTest {
    private TransactionManager transactionManager;

    @Before
    public void setUp() throws Exception {
        transactionManager = new TransactionManager(new ParkingChargeStrategyFactory());
    }

    @Test
    public void testPark() {
        Car car = new Car("98YYS", CarType.COMPACT, "Mick Hirsch");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("9987", car, expirationDate, registrationDate);
        ParkingEvent event = new ParkingEvent(ParkingLotType.LOTB, permit);

        ParkingTransaction transaction = transactionManager.park(event);

        ArrayList<ParkingTransaction> transactions = transactionManager.getTransactions();

        assertEquals(1, transactions.size());
        assertEquals(transaction, transactions.get(0));
    }

    @Test
    public void testGetParkingChargesForPermit() {
        Car car = new Car("029BH", CarType.COMPACT, "Mick Tyron");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("93875", car, expirationDate, registrationDate);
        ParkingEvent event = new ParkingEvent(ParkingLotType.LOTB, permit);

        transactionManager.park(event);
        transactionManager.calculateParkingCharge(DayOfWeek.MONDAY);

        Money expectedCharge = new Money(10.0, 0);
        Money calculatedCharge = transactionManager.getParkingCharges(permit, 
            ParkingChargeStrategyFactory.createDailyChargeDecorator(ParkingLotType.LOTB));

        assertEquals(expectedCharge, calculatedCharge);
    }

    @Test
    public void testCalculateParkingCharge() {
        Car car = new Car("238UX", CarType.COMPACT, "Maddie Tryon");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("83276", car, expirationDate, registrationDate);
        ParkingEvent event = new ParkingEvent(ParkingLotType.LOTA, permit);

        transactionManager.park(event);

        transactionManager.calculateParkingCharge(DayOfWeek.SATURDAY);

        Money expectedCharge = new Money(10.0, 0);
        Money calculatedCharge = transactionManager.getParkingCharges(permit, 
            ParkingChargeStrategyFactory.createDailyChargeDecorator(ParkingLotType.LOTA));

        assertEquals(expectedCharge, calculatedCharge);
    }

}