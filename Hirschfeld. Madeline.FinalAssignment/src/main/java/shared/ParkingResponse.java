/**
 * The ParkingResponse class is a response object
 * that includes the status and a message
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.main.java.shared;


public class ParkingResponse implements JsonSerializable {
   //declare variables
    private int status;
    private String message;

    //constructor for ParkingResponse with status and message
    public ParkingResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    //Getters & Setters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ParkingResponse{" +"statusCode=" + status + ", message='" 
        + message + '\'' +'}';
    }
}