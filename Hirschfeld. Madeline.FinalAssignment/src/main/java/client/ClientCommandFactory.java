/**
 * The ClientCommandFactory manages the creation of ClientOpsCommand
 * objects based on the command keyword.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */
package src.main.java.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class ClientCommandFactory {

    //static map that holds definitions of the commands
    private static final Map<String, String[]> COMMAND_DEF = new HashMap<>();

    //populates COMMAND_DEF map with definitions
    static {
        COMMAND_DEF.put("CUSTOMER", new String[] { "Register Customer", "First Name", "Last Name", "Street Address",
                "City", "State", "ZipCode" });
        COMMAND_DEF.put("CAR", new String[] { "Register Vehicle", "License", "Car Type", "Permit ID" });
        COMMAND_DEF.put("PARK", new String[] {"Start Parking", "Permit Id", "Time"});
        COMMAND_DEF.put("FINISHPARK", new String[] {"Finish Parking", "Permit Id", "Time"});
        COMMAND_DEF.put("CHARGES", new String[] {"Get Charges", "Customer", "Car"});
        COMMAND_DEF.put("GETPERMIT", new String[] {"Get Permit", "Customer ID"});
        COMMAND_DEF.put("GETCUSTOMERID", new String[] { "Get Customer ID", "First Name", "Last Name" });
    }

//retreinevs ClientOpsCommand object corresponding to the commandKey
    public static ClientOpsCommand getCommand(String commandKey) {
        if (!COMMAND_DEF.containsKey(commandKey)) {
            throw new IllegalArgumentException("Command is unknown.");

        }
        String[] def = COMMAND_DEF.get(commandKey);
        return new ClientOpsCommand(def[0], commandKey, Arrays.asList(def).subList(1, def.length));
    }

    //read only view of commadns and their definitions
    public static Map<String, String[]> getAvailableCommands() {
        return Collections.unmodifiableMap(COMMAND_DEF);
    }
}