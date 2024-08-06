/**
 * Enumeration manages types of parking lots and stores daily rate for 
 * each type of parking lot.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.main.java.parking.parkingLotData;

public enum ParkingLotType {
    LOTA(5.00),
    LOTB(10.00),
    LOTC(20.00);

    private double hourlyRate;

    //Construtor intializes the daily rate
    ParkingLotType(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    //mehtod to return daily rate based on parking lot type
    public double getHourlyRate() {
        return hourlyRate;
    }
    
}