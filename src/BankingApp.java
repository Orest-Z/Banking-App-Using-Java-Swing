import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * BankingApp.java (UPDATED VERSION)
 * 
 * This is your main banking application with ONE IMPORTANT UPDATE:
 * - Now reads user data files that contain "PASSWORD:hash" on the first line
 * - Everything else remains the same as your original version!
 */
public class BankingApp {
    private final String username;
    private double balance = 0.0;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public BankingApp(String user) {
        this.username = user;

        JFrame frame = new JFrame("Banking App v2.0 - Secure Edition");
        frame.setSize(450, 600);
        frame.getContentPane().setBackground(Color.decode("#FFEF5F"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // ==========================================
        // LIVE CLOCK
        // ==========================================
        
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(250, 10, 180, 30);
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        timeLabel.setForeground(Color.decode("#4D2B8C"));
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
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.decode("#4D2B8C"));
        welcomeLabel.setBounds(50, 5, 200, 30);
        frame.add(welcomeLabel);

        // ==========================================
        // BALANCE DISPLAY
        // ==========================================
        
        JLabel label = new JLabel("Balance: $0.0");
        label.setBounds(50, 30, 200, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        label.setForeground(Color.decode("#4D2B8C"));
        frame.add(label);

        // ==========================================
        // AMOUNT INPUT FIELD
        // ==========================================
        
        JTextField input = new JTextField();
        input.setBounds(50, 70, 150, 30);
        frame.add(input);

        // ==========================================
        // BUTTONS
        // ==========================================
        
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(50, 110, 100, 30);
        depositBtn.setBackground(Color.green);
        frame.add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(160, 110, 100, 30);
        withdrawBtn.setBackground(Color.red);
        frame.add(withdrawBtn);

        JButton DownloadHistory = new JButton("Download History");
        DownloadHistory.setBounds(270, 110, 150, 30);
        DownloadHistory.setBackground(Color.decode("#EEA727"));
        frame.add(DownloadHistory);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(270, 145, 150, 30);
        saveBtn.setBackground(Color.CYAN);
        frame.add(saveBtn);

        // ==========================================
        // TRANSACTION HISTORY DISPLAY
        // ==========================================
        
        JLabel historyTitle = new JLabel("Transaction History:");
        historyTitle.setBounds(50, 160, 200, 30);
        frame.add(historyTitle);

        JTextArea historyLog = new JTextArea();
        historyLog.setEditable(false);
        historyLog.setFont(new Font("Monospaced", Font.BOLD, 12));

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
