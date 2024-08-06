/**
 * 
 * The ParkingObserverTest makes sure that the update method is called 
 * with a ParkingEvent and is recorded in the TransactionManager.
 *
 * @author (Maddie Hirschfeld)
 * @version (October 15, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import src.main.java.parking.customerData.*;
import src.main.java.parking.management.*;
import src.main.java.parking.parkingLotData.*;
import src.main.java.parking.transactionManager.*;
import src.main.java.parking.parkingCharges.*;

import java.util.Calendar;

public class ParkingObserverTest {
    private TransactionManager transactionManager;
    private ConcreteParkingObserver observer;

    @Before
    public void setUp() {
        transactionManager = new TransactionManager(new ParkingChargeStrategyFactory());
        observer = new ConcreteParkingObserver(transactionManager);
    }

    @Test
    public void testObserverUpdate() {
        Car car = new Car("987YUH", CarType.COMPACT, "Maddie Hirsch");
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add(Calendar.DAY_OF_MONTH, 30);
        Calendar registrationDate = Calendar.getInstance();
        ParkingPermit permit = new ParkingPermit("9386", car, expirationDate, registrationDate);
        ParkingEvent event = new ParkingEvent(ParkingLotType.LOTC, permit);

        observer.update(event);

        assertEquals(1, transactionManager.getTransactions().size());
    }
}