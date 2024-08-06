/**
 * The ParkingService class handles incoming commmands
 * to manage parking related operations
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.server;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.main.java.parking.customerData.*;
import src.main.java.parking.management.*;
import src.main.java.parking.transactionManager.*;

public class ParkingService {
    protected final ParkingOffice parkingOffice;
    protected final TransactionManager transactionManager;

    public ParkingService(ParkingOffice parkingOffice, TransactionManager transactionManager) {
        this.parkingOffice = parkingOffice;
        this.transactionManager = transactionManager;
    }

    protected String handleInput(InputStream in) {
        @SuppressWarnings("resource")
        // The scanner and input stream will be closed when we disconnect
        Scanner scanner = new Scanner(in);
        ArrayList<String> data = new ArrayList<>();
        while (scanner.hasNext()) {
            String token = scanner.nextLine();
            if (token.equals("end")) {
                break;
            }
            data.add(token);
        }
        System.out.println("data: " + data);
        return performCommand(data.remove(0), data);
    }

    public String performCommand(String command, List<String> args) {
        System.out.println("Received command: " + command);
        String matched;
        switch (command.toUpperCase()) {
            case "CUSTOMER":
                // args.get(0)=firstName
                // args.get(1)=lastName
                // args.get(2)=streetAddress
                // args.get(3)=city
                // args.get(4)=state
                // args.get(5)=zipCode
                // args.get(6)=phoneNumber
                String firstName = args.get(0);
                String lastName = args.get(1);
                Address address = new Address.AddressBuilder(args.get(2), args.get(3), args.get(4), args.get(5))
                        .build();
                String phoneNumber = args.get(6);

                Customer customer = new Customer.CustomerBuilder(firstName, lastName, address)
                        .phoneNumber(phoneNumber)
                        .build();
                parkingOffice.registerCustomer(customer);
                return "Registered Customer: " + customer.toString();


            case "CAR":
                // args.get(0)=license
                // args.get(1)=carType
                // args.get(2)=customerId
                String licensePlate = args.get(0);
                CarType carType = CarType.valueOf(args.get(1));
                String ownerId = args.get(2);

                // Get owner from customerId
                Customer owner = parkingOffice.getCustomer(ownerId);
                if (owner == null) {
                    // error handling if cannot find the owner
                    return "Invalid ID: " + ownerId + ". Customer not Found. ";
                }

                Car car = new Car(licensePlate, carType, owner.getCustomerId());
                // returns parking permit
                ParkingPermit permitCustomer = parkingOffice.register(car);
                return "Registered Car: " + car.toString() + " with Permit ID: " + permitCustomer.getPermitId();

            // Start Parking
            case "PARK":
                // args.get(0)=permitID
                // args.get(1)=Time
                String permitIdStart = args.get(0);
                String startTime = args.get(1);

                return "Parking started for permit ID: " + permitIdStart + " at " + startTime;
 
            // End of Parking
            case "FINISHPARK":
                // args.get(0)=permitID
                // args.get(1)=Time
                String permitIdFinish = args.get(0);
                String finishTime = args.get(1);

                return "Parking finished for permit ID: " + permitIdFinish + " at " + finishTime;

            case "CHARGES":
                // args.get(0)=Customer
                // args.get(1)=Car
                String customerIdCharges = args.get(0);
                String carId = args.get(1);

                return "Total Charges for Customer " + customerIdCharges + " : " + carId;

            // gets permit id for custmer
            case "GETPERMIT":
                // args.get(0)=customerId
                String customerIdPermit = args.get(0);
                // Use the ParkingOffice to get the customer's permit
                ParkingPermit permit = parkingOffice.getPermitByCustomerId(customerIdPermit);
                if (permit != null) {
                    return "Permit ID for customer " + customerIdPermit + " is: " + permit.getPermitId();
                } else {
                    return "No permit found for Customer ID: " + customerIdPermit;
                }

            //gets customer id based on first and last name of the customer
            case "GETCUSTOMERID":
            // args.get(0)=First Name
            // args.get(1)=Last Name
            String firstNameId = args.get(0);
            String lastNameId = args.get(1);
            Customer customer1 = parkingOffice.findCustomerIdByName(firstNameId, lastNameId);
            if (customer1 != null) {
                String customerId = customer1.getCustomerId();
                return "Customer ID for " + firstNameId + " " + lastNameId + " is: " + customerId;
            } else {
                return "Customer not found for name: " + firstNameId + " " + lastNameId;
            }

            default:
                matched = "Unknown command: " + command;
                break;
        }
        matched += ": " + args;
        return matched;
    }

    public String performCommand(String commandName, String[] parameters) {
        return null;
    }
}