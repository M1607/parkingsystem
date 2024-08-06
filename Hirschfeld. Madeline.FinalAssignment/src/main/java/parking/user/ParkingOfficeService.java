/**
 * The ParkingOfficeService interface for the proxy pattern
 * that implements access controls for what users can do 
 * based on their role
 *
 * @author (Maddie Hirschfeld)
 * @version (October 22, 2023)
 */
package src.main.java.parking.user;

public interface ParkingOfficeService {
     
    void userMethod();

    void adminMethod();

    }