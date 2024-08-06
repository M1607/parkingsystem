/**
 * The CustomerService class is responsible for creating customers, changing addresses,
 * and managing car registration.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.main.java.parking.management;

import src.main.java.parking.customerData.*;
import src.main.java.shared.JsonSerializable;


public class CustomerService implements JsonSerializable {

    // Method to create a Customer object
    public static Customer createCustomer(String firstName, String lastName, Address address, String phoneNumber) {
        return new Customer.CustomerBuilder(firstName, lastName, address)
                .phoneNumber(phoneNumber)
                .build();
    }
   
    // Method to change the Address
    public static void changeAddress(Customer customer, Address newAddress) {
        customer.setAddress(newAddress);
    }

    // Method to notify about car registration
    public static String notifyCarRegistered(Customer customer, Car car) {
        return customer.getFirstName() + " " + customer.getFirstName() + " registered car to their account.";
    }

    // Method to notify about customer registration
    public static String notifyCustomerRegistered(Customer customer) {
        return customer.getFirstName() + " " + customer.getLastName() + " is registered.";
    }

    // Method allows customer to add a car to their list of owned cars
    public static void addCar(Customer customer, Car car) {
        customer.addCar(car);
    }
}