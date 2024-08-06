/**
 * The Car class repersents the cars in the parking system and has methods
 * to retrieve and display car information.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.customerData;

import src.main.java.shared.JsonSerializable;
import java.util.Objects;

public class Car implements JsonSerializable {
    // Declare variables
    private String license;
    private CarType type;
    private String owner;

    // Default constructor that doesn't take any parameters and creates
    // object based on default values
    public Car() {

    }

    // Constructor for car class. The constructor will set the paramaters
    // while creating the instance
    //
    // @param license
    // @param type
    // @param owner
    //
    // Validation logic in the constructor method to make sure necessary
    // fields are not empty before creating the car object.
    public Car(String license, CarType type, String customer) {
        if (license == null || license.isEmpty()) {
            throw new IllegalArgumentException("License cannot be empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Car type cannot be empty.");
        }
        if (customer == null || customer.isEmpty()) {
            throw new IllegalArgumentException("Owner cannot be empty.");
        }
        this.license = license;
        this.type = type;
        this.owner = customer;
    }

    // Getters and setters for license, type, and owner.
    // There is validation logic for setter methods.
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        if (license == null || license.isEmpty()) {
            throw new IllegalArgumentException("License cannot be empty.");
        }
        this.license = license;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        if (type == null) {
            throw new IllegalArgumentException("Type of car cannot be empty.");
        }
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if (owner == null || owner.isEmpty()) {
            throw new IllegalArgumentException("Owner cannot be empty.");
        }
        this.owner = owner;
    }

    // Create string of license, car, and ownder
    @Override
    public String toString() {
        return "License Number: " + license + "\n" +
                "Type of Car: " + type + "\n" +
                "Owner of Car: " + owner + "\n";
    }

    // compares car objects based on license value
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Car car = (Car) obj;
        return Objects.equals(license, car.license) &&
                type == car.type &&
                Objects.equals(owner, car.owner);
    }

    // Generates a hash code based on license value
    @Override
    public int hashCode() {
        return Objects.hash(license);
    }

    public void add(Car car) {
    }

}