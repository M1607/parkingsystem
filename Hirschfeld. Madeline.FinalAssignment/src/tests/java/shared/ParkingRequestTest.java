/**
 * The ParkingRequestTest class is a unit test
 * that ensures the funcationality of the ParkingRequest
 * class for JSON serialization and deserialization
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.tests.java.shared;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Test;

import src.main.java.shared.JsonSerializable;
import src.main.java.shared.ParkingRequest;

public class ParkingRequestTest {
    @Test
    public void testParkingRequestToJson() {
        Properties properties = new Properties();
        properties.setProperty("key", "value");
        ParkingRequest request = new ParkingRequest("Register", properties);

        String json = request.toJson(request);
        assertNotNull(json);

        //Print JSON string
        System.out.println("JSON string: " + json);
    }



    @Test
    public void testParkingRequestFromJson() {
        String json = "{\"commandName\":\"register\",\"properties\":{\"key\":\"value\"}}";
        ParkingRequest request = JsonSerializable.fromJson(json, ParkingRequest.class);

        assertEquals("Register", request.getCommandName());
        assertEquals("value", request.getProperties().getProperty("key"));

        //PrintParkingRequest object
        System.out.println("Created object from JSON:");
        System.out.println("commandName: " + request.getCommandName());
        System.out.println("Properties: " + request.getProperties());
    }
}