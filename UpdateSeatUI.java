
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UpdateSeatUI extends JFrame implements ActionListener
{
    private JButton reserveSeatButton;
    private JButton freeSeatButton;
    private JTextField cinemaNameField;
    private JTextField roomIdField;
    private JTextField timeField;
    private JTextField seatsField;

    public UpdateSeatUI() {
        
        jFrameSetUp();
        addLabelAndButton();

    }

    public void jFrameSetUp() {
        setTitle("User Login");
        setSize(500, 300);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setLayout(new GridLayout(5, 2));
    }

    public void addLabelAndButton() {
        JLabel cinemaNameLabel = new JLabel("Cinema name(please enter full name):");
        cinemaNameField = new JTextField(20);

        JLabel roomIdLabel = new JLabel("Room Id: ");
       roomIdField = new JTextField(20);

        JLabel timeLabel = new JLabel("Time (For example:3, 13, 17): ");
        timeField = new JTextField(20);

        JLabel seatsLabel = new JLabel("Seats (separated by comma): ");
        seatsField = new JTextField(20);

        freeSeatButton = new JButton("Empty those seats");
        freeSeatButton.addActionListener(this);
        reserveSeatButton = new JButton("Reserve those seats");
        reserveSeatButton.addActionListener(this);


        add(cinemaNameLabel);
        add(cinemaNameField);
        add(roomIdLabel);
        add(roomIdField);
        add(timeLabel);
        add(timeField);
        add(seatsLabel);
        add(seatsField);
        add(freeSeatButton);
        add(reserveSeatButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cinemaName = cinemaNameField.getText();
        String roomId = roomIdField.getText();
        String time = timeField.getText();
        String seatString = seatsField.getText();
        if (cinemaName.equals("") || roomId.equals("") || time.equals("") || seatString.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill all the blank");
        }
        else {
            String id = cinemaName + " " + roomId + " " + time;
            System.out.println(id);
            SeatAvailability seatAvail = new SeatAvailability(id);
            if (e.getSource() == freeSeatButton) {
                seatAvail.setFreeSeat(seatString.replace(",","&"));
            }
            else if (e.getSource() == reserveSeatButton) {
                seatAvail.reserveSeat(seatString.replace(",", "&"));
            }
        }
    }
}