/**
 * The Address class stores and retrieves address information. Validation 
 * logic is used to make sure that fields are not empty before the addres
 * object is created.
 * 
 * Then the getAddressInfo() method displays the address information.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.customerData;

import java.util.Objects;

import src.main.java.shared.JsonSerializable;


//Class for address
public class Address implements JsonSerializable {
    // Declare the variable
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zipCode;

    // Default constructor that doesn't take any parameters and creates
    // object based on default values
    public Address() {

    }
    // Constructor with required parameters
    public Address(String streetAddress1, String city, String state, String zipCode) {
        this.streetAddress1 = streetAddress1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // builder pattern to create adderss object
    public static class AddressBuilder {
        // required parameters
        private String streetAddress1;
        private String city;
        private String state;
        private String zipCode;

        // opitional parameters
        // initialized default values
        private String streetAddress2 = "";

        public AddressBuilder(String streetAddress1, String city, String state, String zipCode) {
            if(streetAddress1 == null || streetAddress1.isEmpty()) {
            throw new IllegalArgumentException("Street Address cannot be empty.");
        }
        if(city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }
        if(state == null || state.isEmpty()){
            throw new IllegalArgumentException("State cannot be empty.");
        }
        if(zipCode == null || zipCode.isEmpty()){
            throw new IllegalArgumentException("Zipcode cannot be empty");
        }
        this.streetAddress1 = streetAddress1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        }

        public AddressBuilder streetAddress2(String streetAddress2) {
            this.streetAddress2 = streetAddress2;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.streetAddress1 = streetAddress1;
            address.streetAddress2 = streetAddress2;
            address.city = city;
            address.state = state;
            address.zipCode = zipCode;
            return address;
        }

        public String getAddressInfo() {
            return null;
        }
    }

    public Address(AddressBuilder builder){
        this.streetAddress1 = builder.streetAddress1;
        this.streetAddress2 = builder.streetAddress2;
        this.state = builder.state;
        this.city = builder.city;
        this.zipCode = builder.zipCode;
    }

    // Removed the setters to make the object immutable.
    // In case a customer does need to change ther address,
    // I provided a method in the customer class.
    public String getStreetAddress1() {
        return streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    // String with address info
    public String getAddressInfo() {
        return streetAddress1 + ", " +
                (streetAddress2.isEmpty() ? "" : streetAddress2 + ", ") +
                city + ", " + state + " " + zipCode;
    }

    // Compares address object based on attributes
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return Objects.equals(streetAddress1, address.streetAddress1) &&
                Objects.equals(streetAddress2, address.streetAddress2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(zipCode, address.zipCode);
    }

    // Creates hash code based on attributes
    @Override
    public int hashCode() {
        return Objects.hash(streetAddress1, streetAddress2, city, state, zipCode);
    }

}