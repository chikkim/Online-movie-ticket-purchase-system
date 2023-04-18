import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AwardTicketGUI extends JFrame implements ActionListener 
{
    private JButton awardButton;
    private JTextField usernameField,movieNameField, theaterField, timeField, roomIDField, seatsField ;

    public AwardTicketGUI() {
        jFrameSetUp();

    }
    public void jFrameSetUp() {
        setTitle("Award ticket");
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel movieNameLabel = new JLabel("Movie Name:");
        movieNameField = new JTextField(20);

        JLabel theaterLabel = new JLabel("Theater:");
        theaterField = new JTextField(20);

        JLabel roomIDLabel = new JLabel("Room ID:");
        roomIDField = new JTextField(20);

        JLabel timeLabel = new JLabel("Time:");
        timeField = new JTextField(20);

        JLabel seatLabel = new JLabel("Enter seats (seperated by comma): ");
        seatsField = new JTextField();

        awardButton = new JButton("Award Ticket");
        awardButton.addActionListener(this);


        setLayout(new GridLayout(7, 2));

        add(usernameLabel);
        add(usernameField);
        add(movieNameLabel);
        add(movieNameField);
        add(theaterLabel);
        add(theaterField);
        add(roomIDLabel);
        add(roomIDField);
        add(timeLabel);
        add(timeField);
        add(seatLabel);
        add(seatsField);
        add(new JLabel(""));
        add(awardButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == awardButton) {
            String userName = usernameField.getText();
            String movieName = movieNameField.getText();
            String theater = theaterField.getText();
            String roomID = roomIDField.getText();
            String time = timeField.getText();
            String seatString = seatsField.getText();
            User user = new User(userName);
            if (userName.equals("") || movieName.equals("") || theater.equals("") || roomID.equals("") || time.equals("") || seatString.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill all the text box above");
            }
            else if (!user.checkUserExists()) {
                return;
            }
            else {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to award " + userName+ " ticket for movie " + movieName +"?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                user.updateMovieList(movieName + "," + theater + " " + roomID + " " + time, seatString, "awarded");
                user.setMessage("Your are awarded movie " + movieName + " at " + theater + " at " + time);
                SeatAvailability seatAvailability = new SeatAvailability(theater + " " + roomID + " " + time);
                seatAvailability.authenticateSeat(movieName);
                seatAvailability.reserveSeat(seatString);
                JOptionPane.showMessageDialog(this, "Award ticket to " + userName + " successfully");
                this.setVisible(false);
                } 
            }
        }
    }
}