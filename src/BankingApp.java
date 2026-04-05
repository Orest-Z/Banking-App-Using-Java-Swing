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
        //MENU BAR
        //=========================================
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
        accountMenu.add(profileItem);
        accountMenu.addSeparator();
        accountMenu.add(logoutItem);

        // Add menus to bar
        menuBar.add(fileMenu);
        menuBar.add(accountMenu);
        frame.setJMenuBar(menuBar);

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
        // BUTTONS
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
        // TRANSFER BUTTON LOGIC
        // ==========================================

        transferBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(input.getText());
                String recipient = recipientInput.getText().trim();
                if (recipient.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a recipient username!");
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

        profileItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "User: " + username + "\nAccount Type: Student Checking", "Profile", JOptionPane.INFORMATION_MESSAGE);
        });

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
}
