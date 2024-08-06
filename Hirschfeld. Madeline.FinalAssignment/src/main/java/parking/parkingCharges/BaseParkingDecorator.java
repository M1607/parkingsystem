/**
 * 
 * StandardParkingDecorator is a concrete sublcase of 
 * BaseParkingCharge
 * 
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.parkingCharges;


import src.main.java.parking.parkingLotData.ParkingLotType;
import src.main.java.parking.transactionManager.ParkingPermit;

public class BaseParkingDecorator extends BaseParkingCharge {
    
    private double hourlyRate;

    public BaseParkingDecorator(ParkingLotType lotType){
        super(lotType);
        this.hourlyRate = lotType.getHourlyRate();
    }

    @Override
    public Money calculateCharge(EntryTime entryTime, ParkingPermit parkingPermit) {
        Money rate = new Money(hourlyRate);
        return rate.multiply(hoursParked);
    }
}