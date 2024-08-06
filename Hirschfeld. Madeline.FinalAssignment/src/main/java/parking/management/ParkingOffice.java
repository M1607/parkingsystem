/**
 * The parking office class manages customers, cars, parking charges, and parking lots
 * for the University parking system. It also suppports operaitons for customer 
 * registraition, parking charges, and parking lots.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.parking.management;

import src.main.java.shared.JsonSerializable;
import java.util.HashMap;

import src.main.java.parking.customerData.*;
import src.main.java.parking.parkingLotData.ParkingLot;
import src.main.java.parking.transactionManager.*;

public class ParkingOffice implements JsonSerializable{
    private String parkingOfficeName;
    private HashMap<String, Customer> customers;
    private HashMap<String, ParkingLot> parkingLots;
    private HashMap<String, Car> customerCars;
    private PermitManager permitManager;
    private Address parkingAddress;
    private String customerId;

    // Default constructor that doesn't take any parameters and creates
    // object based on default values
    public ParkingOffice() {
        customers = new HashMap<String, Customer>();
        parkingLots = new HashMap<String, ParkingLot>();
        this.permitManager = new PermitManager();
        this.customerCars = new HashMap<String, Car>();
    }

    // Constructor sets parameters while creating the instance:
    //
    // @param parkingOfficeName
    // @param customers
    // @param parkingLots
    // @param parkingAddress
    //
    public ParkingOffice(String parkingOfficeName, HashMap<String, Customer> customers,
            HashMap<String, ParkingLot> parkingLots, Address parkingAddress) {
        if (parkingOfficeName == null || parkingOfficeName.isEmpty()) {
            throw new IllegalArgumentException("Parking Office Name cannot be empty.");
        }
        this.parkingOfficeName = parkingOfficeName;
        this.parkingAddress = parkingAddress;
        this.customers = new HashMap<>();
        this.parkingLots = new HashMap<>();
        this.permitManager = new PermitManager();
        this.customerCars = new HashMap<>();
    }

    // Getters & Setters
    public String getParkingOfficeName() {
        return parkingOfficeName;
    }

    public void setParkingOfficeName(String parkingOfficeName) {
        if (parkingOfficeName == null || parkingOfficeName.isEmpty()) {
            throw new IllegalArgumentException("Parking Office Name cannot be empty.");
        }
        this.parkingOfficeName = parkingOfficeName;
    }

    public HashMap<String, Customer> getCustomers() {
        return customers;
    }

    public HashMap<String, ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public HashMap<String, Car> getCustomerCars() {
        return customerCars;
    }

    public Address getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(Address parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    // Basic functionality methods which can be accessed by all roles
    public void userMethod() {
        getParkingLot(parkingOfficeName);

    }

    // Methods that can be accessed by users witha an admin role
    public void adminMethod() {
        registerCustomer(null);
        removeCustomer(null);
        getCustomers();
        addLot(null);
        removeLot(null);
        removeCar(parkingOfficeName);
        getCarByPermitId(parkingOfficeName);
    }

    // Method to register a new customer at Parking Office
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    // Method to remove customer at Parking office
    public Customer removeCustomer(Customer customer) {
        Customer removeCustomer = customers.remove(customer.getCustomerId());

        return removeCustomer;
    }

    // Method to retrieve customer information
    public Customer getCustomer(String customerId) {
        Customer customer = customers.get(customerId);
        return customer;
    }

    // Method to add a parking lot to parking office
    public void addLot(ParkingLot parkingLot) {
        parkingLots.put(parkingLot.getId(), parkingLot);
    }

    // Method to remove parking lot at parking office
    public ParkingLot removeLot(ParkingLot parkingLot) {
        ParkingLot removedLot = parkingLots.remove(parkingLot.getId());
        return removedLot;
    }

    // Method to get parking lot based on it's ID
    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLots.get(parkingLotId);
    }

    // Method to remove car at parking office by it's permit ID
    public Car removeCar(String permitId) {
        ParkingPermit permit = permitManager.getPermits().get(permitId);
        if (permit == null) {
            return null;
        }
        Car removedCar = permit.getCar();

        customerCars.remove(removedCar.getLicense());
        permitManager.getPermits().remove(permitId);
        return removedCar;

    }

    // Method to register a new car and return permit ID
    public ParkingPermit register(Car car) {
        ParkingPermit permit = permitManager.register(car);
        customerCars.put(car.getLicense(), car);
        return permit;
    }

    // Method to get a permit by its ID
    public ParkingPermit getPermitById(String permitId) {
        return permitManager.getPermits().get(permitId);
    }

    //method to get permitid based on customer id
    public ParkingPermit getPermitByCustomerId(String customerId) {
        for (ParkingPermit permit : permitManager.getPermits().values()) {
            if (permit.getCustomer().getCustomerId().equals(customerId)) {
                return permit;
            }
        }
        return null;
    }

    //method to find customer name and return customerId
    public Customer findCustomerIdByName(String firstName, String lastName) {
        for (Customer customer : customers.values()) {
            if (customer.getFirstName().equalsIgnoreCase(firstName) && 
                customer.getLastName().equalsIgnoreCase(lastName)) {
                return customer;
            }
        }
        return null;
    } 

    // Method to get a car by its permitID
    public Car getCarByPermitId(String permitId) {
        ParkingPermit permit = getPermitById(permitId);
        if (permit != null) {
            return permit.getCar();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Parking Office Name: " + parkingOfficeName +
                "\nCustomers: " + customers +
                "\nParking Lots: " + parkingLots +
                "\nParking Office Address: " + parkingAddress;
    }

}