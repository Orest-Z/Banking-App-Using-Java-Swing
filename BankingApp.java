import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class BankingApp {
    private double balance = 0.0;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public BankingApp() {
        JFrame frame = new JFrame("Banking App v1.5");
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(250, 10, 200, 30);
        frame.add(timeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText("Time: " + LocalDateTime.now().format(dtf));
            }
        });
        timer.start();

        JLabel label = new JLabel("Balance: $0.0");
        label.setBounds(50, 30, 200, 30);
        frame.add(label);

        JTextField input = new JTextField();
        input.setBounds(50, 70, 150, 30);
        frame.add(input);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(50, 110, 100, 30);
        frame.add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(160, 110, 100, 30);
        frame.add(withdrawBtn);

        JLabel historyTitle = new JLabel("Transaction History:");
        historyTitle.setBounds(50, 170, 200, 30);
        frame.add(historyTitle);

        JTextArea historyLog = new JTextArea();
        historyLog.setEditable(false);
        historyLog.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));

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

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BankingApp();
    }
}
