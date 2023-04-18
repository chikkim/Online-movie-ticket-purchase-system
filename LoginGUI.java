// update the code line 39

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginGUI extends JFrame implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JLabel statusLabel;
    
    public LoginGUI() {
        
        jFrameSetUp();
        addPanel();
        
    }

    public void jFrameSetUp() {
        setTitle("Login");
        setSize(300, 150);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    public void addPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        statusLabel = new JLabel("");
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        loginButton.addActionListener(this);
        mainPanel.add(statusLabel);
        mainPanel.add(backButton);
        backButton.addActionListener(this);
        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = new User(username, password);
            if (user.getStatus().equals("blocked")){
                JOptionPane.showMessageDialog(null, "This account is being blocked!");
            }
            else if (!user.checkUserExists()) {
                JOptionPane.showMessageDialog(null, "User name or password is not correct. Please try again");
            }
            else {
                this.dispose();
                new homescreenGUI(user);
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            HomePage homepage = new HomePage();
            homepage.setVisible(true);
            homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
