import javax.swing.*;

public class LoginScreen {
    public LoginScreen() {
      //Add the login main frame
        JFrame loginframe = new JFrame("Login Screen");
        loginframe.setSize(400,400);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setLayout(null);
        loginframe.setResizable(false);
    //Add a label for the username input
          JLabel loginlabel = new JLabel("Enter Username:");
          loginlabel.setBounds(100,50,100,30);
          loginframe.add(loginlabel);
