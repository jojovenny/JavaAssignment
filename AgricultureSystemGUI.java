# JavaAssignment
  import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgricultureSystemGUI extends JFrame {
    private JTextArea textArea;
    private String registeredUsername;
    private String registeredPassword;

    // Constructor for the JFrame
    public AgricultureSystemGUI() {
        setTitle("Agriculture and Farming System");
        setSize(850, 450);  // Adjusted width for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the heading label and add it to the top of the panel
        JLabel headingLabel = new JLabel("<html><center><b>WELCOME TO JOVITHA<br>AGRICULTURE AND FARMING APPLICATION SYSTEM</b></center></html>");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font to bold
        panel.add(headingLabel, BorderLayout.NORTH); // Add the heading label at the top

        // Create an area for the image above the Weather Forecast button
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout()); // Use BorderLayout to center the image
        JLabel imageLabel = new JLabel(); // Label to display the image
        String imagePath = "C:\\Users\\hillarius\\Desktop\\images\\agri.jpeg"; // Full image path

        // Load the image, resize it to 450px width, and maintain aspect ratio
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage(); // Get the original image
        Image resizedImage = image.getScaledInstance(500, -1, Image.SCALE_SMOOTH); // Resize to 450px width
        imageIcon = new ImageIcon(resizedImage); // Create new ImageIcon with resized image

        imageLabel.setIcon(imageIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER); // Add the image to the center of the panel

        // Add the image panel to the main panel on the left side (using BorderLayout)
        panel.add(imagePanel, BorderLayout.CENTER); // This will place the image above the buttons and below the heading

        // Create a panel for the text area to display the output (next to the image)
        JPanel textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(250, 300)); // Set the preferred size for the text area
        textArea.setMargin(new Insets(5, 5, 5, 5)); // Adjust margins to squeeze the content inside the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        textPanel.add(scrollPane, BorderLayout.CENTER); // Add the scrollable text area

        // Add the text panel to the right of the image panel
        panel.add(textPanel, BorderLayout.EAST); // This places the output area to the right of the image

        // Create a button panel (buttons will now be arranged horizontally at the bottom)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal layout with centered buttons

        // Define buttons
        JButton weatherButton = new JButton("Weather Forecast");
        JButton pestButton = new JButton("Pest Identification");
        JButton marketButton = new JButton("Market Prices");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(weatherButton);
        buttonPanel.add(pestButton);
        buttonPanel.add(marketButton);
        buttonPanel.add(exitButton);

        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom of the screen

        add(panel);

        // Action listeners for the buttons
        weatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weatherForecast();
            }
        });

        pestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pestIdentification();
            }
        });

        marketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marketPrices();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Prompt for registration and login
        registerAndLogin();
    }

    // Function to prompt for registration and login
    public void registerAndLogin() {
        // Ask the user to register
        JOptionPane.showMessageDialog(this, "Please register first!");

        // Show registration dialog
        JPanel registerPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        registerPanel.add(new JLabel("Enter username:"));
        registerPanel.add(usernameField);
        registerPanel.add(new JLabel("Enter password:"));
        registerPanel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(this, registerPanel, "User Registration", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            registeredUsername = usernameField.getText();
            registeredPassword = new String(passwordField.getPassword());

            JOptionPane.showMessageDialog(this, "Registration successful! Now please log in.");

            // Show login dialog
            login();
        }
    }

    // Function for user login
    public void login() {
        // Ask the user to login
        JPanel loginPanel = new JPanel(new GridLayout(2, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        loginPanel.add(new JLabel("Enter username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Enter password:"));
        loginPanel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(this, loginPanel, "User Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String inputUsername = usernameField.getText();
            String inputPassword = new String(passwordField.getPassword());

            // Validate login credentials
            if (inputUsername.equals(registeredUsername) && inputPassword.equals(registeredPassword)) {
                textArea.setText("Login successful! Welcome, " + registeredUsername + ".\n");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
                login(); // Retry login if credentials are incorrect
            }
        }
    }

    // Function for weather forecasting
    public void weatherForecast() {
        textArea.setText("");
        String region = JOptionPane.showInputDialog(this, "Enter the region for weather forecast:");
        if (region != null) {
            textArea.append("Weather forecast for " + region + ":\n");
            textArea.append("Temperature: 28°C, Rain: Moderate, Wind: Light\n");
            textArea.append("Be sure to plan your farming activities accordingly!\n");

            // Display the size of the output area
            Dimension size = textArea.getSize();
            textArea.append("\nOutput area size: Width = " + size.width + ", Height = " + size.height + " pixels\n");
        }
    }

    // Function for pest identification
    public void pestIdentification() {
        textArea.setText("");
        String pest = JOptionPane.showInputDialog(this, "Enter the name of the pest to identify:");
        if (pest != null) {
            switch (pest.toLowerCase()) {
                case "locust":
                    textArea.append("Locust: A destructive pest that destroys crops. Control with pesticides.\n");
                    break;
                case "aphid":
                    textArea.append("Aphid: A small insect that damages plants by feeding on sap. Use insecticidal soap.\n");
                    break;
                default:
                    textArea.append("Pest not recognized. Please contact an agricultural expert.\n");
                    break;
            }

            // Display the size of the output area
            Dimension size = textArea.getSize();
            textArea.append("\nOutput area size: Width = " + size.width + ", Height = " + size.height + " pixels\n");
        }
    }

    // Function for market prices
    public void marketPrices() {
        textArea.setText("");
        String product = JOptionPane.showInputDialog(this, "Enter the product to get current market price:");
        if (product != null) {
            switch (product.toLowerCase()) {
                case "maize":
                    textArea.append("Current price for Maize: TZS 1,200 per kg\n");
                    break;
                case "coffee":
                    textArea.append("Current price for Coffee: TZS 3,500 per kg\n");
                    break;
                default:
                    textArea.append("Product not recognized. Please check again.\n");
                    break;
            }

            // Display the size of the output area
            Dimension size = textArea.getSize();
            textArea.append("\nOutput area size: Width = " + size.width + ", Height = " + size.height + " pixels\n");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Define the path to the image folder
        String imageFolderPath = "C:\\Users\\hillarius\\Desktop\\images\\";

        // Create the full path for the image
        String imagePath = imageFolderPath + "agri.jpeg";  // Replace with your actual image filename

        // Run the GUI with the image path
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgricultureSystemGUI().setVisible(true);
            }
        });
    }
}
