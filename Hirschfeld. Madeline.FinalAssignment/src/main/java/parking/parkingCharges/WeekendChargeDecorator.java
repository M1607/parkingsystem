/**
 * 
 * WeekendChargeStrategy class calculates the parking charge
 * based on the weekend rate and applies a discount based on car 
 * type.
 * 
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.parkingCharges;

import src.main.java.parking.transactionManager.ParkingPermit;

public class WeekendChargeDecorator extends DailyChargeDecorator {

    // double the base/fixed daily rate of parking lot
    public WeekendChargeDecorator(BaseParkingCharge component) {
        super(component);
    }

    // calculates total charge and applies discount based on car type
    @Override
    public Money calculateCharge(EntryTime entryTime, ParkingPermit permit) {
        Money dailyCharge = super.calculateCharge(entryTime, permit);

        //doubles the daily charge for the weekend
        Money weekendCharge = dailyCharge.multiply(2);

        return weekendCharge;
    }
}