import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SeatAvailabilityUI extends JFrame implements ActionListener{

    private JButton[][] seats;
    private int[][] avSeats = new int[8][10];
    private JButton nextButton;
    private JTextField seatField;
    private String id,seatString, movieTitle;
    private int quantity;
    private double total;
    private User user;

    public SeatAvailabilityUI(String movieTitle, String id, int quantity, double amount, User user) {
        this.movieTitle = movieTitle;
        this.user = user;
        this.id = id;
        this.quantity = quantity;
        this.total = amount;
        
        jFrameSetUp();
        readSeats();
        showSeatsByAddingPanel();
    }


    public void jFrameSetUp() {
        setTitle("Cinema Seat Availability");
        setSize(800, 600);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        setVisible(true);
    }

    public void readSeats() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    String list = values[2];
                    list = list.trim();
                    // Split all the element of array by ;
                    String[] nums = list.split(" ");
                    int numRows = 8;
                    int numCols = 10; // Assuming all rows have same number of columns
                    avSeats = new int[numRows][numCols];
                    for (int i = 0; i < numRows; i++) {
                        for (int j = 0; j < numCols; j++) {
                            String value = nums[i*10+j].trim();
                            if (value.matches("-?\\d+")) {
                                avSeats[i][j] = Integer.parseInt(value);
                            } else {
                                // Handle non-numeric values
                                avSeats[i][j] = 0;
                            }
                        }
                    };
                    break;
                }
            }
            reader.close();
        } 
        catch (IOException e) {
            System.out.println("File does not exist");
        }
    }

    public void showSeatsByAddingPanel() {
        int ROWS = 8;
        int COLS = 10;

        JPanel screenPanel = new JPanel();
        screenPanel.setPreferredSize(new Dimension(100,50));
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.PAGE_AXIS));
        JLabel screenLabel = new JLabel("SCREEN");
        screenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        screenPanel.add(Box.createVerticalGlue());
        screenPanel.add(screenLabel);
        screenPanel.add(Box.createVerticalGlue());

        JPanel seatPanel = new JPanel(new GridLayout(ROWS, COLS));
        seats = new JButton[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JButton seat;
                if (avSeats[i][j] ==0) {
                    if (j==9) {
                        seat = new JButton(String.format("%d%d", i+1, 0));
                    }
                    else {
                        seat = new JButton(String.format("%d%d", i, j + 1));
                    }
                }
                else {
                    seat = new JButton("X");
                    seat.setEnabled(false);
                }
                seats[i][j] = seat;
                seatPanel.add(seat);
            }
        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.setSize(800,500);
        nextButton = new JButton("Go to payment");
        nextButton.addActionListener(this);
        buttonPanel.add(new JLabel("Please enter your seat below (separated by comma): "));
        seatField = new JTextField();
        buttonPanel.add(seatField);
        buttonPanel.add(nextButton);
        

        add(screenPanel, BorderLayout.NORTH);
        add(seatPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == nextButton) {
            seatString = seatField.getText();
            String[] seatList = seatString.split(",");
            Boolean allowUpdate = true;
            Boolean allowPopup = true;
        try (BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));)
        {   
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if ((values[1].trim()).equals(id)) {
                    String list = values[2];
                    list = list.trim();
                    // Split all the element of array by space
                    String[] nums = list.split(" ");
                    if (seatList.length != quantity) {
                        JOptionPane.showMessageDialog(this, "The number of seats not equal the number of tickets");
                        allowUpdate = false;
                        allowPopup = false;
                        break;
                    }
                    for (int i=0; i<seatList.length; i++) {
                        int n = Integer.parseInt(seatList[i].trim());
                        int first = n/10;
                        int sec = n%10;
                        if (Integer.parseInt(nums[first*10 + sec -1]) !=0) {
                            JOptionPane.showMessageDialog(this, "Unavailable Seat. Try again!");
                            allowUpdate = false;
                            allowPopup = false;
                            break;
                        }
                    }
                } 
            }
        }
            catch (IOException event) {
                System.out.println("File does not exist");
            }

            if (allowPopup) {
                int confirm = JOptionPane.showConfirmDialog(this, "We only accept transaction by credit card. Will you pay by credit card?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                        new CreditCardTransactionGUI(user, total, seatString.replace(",","&"), id);
                        this.setVisible(false);
                }}
            }
    }
}