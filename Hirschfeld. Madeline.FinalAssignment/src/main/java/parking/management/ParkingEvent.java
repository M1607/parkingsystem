/**
 * The ParkingLot class manages cars entering and exiting the parking lot.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.parking.management;

import src.main.java.parking.parkingCharges.EntryTime;
import src.main.java.parking.parkingLotData.ParkingLotType;
import src.main.java.parking.transactionManager.ParkingPermit;
import src.main.java.shared.JsonSerializable;

public class ParkingEvent implements JsonSerializable{
    private ParkingLotType lotType;
    private EntryTime entryTime;
    private EntryTime exitTime;
    private ParkingPermit permit;

    // constructor for when car enters parking lot
    public ParkingEvent(ParkingLotType lotType, ParkingPermit permit) {
        this.lotType = lotType;
        this.permit = permit;
        this.entryTime = new EntryTime();
    }

    // constructor when care exits a parking lot
    public ParkingEvent(ParkingLotType lotType, ParkingPermit permit, EntryTime entryTime) {
        this.lotType = lotType;
        this.permit = permit;
        this.entryTime = entryTime;
        this.exitTime = new EntryTime();
    }

    //Getters & Setters
    public ParkingLotType getLotType() {
        return lotType;
    }

    public void setLot(ParkingLotType lotType) {
        this.lotType = lotType;
    }

    public EntryTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(EntryTime entryTime) {
        this.entryTime = entryTime;
    }

    public EntryTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(EntryTime exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingPermit getPermit() {
        return permit;
    }

    public void setPermit(ParkingPermit permit) {
        this.permit = permit;
    }
}