/**
 * The enumeration manages information on the type of car
 * that the customer has. It also stores the discount the
 * customer receives based on the type of car.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

 package src.main.java.parking.customerData;

public enum CarType {
    COMPACT(20),
    SUV(0),
    ELECTRIC(20),
    TWOWHEEL(20);

    private int discount;

    //Initialize discount percentage for each type of car
    CarType(int discount) {
        if(discount < 0 || discount > 100) {
            throw new IllegalArgumentException ("Discount needs to be between 0 and 100.");
        }
        this.discount = discount;
    }

    //Method returns the discount percentage based on the
    //type of car

    public double getDiscount() {
        return discount;
    }
}