import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BankingApp {
    private final String username;
    private double balance = 0.0;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    // COLORS
    Color softCream = new Color(255, 253, 208);
    Color darkIndigo = new Color(75, 0, 130);
    Color charcoalGray = new Color(51, 51, 51);
    Color mutedPurple = new Color(106, 13, 173);
    Color forestGreen = new Color(34, 139, 34);
    Color crimsonRed = new Color(220, 53, 69);
    Color goldenYellow = new Color(255, 193, 7);
    Color skyBlue = new Color(33, 150, 243);
    Color borderGray = new Color(200, 200, 200);
    Color logoutOrange = new Color(255, 152, 0);

    // FONTS
    Font headerFont = new Font("Segoe UI", Font.BOLD, 20);
    Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
    Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
    Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
    Font smallFont = new Font("Segoe UI", Font.PLAIN, 12);
    Font historyFont = new Font("Segoe UI", Font.PLAIN, 11);

    public BankingApp(String user) {
        this.username = user;

        JFrame frame = new JFrame("Banking App v2.3");
        frame.setSize(500, 650);
        frame.getContentPane().setBackground(softCream);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // ==========================================
        // LIVE CLOCK
        // ==========================================

        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(280, 10, 200, 30);
        timeLabel.setFont(smallFont);
        timeLabel.setForeground(mutedPurple);
        frame.add(timeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String liveTime = LocalDateTime.now().format(dtf);
                timeLabel.setText("Time: " + liveTime);
            }
        });
        timer.start();

        //==========================================
        // MENU BAR
        //==========================================
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save Data");
        JMenuItem downloadItem = new JMenuItem("Download Statement");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(saveItem);
        fileMenu.add(downloadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu accountMenu = new JMenu("Account");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem profileItem = new JMenuItem("View Profile");
        JMenuItem changePasswordItem = new JMenuItem("Change Password");
        JMenuItem settingsItem = new JMenuItem("Settings");
        accountMenu.add(profileItem);
        accountMenu.add(changePasswordItem);
        accountMenu.add(settingsItem);
        accountMenu.addSeparator();
        accountMenu.add(logoutItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem faqItem = new JMenuItem("FAQ");
        JMenuItem contactItem = new JMenuItem("Contact Support");
        helpMenu.add(aboutItem);
        helpMenu.add(faqItem);
        helpMenu.addSeparator();
        helpMenu.add(contactItem);

        menuBar.add(fileMenu);
        menuBar.add(accountMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // Help menu actions (placeholder for now)
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Banking App v2.3\nDeveloped for educational purposes.\n\nMore info coming soon.",
                "About", JOptionPane.INFORMATION_MESSAGE));

        faqItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "FAQ\n\n" +
                        "Q: How do I deposit money?\nA: Enter an amount in the Amount field and click Deposit.\n\n" +
                        "Q: How do I transfer to another user?\nA: Enter amount, type the recipient's username, click Transfer.\n\n" +
                        "Q: How do I save my data?\nA: Use File > Save Data or it will prompt you on logout/exit.\n\n" +
                        "More FAQs coming soon.",
                "FAQ", JOptionPane.INFORMATION_MESSAGE));

        contactItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Contact Support\n\nEmail: support@bankingapp.com\nPhone: +1 (555) 000-0000\n\nSupport hours: Mon-Fri, 9AM - 5PM",
                "Contact Support", JOptionPane.INFORMATION_MESSAGE));

        // ==========================================
        // WELCOME LABEL
        // ==========================================

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(labelFont);
        welcomeLabel.setForeground(darkIndigo);
        welcomeLabel.setBounds(50, 5, 200, 30);
        frame.add(welcomeLabel);

        // ==========================================
        // BALANCE DISPLAY
        // ==========================================

        JLabel label = new JLabel("Balance: $0.0");
        label.setBounds(50, 30, 200, 30);
        label.setFont(headerFont);
        label.setForeground(darkIndigo);
        frame.add(label);

        // ==========================================
        // AMOUNT INPUT FIELD
        // ==========================================

        JLabel amountLabel = new JLabel("Amount ($):");
        amountLabel.setBounds(50, 70, 120, 20);
        amountLabel.setFont(labelFont);
        amountLabel.setForeground(charcoalGray);
        frame.add(amountLabel);

        JTextField input = new JTextField();
        input.setBounds(50, 92, 180, 32);
        input.setFont(inputFont);
        input.setForeground(charcoalGray);
        input.setBackground(Color.WHITE);
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        frame.add(input);

        JLabel recipientLabel = new JLabel("Transfer to (Username):");
        recipientLabel.setBounds(250, 70, 200, 20);
        recipientLabel.setFont(labelFont);
        recipientLabel.setForeground(charcoalGray);
        frame.add(recipientLabel);

        JTextField recipientInput = new JTextField();
        recipientInput.setBounds(250, 92, 200, 32);
        recipientInput.setFont(inputFont);
        recipientInput.setForeground(charcoalGray);
        recipientInput.setBackground(Color.WHITE);
        recipientInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        frame.add(recipientInput);

        // ==========================================
        // ACTION BUTTONS
        // ==========================================

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setFont(buttonFont);
        depositBtn.setBounds(30, 140, 140, 36);
        depositBtn.setBackground(forestGreen);
        depositBtn.setForeground(Color.WHITE);
        depositBtn.setFocusPainted(false);
        frame.add(depositBtn);
        depositBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                depositBtn.setBackground(new Color(46, 184, 46));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                depositBtn.setBackground(forestGreen);
            }
        });

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(180, 140, 140, 36);
        withdrawBtn.setBackground(crimsonRed);
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.setFont(buttonFont);
        withdrawBtn.setFocusPainted(false);
        frame.add(withdrawBtn);
        withdrawBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                withdrawBtn.setBackground(new Color(244, 67, 54));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                withdrawBtn.setBackground(crimsonRed);
            }
        });

        JButton transferBtn = new JButton("Transfer");
        transferBtn.setBounds(330, 140, 140, 36);
        transferBtn.setBackground(skyBlue);
        transferBtn.setForeground(Color.WHITE);
        transferBtn.setFont(buttonFont);
        transferBtn.setFocusPainted(false);
        frame.add(transferBtn);
        transferBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transferBtn.setBackground(new Color(66, 165, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                transferBtn.setBackground(skyBlue);
            }
        });

        // ==========================================
        // TRANSACTION HISTORY DISPLAY
        // ==========================================

        JLabel historyTitle = new JLabel("Transaction History:");
        historyTitle.setBounds(30, 192, 220, 24);
        historyTitle.setFont(labelFont);
        historyTitle.setForeground(darkIndigo);
        frame.add(historyTitle);

        JTextArea historyLog = new JTextArea();
        historyLog.setEditable(false);
        historyLog.setFont(historyFont);
        historyLog.setForeground(charcoalGray);
        historyLog.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(historyLog);
        scrollPane.setBounds(30, 220, 440, 370);
        frame.add(scrollPane);

        // ==========================================
        // DEPOSIT BUTTON LOGIC
        // ==========================================

        depositBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(input.getText());
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive!");
                    return;
                }
                balance += amount;
                String logTime = LocalDateTime.now().format(dtf);
                historyLog.append("[" + logTime + "] Deposited: $" + amount + "\n");
                label.setText("Balance: $" + balance);
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a number!");
            }
        });

        // ==========================================
        // WITHDRAW BUTTON LOGIC
        // ==========================================

        withdrawBtn.addActionListener(event -> {
            try {
                double amount = Double.parseDouble(input.getText());
                if (amount <= balance) {
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(frame, "Amount must be positive!");
                        return;
                    }
                    balance -= amount;
                    String logTime = LocalDateTime.now().format(dtf);
                    historyLog.append("[" + logTime + "] Withdrew:  $" + amount + "\n");
                    label.setText("Balance: $" + balance);
                } else {
                    JOptionPane.showMessageDialog(frame, "Balance is too low!");
                }
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // ==========================================
        // TRANSFER BUTTON LOGIC (with recipient validation)
        // ==========================================

        transferBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(input.getText());
                String recipient = recipientInput.getText().trim();

                if (recipient.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a recipient username!");
                    return;
                }
                if (recipient.equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(frame, "You cannot transfer funds to yourself!");
                    return;
                }
                if (!LoginScreen.userExists(recipient)) {
                    JOptionPane.showMessageDialog(frame,
                            "User \"" + recipient + "\" is not registered in the system.\nPlease check the username and try again.",
                            "Recipient Not Found", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive!");
                    return;
                }
                if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!");
                    return;
                }

                // Confirm before transferring
                int confirm = JOptionPane.showConfirmDialog(frame,
                        "Transfer $" + amount + " to " + recipient + "?\nYour new balance will be: $" + (balance - amount),
                        "Confirm Transfer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirm != JOptionPane.YES_OPTION) return;

                balance -= amount;
                String logTime = LocalDateTime.now().format(dtf);
                historyLog.append("[" + logTime + "] Transferred: $" + amount + " -> " + recipient + "\n");
                label.setText("Balance: $" + balance);
                input.setText("");
                recipientInput.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid amount!");
            }
        });

        // ==========================================
        // MENU BAR ACTIONS
        // ==========================================

        saveItem.addActionListener(e -> {
            try {
                String envPath = System.getenv("HISTORY_PATH");
                File userFile;
                if (envPath != null) {
                    userFile = new File(envPath);
                } else {
                    userFile = new File("data/" + username + "_data.txt");
                }

                String passwordLine = "";
                if (userFile.exists()) {
                    Scanner sc = new Scanner(userFile);
                    if (sc.hasNextLine()) {
                        String firstLine = sc.nextLine();
                        if (firstLine.startsWith("PASSWORD:")) {
                            passwordLine = firstLine;
                        }
                    }
                    sc.close();
                }

                FileWriter fw = new FileWriter(userFile);
                PrintWriter pw = new PrintWriter(fw);
                if (!passwordLine.isEmpty()) {
                    pw.println(passwordLine);
                }
                pw.println(balance);
                pw.print(historyLog.getText());
                pw.close();
                fw.close();

                JOptionPane.showMessageDialog(frame, "Bank data has been saved!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving data: " + ex.getMessage());
            }
        });

        downloadItem.addActionListener(e -> {
            try {
                FileWriter fw = new FileWriter("bankstatement.txt");
                PrintWriter pw = new PrintWriter(fw);
                pw.println("==================================");
                pw.println("      OFFICIAL BANK STATEMENT     ");
                pw.println("      Date: " + LocalDateTime.now());
                pw.println("==================================");
                pw.println("");
                pw.print(historyLog.getText());
                pw.close();
                fw.close();
                JOptionPane.showMessageDialog(frame, "Bank Statement has been downloaded!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        exitItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Do you want to save before exiting?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                saveItem.getActionListeners()[0].actionPerformed(e);
                Timer exitTimer = new Timer(500, evt -> System.exit(0));
                exitTimer.setRepeats(false);
                exitTimer.start();
            } else if (confirm == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        });

        logoutItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Do you want to save before logging out?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                saveItem.getActionListeners()[0].actionPerformed(e);
                frame.dispose();
                new LoginScreen();
            } else if (confirm == JOptionPane.NO_OPTION) {
                frame.dispose();
                new LoginScreen();
            }
        });

        profileItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "User: " + username + "\nAccount Type: Student Checking",
                "Profile", JOptionPane.INFORMATION_MESSAGE));

        // ==========================================
        // CHANGE PASSWORD (Account Menu)
        // ==========================================

        changePasswordItem.addActionListener(e -> showChangePasswordDialog(frame, borderGray));

        // ==========================================
        // SETTINGS (Account Menu)
        // ==========================================

        settingsItem.addActionListener(e -> showSettingsDialog(frame, borderGray, label, welcomeLabel));

        // ==========================================
        // LOAD USER DATA ON STARTUP
        // ==========================================

        String envPath = System.getenv("HISTORY_PATH");
        File file;
        if (envPath != null) {
            file = new File(envPath);
        } else {
            file = new File("data/" + username + "_data.txt");
        }
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                if (sc.hasNextLine()) {
                    String firstLine = sc.nextLine();
                    if (firstLine.startsWith("PASSWORD:")) {
                        if (sc.hasNextLine()) {
                            String balanceLine = sc.nextLine();
                            balance = Double.parseDouble(balanceLine.trim());
                            label.setText("Balance: $" + balance);
                        }
                    } else {
                        balance = Double.parseDouble(firstLine.trim());
                        label.setText("Balance: $" + balance);
                    }
                }
                while (sc.hasNextLine()) {
                    historyLog.append(sc.nextLine() + "\n");
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found, starting fresh.");
            } catch (NumberFormatException e) {
                System.out.println("Error reading balance, starting at 0.0");
                balance = 0.0;
            }
        } else {
            balance = 0.0;
        }

        // ==========================================
        // SHOW THE WINDOW
        // ==========================================

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // ==========================================
    // CHANGE PASSWORD DIALOG
    // ==========================================

    private void showChangePasswordDialog(JFrame parent, Color borderGray) {
        JDialog dialog = new JDialog(parent, "Change Password", true);
        dialog.setSize(380, 290);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(new Color(255, 253, 208));
        dialog.setLocationRelativeTo(parent);

        Font lf = new Font("Segoe UI", Font.BOLD, 14);
        Font inf = new Font("Segoe UI", Font.PLAIN, 13);
        Font bf = new Font("Segoe UI", Font.BOLD, 13);
        Color mp = new Color(106, 13, 173);

        JLabel infoLabel = new JLabel("Change your account password:");
        infoLabel.setBounds(20, 15, 340, 20);
        infoLabel.setFont(lf);
        infoLabel.setForeground(mp);
        dialog.add(infoLabel);

        JLabel currentLabel = new JLabel("Current Password:");
        currentLabel.setBounds(20, 50, 160, 20);
        currentLabel.setFont(lf);
        dialog.add(currentLabel);

        JPasswordField currentField = new JPasswordField();
        currentField.setBounds(20, 72, 330, 30);
        currentField.setFont(inf);
        currentField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        dialog.add(currentField);

        JLabel newLabel = new JLabel("New Password:");
        newLabel.setBounds(20, 112, 160, 20);
        newLabel.setFont(lf);
        dialog.add(newLabel);

        JPasswordField newField = new JPasswordField();
        newField.setBounds(20, 134, 330, 30);
        newField.setFont(inf);
        newField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        dialog.add(newField);

        JLabel confirmLabel = new JLabel("Confirm New Password:");
        confirmLabel.setBounds(20, 174, 200, 20);
        confirmLabel.setFont(lf);
        dialog.add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(20, 196, 330, 30);
        confirmField.setFont(inf);
        confirmField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
        dialog.add(confirmField);

        JButton changeBtn = new JButton("Change Password");
        changeBtn.setBounds(90, 238, 190, 32);
        changeBtn.setFont(bf);
        changeBtn.setBackground(new Color(65, 105, 225));
        changeBtn.setForeground(Color.WHITE);
        changeBtn.setFocusPainted(false);
        dialog.add(changeBtn);

        changeBtn.addActionListener(e -> {
            String current = new String(currentField.getPassword());
            String newPass = new String(newField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (current.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!isCurrentPasswordCorrect(username, LoginScreen.hashPassword(current))) {
                JOptionPane.showMessageDialog(dialog, "Current password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!newPass.equals(confirm)) {
                JOptionPane.showMessageDialog(dialog, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (newPass.length() < 6) {
                JOptionPane.showMessageDialog(dialog, "Password must be at least 6 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (updatePasswordInCredentials(username, LoginScreen.hashPassword(newPass))) {
                JOptionPane.showMessageDialog(dialog, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Error updating password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    // ==========================================
    // SETTINGS DIALOG
    // ==========================================

    private void showSettingsDialog(JFrame parent, Color borderGray, JLabel balanceLabel, JLabel welcomeLabel) {
        JDialog dialog = new JDialog(parent, "Settings", true);
        dialog.setSize(380, 240);
        dialog.setLayout(null);
        dialog.setResizable(false);
        dialog.getContentPane().setBackground(new Color(255, 253, 208));
        dialog.setLocationRelativeTo(parent);

        Font lf = new Font("Segoe UI", Font.BOLD, 14);
        Font inf = new Font("Segoe UI", Font.PLAIN, 13);
        Font bf = new Font("Segoe UI", Font.BOLD, 13);
        Color mp = new Color(106, 13, 173);

        JLabel titleLabel = new JLabel("Account Settings");
        titleLabel.setBounds(20, 15, 340, 22);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(mp);
        dialog.add(titleLabel);

        JLabel nameLabel = new JLabel("Display Name:");
        nameLabel.setBounds(20, 55, 140, 20);
        nameLabel.setFont(lf);
        dialog.add(nameLabel);

        JTextField nameField = new JTextField(username);
        nameField.setBounds(170, 52, 180, 28);
        nameField.setFont(inf);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        dialog.add(nameField);

        JLabel typeLabel = new JLabel("Account Type:");
        typeLabel.setBounds(20, 95, 140, 20);
        typeLabel.setFont(lf);
        dialog.add(typeLabel);

        JLabel typeValue = new JLabel("Student Checking");
        typeValue.setBounds(170, 95, 180, 20);
        typeValue.setFont(inf);
        typeValue.setForeground(new Color(51, 51, 51));
        dialog.add(typeValue);

        JLabel currencyLabel = new JLabel("Currency Symbol:");
        currencyLabel.setBounds(20, 133, 140, 20);
        currencyLabel.setFont(lf);
        dialog.add(currencyLabel);

        String[] currencies = {"$ (USD)", "€ (EUR)", "£ (GBP)", "¥ (JPY)"};
        JComboBox<String> currencyBox = new JComboBox<>(currencies);
        currencyBox.setBounds(170, 130, 180, 28);
        currencyBox.setFont(inf);
        dialog.add(currencyBox);

        JButton saveSettingsBtn = new JButton("Save Settings");
        saveSettingsBtn.setBounds(90, 172, 190, 32);
        saveSettingsBtn.setFont(bf);
        saveSettingsBtn.setBackground(new Color(34, 139, 34));
        saveSettingsBtn.setForeground(Color.WHITE);
        saveSettingsBtn.setFocusPainted(false);
        dialog.add(saveSettingsBtn);

        saveSettingsBtn.addActionListener(e -> {
            String newName = nameField.getText().trim();
            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Display name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            welcomeLabel.setText("Welcome, " + newName);

            String selectedCurrency = (String) currencyBox.getSelectedItem();
            String symbol = selectedCurrency != null ? selectedCurrency.substring(0, 1) : "$";
            String currentBalance = balanceLabel.getText().replaceAll("[^0-9.]", "");
            balanceLabel.setText("Balance: " + symbol + currentBalance);

            JOptionPane.showMessageDialog(dialog, "Settings saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    // ==========================================
    // HELPER: Verify current password against credentials.txt
    // ==========================================

    private boolean isCurrentPasswordCorrect(String username, String hashedPassword) {
        File credFile = new File("credentials.txt");
        if (!credFile.exists()) return false;
        try (Scanner sc = new Scanner(credFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
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
    // HELPER: Update password in credentials.txt
    // ==========================================

    private boolean updatePasswordInCredentials(String username, String newHashedPassword) {
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
}