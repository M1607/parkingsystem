/**
 * The ParkingPermit class models a parking permit with in the
 * university parking system.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.transactionManager;

import src.main.java.shared.JsonSerializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import src.main.java.parking.customerData.*;

public class ParkingPermit implements JsonSerializable {
    //declare variables
    private String permitId;
    private Car car;
    private Calendar expirationDate;
    private Calendar registraitonDate;
    private Customer customer;

    //Default constructor that doesn't take any parameters and creates
    //object based on default values
    public ParkingPermit() {

    }
    
    public ParkingPermit(String permitId, Car car, Calendar expirationDate, Calendar registrationDate) {
        if(permitId == null || permitId.isEmpty()) {
            throw new IllegalArgumentException("Permit ID cannot be empty.");
        }

        this.permitId = permitId;
        this.car = car;
        this.expirationDate = expirationDate;
        this.registraitonDate = registrationDate;
    }

    //Getters and setters
    // Validation logic is provided for setters.
    public String getPermitId() {
        return permitId;
    }
    
    public void setPermitId(String permitId) {
        if(permitId == null || permitId.isEmpty()) {
            throw new IllegalArgumentException("Permit ID cannot be empty.");
        }
        this.permitId = permitId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be empty.");
        }
        this.car = car;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Calendar getRegistrationDate () {
        return registraitonDate;
    }

    public void setRegistrationDate(Calendar registrationDate) {
        this.registraitonDate = registrationDate;
    }

    public CarType getCarTypeFromPermit() {
        return car.getType();
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //Method that converts date from calendar to string (mm/dd/yyyy)
    public String getDateString(Calendar date) {
        String dateString = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
        dateString = sdf.format(date.getTime());

        return dateString;
    }
}