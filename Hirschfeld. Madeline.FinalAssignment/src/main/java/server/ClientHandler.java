/**
 * The ClientHandler class handles incoming
 * client requests and processes them.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 11, 2023)
 */

package src.main.java.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.main.java.shared.JsonSerializable;
import src.main.java.shared.ParkingRequest;
import src.main.java.shared.ParkingResponse;

public class ClientHandler implements Runnable {
    // field to Store ParkingService
    private static ParkingService service;
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private final Socket client;

    public ClientHandler(ParkingService service, Socket client) {
        this.service = service;
        this.client = client;
    }

    @Override
    public void run() {
        // record start time
        long start = System.currentTimeMillis();

        try (PrintWriter pw = new PrintWriter(client.getOutputStream());
                Scanner scanner = new Scanner(client.getInputStream())) {

            // read input from client and create JSON request
            String inputJson = readInput(scanner);

            // process JSON input and retrieve the response
            String output;
            try {
                // deserialize JSON to ParkingRequest,
                // then process and serialize to JSON response
                ParkingRequest request = JsonSerializable.fromJson(inputJson, ParkingRequest.class);

                // converts properties to List<String>
                List<String> argsList = new ArrayList<String>();
                for (Object obj : request.getProperties().values()) {
                    argsList.add(String.valueOf(obj));
                }

                // process ParkingRequest using ParkingService
                output = service.performCommand(request.getCommandName(), argsList);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                output = ex.getMessage();
            }

            // sends JSON response back to the client
            pw.println(output);
            pw.println("end");
            pw.flush();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to read from client.", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close client socket.", e);
            }

            // record end time
            long end = System.currentTimeMillis();
            long handlingTime = end - start;
            System.out.println("Client Handling Time: " + handlingTime + "ms");
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    // reads incoming data from client
    private static String readInput(Scanner scanner) {
        String input;
        StringBuilder requestBuilder = new StringBuilder();
        while (!(input = scanner.nextLine()).equals("end")) {
            requestBuilder.append(input);
        }
        return requestBuilder.toString();
    }

    // processes JSON string input
    private String processInput(String inputJson) {
        // deserialize JSON string to ParkingRequst object
        ParkingRequest request = JsonSerializable.fromJson(inputJson, ParkingRequest.class);

        // Use ParkingRequest to create ParkingResponse
        ParkingResponse response = processRequest(request);

        // serialize ParkingResponse to JSON string
        return response.toJson(request);
    }

    // processes ParkingRequest
    private ParkingResponse processRequest(ParkingRequest request) {
        // get the command and args
        String command = request.getCommandName();
        Properties properties = request.getProperties();
        List<String> args = new ArrayList<>();

        // add each property value to a list
        for (Object value : properties.values()) {
            args.add(String.valueOf(value));
        }

        // perform command
        String commandResult;
        try {
            commandResult = service.performCommand(command, args);
        } catch (Exception e) {
            e.printStackTrace();
            return new ParkingResponse(500, "Error: " + e.getMessage());
        }

        // determine status based on command result
        int status = commandResult.startsWith("Error: ") ? 400 : 200;

        // return parking response with status and command result
        return new ParkingResponse(200, commandResult);
    }

}
