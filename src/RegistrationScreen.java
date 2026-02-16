import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * RegistrationScreen.java
 * 
 * This class creates a user-friendly registration form where new users can:
 * 1. Create a username
 * 2. Set a secure password
 * 3. Confirm their password
 * 4. Register their account safely
 * 
 * SECURITY FEATURES:
 * - Passwords are HASHED (not stored as plain text)
 * - Usernames are checked for duplicates
 * - Password strength validation
 * - Input sanitization
 */
public class RegistrationScreen {
    
    // CONSTRUCTOR: This runs when you create a new RegistrationScreen object
    public RegistrationScreen() {

        //Colors and fonts improvements
        // COLOR SCHEME (matching LoginScreen)
        Color softCream = new Color(255, 253, 208);
        Color darkIndigo = new Color(75, 0, 130);
        Color charcoalGray = new Color(51, 51, 51);
        Color mutedPurple = new Color(106, 13, 173);
        Color forestGreen = new Color(34, 139, 34);
        Color lightGray = new Color(189, 189, 189);
        Color borderGray = new Color(200, 200, 200);

        // FONTS (matching LoginScreen)
        Font headerFont = new Font("Segoe UI", Font.BOLD, 28);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
        Font smallFont = new Font("Segoe UI", Font.ITALIC, 11);

        // ==========================================
        // STEP 1: CREATE THE MAIN WINDOW (FRAME)
        // ==========================================
        
        JFrame registerFrame = new JFrame("Create New Account");
        registerFrame.setSize(450, 500);  // Width: 450px, Height: 500px
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not entire app
        registerFrame.setLayout(null);  // null layout = we manually position everything with setBounds()
        registerFrame.getContentPane().setBackground(softCream);
        registerFrame.setResizable(false); // User can't resize the window

        // ==========================================
        // STEP 2: CREATE THE TITLE LABEL
        // ==========================================
        
        JLabel titleLabel = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        titleLabel.setFont(headerFont);
        titleLabel.setForeground(darkIndigo); // Purple color
        titleLabel.setBounds(0, 20, 450, 40); // x, y, width, height
        registerFrame.add(titleLabel);
        
        // ==========================================
        // STEP 3: USERNAME FIELD
        // ==========================================
        
        // Label that says "Username:"
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 80, 200, 30);
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(mutedPurple);
        registerFrame.add(usernameLabel);
        
        // Text field where user types their username
        JTextField usernameField = new JTextField();
        usernameField.setBounds(80, 115, 280, 35);
        usernameField.setFont(inputFont);
        registerFrame.add(usernameField);

        usernameField.setForeground(charcoalGray);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        // Hint text below the username field
        JLabel usernameHint = new JLabel("(3-20 characters, letters and numbers only)");
        usernameHint.setBounds(80, 150, 300, 20);
        usernameHint.setFont(smallFont);
        usernameHint.setForeground(charcoalGray);
        registerFrame.add(usernameHint);
        
