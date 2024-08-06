/**
 * The ParkingLot class manages cars entering and exiting the parking lot.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.parking.parkingLotData;

import src.main.java.shared.JsonSerializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import src.main.java.parking.customerData.*;
import src.main.java.parking.management.*;
import src.main.java.parking.parkingCharges.EntryTime;
import src.main.java.parking.transactionManager.ParkingPermit;

public class ParkingLot implements JsonSerializable {
    // declare variables
    private String id;
    private String name;
    private Address address;
    private int capacity;
    private ParkingLotType lotType;
    private ArrayList<Car> cars;
    private ParkingObserver parkingObserver;

    //Default constructor that doesn't take any parameters and creates
    //object based on default values
    public ParkingLot() {

    }
    
   // Constructor for ParkingLot class. The constructor will set the paramaters
    // while creating the instance
    //
    // @param id
    // @param name
    // @param address
    // @param capactiy
    //
    // Validation logic in the constructor method to make sure necessary
    // fields are not empty before creating the parkinglot object.
    public ParkingLot(String id, String name, Address address, int capacity, 
    ParkingLotType parkingLotType) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.cars = new ArrayList<Car>();
        this.parkingLotType = parkingLotType;

    }

    //constructor takes ParkingObserver in
    public ParkingLot(ParkingObserver parkingObserver) {
        this.parkingObserver = parkingObserver;
        this.cars = new ArrayList<>();
    }

    //method for car entering parking lot
    public void carEntry(ParkingLotType lotType, ParkingPermit permit) {
        EntryTime entryTime = new EntryTime();
        ParkingEvent event = new ParkingEvent(lotType, permit, entryTime);

        //notifies ParkingObserver
        parkingObserver.update(event);

        //calls TransactionManager park() to register charge
        parkingObserver.getTransactionManager().park(event);
    }

    //method for car exiting the parking lot
    public void carExit(ParkingLotType lotType, ParkingPermit permit, EntryTime entryTime) {
        ParkingEvent event = new ParkingEvent(lotType, permit, entryTime);

        //notifies ParkingObserver
        parkingObserver.update(event);

        //calls TransactionManager park() to register charge
        parkingObserver.getTransactionManager().park(event);
    }
    

    // Getters
    // Removed setters for id, address, and capacity since
    // it is likely they would not change
    public String getId() {
        return id;
    }

    public String getName () {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkingLotType getParkingLotType() {
        return parkingLotType;
    }
    public void setParkingLotType(ParkingLotType parkingLotType) {
        if(lotType == null){
            throw new IllegalArgumentException("Lot Type cannot be empty.");
        }
        this.parkingLotType = parkingLotType;
    }

    // string of Parking lot information (id, address, and it's capacity)
    @Override
    public String toString() {
        return "Parking Lot Name: " + name +
                "\nParking Lot ID: " + id +
                "\nLocation: " + address.getAddressInfo() +
                "\nLot Capacity: " + capacity;
    }


    // allows other classes to view the list but not modify anything in the list
    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    // Define the parking lot type
    private ParkingLotType parkingLotType;

    // Constructor to initialize the parking lot with a specific type
    public ParkingLot(ParkingLotType parkingLotType) {
            this.parkingLotType = parkingLotType;
        }
    
    // Method to get daily rate based on parking lot type
    public double getDailyRate() {
            return parkingLotType.getHourlyRate();
        }

    // compares parking lot to specified object
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ParkingLot that = (ParkingLot) o;
        return capacity == that.capacity &&
                Objects.equals(id, that.id) &&
                Objects.equals(address, that.address);

    }

    //returns hashcode for parking lot
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, capacity);
    }
}