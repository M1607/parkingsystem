/**
 * The ParkingTransaction class manages parking transaction within the
 * university parking system. The class stores the permit and lot the
 * car is in.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.parkingCharges;

import java.util.UUID;

import src.main.java.parking.parkingLotData.ParkingLot;
import src.main.java.parking.transactionManager.ParkingPermit;
import src.main.java.shared.JsonSerializable;

public class ParkingTransaction implements JsonSerializable{
    //declare variables
    private ParkingPermit permit;
    private ParkingLot lot;
    private String transactionId;

    //Default constructor that doesn't take any parameters and creates
    //object based on default values
    public ParkingTransaction() {
        this.transactionId = UUID.randomUUID().toString();
    }

    //builder pattern to create transaction
    public static class TransactionBuilder {
        //required parameters
        private final ParkingPermit permit;
        private final ParkingLot lot;

        //no optional parameters

        public TransactionBuilder(ParkingPermit permit, ParkingLot lot){
            this.permit = permit;
            this.lot = lot;
        }

        public ParkingTransaction build() {
            ParkingTransaction transaction = new ParkingTransaction();
            transaction.permit = permit;
            transaction.lot = lot;
            return transaction;
        }
    }

    public ParkingTransaction(TransactionBuilder builder) {
        this.permit= builder.permit;
        this.lot = builder.lot;
    }

    //Getters and setters
    // Validation logic is provided for setters.
    public ParkingPermit getPermit() {
        return permit;
    }

    public void setPermit(ParkingPermit permit) {
        if(permit == null) {
            throw new IllegalArgumentException("Permit cannot be empty.");
        }
        this.permit = permit;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public void setLot(ParkingLot lot) {
        if(lot == null) {
            throw new IllegalArgumentException("Parking lot cannot be empty.");
        }
    }

    public String getTransactionId() {
        return transactionId;
    }

    //Method to calculate transaction based on the provided strategy from ParkingChargeStrategy
    public Money getChargedAmount(BaseParkingCharge baseCharge) {
        EntryTime entryTime = new EntryTime();
        return baseCharge.calculateCharge(entryTime, permit);
    }
}