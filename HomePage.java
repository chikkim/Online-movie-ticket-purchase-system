
import javax.swing.*;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

public class HomePage extends JFrame implements ActionListener{
    private JButton customerLoginButton, signUpButton, ITLoginButton;
    
    HomePage() {
        addPanel();
        jFrameSetUp();
    }

    public void jFrameSetUp() {
        setTitle("Home screen");
        setSize(700, 420);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    public void addPanel() {
        // Add the name label
        JLabel nameLabel = new JLabel("G4 Cinema", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD,36));
        add(nameLabel, BorderLayout.NORTH);

        //adding space
        JPanel spacPanel = new JPanel();
        spacPanel.setBackground(Color.white);
        spacPanel.setPreferredSize(new Dimension(700,300));
        add(spacPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();

        customerLoginButton = new JButton("Customer log in");
        customerLoginButton.setFont(new Font("Arial", Font.BOLD, 24));
        customerLoginButton.setPreferredSize(new Dimension(220, 50));
        customerLoginButton.addActionListener(this);

        ITLoginButton = new JButton("IT Department log in");
        ITLoginButton.setFont(new Font("Arial", Font.BOLD, 24));
        ITLoginButton.setPreferredSize(new Dimension(300, 50));
        ITLoginButton.addActionListener(this);

        signUpButton = new JButton("Sign up");
        signUpButton.setPreferredSize(new Dimension(150, 50));
        signUpButton.setFont(new Font("Arial", Font.BOLD, 24));
        signUpButton.addActionListener(this);


        mainPanel.add(ITLoginButton);
        mainPanel.add(customerLoginButton);
        mainPanel.add(signUpButton);
        add(mainPanel, BorderLayout.SOUTH);
    }

    LoginGUI login = new LoginGUI();
    SignUpGUI signup = new SignUpGUI();
    ITDeptLoginGUI itDeptLogin = new ITDeptLoginGUI();
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == customerLoginButton) {
            login.setVisible(true);
            signup.setVisible(false);
            itDeptLogin.setVisible(false);
            dispose();
        }
        else if (e.getSource() == signUpButton) {   
            login.setVisible(false);
            signup.setVisible(true);
            itDeptLogin.setVisible(false);
            dispose();
        }
        else if (e.getSource()  == ITLoginButton) {
            login.setVisible(false);
            signup.setVisible(false);
            itDeptLogin.setVisible(true);
            dispose();
        }
    }
}
