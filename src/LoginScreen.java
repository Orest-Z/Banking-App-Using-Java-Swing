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
    //Added the textfield and set its bounds where the user will be able to input its username    
        JTextField username = new JTextField();
        username.setBounds(100,150,100,30);
        loginframe.add(username);
    //Created a label for login password
        JLabel loginPassword = new JLabel("Enter Password:");
        loginPassword.setBounds(100,200,100,30);
        loginframe.add(loginPassword);
    //Added the password input field (Different from text input field the password field shows credintials as *)
        JPasswordField password = new JPasswordField();
        password.setBounds(100,250,100,30);
        loginframe.add(password);
    //Added the Login Button
        JButton loginbutton = new JButton("Login");
        loginbutton.setBounds(100,300,100,30);
        loginframe.add(loginbutton);
    //Added an action listener to take the credintials of the user the moment he cicks Login Button
        loginbutton.addActionListener(e->{
            String userid=username.getText();//Gets the username
            String pass=password.getText();//Gets the password
    //Conditions password and username checking        
                if(userid.isEmpty()||pass.isEmpty()){
                    JOptionPane.showMessageDialog(loginframe,"Please enter username and password");
                }else if(userid.equals("Orest")&&pass.equals("1234")){
                    loginframe.dispose();
                    new BankingApp();
                }else{
                    JOptionPane.showMessageDialog(loginframe,"Invalid username or password");
                }
        });
        
    //Fixed a bug where the window was not showing cause of which was to set the frame to visible
        loginframe.setVisible(true);
    }
    //Transfered the main method here so the applicatoin initializes at this point then if succesful BankingApp.java is ran
    public static void main(String[] args) {
        new LoginScreen();
    }    
    
}
