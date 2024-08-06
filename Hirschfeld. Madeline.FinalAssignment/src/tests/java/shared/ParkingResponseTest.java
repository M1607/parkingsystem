/**
 * The ParkingResponsetTest class is a unit test that
 * verifies the serialization and deserialization of the 
 * ParkingResponse Class
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.tests.java.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import src.main.java.shared.JsonSerializable;
import src.main.java.shared.ParkingResponse;

public class ParkingResponseTest {
    @Test
    public void testParkingResponseToJson() {
        ParkingResponse response = new ParkingResponse(200, "Success");

        String json = response.toJson(null);
        assertNotNull(json);

        // Print JSON string
        System.out.println("JSON string: " + json);
    }

    @Test
    public void testParkingResponseFromJson() {
        String json = "{\"status\":200,\"message\":\"Success\"}";
        ParkingResponse response = JsonSerializable.fromJson(json, ParkingResponse.class);

        assertEquals(200, response.getStatus());
        assertEquals("Success", response.getMessage());

        // Print the ParkingResponse Object
        System.out.println("Created object from JSON:");
        System.out.println("Status: " + response.getStatus());
        System.out.println("Message: " + response.getMessage());
    }

}