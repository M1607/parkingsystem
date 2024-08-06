/**
 * The Commmand class runs commands that are 
 * executed on the client side
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.client;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ClientOpsCommand {
  //declare variables
  private final String name;
  private final String command;
  private final List<String> fieldNames;
  
  //contructor that initialzes the creation of a command
  //and initializezs properties
  public ClientOpsCommand(String name, String command, List<String> fieldNames) {
    System.out.println("Creating command: " + name + " with fields: " + fieldNames);
    this.name = name;
    this.command = command;
    this.fieldNames = Collections.unmodifiableList(fieldNames);
  }
  
  public String name() {
    return name;
  }

  public String getName() {
    return name;
}
  
  public List<String> fieldNames() {
    return fieldNames;
  }

  public String getCommand() {
    return this.command;
}

  //executes commands with the provided data
  public String execute(Map<String, String> data) {
    try {
      List<String> response = Client.runCommand(this.command, data);
      return String.join("\n", response);
    } catch (IOException e) {
      e.printStackTrace();
      return "Error: " + e.getMessage();
    }
  }

}