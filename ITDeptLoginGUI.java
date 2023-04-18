// update the code line 39

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ITDeptLoginGUI extends JFrame implements ActionListener{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JLabel statusLabel;
    
    public ITDeptLoginGUI() {
    
        jFrameSetUp();
        addPanel();  
    }

    public void jFrameSetUp() {
        setTitle("IT Department Login");
        setSize(300, 150);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    public void addPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("IT Dept name:");
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
        mainPanel.add(backButton);
        backButton.addActionListener(this);
        mainPanel.add(statusLabel);
        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            ITDepartment itDept = new ITDepartment(username, password);
            if (itDept.checkUserExists()) {
                this.dispose();
                new ITDeptOptionList(itDept);
            }
            else {
                JOptionPane.showMessageDialog(null, "User name or password is not correct. Please try again");
            }
        } else if (e.getSource() == backButton) {
            this.dispose();
            HomePage homepage = new HomePage();
            homepage.setVisible(true);
            homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
