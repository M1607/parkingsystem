/**
 * The RegisterCustomerCommand registers new customers in the
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

public class RegisterCustomerCommand implements Command {

    private ParkingOffice office;

    public RegisterCustomerCommand(ParkingOffice office) {
        this.office = office;
    }

    @Override
    public String execute(Properties params) {
        try {
            checkParameters(params);
        } catch (Exception e) {
            return "Parameter check failed: " + e.getMessage();
        }

        String firstName = params.getProperty("firstName");
        String lastName = params.getProperty("lastName");
        String customerId = params.getProperty("customerId");
        String streetAddress1 = params.getProperty("streetAddress1");
        String streetAddress2 = params.getProperty("streetAddress2");
        String city = params.getProperty("city");
        String state = params.getProperty("state");
        String zipCode = params.getProperty("zipCode");
        String phoneNumber = params.getProperty("phoneNumber");

        if (streetAddress1 == null || city == null ||
                state == null || zipCode == null) {
            return "Address parameter is missing";
        }

        try {
            Customer newCustomer = CustomerService.createCustomer(firstName, lastName,
                    new Address(streetAddress1, streetAddress2, city, state),
                    phoneNumber);
            
            newCustomer.setCustomerID(customerId);
            office.registerCustomer(newCustomer);

            String successResponse = CustomerService.notifyCustomerRegistered(newCustomer);

            return "Success! " + successResponse;

        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }

    }

    private void checkParameters(Properties params)
            throws Exception {
        if (!params.containsKey("firstName") || params.getProperty("firstName").isEmpty()) {
            throw new Exception("First name parameter is missing.");
        }
        if (!params.containsKey("lastName") || params.getProperty("lastName").isEmpty()) {
            throw new Exception("Last name parameter is missing.");
        }
        if (!params.containsKey("customerId") || params.getProperty("customerId").isEmpty()) {
            throw new Exception("Customer's ID parameter is missing.");
        }
        if (!params.containsKey("phoneNumber") || params.getProperty("phoneNumber").isEmpty()) {
            throw new Exception("Phone Number parameter is missing.");
        }

    }

    @Override
    public String getCommandName() {
        return "customer_registration";
    }

    @Override
    public String getDisplayName() {
        return "Customer Registration";
    }
}