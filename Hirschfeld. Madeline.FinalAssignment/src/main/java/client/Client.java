/**
 * The Client class is a command line interface for
 * the parking lot system. Furthermore, it allows users
 * to interact with the server with providing commands
 * and getting responses.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import src.main.java.shared.ParkingRequest;

public class Client {

  // details for server connection
  private static final int PORT = 7777;
  private static final String SERVER = "localhost";

  private Client() {
  }

  // method that sends command to server and recieves response
  /**
   * @param command
   * @param data
   * @return
   * @throws IOException
   */
  public static List<String> runCommand(String commandKey, Map<String, String> data)
      throws IOException {

    InetAddress host = InetAddress.getByName(SERVER);
    // convert data map to properties
    Properties properties = new Properties();
    properties.putAll(data);

    // create ParkingRequest object
    ParkingRequest request = new ParkingRequest(commandKey, properties);

    // serialize ParkingRequest to JSON string
    String requestJson = request.toJson(request);

    // initialize response
    List<String> response = new ArrayList<>();

    try (Socket link = new Socket(host, PORT);
        Scanner scanner = new Scanner(link.getInputStream());
        PrintWriter output = new PrintWriter(link.getOutputStream())) {

      // connect to server
      System.out.println("You are now connected to: " + host.getHostAddress());

      output.println(requestJson);
      output.println("end");
      output.flush();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if ("end".equals(line)) {
          break;
        }
        response.add(line);
      }
    }
    return response;

  }

  // main method used to run client from commmand line
  public static void main(String[] args) throws IOException {

    if (args.length == 0 || args[0].equals("LIST")) {
      System.out.println("Here are the commands we know about.");
      System.out.println(
          "Usage: $ java ict4300.week8.client.Client COMMAND label1=value1 label2=value2 ...");
      System.out.println();
      ClientCommandFactory.getAvailableCommands().forEach((key, value) -> {
        System.out.format("%s: %s ", value[0], key);
        for (int i = 1; i < value.length; ++i) {
          System.out.format("%s=value ", value[i].replaceAll(" ", "").toLowerCase());
        }
        System.out.println();
      });
      return;
    }

    ClientOpsCommand clientCommand = ClientCommandFactory.getCommand(args[0].toUpperCase());
    if (clientCommand == null) {
      System.out.println("Unrecognized command: " + args[0]);
      return;
    }

    Map<String, String> values = new LinkedHashMap<>();
    for (String label : clientCommand.fieldNames()) {
      for (int i = 1; i < args.length; ++i) {
        if (args[i].startsWith(label.replaceAll(" ", "").toLowerCase())) {
          values.put(label, args[i].replaceAll(".*=", ""));
          break;
        }
      }
    }
    // Use the command key for the server communication, not the user-friendly name.
    List<String> serverResponse = Client.runCommand(clientCommand.getCommand(), values);
    serverResponse.forEach(System.out::println);
  }
}