/**
 * The ParkingGui class is a GUI for interacting
 * with the parking lot system
 * 
 *
 *
 * @author (Maddie Hirschfeld)
 * @version (November 18, 2023)
 */

package src.main.java.client;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import src.main.java.parking.management.ParkingOffice;
import src.main.java.parking.transactionManager.TransactionManager;
import src.main.java.server.ParkingService;

@SuppressWarnings("serial")
public class ParkingGui extends JFrame {

  private final JTextArea textArea;
  private final ParkingService parkingService;

  public ParkingGui(List<ClientOpsCommand> commands, ParkingService parkingService) {
    //main panel for GUI
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    this.parkingService =parkingService;

    //GUI dimensions
    setPreferredSize(new Dimension(1100, 400));
    getContentPane().setLayout(new GridLayout()); // the default GridLayout is a 1x1 grid
    getContentPane().add(mainPanel);

    //panel for buttons and text area for output
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    mainPanel.add(buttonPanel);
    textArea = new JTextArea();
    mainPanel.add(textArea);

    //buttons for each command
    for (ClientOpsCommand cmd : commands) {
      JButton button = new JButton(cmd.name());
      button.addActionListener(new CommandListener(textArea, cmd, parkingService));
      buttonPanel.add(button);
    }
    
    //quit button to exit application
    JButton quitButton = new JButton("Quit");
    buttonPanel.add(quitButton);

    quitButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });

    pack();
  }

  //method to display dialog for user input
  static Map<String, String> dialog(List<String> labels) {
    JPanel input = new JPanel(new GridBagLayout());
    GridBagConstraints config = new GridBagConstraints();

    List<JTextField> fields = new ArrayList<>();
    for (int i = 0; i < labels.size(); ++i) {
      JTextField field = new JTextField(20);
      fields.add(field);
      config.gridx = 0;
      config.gridy = i;
      input.add(new JLabel(labels.get(i)), config);
      config.gridx = 1;
      input.add(field, config);
    }
    int result = JOptionPane.showConfirmDialog(
        null, input, "Please Enter the Values", 
        JOptionPane.OK_CANCEL_OPTION);
    Map<String, String> values = new LinkedHashMap<>();
    if (result == JOptionPane.OK_OPTION) {
      for (int i = 0; i < labels.size(); ++i) {
        values.put(labels.get(i), fields.get(i).getText());
      }
    }
    return values;
  }

  public static void main(String[] args) {
    ParkingOffice parkingOffice = new ParkingOffice();
    TransactionManager transactionManager = new TransactionManager(null);

    ParkingService parkingService = new ParkingService(parkingOffice, transactionManager);
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        // Get commands from the ClientCommandFactory
        List<ClientOpsCommand> commands = new ArrayList<>();
        ClientCommandFactory.getAvailableCommands().forEach((commandKey, commandArray) -> {
            commands.add(new ClientOpsCommand(commandArray[0], commandKey,
                    Arrays.asList(commandArray).subList(1, commandArray.length)));
        });
  
        new ParkingGui(commands, parkingService).setVisible(true);
      }
    });
  }

  //handles button clicks
  private static class CommandListener implements ActionListener {
    private final ClientOpsCommand command;
    private final JTextArea textArea;
    private final ParkingService parkingService;

    CommandListener(JTextArea textArea, ClientOpsCommand command, ParkingService parkingService) {
      this.textArea = textArea;
      this.command = command;
      this.parkingService = parkingService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Map<String, String> values = dialog(command.fieldNames());
      String output = "";
      for (Map.Entry<String, String> entry : values.entrySet()) {
        output += String.format("%s: %s%n", entry.getKey(), entry.getValue());
      }
      textArea.setText(output);
      if (values.size() > 0) {
        // Convert the values map to a list of arguments
        List<String> args = new ArrayList<>(values.values());
        String response = parkingService.performCommand(command.getCommand().toUpperCase(), args);
        textArea.setText(String.format("%s%n%s", textArea.getText(), response));
      }
    }
  }
}