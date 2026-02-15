import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * LoginScreen.java (UPDATED VERSION)
 * 1. Works with the new registration system
 * 2. Uses hashed passwords for security
 * 3. Validates credentials from the credentials.txt file
 * 4. Has a "Create Account" button for new users
 */
public class LoginScreen {
    
    public LoginScreen() {
        
        // ==========================================
        // STEP 1: CREATE THE LOGIN WINDOW
        // ==========================================
        //Color improvements
        Color softCream = new Color(255, 253, 208);      // Main background (softer yellow)
        Color cardBackground = new Color(255, 255, 240); // Even lighter for panels/cards

    // TEXT COLORS
        Color darkIndigo = new Color(75, 0, 130);        // Headers (instead of bright purple)
        Color charcoalGray = new Color(51, 51, 51);      // Body text (easier to read)
        Color mutedPurple = new Color(106, 13, 173);     // Secondary text/labels

    // BUTTON COLORS
    Color forestGreen = new Color(34, 139, 34);      // Login button
    Color royalBlue = new Color(65, 105, 225);       // Create Account button
    Color buttonHoverGreen = new Color(46, 184, 46); // Login hover
    Color buttonHoverBlue = new Color(85, 125, 245); // Create Account hover

    // ACCENTS
    Color errorRed = new Color(220, 53, 69);         // Error messages
    Color borderGray = new Color(200, 200, 200);     // Input field borders

            
        JFrame loginframe = new JFrame("Login Screen");
        loginframe.setSize(400, 450); // Made slightly taller to fit the new button
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setLayout(null);
        loginframe.getContentPane().setBackground(softCream);
        loginframe.setResizable(false);

        // ==========================================
        // STEP 2: TITLE LABEL
        // ==========================================
        
        JLabel title = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        title.setForeground(darkIndigo);
        title.setBounds(0, 10, 400, 40);
        loginframe.add(title);

        // ==========================================
        // STEP 3: USERNAME INPUT
        // ==========================================
        
        JLabel loginlabel = new JLabel("Enter Username:");
        loginlabel.setBounds(100, 60, 200, 30);
        loginlabel.setForeground(darkIndigo);
        loginframe.add(loginlabel);
        loginlabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        JTextField username = new JTextField();
        username.setBounds(100, 90, 200, 30);
        username.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loginframe.add(username);

        // ==========================================
        // STEP 4: PASSWORD INPUT
        // ==========================================
        
        JLabel loginPassword = new JLabel("Enter Password:");
        loginPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
        loginPassword.setBounds(100, 140, 200, 30);
        loginPassword.setForeground(darkIndigo);
        loginframe.add(loginPassword);

        JPasswordField password = new JPasswordField();
        password.setBounds(100, 170, 200, 30);
        password.setFont(new Font("SansSerif", Font.PLAIN, 14));
        loginframe.add(password);

        // ==========================================
        // STEP 5: LOGIN BUTTON
        // ==========================================
        
        JButton loginbutton = new JButton("Login");
        loginbutton.setBounds(100, 230, 200, 40);
        loginbutton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginbutton.setBackground(Color.decode("#4CAF50"));
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFocusPainted(false);
        loginframe.add(loginbutton);

        // ==========================================
        // STEP 6: CREATE ACCOUNT BUTTON (NEW!)
        // ==========================================
        
        JButton createAccountButton = new JButton("Create New Account");
        createAccountButton.setBounds(100, 280, 200, 40);
        createAccountButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        createAccountButton.setBackground(Color.decode("#2196F3")); // Blue color
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        loginframe.add(createAccountButton);
        
        // Helpful text below the button
        JLabel hintLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        hintLabel.setBounds(100, 325, 200, 20);
        hintLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        hintLabel.setForeground(Color.DARK_GRAY);
        loginframe.add(hintLabel);

        // ==========================================
        // STEP 7: LOGIN BUTTON ACTION LISTENER
        // ==========================================
        
        loginbutton.addActionListener(e -> {
            
            // Get the username and password from the fields
            String userid = username.getText().trim();
            String pass = new String(password.getPassword());
            
            // -----------------------------------
            // VALIDATION: Check if fields are empty
            // -----------------------------------
            if (userid.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(loginframe, 
                    "Please enter username and password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // -----------------------------------
            // AUTHENTICATION: Verify credentials
            // -----------------------------------
            
            // Hash the password that user entered
            String hashedInputPassword = hashPassword(pass);
            
            if (hashedInputPassword == null) {
                JOptionPane.showMessageDialog(loginframe, 
                    "Authentication error. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if credentials are valid
            if (authenticateUser(userid, hashedInputPassword)) {
                // SUCCESS! User credentials are correct
                loginframe.dispose(); // Close login window
                new BankingApp(userid); // Open banking app
            } else {
                // FAILURE! Invalid credentials
                JOptionPane.showMessageDialog(loginframe, 
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
                
                // Clear the password field for security
                password.setText("");
            }
        });
        
        // ==========================================
        // STEP 8: CREATE ACCOUNT BUTTON ACTION
        // ==========================================
        
        createAccountButton.addActionListener(e -> {
            loginframe.dispose(); // Close login window
            new RegistrationScreen(); // Open registration window
        });
        
        // ==========================================
        // STEP 9: ALLOW ENTER KEY TO LOGIN
        // ==========================================
        
        // When user presses Enter in password field, trigger login
        password.addActionListener(e -> loginbutton.doClick());
        
        // ==========================================
        // STEP 10: SHOW THE WINDOW
        // ==========================================
        
        loginframe.setLocationRelativeTo(null); // Center window on screen
        loginframe.setVisible(true);
    }

    /**
     * ==========================================
     * AUTHENTICATION METHOD
     * ==========================================
     * 
     * This method checks if the username and password are valid.
     * 
     * HOW IT WORKS:
     * 1. Opens the credentials.txt file
     * 2. Reads each line (format: username:hashedPassword)
     * 3. Splits each line by the ":" character
     * 4. Compares the username and hashed password
     * 5. Returns true if match found, false otherwise
     * 
     * PARAMETERS:
     * @param username - The username to check
     * @param hashedPassword - The hashed password to verify
     * 
     * RETURNS:
     * @return true if credentials are valid, false otherwise
     */
    private boolean authenticateUser(String username, String hashedPassword) {
        
        // Check if credentials file exists
        File credFile = new File("credentials.txt");
        
        if (!credFile.exists()) {
            // No users registered yet
            return false;
        }
        
        try {
            // Open the credentials file
            Scanner scanner = new Scanner(credFile);
            
            // Read each line in the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Split the line by ":" to get username and password
                // Example line: "Orest:03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"
                String[] parts = line.split(":", 2); // 2 means split into maximum 2 parts
                
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    
                    // Check if username and password match
                    if (storedUsername.equals(username) && storedPassword.equals(hashedPassword)) {
                        scanner.close();
                        return true; // Authentication successful!
                    }
                }
            }
            
            scanner.close();
            return false; // No matching credentials found
            
        } catch (FileNotFoundException e) {
            // File not found (shouldn't happen since we checked, but just in case)
            return false;
        }
    }
    
    /**
     * ==========================================
     * PASSWORD HASHING METHOD
     * ==========================================
     * 
     * This is the SAME hashing method used in RegistrationScreen.
     * We need it here to hash the login password so we can compare it
     * to the stored hash.
     * 
     * IMPORTANT: This method MUST be identical to the one in RegistrationScreen,
     * otherwise the hashes won't match!
     */
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ==========================================
     * MAIN METHOD
     * ==========================================
     * 
     * This is where your program starts!
     */
    public static void main(String[] args) {
        // Always run Swing applications on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}
