import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangePasswordGUI extends JFrame implements ActionListener {
   // GUI components
   private JTextField usernameField;
   private JPasswordField passwordField;
   private JPasswordField newPassword;
   private JPasswordField confirmNewJPasswordField;
   private JButton changeButton;
   private JLabel messageLabel;

   public ChangePasswordGUI() {

      jFrameSetUp();
      paneSetUp();
}
   public void jFrameSetUp() {
      setTitle("Change Password");
      setSize(600, 350);
      setResizable(false);
      setLocationRelativeTo(null);
      setVisible(true);
   }

   public void paneSetUp() {
      Container contentPane = getContentPane();
      contentPane.setLayout(new GridLayout(5, 2, 5, 5));
      usernameField = new JTextField();
      passwordField = new JPasswordField();
      newPassword = new JPasswordField();
      confirmNewJPasswordField = new JPasswordField();
      changeButton = new JButton("Change password");
      changeButton.addActionListener(this);
      messageLabel = new JLabel();

      contentPane.add(new JLabel("Username: "));
      contentPane.add(usernameField);
      contentPane.add(new JLabel("Old password: "));
      contentPane.add(passwordField);
      contentPane.add(new JLabel("New password"));
      contentPane.add(newPassword);
      contentPane.add(new JLabel("Comfirm new password:"));
      contentPane.add(confirmNewJPasswordField);
      contentPane.add(changeButton);
      contentPane.add(messageLabel);
   }

   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == changeButton) {
         // Get the user information from the text fields
         String username = new String(usernameField.getText());
         String oldpass = new String(passwordField.getPassword());
         String newpass = new String(newPassword.getPassword());
         String confirmNew = new String(confirmNewJPasswordField.getPassword());
         User user = new User(username, oldpass);
         if (newpass.isEmpty() || confirmNew.isEmpty() || newpass.isEmpty() || confirmNew.isEmpty()) {
            messageLabel.setText("Please fill all the text areas");
            return;
         }
         else if (!user.checkUserExists()) {
            messageLabel.setText("User name or old password not correct");
            return;
         }
         // Check if the old password match with the new one
         else if (!newpass.equals(confirmNew))  {
            messageLabel.setText("Password does not match");
            return;
        }

         user.changePassword(newpass);
         messageLabel.setText("User information updated successfully");
      }
   }
}
