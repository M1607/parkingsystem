/**
 * The User Class is used with the ParkingOffice Proxy
 * to implement access controls to the parking system.
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */

package src.main.java.parking.user;

import src.main.java.shared.JsonSerializable;

public class User implements JsonSerializable {
    //declare variables
    private String username;
    private Role role;

    //constructor that initializes username and role
    public User (String username, Role role) {
        this.username = username;
        this.role = role;
    }

    //method returns the role of the user
    public Role getRole() {
        return role;
    }

}