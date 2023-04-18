import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.regex.*;

public class SignUpGUI extends JFrame implements ActionListener {
   // GUI components
   private JTextField usernameField, emailField;
   private JPasswordField passwordField;
   private JPasswordField confirmJPasswordField;
   private JButton signUpButton, backButton;
   private JLabel messageLabel;
   private int lastUserId = 0;
   private static final String USER_FILE = "users.csv";

   public SignUpGUI() {
      
      jFrameSetUp();
      addPanel();
   }

   public void jFrameSetUp() {
      setTitle("Sign Up");
      setSize(500, 250);

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
      int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
      setLocation(x, y);
   }
   public void addPanel() {
      Container contentPane = getContentPane();
      contentPane.setLayout(new GridLayout(7, 2, 5, 5));

      contentPane.add(new Label("*Email: "));
      emailField = new JTextField();
      contentPane.add(emailField);

      contentPane.add(new JLabel("*Username: "));
      usernameField = new JTextField();
      contentPane.add(usernameField);

      contentPane.add(new JLabel("*Password: "));
      passwordField = new JPasswordField();
      contentPane.add(passwordField);

      contentPane.add(new JLabel("*Confirm password"));
      confirmJPasswordField = new JPasswordField();
      contentPane.add(confirmJPasswordField);


      signUpButton = new JButton("Sign Up");
      signUpButton.addActionListener(this);
      contentPane.add(signUpButton);

      backButton = new JButton("Back");
      backButton.addActionListener(this);
      contentPane.add(backButton);

      messageLabel = new JLabel();


      contentPane.add(messageLabel);

      // Initialize the last user ID from the file
      try (BufferedReader in = new BufferedReader(new FileReader(USER_FILE))) {
         String line;
         while ((line = in.readLine()) != null) {
            String[] parts = line.split(",");
            int userId = Integer.parseInt(parts[2]);
            if (userId > lastUserId) {
               lastUserId = userId;
            }
         }
      } catch (IOException e) {
         messageLabel.setText("Error initializing user ID");
      }
   }
   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == signUpButton) {
         // Get the user information from the text fields
         String email = emailField.getText();
         String username = usernameField.getText();
         String password = new String(passwordField.getPassword());
         String confirmPassword = new String(confirmJPasswordField.getPassword());
         String regex = "^(.+)@(.+)$";  
        //Compile regular expression to get the pattern  
         Pattern pattern = Pattern.compile(regex); 
         Matcher matcher = pattern.matcher(email); 
         User user = new User(username, password);

         if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All boxes must be filled");
            return;
         }
         // Check if the user already exists
         else if (user.checkUserExists()) {
            JOptionPane.showMessageDialog(null, "Username already exists");
            return;
         }
         else if (!password.equals(confirmPassword))  {
            JOptionPane.showMessageDialog(null, "Password does not match");
            return;
         }
         else if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Email is not in a correct format");
            return;
         }
         
         // Generate a new user ID
         int userId = lastUserId + 1;
         lastUserId = userId;

         user.authenticateUser(Integer.toString(userId), email);
         // Display success message
         JOptionPane.showMessageDialog(null, "Sign up successfully");
         dispose();
         HomePage homepage = new HomePage();
         homepage.setVisible(true);
         homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);

      } 
      else if (event.getSource() == backButton) {
         this.dispose();
         HomePage homepage = new HomePage();
         homepage.setVisible(true);
         homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);
      }
   }

   public static void main(String[] args) {
      SignUpGUI gui = new SignUpGUI();
      gui.setVisible(true);
   }
}
