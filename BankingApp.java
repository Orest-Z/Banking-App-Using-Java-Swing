  import javax.swing.*;
  import java.awt.event.*;
  import java.time.*;
  import java.time.format.DateTimeFormatter;

  public class BankingApp {
      private double balance = 0.0;
      private DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
  
      public BankingApp() {
          JFrame frame = new JFrame("Banking App v1.4");
          frame.setSize(400, 400);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setLayout(null);

            String currentTime = dtf.format(LocalDateTime.now());
            JLabel timeLabel = new JLabel("Time: "+currentTime);
            timeLabel.setBounds(250, 20, 200, 10);
            frame.add(timeLabel);
        
          JLabel label = new JLabel("Balance: $0.0");
          label.setBounds(50, 30, 200, 30);
          frame.add(label);
  
          JTextField input = new JTextField();
          input.setBounds(50, 70, 150, 30);
          frame.add(input);
  
          JButton depositBtn = new JButton("Deposit");
          depositBtn.setBounds(50, 110, 100, 30);
          frame.add(depositBtn);

          JLabel historyTitle = new JLabel("Transaction History:");
          historyTitle.setBounds(50, 200, 200, 30);
          frame.add(historyTitle);

          JTextArea historyLog = new JTextArea();
          historyLog.setEditable(false); 
          historyLog.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12)); 

          JScrollPane scrollPane = new JScrollPane(historyLog);
          scrollPane.setBounds(50, 200, 300, 160);
          frame.add(scrollPane);
        
     depositBtn.addActionListener(e -> {
              try {
                  double amount = Double.parseDouble(input.getText());
                  
                  balance += amount; 
                  historyLog.append("[" + currentTime + "] Deposited: $" + amount + "\n");
                  label.setText("Balance: $" + balance);
                  input.setText(""); 
              } catch (Exception ex) {
                  JOptionPane.showMessageDialog(frame, "Please enter a number!");
              }
          });
  withdrawBtn.addActionListener(event->{
            try {
               double ammount = Double.parseDouble(input.getText());
                if (ammount<=balance) {
                    balance -= ammount;
                    historyLog.append("[" + currentTime + "] Withdrew: $" + ammount + "\n");
                }else {
                    JOptionPane.showMessageDialog(frame, "Balance is too low!");
                }
                label.setText("Balance: $" + balance);
                input.setText("");
            } catch (Exception ex) {
                System.out.println("Invalid input!");
            };
        });;
  
          frame.setVisible(true);
      }
  
      public static void main(String[] args) {
          new BankingApp();
      }
  }