        // ==========================================
        // STEP 4: PASSWORD FIELD
        // ==========================================
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 180, 200, 30);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(mutedPurple);
        registerFrame.add(passwordLabel);
        
        // JPasswordField hides the password with dots (••••)
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(80, 215, 280, 35);
        passwordField.setFont(inputFont);
        registerFrame.add(passwordField);
        passwordField.setForeground(charcoalGray);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
        JLabel passwordHint = new JLabel("(Minimum 6 characters)");
        passwordHint.setBounds(80, 250, 300, 20);
        passwordHint.setFont(smallFont);
        passwordHint.setForeground(charcoalGray);
        registerFrame.add(passwordHint);
        
        // ==========================================
        // STEP 5: CONFIRM PASSWORD FIELD
        // ==========================================
        
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(80, 280, 200, 30);
        confirmPasswordLabel.setFont(smallFont);
        confirmPasswordLabel.setForeground(mutedPurple);
        registerFrame.add(confirmPasswordLabel);
        
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(80, 315, 280, 35);
        confirmPasswordField.setFont(inputFont);
        registerFrame.add(confirmPasswordField);
        confirmPasswordField.setForeground(charcoalGray);
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        // ==========================================
        // STEP 6: REGISTER BUTTON
        // ==========================================
        
        JButton registerButton = new JButton("Register Account");
        registerButton.setBounds(80, 370, 280, 45);
        registerButton.setFont(buttonFont);
        registerButton.setBackground(forestGreen); // Green color
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false); // Removes the ugly border when clicked
        registerFrame.add(registerButton);
        // ADD THIS ENTIRE BLOCK after registerFrame.add(registerButton);
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(46, 184, 46)); // buttonHoverGreen
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(forestGreen);
            }
        });
        
        // ==========================================
        // STEP 7: BACK TO LOGIN BUTTON
        // ==========================================
        
        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(80, 425, 280, 35);
        backButton.setFont(buttonFont);
        backButton.setBackground(lightGray);
        registerFrame.add(backButton);

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(169, 169, 169)); // darker gray
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(lightGray);
            }
        });

        // ==========================================
        // STEP 8: REGISTER BUTTON ACTION LISTENER
        // This code runs when the user clicks "Register Account"
        // ==========================================
        
        registerButton.addActionListener(e -> {
            
            // Get the text from all three fields
            String username = usernameField.getText().trim(); // trim() removes spaces from start and end
            String password = new String(passwordField.getPassword()); // Convert char[] to String
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            // -----------------------------------
            // VALIDATION STEP 1: Check if fields are empty
            // -----------------------------------
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Please fill in all fields!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return; // Stop here if validation fails
            }
            
            // -----------------------------------
            // VALIDATION STEP 2: Check username format
            // -----------------------------------
            // Username must be 3-20 characters and only contain letters and numbers
            if (username.length() < 3 || username.length() > 20) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Username must be between 3 and 20 characters!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Pattern.matches() checks if the username matches the pattern
            // ^[a-zA-Z0-9]+$ means: start(^), one or more(+) letters(a-zA-Z) or numbers(0-9), end($)
            if (!Pattern.matches("^[a-zA-Z0-9]+$", username)) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Username can only contain letters and numbers!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // -----------------------------------
            // VALIDATION STEP 3: Check password strength
            // -----------------------------------
            if (password.length() < 6) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Password must be at least 6 characters long!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // -----------------------------------
            // VALIDATION STEP 4: Check if passwords match
            // -----------------------------------
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Passwords do not match!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                // Clear both password fields so user can re-enter
                passwordField.setText("");
                confirmPasswordField.setText("");
                return;
            }
            
            // -----------------------------------
            // VALIDATION STEP 5: Check if username already exists
            // -----------------------------------
            // We check if a file called "username_data.txt" already exists
            File userFile = new File(username + "_data.txt");
            if (userFile.exists()) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Username already exists! Please choose another one.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                usernameField.setText(""); // Clear the username field
                return;
            }
            
            // -----------------------------------
            // STEP 9: HASH THE PASSWORD
            // -----------------------------------
            // We NEVER store passwords in plain text!
            // Hashing is a one-way function that converts "1234" into something like "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"
            String hashedPassword = hashPassword(password);
            
            if (hashedPassword == null) {
                JOptionPane.showMessageDialog(registerFrame, 
                    "Error creating account. Please try again.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // -----------------------------------
            // STEP 10: CREATE THE USER FILE
            // -----------------------------------
            // We create a file called "username_data.txt" with the hashed password
            try {
                // Create the file
                File dataFile = new File(username + "_data.txt");
                FileWriter fw = new FileWriter(dataFile);
                PrintWriter pw = new PrintWriter(fw);
                
                // Write the hashed password on the first line
                pw.println("PASSWORD:" + hashedPassword);
                
                // Write initial balance (0.0) on the second line
                pw.println("0.0");
                
                // The rest of the file will be transaction history (empty for now)
                
                pw.close();
                fw.close();
                
                // -----------------------------------
                // STEP 11: CREATE CREDENTIALS FILE
                // -----------------------------------
                // We also create a master file that stores all usernames and their hashed passwords
                // This makes login verification faster
                FileWriter credFw = new FileWriter("credentials.txt", true); // true = append to file
                PrintWriter credPw = new PrintWriter(credFw);
                credPw.println(username + ":" + hashedPassword);
                credPw.close();
                credFw.close();
                
                // Show success message
                JOptionPane.showMessageDialog(registerFrame, 
                    "Account created successfully!\nYou can now login with your credentials.", 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Close registration window and open login screen
                registerFrame.dispose();
                new LoginScreen();
                
            } catch (IOException ex) {
                // If something goes wrong with file creation
                JOptionPane.showMessageDialog(registerFrame, 
                    "Error creating account: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // ==========================================
        // STEP 12: BACK BUTTON ACTION LISTENER
        // ==========================================
        backButton.addActionListener(e -> {
            registerFrame.dispose(); // Close registration window
            new LoginScreen(); // Open login screen
        });
        
        // ==========================================
        // STEP 13: SHOW THE WINDOW
        // ==========================================
        registerFrame.setLocationRelativeTo(null); // Center the window on screen
        registerFrame.setVisible(true); // Make the window visible
    }
    
    /**
     * ==========================================
     * PASSWORD HASHING METHOD
     * ==========================================
     * 
     * This method takes a plain text password and converts it into a hashed version.
     * 
     * HOW IT WORKS:
     * 1. We use SHA-256 algorithm (Secure Hash Algorithm)
     * 2. SHA-256 takes any input and produces a 64-character hexadecimal output
     * 3. It's a ONE-WAY function - you can't reverse it to get the original password
     * 4. The same password always produces the same hash
     * 
     * EXAMPLE:
     * Input: "password123"
     * Output: "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f"
     * 
     * WHY THIS IS SECURE:
     * - Even if someone steals the file, they can't see the real password
     * - When user logs in, we hash their input and compare it to the stored hash
     * - If hashes match, password is correct!
     */
    private String hashPassword(String password) {
        try {
            // MessageDigest is Java's built-in class for hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // Convert password to bytes and hash it
            byte[] hashBytes = md.digest(password.getBytes());
            
            // Convert bytes to hexadecimal string (easier to store and compare)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                // Convert each byte to a 2-digit hex number
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Add leading zero if needed
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            // This should never happen since SHA-256 is always available
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * ==========================================
     * MAIN METHOD (FOR TESTING)
     * ==========================================
     * 
     * This allows you to run the RegistrationScreen independently for testing
     * Usage: Right-click this file and select "Run"
     */
    public static void main(String[] args) {
        // Run on the Event Dispatch Thread (EDT) - required for Swing applications
        SwingUtilities.invokeLater(() -> new RegistrationScreen());
    }
}
