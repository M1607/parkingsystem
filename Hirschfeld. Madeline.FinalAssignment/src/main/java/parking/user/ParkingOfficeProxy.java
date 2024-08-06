/**
 * The ParkingOfficeProxy is a proxy for the ParkingOffice
 * class. The proxy is useed to control access to the functionality
 * of the ParkingOffice based on the role of the user.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.user;

import src.main.java.parking.management.ParkingOffice;
import src.main.java.shared.JsonSerializable;

public class ParkingOfficeProxy implements JsonSerializable {
    private ParkingOffice parkingOffice;
    private User user;

    //constructor that accepts user and initializes the real service, ParkingOffice
    public ParkingOfficeProxy (User user) {
        this.user = user;
        this.parkingOffice = ParkingOffice();
    }

    private ParkingOffice ParkingOffice() {
        return null;
    }

    //Call to ParkingOffice's user method that is access by all users
    public void userMethod() {
        parkingOffice.userMethod();
    }

    //Method that checks the user's role and access to admin methods
    //in the ParkingOffice
    public void adminMethod() {
        if(user.getRole() == Role.ADMIN) {
            parkingOffice.adminMethod();
        } else {
            System.out.println("Admin permissions required.");
        }
    }
}