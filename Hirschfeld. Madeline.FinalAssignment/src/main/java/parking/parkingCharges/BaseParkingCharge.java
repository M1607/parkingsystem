/**
 * 
 * BaseParkingCharge represents the basic charge for
 * the parking system
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.parkingCharges;


import src.main.java.parking.parkingLotData.ParkingLotType;
import src.main.java.parking.transactionManager.ParkingPermit;

public abstract class BaseParkingCharge {
    protected ParkingLotType lotType;
    protected long hoursParked;

    public BaseParkingCharge(ParkingLotType lotType) {
        this.lotType = lotType;
    }

    public void setHoursParked(EntryTime entryTime) {
        this.hoursParked = entryTime.getTotalHours();
    }

    public abstract Money calculateCharge(EntryTime entryTime, ParkingPermit parkingPermit);
}