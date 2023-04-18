import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class User {
    private String username;
    private String userPassword, message;
    private int userId;
    private String status = "";

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
        this.message = "No message";
    }
    public User(String username) {
        this.username = username;
        this.message = "No message";
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = in.readLine()) != null) {
               String[] parts = line.split(",");
               if (parts[0].equals(username)) {
                  this.userPassword = parts[1];
                  this.userId = Integer.parseInt(parts[2]);
               }
            }
            
         } catch (IOException e) {
            System.out.println("File does not exist");
         }
    }
    public User(int userId) {
        this.message = "No message";
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = in.readLine()) != null) {
               String[] parts = line.split(",");
               if (Integer.parseInt(parts[2]) == userId) {
                  this.username = parts[0];
                  this.userPassword = parts[1];
                  this.userId = userId;
               }
            }
            
         } catch (IOException e) {
            System.out.println("File does not exist");
         }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }
    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getStatus() {
        if (!this.status.equals("")) {
            return this.status;
        }
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"));
           PrintWriter out = new PrintWriter(new FileWriter("users.csv" + ".tmp"))) {
         String line;
        while ((line = in.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username)) {
               return parts[3];
            }
            out.println(line);
         }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating user information");
            return "";
        }
        return "User does not exist";
    }

    public String getEmail() {
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"));
           PrintWriter out = new PrintWriter(new FileWriter("users.csv" + ".tmp"))) {
         String line;
        while ((line = in.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username)) {
               return parts[4];
            }
            out.println(line);
         }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating user information");
            return "";
        }
        return "User does not exist";
    }

    public void setStatus(String status) {
        this.status = status;
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"));
                    PrintWriter out = new PrintWriter(new FileWriter("users.csv" + ".tmp"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(username)) {
                        parts[3] = "blocked";
                        line = String.join(",", parts);
                        }           
                        out.println(line);
                    }
                } 
                catch (IOException event) {
                    JOptionPane.showMessageDialog(null, "ERROR: Blocking user fails");
                    return;
                }
                // Replace the original file with the updated file
                File originalFile = new File("users.csv");
                originalFile.delete();
                File updatedFile = new File("users.csv" + ".tmp");
                updatedFile.renameTo(originalFile);
    }

    public int getId() {
        return this.userId;
    }

    public void updateMovieList(String movieInfor, String seatString, String method) {
        try (BufferedReader reader = new BufferedReader(new FileReader("movieTracker.csv"));
            PrintWriter out = new PrintWriter(new FileWriter("movieTracker.csv" + ".tmp"))) 
                { 
                    String line;
                    boolean flag = false;
                    String[] nameAndTheater = movieInfor.split(",");
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(",");
                        if (values[0].equals(username)) {
                            line = line + "," + nameAndTheater[0] +"::"+nameAndTheater[1] + "::" + seatString.replace(",", "&") + "::" + method;
                            flag = true;
                        }
                        out.println(line);
                    }
                    if (!flag) {
                        line = username + "," + nameAndTheater[0] + "::" + nameAndTheater[1] + "::" + seatString + "::" + method;
                        out.println(line);
                    }
                  }
        catch (IOException event) {
            System.out.println("File does not exist");
        }
        File originalFile = new File("movieTracker.csv");
        originalFile.delete();
        File updatedFile = new File("movieTracker.csv" + ".tmp");
        updatedFile.renameTo(originalFile);
    }

    public String getPurchase() {
        try (BufferedReader reader = new BufferedReader(new FileReader("movieTracker.csv"));) 
                { 
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(",");
                        if (values[0].equals(username)) {
                            return line;
                        }
                    }
                }
        catch (IOException event) {
            System.out.println("File does not exist");
        }
        return "";
    }

    public boolean checkUserExists() {
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"))) {
           String line;
           while ((line = in.readLine()) != null) {
              String[] parts = line.split(",");
              if (parts[0].equals(username) && parts[1].equals(userPassword)) {
                  if (!parts[3].equals("active")) {
                      break;
                  }
                 return true;
              }
           }
           return false;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error checking user information");
            return false;
        }
     }

    public void changePassword(String newpass) {
        try (BufferedReader in = new BufferedReader(new FileReader("users.csv"));
           PrintWriter out = new PrintWriter(new FileWriter("users.csv" + ".tmp"))) {
         String line;
        while ((line = in.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(username)) {
               parts[1] = newpass;
               line = String.join(",", parts);
            }
            out.println(line);
         }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating user information");
            return;
        }
        this.setUserPassword(newpass);
      
        // Replace the original file with the updated file
        File originalFile = new File("users.csv");
        originalFile.delete();
        File updatedFile = new File("users.csv" + ".tmp");
        updatedFile.renameTo(originalFile);
        }

    //create a fake user information to csv file
    public void authenticateUser(String userId, String email) {
        boolean notExist = true;
        try (PrintWriter out = new PrintWriter(new FileWriter("users.csv", true));
                BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if ((values[0].trim()).equals(username)) {
                        notExist = false;
                    }
            }
            if (notExist) {
                out.println(username + "," + userPassword + "," + userId + "," + "active" + "," +email);
            }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing user information");
            return;
        }
    }
}