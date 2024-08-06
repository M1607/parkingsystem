/**
 * 
 * DailyChargeDecorator class calculates the carge based on parking
 * lot rate, duration, and discount based on the CarType
 * 
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.parkingCharges;

import src.main.java.parking.customerData.CarType;
import src.main.java.parking.transactionManager.ParkingPermit;

public class DailyChargeDecorator extends BaseParkingCharge {

    private BaseParkingCharge component;

    // Rate for parking lot based on type
    public DailyChargeDecorator(BaseParkingCharge component) {
        super(component.lotType);
        this.component = component;
        this.hoursParked = component.hoursParked;
    }

    // calculates total charge and applies discount based on car type
    @Override
    public Money calculateCharge(EntryTime entryTime, ParkingPermit permit) {
        Money baseChargePerHour = component.calculateCharge(entryTime, permit);
        

        Money totalCharge = baseChargePerHour.multiply(component.hoursParked);

        // Apply the discount based on CarType
        CarType carType = permit.getCarTypeFromPermit();
        double discountPercent = carType.getDiscount() / 100;
        Money discountAmount = totalCharge.multiply(discountPercent);

        return totalCharge.subtract(discountAmount);
    }
}