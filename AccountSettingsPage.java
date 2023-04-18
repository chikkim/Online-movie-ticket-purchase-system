import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSettingsPage extends JFrame implements ActionListener{
    private JPanel panel1;
    private JButton historyButton;
    private JButton changePasswordButton;
    private User user;


    public AccountSettingsPage(User user) {
        this.user = user;
        jFrameSetUp();

    }

    public void jFrameSetUp() {
        // set up the window properties
        setTitle("Account Settings");
        setSize(600, 300);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        // create the panel to hold the components
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 2));

        // create the components
        JLabel nameLabel = new JLabel("User name:");
        JLabel nameField = new JLabel(user.getUsername());
        JLabel emailAddressLabel = new JLabel("Email address:");
        JLabel emailAddressField = new JLabel(user.getEmail());
        JLabel update = new JLabel("Update*:");
        JLabel message = new JLabel("No message");
        historyButton = new JButton("View History");
        historyButton.addActionListener(this);
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(this);

        // add the components to the panel
        panel1.add(nameLabel);
        panel1.add(nameField);
        panel1.add(emailAddressLabel);
        panel1.add(emailAddressField);
        panel1.add(update);
        panel1.add(message);
        panel1.add(historyButton);
        panel1.add(changePasswordButton);

        // add the panel to the frame
        add(panel1);

        // set the frame to be visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == historyButton) {
            new HistoryUI(user);
        }
        else if (e.getSource() == changePasswordButton) {
            ChangePasswordGUI change = new ChangePasswordGUI();
            change.setVisible(true);
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
