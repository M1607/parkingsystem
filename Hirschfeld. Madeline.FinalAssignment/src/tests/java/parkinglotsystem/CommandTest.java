/**
 * The CommandTest class 
 *
 * @author (Maddie Hirschfeld)
 * @version (September 17, 2023)
 */

package src.tests.java.parkinglotsystem;

import org.junit.Before;
import org.junit.Test;

import src.main.java.parking.management.Command;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    private Command command;

    @Before
    public void setUp() {
        command = new NewCommand();
    }

    @Test
    public void testGetCommandName() {
        String expectedCommandName = "new_command";
        String actualCommandName = command.getCommandName();
        assertEquals(expectedCommandName, actualCommandName);
    }

    @Test
    public void testGetDisplayName() {
        String expectedDisplayName = "New Command";
        String actualDisplayName = command.getDisplayName();
        assertEquals(expectedDisplayName, actualDisplayName);
    }

    @Test
    public void testExecute() {
        Properties params = new Properties();
        params.setProperty("param3", "value3");
        params.setProperty("param4", "value4");

        String expectedExecutionResult = "New command executed with params: param3=value3, param4=value4";

    
        String actualExecutionResult = command.execute(params);

        assertEquals(expectedExecutionResult, actualExecutionResult);
    }

    
    private class NewCommand implements Command {
        @Override
        public String getCommandName() {
            return "new_command";
        }

        @Override
        public String getDisplayName() {
            return "New Command";
        }

        @Override
        public String execute(Properties params) {
            StringBuilder result = new StringBuilder("New command executed with params: ");
            params.forEach((key, value) -> result.append(key).append("=").append(value).append(", "));
            return result.toString().replaceAll(", $", "");
        }
    }
}