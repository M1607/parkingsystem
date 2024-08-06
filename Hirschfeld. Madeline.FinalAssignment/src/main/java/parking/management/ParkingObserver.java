/**
 * 
 * ParkingObserver class observes parking events and communicates
 * them to the TransactionManager.
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 15, 2023)
 */
package src.main.java.parking.management;

import src.main.java.parking.transactionManager.TransactionManager;

public abstract class ParkingObserver {

    // constructor
    // initalizes observer with TransactionManager instance
    private TransactionManager transactionManager;

    public ParkingObserver(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    // update method that is called when a parking event occurs
    public abstract void update(ParkingEvent parkingEvent);

    //allows observer access to the Transactionmanager
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
}