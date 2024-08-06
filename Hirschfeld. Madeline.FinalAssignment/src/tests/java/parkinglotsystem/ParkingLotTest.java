/**
 * 
 * The ParkingLotTest verifies the functionaliy of ParkingLot.
 *
 * @author (Maddie Hirschfeld)
 * @version (October 15, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import src.main.java.parking.customerData.*;
import src.main.java.parking.parkingCharges.*;
import src.main.java.parking.parkingLotData.*;
import src.main.java.parking.transactionManager.*;

import java.util.Calendar;

public class ParkingLotTest {
    private ParkingLot parkingLot;
    private TransactionManager transactionManager;
    private ConcreteParkingObserver observer;

    @Before
    public void setUp() {
        transactionManager = new TransactionManager(new ParkingChargeStrategyFactory());
        observer = new ConcreteParkingObserver(transactionManager);
        parkingLot = new ParkingLot("L-896", "Mountain Lot", new Address("897 Aspen St.", "Mountain Village", "CO", "81435"), 45, ParkingLotType.LOTB);
        parkingLot = new ParkingLot(observer); 
    }

    @Test
    public void testCarEntry() {
        Car car = new Car("XY7635", CarType.COMPACT, "Maddie Hirsch");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("98787", car, expirationDate, registrationDate);

        parkingLot.carEntry(ParkingLotType.LOTB, permit);

        assertEquals(2, transactionManager.getTransactions().size());
    }

     @Test
    public void testCarExit() {
        Car car = new Car("XY7635", CarType.COMPACT, "Maddie Hirsch");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("98787", car, expirationDate, registrationDate);

        parkingLot.carExit(ParkingLotType.LOTB, permit, new EntryTime());

        assertEquals(2, transactionManager.getTransactions().size());
    }
}