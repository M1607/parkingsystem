/**
 * The Server class runs the server for the
 * parkingsystem application.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import src.main.java.parking.customerData.*;
import src.main.java.parking.management.*;
import src.main.java.parking.parkingCharges.ParkingChargeStrategyFactory;
import src.main.java.parking.transactionManager.TransactionManager;

public class Server {
  static {
    System.setProperty(
        "java.util.logging.SimpleFormatter.format", "%1$tc %4$-7s (%2$s) %5$s %6$s%n");
  }

  // logs server related messages
  private static final Logger logger = Logger.getLogger(Server.class.getName());

  // port number that the server will listen for incoming connections
  private final int PORT = 7777;

  // processes client reqeusts
  private final ParkingService service;

  // ExecutorService for managing threads
  private final ExecutorService executorService;
  private final AtomicInteger tasks = new AtomicInteger(0);

  // constructor that assigns ParkingService used for server
  public Server(ParkingService service) {
    this.service = service;
    // create executorservice with fixed thread pool
    this.executorService = Executors.newFixedThreadPool(20);
  }

  // starts the server and listens/handles connections
  public void startServer() throws IOException {
    logger.info("Starting server: " + InetAddress.getLocalHost().getHostAddress());
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      serverSocket.setReuseAddress(true);
      while (true) {
        Socket client = serverSocket.accept();

        // creates new thread for each client request
        executorService.submit(() -> {
          try {
            Thread clientThread = new Thread(new ClientHandler(service, client));
            clientThread.start();
          } finally {
            tasks.incrementAndGet();
          }
        });
      }
    }
  }

  // main method and starts the server
  public static void main(String[] args) throws Exception {
    Address parkingOfficeAddress = new Address.AddressBuilder("982 Carhenge Rd.",
        "Telluride", "CO", "81435")
        .build();
    ParkingOffice parkingOffice = new ParkingOffice("Carhenge Office",
        null, null, parkingOfficeAddress);
    ParkingChargeStrategyFactory chargeStrategyFactory = new ParkingChargeStrategyFactory();
    TransactionManager transactionManager = new TransactionManager(chargeStrategyFactory);

    ParkingService service = new ParkingService(parkingOffice, transactionManager);

    new Server(service).startServer();
  }
}