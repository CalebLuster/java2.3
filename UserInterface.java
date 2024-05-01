import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UserInterface extends JFrame {
    private JTextField textBox;
    private JMenu menu;
    private JMenuItem dateTimeOption, saveOption, colorOption, exitOption;

    public UserInterface() {
        setTitle("User Interface");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textBox = new JTextField();
        add(textBox, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Options");
        menuBar.add(menu);

        dateTimeOption = new JMenuItem("Print Data and Time");
        saveOption = new JMenuItem("Save to file");
        colorOption = new JMenuItem("Change Background Color");
        exitOption = new JMenuItem("Exit");

        menu.add(dateTimeOption);
        menu.add(saveOption);
        menu.add(colorOption);

        menu.addSeparator();

        menu.add(exitOption);

        dateTimeOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateAndTime = formatter.format(new Date());
                textBox.setText(currentDateAndTime);
            }
        });

        saveOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
                    writer.write(textBox.getText());
                    JOptionPane.showMessageDialog(null, "Contents saved to log.txt.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to save to file.");
                }
            }
        });

    colorOption.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Random rand = new Random();
            float hue = rand.nextFloat() * 0.33f; // Generate a random hue value between 0 and 120
            float saturation = 1.0f; // Set saturation to maximum
            float brightness = 0.8f; // Set brightness to a medium value

            // Create a Color object with the generated hue, saturation, and brightness values
            Color color = Color.getHSBColor(hue, saturation, brightness);

            // Set the background color of the text box to the generated color
            textBox.setBackground(color);
        }
    });

        exitOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        UserInterface frame = new UserInterface();
        frame.setVisible(true);
    }
    
}