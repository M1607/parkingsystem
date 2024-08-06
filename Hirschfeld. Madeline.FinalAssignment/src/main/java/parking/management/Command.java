/**
 * The Command interface executes commands in the parking lot system.
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.main.java.parking.management;

import java.util.Properties;

public interface Command {
    String getCommandName();
    String getDisplayName();
    String execute(Properties params);
}