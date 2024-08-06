/**
 * PermitManager class manages permits in the parking lot system.
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.transactionManager;

import src.main.java.shared.JsonSerializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import src.main.java.parking.customerData.Car;

public class PermitManager implements JsonSerializable {
   
    private HashMap<String, ParkingPermit> permits = null;

    //Default constructor that doesn't take any parameters and creates
    //object based on default values
    public PermitManager() {
        permits = new HashMap<String, ParkingPermit>();

    }

    // Constructor for PermitManager class. The constructor will set the paramaters
    // while creating the instance
    //
    // @param permits
    //
    // Validation logic in the constructor method to make sure necessary
    // fields are not empty before creating the permits object.
    public PermitManager(HashMap<String, ParkingPermit> permits) {
        if (permits == null || permits.isEmpty()) {
            throw new IllegalArgumentException("Permtis cannot be empty.");
        }
        this.permits = permits;
    }

    //Getters and Setters
    public HashMap<String, ParkingPermit> getPermits() {
        return permits;
    }

    public void setPermits(HashMap<String, ParkingPermit> permits) {
        if (permits == null || permits.isEmpty()) {
            throw new IllegalArgumentException("Permtis cannot be empty.");
        }
        this.permits = permits;
    }

    //Method creates ParkingPermit object and adds it to the colelction
    public ParkingPermit register(Car car) {
            
            ParkingPermit permit = new ParkingPermit();
            permit.setPermitId(UUID.randomUUID().toString());
            permit.setCar(car);
            
            Calendar expirationDate = Calendar.getInstance();
            expirationDate.add(Calendar.YEAR, 1);
            
            permit.setExpirationDate(expirationDate);
            permit.setRegistrationDate(Calendar.getInstance());
            
            permits.put(car.getLicense(), permit);
            
            return permit;
	}
}