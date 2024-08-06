/**
 * The ParkingRequest class holds request types and
 * their details and then converts them to and from JSON
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.main.java.shared;

import java.util.Properties;

public class ParkingRequest implements JsonSerializable {
    // declare variables
    private String commandName;
    private Properties properties;

    // constructor initializes ParkingRequest with command
    // and the associated properties
    public ParkingRequest(String commandName, Properties properties) {
        this.commandName = commandName;
        this.properties = properties;
    }

    // Getters & setters
    public String getCommandName() {
        return commandName;
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "ParkingRequest{" + "commandName='" + commandName + '\'' +
                ", properties=" + properties + '}';
    }
}