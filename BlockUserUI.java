

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BlockUserUI extends JFrame implements ActionListener 
{
    private JButton loginButton;
    private JTextField usernameField;

    public BlockUserUI() {
        jFrameSetUp();
    }

    public void jFrameSetUp() {
        setTitle("User Login");
        setSize(300, 100);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        loginButton = new JButton("Block User");
        loginButton.addActionListener(this);

        setLayout(new GridLayout(2, 2));

        add(usernameLabel);
        add(usernameField);
        add(new JLabel(""));
        add(loginButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userName = usernameField.getText();
            User user = new User(userName);
            if (userName.equals("")) {
                JOptionPane.showMessageDialog(this, "User name cannot be empty");
            }
            else if (!user.checkUserExists()) {
                JOptionPane.showMessageDialog(this, "User does not exist");
            }
            else {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to block user " + userName+ "?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                user.setStatus("blocked");
                JOptionPane.showMessageDialog(this, "Blocking user "+ userName +" successfully");
                this.setVisible(false);
                } 
            }
        }
    }
}