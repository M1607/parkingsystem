/**
 * 
 * The EntryTime class gets the current date and time
 * so that the system has a timestamp for when a car enters
 * a parking lot.
 * 
 *
 * @author (Maddie Hirschfeld)
 * @version (November 6, 2023)
 */
package src.main.java.parking.parkingCharges;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import src.main.java.shared.JsonSerializable;
import java.time.Duration;

public class EntryTime implements JsonSerializable {
    private LocalDateTime entryTime;

    //constructor to initailze entry time to current date and time
    public EntryTime() {
        this.entryTime = LocalDateTime.now();
    }

    //Getter mehtod to return entryTime
    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    //total hours from entry
    public long getTotalHours() {
        Duration duration = Duration.between(entryTime, LocalDateTime.now());
        return duration.toHours();
    }

    //provides string of EntryTime object and uses formatter to format date
    @Override
    public String toString() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return entryTime.format(formatDate);
    }
    
}