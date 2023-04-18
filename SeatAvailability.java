import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class SeatAvailability {
    private String theater, roomId, time, id;

    public SeatAvailability(String theater, String roomId, String time) {
        this.theater = theater;
        this.roomId = roomId;
        this.time = time;
        this.id = theater + " " + roomId + " " + time;
    }

    public SeatAvailability(String id) {
        this.id = id;
        String[] values = id.split(" ");
        String temp="";
        for (int i =0; i<values.length -2; i++) {
            temp = temp + values[i];
        }
        this.theater = temp;
        this.roomId = values[values.length -2];
        this.time = values[values.length -1];
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void reserveSeat(String seatString) {
        String temp = seatString.replace(",", "&");
        String[] seatList = temp.split("&");
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        PrintWriter out = new PrintWriter(new FileWriter("seats.csv" + ".tmp"))) 
        {   
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    flag = true;
                    String list = values[2];
                    list = list.trim();
                    // Split all the element of array by space
                    String[] nums = list.split(" ");
                    for (int i=0; i<seatList.length; i++) {
                        int n = Integer.parseInt(seatList[i].trim());
                        int first = n/10;
                        int sec = n%10;
                        nums[first*10 + sec -1] ="1";
                        values[2] = String.join(" ", nums);  
                        }
                        line = String.join(",", values);
                    }
                    out.println(line);
            } 
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Cinema Name or Room ID or Time does not exist. Please Try again!!");
            }
        }
        catch (IOException event) {
            System.out.println("File does not exist");
        }
        // Replace the original file with the updated file
        File originalFile = new File("seats.csv");
        originalFile.delete();
        File updatedFile = new File("seats.csv" + ".tmp");
        updatedFile.renameTo(originalFile);
    }
    
    public String getMovieName() {
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"))){
            String line ;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    return values[0];
            }
        }
        }
        catch (IOException event) {
            System.out.println("File does not exist");
        }
        return "";
    }
    
    public void setFreeSeat(String seatString) {
        String temp = seatString.replace(",", "&");
        String[] seatList = temp.split("&");
        boolean flag = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        PrintWriter out = new PrintWriter(new FileWriter("seats.csv" + ".tmp"))) 
        {   
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    flag = true;
                    String list = values[2];
                    list = list.trim();
                    // Split all the element of array by space
                    String[] nums = list.split(" ");
                    for (int i=0; i<seatList.length; i++) {
                        int n = Integer.parseInt(seatList[i].trim());
                        int first = n/10;
                        int sec = n%10;
                        nums[first*10 + sec -1] ="0";
                        values[2] = String.join(" ", nums);  
                        }
                        line = String.join(",", values);
                    }
                    out.println(line);
            } 
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Cinema Name or Room ID or Time does not exist. Please Try again!!");
            }
        }
        catch (IOException event) {
            System.out.println("File does not exist");
        }
        // Replace the original file with the updated file
        File originalFile = new File("seats.csv");
        originalFile.delete();
        File updatedFile = new File("seats.csv" + ".tmp");
        updatedFile.renameTo(originalFile);
    }

    public String getSeatStatus(String seat) {
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        PrintWriter out = new PrintWriter(new FileWriter("seats.csv" + ".tmp"))) 
        {   
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    String list = values[2];
                    list = list.trim();
                    // Split all the element of array by space
                    String[] nums = list.split(" ");
                    int n = Integer.parseInt(seat.trim());
                    if (nums[n - 1].equals("0")){
                        return "Empty";
                    }
                    else if (nums[n - 1].equals("1")){
                        return "Reserved";
                    }
                    values[2] = String.join(" ", nums);  
                }
                }
        } 
        catch (IOException event) {
            System.out.println("File does not exist");
        }

        return "Getting status of seat #" +seat+" fails";
    }

    public String getAllAvailableSeatsForAMovie() {
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
             PrintWriter out = new PrintWriter(new FileWriter("seats.csv" + ".tmp"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    String list = values[2];
                    return list.trim();
                }
            }
        } catch (IOException event) {
            System.out.println("File does not exist");
        }
        return "No just showtime is present";
    }

    //create a fake seat availability in seats.csv file
    public void authenticateSeat(String movieName) {
        String zeros = "0 ".repeat(80);
        Boolean notExist = true;
        try (PrintWriter out = new PrintWriter(new FileWriter("seats.csv", true));
            BufferedReader reader = new BufferedReader(new FileReader("seats.csv"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if ((values[1].trim()).equals(id)) {
                        notExist = false;
                    }
            }
            if (notExist) {
                out.println(movieName + "," + id + "," + zeros);
            }
        } 
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error:");
            return;
        }
    }
}
