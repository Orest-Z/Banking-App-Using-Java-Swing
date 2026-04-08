import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginScreen {

    public LoginScreen() {

        // ==========================================
        // COLORS
        // ==========================================
        Color softCream = new Color(255, 253, 208);
        Color cardBackground = new Color(255, 255, 240);
        Color darkIndigo = new Color(75, 0, 130);
        Color charcoalGray = new Color(51, 51, 51);
        Color mutedPurple = new Color(106, 13, 173);
        Color forestGreen = new Color(34, 139, 34);
        Color royalBlue = new Color(65, 105, 225);
        Color buttonHoverGreen = new Color(46, 184, 46);
        Color buttonHoverBlue = new Color(85, 125, 245);
        Color errorRed = new Color(220, 53, 69);
        Color borderGray = new Color(200, 200, 200);
        Color forgotColor = new Color(100, 100, 200);

        // ==========================================
        // FONTS
        // ==========================================
        Font headerFont = new Font("Segoe UI", Font.BOLD, 28);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);
        Font smallFont = new Font("Segoe UI", Font.ITALIC, 12);
        Font linkFont = new Font("Segoe UI", Font.PLAIN, 12);

        JFrame loginframe = new JFrame("Login Screen");
        loginframe.setSize(400, 500);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setLayout(null);
        loginframe.getContentPane().setBackground(softCream);
        loginframe.setResizable(false);

        // ==========================================
        // MENU BAR WITH HELP
        // ==========================================

        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem faqItem = new JMenuItem("FAQ");
        JMenuItem contactItem = new JMenuItem("Contact Support");

        helpMenu.add(aboutItem);
        helpMenu.add(faqItem);
        helpMenu.addSeparator();
        helpMenu.add(contactItem);

        menuBar.add(helpMenu);
        loginframe.setJMenuBar(menuBar);

        // Help menu actions (placeholder dialogs for now)
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(loginframe,
                "Banking App v2.3\nDeveloped for educational purposes.\n\nMore info coming soon.",
                "About", JOptionPane.INFORMATION_MESSAGE));

        faqItem.addActionListener(e -> JOptionPane.showMessageDialog(loginframe,
                "FAQ\n\n" +
                        "Q: How do I create an account?\nA: Click 'Create New Account' on the login screen.\n\n" +
                        "Q: I forgot my password. What do I do?\nA: Click 'Forgot Password?' below the login button.\n\n" +
                        "Q: Is my data safe?\nA: Passwords are stored using SHA-256 hashing.\n\n" +
                        "More FAQs coming soon.",
                "FAQ", JOptionPane.INFORMATION_MESSAGE));

        contactItem.addActionListener(e -> JOptionPane.showMessageDialog(loginframe,
                "Contact Support\n\nEmail: support@bankingapp.com\nPhone: +1 (555) 000-0000\n\nSupport hours: Mon-Fri, 9AM - 5PM",
                "Contact Support", JOptionPane.INFORMATION_MESSAGE));

        // ==========================================
        // TITLE
        // ==========================================

        JLabel title = new JLabel("WELCOME BACK", SwingConstants.CENTER);
        title.setFont(headerFont);
        title.setForeground(darkIndigo);
        title.setBounds(0, 10, 400, 40);
        loginframe.add(title);

        // ==========================================
        // USERNAME INPUT
        // ==========================================

        JLabel loginlabel = new JLabel("Enter Username:");
        loginlabel.setBounds(100, 60, 200, 30);
        loginlabel.setForeground(mutedPurple);
        loginlabel.setFont(labelFont);
        loginframe.add(loginlabel);

        JTextField username = new JTextField();
        username.setForeground(charcoalGray);
        username.setBounds(100, 90, 200, 30);
        username.setFont(inputFont);
        username.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginframe.add(username);

        // ==========================================
        // PASSWORD INPUT
        // ==========================================

        JLabel loginPassword = new JLabel("Enter Password:");
        loginPassword.setFont(labelFont);
        loginPassword.setBounds(100, 140, 200, 30);
        loginPassword.setForeground(mutedPurple);
        loginframe.add(loginPassword);

        JPasswordField password = new JPasswordField();
        password.setBounds(100, 170, 200, 30);
        password.setFont(inputFont);
        password.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        loginframe.add(password);

        // ==========================================
        // FORGOT PASSWORD LINK
        // ==========================================

        JLabel forgotLabel = new JLabel("<html><u>Forgot Password?</u></html>", SwingConstants.RIGHT);
        forgotLabel.setBounds(100, 205, 200, 20);
        forgotLabel.setFont(linkFont);
        forgotLabel.setForeground(forgotColor);
        forgotLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginframe.add(forgotLabel);

        forgotLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                forgotLabel.setForeground(new Color(60, 60, 180));
            }
            public void mouseExited(MouseEvent evt) {
                forgotLabel.setForeground(forgotColor);
            }
            public void mouseClicked(MouseEvent evt) {
                showForgotPasswordDialog(loginframe, borderGray);
            }
        });

        // ==========================================
        // LOGIN BUTTON
        // ==========================================

        JButton loginbutton = new JButton("Login");
        loginbutton.setBounds(100, 235, 200, 40);
        loginbutton.setFont(buttonFont);
        loginbutton.setBackground(forestGreen);
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setFocusPainted(false);
        loginframe.add(loginbutton);
        loginbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginbutton.setBackground(buttonHoverGreen);
            }
            public void mouseExited(MouseEvent evt) {
                loginbutton.setBackground(forestGreen);
            }
        });

        // ==========================================
        // CREATE ACCOUNT BUTTON
        // ==========================================

        JButton createAccountButton = new JButton("Create New Account");
        createAccountButton.setBounds(100, 285, 200, 40);
        createAccountButton.setFont(buttonFont);
        createAccountButton.setBackground(royalBlue);
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        loginframe.add(createAccountButton);
        createAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                createAccountButton.setBackground(buttonHoverBlue);
            }
            public void mouseExited(MouseEvent evt) {
                createAccountButton.setBackground(royalBlue);
            }
        });

        JLabel hintLabel = new JLabel("Don't have an account?", SwingConstants.CENTER);
        hintLabel.setBounds(100, 330, 200, 20);
        hintLabel.setFont(smallFont);
        hintLabel.setForeground(Color.DARK_GRAY);
        loginframe.add(hintLabel);

        // ==========================================
        // LOGIN BUTTON ACTION
        // ==========================================

        loginbutton.addActionListener(e -> {
            String userid = username.getText().trim();
            String pass = new String(password.getPassword());

            if (userid.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(loginframe,
                        "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String hashedInputPassword = hashPassword(pass);

            if (hashedInputPassword == null) {
                JOptionPane.showMessageDialog(loginframe,
                        "Authentication error. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (authenticateUser(userid, hashedInputPassword)) {
                loginframe.dispose();
                new BankingApp(userid);
            } else {
                JOptionPane.showMessageDialog(loginframe,
                        "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                password.setText("");
            }
        });

        // ==========================================
        // CREATE ACCOUNT BUTTON ACTION
        // ==========================================

        createAccountButton.addActionListener(e -> {
            loginframe.dispose();
            new RegistrationScreen();
        });

        // Enter key triggers login
        password.addActionListener(e -> loginbutton.doClick());

        loginframe.setLocationRelativeTo(null);
        loginframe.setVisible(true);
    }

    // ==========================================
    // FORGOT PASSWORD DIALOG
    // ==========================================

    private void showForgotPasswordDialog(JFrame parent, Color borderGray) {
        JDialog dialog = new JDialog(parent, "Reset Password", true);
        dialog.setSize(380, 280);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(new Color(255, 253, 208));
        dialog.setLocationRelativeTo(parent);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);
        Color mutedPurple = new Color(106, 13, 173);

        JLabel infoLabel = new JLabel("Enter your username and a new password:");
        infoLabel.setBounds(20, 15, 340, 20);
        infoLabel.setFont(labelFont);
        infoLabel.setForeground(mutedPurple);
        dialog.add(infoLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 50, 120, 20);
        userLabel.setFont(labelFont);
        dialog.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(20, 72, 330, 30);
        userField.setFont(inputFont);
        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        dialog.add(userField);

        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(20, 112, 120, 20);
        newPassLabel.setFont(labelFont);
        dialog.add(newPassLabel);

        JPasswordField newPassField = new JPasswordField();
        newPassField.setBounds(20, 134, 330, 30);
        newPassField.setFont(inputFont);
        newPassField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        dialog.add(newPassField);

        JLabel confirmLabel = new JLabel("Confirm New Password:");
        confirmLabel.setBounds(20, 174, 200, 20);
        confirmLabel.setFont(labelFont);
        dialog.add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(20, 196, 330, 30);
        confirmField.setFont(inputFont);
        confirmField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        dialog.add(confirmField);

        JButton resetBtn = new JButton("Reset Password");
        resetBtn.setBounds(90, 238, 190, 32);
        resetBtn.setFont(buttonFont);
        resetBtn.setBackground(new Color(65, 105, 225));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        dialog.add(resetBtn);

        dialog.setSize(380, 290);

        resetBtn.addActionListener(e -> {
            String uname = userField.getText().trim();
            String newPass = new String(newPassField.getPassword());
            String confirmPass = new String(confirmField.getPassword());

            if (uname.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(dialog, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (newPass.length() < 6) {
                JOptionPane.showMessageDialog(dialog, "Password must be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!userExists(uname)) {
                JOptionPane.showMessageDialog(dialog, "Username not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (updatePasswordInFile(uname, hashPassword(newPass))) {
                JOptionPane.showMessageDialog(dialog, "Password reset successfully! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Error updating password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    // ==========================================
    // HELPER: Check if a username exists
    // ==========================================

    static boolean userExists(String username) {
        File credFile = new File("credentials.txt");
        if (!credFile.exists()) return false;
        try (Scanner sc = new Scanner(credFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                String[] parts = line.split(":", 2);
                if (parts.length == 2 && parts[0].equals(username)) return true;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    // ==========================================
    // HELPER: Update password in credentials.txt
    // ==========================================

    private boolean updatePasswordInFile(String username, String newHashedPassword) {
        File credFile = new File("credentials.txt");
        if (!credFile.exists()) return false;

        StringBuilder newContent = new StringBuilder();
        boolean found = false;

        try (Scanner sc = new Scanner(credFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(":", 2);
                if (parts.length == 2 && parts[0].equals(username)) {
                    newContent.append(username).append(":").append(newHashedPassword).append("\n");
                    found = true;
                } else {
                    newContent.append(line).append("\n");
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }

        if (!found) return false;

        try (PrintWriter pw = new PrintWriter(new FileWriter(credFile))) {
            pw.print(newContent);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    // ==========================================
    // AUTHENTICATE USER
    // ==========================================

    private boolean authenticateUser(String username, String hashedPassword) {
        File credFile = new File("credentials.txt");
        if (!credFile.exists()) return false;
        try (Scanner scanner = new Scanner(credFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(":", 2);
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(hashedPassword)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }

    // ==========================================
    // HASH PASSWORD (SHA-256)
    // ==========================================

    static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}