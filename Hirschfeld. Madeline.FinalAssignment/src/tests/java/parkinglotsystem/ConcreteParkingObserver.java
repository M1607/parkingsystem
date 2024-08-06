/**
 * 
 * The ConcreteParkingObserver class is created so that we can 
 * test the update method in the ParkingObserver
 *
 * @author (Maddie Hirschfeld)
 * @version (October 15, 2023)
 */
package src.tests.java.parkinglotsystem;

import src.main.java.parking.management.*;
import src.main.java.parking.transactionManager.TransactionManager;

public class ConcreteParkingObserver extends ParkingObserver {

    public ConcreteParkingObserver(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public void update(ParkingEvent parkingEvent) {
        getTransactionManager().park(parkingEvent);
    }
}