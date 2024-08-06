/**
 * 
 * ParkingChargeStrategyFacotry creates instances of parking charge 
 * strategies.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.parking.parkingCharges;

import src.main.java.parking.parkingLotData.ParkingLotType;
import src.main.java.shared.JsonSerializable;

public class ParkingChargeStrategyFactory implements JsonSerializable {

    public static BaseParkingCharge createWeekendChargeDecorator(ParkingLotType lotType) {
            BaseParkingCharge baseCharge = new BaseParkingDecorator(lotType);
            return new WeekendChargeDecorator(new DailyChargeDecorator(baseCharge));
    }

    public static BaseParkingCharge createDailyChargeDecorator(ParkingLotType lotType) {
            BaseParkingCharge baseCharge = new BaseParkingDecorator(lotType);
            return new DailyChargeDecorator(baseCharge);
        }

    public static BaseParkingCharge createSpecialChargeDecorator(ParkingLotType lotType) {
        BaseParkingCharge baseCharge = new BaseParkingDecorator(lotType);
        return new SpecialChargeDecorator(baseCharge);
    }
}