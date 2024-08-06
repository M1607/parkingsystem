/**
 * The JsonSerializable interface defines contract
 * for objects to be converted to and form JSON format
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.shared;


public interface JsonSerializable {
    
    //Gson instance from GsonObj serialize object to JSON string
    default String toJson(ParkingRequest request) {
        return GsonObj.INSTANCE.getGson().toJson(this);
    }

    //deserialize JSON string to an object
    static <T extends JsonSerializable> T fromJson(String json, Class<T> class1) {
        return GsonObj.INSTANCE.getGson().fromJson(json, class1);
    }
}