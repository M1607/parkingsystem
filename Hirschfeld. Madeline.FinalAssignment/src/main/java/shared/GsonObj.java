/**
 * The GsonObj class manages the configuration
 * and instantiation of the Gson object.
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 5, 2023)
 */
package src.main.java.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public enum GsonObj {
    INSTANCE;
    
    private final Gson gson;

    //construtor for enum
    GsonObj() {
        //PrettyPrinting formats JSON to be readable
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //method to access Gson instance
    public Gson getGson() {
        return gson;
    }
}