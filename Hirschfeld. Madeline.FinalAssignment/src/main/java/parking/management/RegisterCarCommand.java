/**
 * The RegisterCarCommand registers new cars in the
 * university parking system.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (October 8, 2023)
 */

package src.main.java.parking.management;

import java.util.Properties;

import src.main.java.parking.customerData.*;

public class RegisterCarCommand implements Command {

    private ParkingOffice office;

    public RegisterCarCommand(ParkingOffice office) {
        this.office = office;
    }

    @Override
    public String execute(Properties params) {
        try {
            checkParameters(params);
        } catch (Exception e) {
            return "Parameter check failed: " + e.getMessage();
        }

        String license = params.getProperty("license");
        CarType type = CarType.valueOf(params.getProperty("type"));
        String owner = params.getProperty("owner");
        String firstName = params.getProperty("firstName");
        String lastName = params.getProperty("lastName");
        String customerId = params.getProperty("customerId");
        String streetAddress1 = params.getProperty("streetAddress1");
        String streetAddress2 = params.getProperty("streetAddress2");
        String city = params.getProperty("city");
        String state = params.getProperty("state");
        String zipCode = params.getProperty("zipCode");

        try {

            // Check if the owner exists
            Customer customer = office.getCustomer(owner);
            if (customer == null) {
                // create new customer if the owner doesn't exist
                Address newAddress = new Address.AddressBuilder(streetAddress1, city, state, zipCode)
                        .streetAddress2(streetAddress2)
                        .build();
                Customer newCustomer = new Customer.CustomerBuilder(firstName, lastName, newAddress)
                        .customerId(customerId)
                        .build();
                office.registerCustomer(newCustomer);
            } else {
                customer = office.getCustomer(owner);
            }

            // creates a new car that goes with existing or new customer
            Car newCar = new Car();
            newCar.setLicense(license);
            newCar.setType(type);
            newCar.setOwner(owner);

            // Adds the new car to the customer's record
            CustomerService.addCar(customer, newCar);

            String successResponse = CustomerService.notifyCarRegistered(customer, newCar);

            return "Success! " + successResponse;

        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }

    }

    private void checkParameters(Properties params)
            throws Exception {
        if (!params.containsKey("license") || params.getProperty("license").isEmpty()) {
            throw new Exception("License parameter is missing.");
        }
        if (!params.containsKey("type") || params.getProperty("type").isEmpty()) {
            throw new Exception("Car Type parameter is missing.");
        }
        if (!params.containsKey("owner") || params.getProperty("owner").isEmpty()) {
            throw new Exception("Owner of Car parameter is missing.");
        }

    }

    @Override
    public String getCommandName() {
        return "car_registration";
    }

    @Override
    public String getDisplayName() {
        return "Car Registration";
    }
}