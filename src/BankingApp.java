import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class BankingApp {
    private String username;
    private double balance = 0.0;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public BankingApp(String user)/*Now the constructor takes as a parameter the username*/ {
        this.username=user;//Used a setter for the username variable
        
        JFrame frame = new JFrame("Banking App v1.5");
        frame.setSize(450, 600);
        frame.getContentPane().setBackground(Color.decode("#FFEF5F"));//Changed backgorund color
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(250, 10, 180, 30);
        timeLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        timeLabel.setForeground(Color.decode("#4D2B8C"));//Changed the color of clock text
        frame.add(timeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText("Time: " + LocalDateTime.now().format(dtf));
            }
        });
        timer.start();
        //Added a welcoming message which is different based on the username variable
        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.decode("#4D2B8C"));
        welcomeLabel.setBounds(50, 5, 200, 30);
        frame.add(welcomeLabel);

        JLabel label = new JLabel("Balance: $0.0");
        label.setBounds(50, 30, 200, 30);
        frame.add(label);

        JTextField input = new JTextField();
        input.setBounds(50, 70, 150, 30);
        frame.add(input);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(50, 110, 100, 30);
        depositBtn.setBackground(Color.green);//Made the deposit button Green
        frame.add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(160, 110, 100, 30);
        withdrawBtn.setBackground(Color.red);//Made the withdraw button red
        frame.add(withdrawBtn);

        JButton DownloadHistory = new JButton("Download History");
        DownloadHistory.setBounds(270, 110, 150, 30);
        frame.add(DownloadHistory);

        JLabel historyTitle = new JLabel("Transaction History:");
        historyTitle.setBounds(50, 170, 200, 30);
        frame.add(historyTitle);

        JTextArea historyLog = new JTextArea();
        historyLog.setEditable(false);
        historyLog.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(historyLog);
        scrollPane.setBounds(50, 200, 330, 300);
        frame.add(scrollPane);

        depositBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(input.getText());
                balance += amount;
                String timestamp = LocalDateTime.now().format(dtf);
                historyLog.append("[" + timestamp + "] Deposited: $" + amount + "\n");
                label.setText("Balance: $" + balance);
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a number!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(input.getText());
                if (amount <= balance) {
                    balance -= amount;
                    String timestamp = LocalDateTime.now().format(dtf);
                    historyLog.append("[" + timestamp + "] Withdrew:  $" + amount + "\n");
                    label.setText("Balance: $" + balance);
                } else {
                    JOptionPane.showMessageDialog(frame, "Balance is too low!");
                }
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

    DownloadHistory.addActionListener(e->{
        try {
            FileWriter fw=new Filewriter();
            PrintWriter pw=new PrintWriter(fw);
                pw.println("==================================");
                pw.println("      OFFICIAL BANK STATEMENT     ");
                pw.println("      Date: " + java.time.LocalDateTime.now());
                pw.println("==================================");
                pw.println("");
                pw.print(historyLog.getText());
                pw.close();
                fw.close();
                JOptionPane.showMessageDialog(frame, "Bank Statement has been downloaded!");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

            

        loginframe.setVisible(true);
    }
    /*Since the BankingApp.java needs to be initialized after the LoginScreen.java is succsesful we need 
    to remove the main method here and transfer it to the LoginScreen class*/
   
}
