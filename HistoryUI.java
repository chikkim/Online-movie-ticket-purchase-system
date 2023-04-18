import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
public class HistoryUI extends JFrame{

    // Define data structures to store purchase history
    private String userName;
    private int rows = 0;
    private boolean found = false;
    public HistoryUI(User user) {
        userName = user.getUsername();
        this.userName = userName;

        // Set the title and layout
        String purchaseList = user.getPurchase();
        String[] values = purchaseList.split(",");
        rows = values.length;

        jFrameSetUp();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("History Purchase", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD,36));
        panel1.add(titleLabel, BorderLayout.NORTH );
        add(panel1);

        if (!purchaseList.equals("")) {
            JPanel panel = new JPanel();
            panel.setLayout( new FlowLayout(FlowLayout.CENTER, 10, 0));
            panel.setPreferredSize(new Dimension(1000,50));
            panel.setBackground(Color.lightGray);
            JLabel movieLabel = new JLabel("Movie name");
            movieLabel.setPreferredSize(new Dimension(140,50));

            JLabel theaterLabel = new JLabel("Theater");
            theaterLabel.setPreferredSize(new Dimension(140,50));

            JLabel roomLabel = new JLabel("Theater Room");
            roomLabel.setPreferredSize(new Dimension(140,50));

            JLabel timeLabel = new JLabel("Time");
            timeLabel.setPreferredSize(new Dimension(140,50));

            JLabel seatLabel = new JLabel("Seat");
            seatLabel.setPreferredSize(new Dimension(140,50));

            JLabel cancelOption = new JLabel("Cancel");
            cancelOption.setPreferredSize(new Dimension(140,50));

            panel.add(movieLabel);
            panel.add(theaterLabel);
            panel.add(roomLabel);
            panel.add(timeLabel);
            panel.add(seatLabel);
            panel.add(cancelOption);

            add(panel);

            for (int i =1; i<rows; i++) {
                String infor = values[i];
                String[] parts = infor.split("::");
                System.out.println(parts[1]);
                JPanel row = new JPanel();
                row.setLayout( new FlowLayout(FlowLayout.CENTER, 10, 10));
                row.setPreferredSize(new Dimension(1000,50));

                JLabel movie = new JLabel(parts[0]);
                movie.setPreferredSize(new Dimension(140,50));

                String[] theaterInfor = parts[1].split(" ");
                String theaterName ="";
                for (int j=0; j<theaterInfor.length-2; j++) {
                    theaterName = theaterName + theaterInfor[j] + " ";
                };
                JLabel theater = new JLabel(theaterName);
                theater.setPreferredSize(new Dimension(140,50));

                JLabel room = new JLabel("#" + theaterInfor[theaterInfor.length - 2]);
                room.setPreferredSize(new Dimension(140,50));

                JLabel time = new JLabel(theaterInfor[theaterInfor.length - 1]);
                time.setPreferredSize(new Dimension(140,50));

                String[] seats = parts[2].split("&");
                Arrays.sort(seats);
                String s = String.join(",", seats);
                JLabel seat = new JLabel(s);
                seat.setPreferredSize(new Dimension(140,50));

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setPreferredSize(new Dimension(140,50));
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelAction(infor);
                    }
                });

                row.add(movie);
                row.add(theater);
                row.add(room);
                row.add(time);
                row.add(seat);
                row.add(cancelButton);

                add(row);
            }
        }
        else {
            JLabel announce = new JLabel("No item ", SwingConstants.CENTER);
            announce.setFont(new Font("Arial", Font.BOLD,20));
            announce.add(titleLabel, BorderLayout.NORTH );
            add(announce);
        }
    }

    public void jFrameSetUp() {

        setTitle("Purchase History");
        setSize(1000,200*rows);
        setLayout(new FlowLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setVisible(true);
    }

    public void cancelAction(String infor) {
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to delete the purchase?!?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String[] values = infor.split("::");
            Ticket ticket = new Ticket(userName, values[0], values[1], values[2]);
            ticket.cancel();
        }
    }
}