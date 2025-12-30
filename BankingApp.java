  import javax.swing.*;
  import java.awt.event.*;
  
  public class BankingApp {
      private double balance = 0.0;
  
      public BankingApp() {
          JFrame frame = new JFrame("My First Bank App");
          frame.setSize(400, 400);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setLayout(null);
  
          JLabel label = new JLabel("Balance: $0.0");
          label.setBounds(50, 30, 200, 30);
          frame.add(label);
  
          JTextField input = new JTextField();
          input.setBounds(50, 70, 150, 30);
          frame.add(input);
  
          JButton depositBtn = new JButton("Deposit");
          depositBtn.setBounds(50, 110, 100, 30);
          frame.add(depositBtn);
  
   depositBtn.addActionListener(e -> {
              try {
                  double amount = Double.parseDouble(input.getText());
                  
                  balance += amount; 
                  
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
