import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * BankingApp.java (UPDATED VERSION)
 */
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

public class BankingApp {
    private final String username;
    private double balance = 0.0;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public BankingApp(String user) {
        this.username = user;

        JFrame frame = new JFrame("Banking App v2.0 - Secure Edition");
        frame.setSize(450, 600);
        frame.getContentPane().setBackground(softCream);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // ==========================================
        // LIVE CLOCK
        // ==========================================
        
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(250, 10, 180, 30); 
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
        
        JTextField input = new JTextField();
        input.setBounds(50, 70, 150, 30);
        input.setFont(inputFont);
        input.setForeground(charcoalGray);
        input.setBackground(Color.WHITE);
        input.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderGray, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        frame.add(input);

        // ==========================================
        // BUTTONS
        // ==========================================
        
        JButton depositBtn = new JButton("Deposit");
                depositBtn.setFont(buttonFont);
        depositBtn.setBounds(50, 110, 100, 30);
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
        withdrawBtn.setBounds(160, 110, 100, 30);
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

        JButton DownloadHistory = new JButton("Download History");
        DownloadHistory.setBounds(270, 110, 150, 30);
        DownloadHistory.setBackground(goldenYellow);
        DownloadHistory.setForeground(charcoalGray);
        DownloadHistory.setFont(buttonFont);
        DownloadHistory.setFocusPainted(false);
        frame.add(DownloadHistory);
         DownloadHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                DownloadHistory.setBackground(new Color(255, 213, 79));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                DownloadHistory.setBackground(goldenYellow);
            }
        });

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(270, 145, 150, 30);
        saveBtn.setFont(buttonFont);
        saveBtn.setBackground(skyBlue);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        frame.add(saveBtn);
        // Hover effects
        saveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveBtn.setBackground(new Color(66, 165, 245));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveBtn.setBackground(skyBlue);
            }
        });

        // ==========================================
        // TRANSACTION HISTORY DISPLAY
        // ==========================================
        
        JLabel historyTitle = new JLabel("Transaction History:");
        historyTitle.setBounds(50, 160, 200, 30);
        historyTitle.setFont(labelFont);
        historyTitle.setForeground(darkIndigo);
        frame.add(historyTitle);

        JTextArea historyLog = new JTextArea();
        historyLog.setEditable(false);
        historyLog.setFont(historyFont);
        historyLog.setForeground(charcoalGray);
        historyLog.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(historyLog);
        scrollPane.setBounds(50, 190, 330, 300);
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
        // DOWNLOAD HISTORY BUTTON LOGIC
        // ==========================================
        
        DownloadHistory.addActionListener(e -> {
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

        // ==========================================
        // SAVE BUTTON LOGIC (UPDATED!)
        // ==========================================
        
        saveBtn.addActionListener(e -> {
            try {
                File userFile = new File(username + "_data.txt");
                FileWriter fw = new FileWriter(userFile);
                PrintWriter pw = new PrintWriter(fw);
                
                // IMPORTANT: We need to preserve the PASSWORD line!
                // First, read the existing password hash from the file
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
                
                // If we found a password line, write it first
                if (!passwordLine.isEmpty()) {
                    pw.println(passwordLine);
                }
                
                // Write the balance
                pw.println(balance);
                
                // Write the transaction history
                pw.print(historyLog.getText());
                
                pw.close();
                fw.close();

                JOptionPane.showMessageDialog(frame, "Bank data has been saved!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving data: " + ex.getMessage());
            }
        });

        // ==========================================
        // LOAD USER DATA ON STARTUP (UPDATED!)
        // ==========================================
        
        File file = new File(username + "_data.txt");

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);

                // -----------------------------------
                // LINE 1: Skip the PASSWORD line
                // -----------------------------------
                if (sc.hasNextLine()) {
                    String firstLine = sc.nextLine();
                    
                    // Check if it's a password line (new format)
                    if (firstLine.startsWith("PASSWORD:")) {
                        // This is the new format, skip this line
                        // The next line will be the balance
                        
                        if (sc.hasNextLine()) {
                            String balanceLine = sc.nextLine();
                            balance = Double.parseDouble(balanceLine.trim());
                            label.setText("Balance: $" + balance);
                        }
                    } else {
                        // This is the old format (no PASSWORD line)
                        // This line IS the balance
                        balance = Double.parseDouble(firstLine.trim());
                        label.setText("Balance: $" + balance);
                    }
                }

                // -----------------------------------
                // REMAINING LINES: Transaction history
                // -----------------------------------
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
            // File doesn't exist, this is a new account
            balance = 0.0;
        }

        // ==========================================
        // SHOW THE WINDOW
        // ==========================================
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
