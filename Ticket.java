import java.io.*;

import javax.swing.JOptionPane;

// Class representing a ticket
public class Ticket {
    public String userName, movieName, time, theater, roomId, seats, theaterInfor,status;


    //theater Information is a string concatenated by Theater, RoomId, time
    public Ticket(String userName, String movieName, String theaterInfor, String seats) {
        this.status = "valid";
	    this.userName = userName;
        this.movieName = movieName;
        this.seats = seats.replace(",", "&");
        this.theaterInfor = theaterInfor;
        String[] values = theaterInfor.split(" ");
        String temp="";
        for (int i =0; i<values.length -2; i++) {
            temp = temp + values[i];
        }
        this.theater = temp;
        this.roomId = values[values.length -2];
        this.time = values[values.length -1];

    }


    public String getStatus() {
        return status;
    }
    public void cancel() {
        try (BufferedReader reader = new BufferedReader(new FileReader("movieTracker.csv"));
            PrintWriter out = new PrintWriter(new FileWriter("movieTracker.csv" + ".tmp"))) 
                { 
                    String line;
                    String res="";
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(",");
                        if (values[0].equals(userName)) {
                            res = values[0];
                            for (int i=1; i<values.length;i++) {
                                if (values[i].equals(movieName+ "::" + theaterInfor + "::" +seats + "::bought")) {
                                    String[] s = values[i].split("::");
                                    SeatAvailability seats = new SeatAvailability(s[1]);
                                    seats.setFreeSeat(s[2]);
                                    continue;
                                }
                                res = res + "," + values[i];              
                            }
                            out.println(res);
                        }
                        else {
                            out.println(line);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Cancel purchase successfully!!");
                }
            catch (IOException event) {
                System.out.println("File does not exist");
            }
            File originalFile = new File("movieTracker.csv");
            originalFile.delete();
            File updatedFile = new File("movieTracker.csv" + ".tmp");
            updatedFile.renameTo(originalFile); 
    }

    //create fake ticket for testing
    public void authenticateTicket() {
        try (BufferedReader reader = new BufferedReader(new FileReader("movieTracker.csv"));
            PrintWriter out = new PrintWriter(new FileWriter("movieTracker.csv" + ".tmp"))) 
                { 
                    String line;
                    boolean flag = false;
                    while ((line = reader.readLine()) != null) {
                        String[] values = line.split(",");
                        if (values[0].equals(userName)) {
                            line = line + "," + movieName +"::"+ theaterInfor + "::" + seats;
                            flag = true;
                        }
                        out.println(line);
                    }
                    if (!flag) {
                        line = userName + "," + movieName + "::" + theaterInfor + "::" + seats;
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

        this.status = "Cancelled";
    }
}
