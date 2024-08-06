/**
 * 
 * SpecialChargeDecorator class calculates the parking charge
 * for special events where discounts are not applied based on the car
 * it is just a flat fee for the day.
 * 
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.parkingCharges;

import src.main.java.parking.transactionManager.ParkingPermit;

public class SpecialChargeDecorator extends BaseParkingCharge {

    private final Money rate = new Money(50, 0);
    private BaseParkingCharge component;

    // base/fixed rate per day
    public SpecialChargeDecorator(BaseParkingCharge component) {
        super(component.lotType);
        this.component = component;
    }

    // calculates total charge based on rate per day. No discount based on cars
    // when there is a special event
    @Override
    public Money calculateCharge(EntryTime entryTime, ParkingPermit permit) {
        long hoursParked = entryTime.getTotalHours();
        // Divide by 24hours and rounds up
        long daysParked = (long) Math.ceil((double) hoursParked / 24);
        Money totalCharge = rate.multiply(daysParked);

        return totalCharge;
    }
}