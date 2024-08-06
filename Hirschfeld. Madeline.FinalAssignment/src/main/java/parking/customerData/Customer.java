/**
 * The Customer class is responsible for holding customer information and 
 * providing getters and setter for accessing and updating the customer data.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.parking.customerData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import src.main.java.shared.JsonSerializable;


public class Customer implements JsonSerializable {
    // declare variables
    private String customerId;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private List<Car> cars = new ArrayList<>();

    // Default constructor that doesn't take any parameters
    public Customer() {
        this.cars = new ArrayList<>();
    }

    // builder pattern to create customer object
    public static class CustomerBuilder {
        // repquired parameters
        private String customerId;
        private final String firstName;
        private final String lastName;
        private Address address;
        

        // optional parameters
        // intialezed default values
        private String phoneNumber = "";
        private List<Car> cars = new ArrayList<>();

        public CustomerBuilder(String firstName, String lastName, Address address) {
            this.firstName = Objects.requireNonNull(firstName, "Customer's first name cannot be empty.");
            this.lastName = Objects.requireNonNull(lastName, "Customer's last name cannot be empty.");
            this.address = Objects.requireNonNull(address, "Customer's address cannot be empty.");
        }

        public CustomerBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerBuilder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder addCar(Car car) {
            this.cars.add(car);
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    private Customer(CustomerBuilder builder) {
        this.customerId = builder.customerId != null && !builder.customerId.isEmpty()
                          ? builder.customerId
                          : generateCustomerId();
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.cars = new ArrayList<>(builder.cars);
    }

    // Generate unique customer ID
    private static String generateCustomerId() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    // Validation logic is provided for setters.
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerID(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address newAddress) {
        if (newAddress == null) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.address = newAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number of customer cannot be empty.");
        }
        this.phoneNumber = phoneNumber;

    }

    public List<Car> getCars() {
        return new ArrayList<>(this.cars);
    }

    //method to add car to customer's car list
    // Method to add a car to the customer's list of cars
    public void addCar(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Cannot add a null car.");
        }
        this.cars.add(car);
    }

    // create string with customer information (name, ID, address, phone)
    @Override
    public String toString() {
        return "Owner ID: " + customerId +
                "\n" + "Customer: " + firstName + " " + lastName +
                "\n" + "Address: " + address.getAddressInfo() +
                "\n" + "Phone: " + phoneNumber + "\n";
    }

    // compares customer to specified object
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    // returns hashcode
    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    public void changeAddress(String string, String string2, String string3, String string4, String string5) {
    }

    public boolean isEmpty() {
        return false;
    }
}