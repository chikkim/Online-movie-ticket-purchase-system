import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ITDepartment {
    String username, password;
    int id;

    public ITDepartment(String username, String userPassword) {
      this.username = username;
      this.password = userPassword;
    }
    public ITDepartment(int userId) {
        try (BufferedReader in = new BufferedReader(new FileReader("ITDeptUsers.csv"))) {
            String line;
            while ((line = in.readLine()) != null) {
               String[] parts = line.split(",");
               if (Integer.parseInt(parts[2]) == userId) {
                  this.username = parts[0];
                  this.password = parts[1];
                  this.id = userId;
               }
            }
            
         } catch (IOException e) {
            System.out.println("File does not exist");
         }
    }

    public boolean checkUserExists() {
        try (BufferedReader in = new BufferedReader(new FileReader("ITDeptUsers.csv"))) {
           String line;
           while ((line = in.readLine()) != null) {
              String[] parts = line.split(",");
              if (parts[0].equals(username) && parts[1].equals(password)) {
                return true;
              }
           }
           return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error checking user information");
            return false;
        }
     }
}
