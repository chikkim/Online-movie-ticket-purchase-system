import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderUI extends JFrame implements ActionListener {
    // instance variables
    private JLabel movieNameLabel, priceLabel, posterLabel, typeLabel, quantityLabel, movieNameField, priceField,
            roomLabel, roomField, timeLabel, theaterLabel;
    private String movieName, theaterID, roomId, time, posterName;
    private JTextField quantityField;
    private JComboBox<String> typeComboBox, timeComboBox, theaterComboBox;
    private JButton orderButton;
    private JPanel mainPanel, buttonPanel;
    private HashMap<String, Double> typePriceMap;
    private String theater;
    private String selectedTime;
    private String[] times;
    private User user;

    public OrderUI(Movie movie, User user) throws IOException {
        this.user = user;
        this.movieName = movie.getTitle();
        this.posterName = movie.getPhoto();

        // set frame properties
        setVisible(true);
        setTitle("Order Ticket");
        setLocationRelativeTo(null);

        typePriceMap = new HashMap<>();
        typePriceMap.put("Regular", 15.0);
        typePriceMap.put("Student", 12.50);
        typePriceMap.put("Senior", 12.50);
        // create and configure GUI components
        movieNameLabel = new JLabel("Movie Name: ");
        movieNameField = new JLabel(movieName);

        theaterLabel = new JLabel("Theater: ");
        String[] theaters = getAllTheaterLocation(movie);
        theater = theaters[0];
        theaterComboBox = new JComboBox<>(theaters);
        theaterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                theater = (String) theaterComboBox.getSelectedItem();
                times = getTime(movie, theater);
                selectedTime = times[0];
                timeComboBox = new JComboBox<>(times);
            }
        });
        timeLabel = new JLabel("Time: ");
        times = getTime(movie, theater);
        selectedTime = times[0];
        timeComboBox = new JComboBox<>(times);
        timeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roomId = getRoomTheater(movie, selectedTime, theater);
                roomLabel = new JLabel("Room: ");
                selectedTime = (String) timeComboBox.getSelectedItem();
                mainPanel.remove(roomField);
                roomField = new JLabel("# " + roomId);
                mainPanel.add(roomField);
                repaint();
                revalidate();
            }
        });

        roomId = getRoomTheater(movie, selectedTime, theater);
        roomLabel = new JLabel("Room: ");
        roomField = new JLabel("# " + roomId);

        priceLabel = new JLabel("Price: ");
        priceField = new JLabel("15.00");

        typeLabel = new JLabel("Type: ");
        String[] types = { "Regular", "Student", "Senior" };
        typeComboBox = new JComboBox<>(types);

        quantityLabel = new JLabel("Number of tickets: ");
        quantityField = new JTextField(5);

        orderButton = new JButton("Order");
        orderButton.addActionListener(this);

        // create main panel and add components
        mainPanel = new JPanel(new GridLayout(8, 2));
        mainPanel.add(movieNameLabel);
        mainPanel.add(movieNameField);
        mainPanel.add(typeLabel);
        mainPanel.add(typeComboBox);
        mainPanel.add(priceLabel);
        mainPanel.add(priceField);
        mainPanel.add(quantityLabel);
        mainPanel.add(quantityField);
        mainPanel.add(theaterLabel);
        mainPanel.add(theaterComboBox);
        mainPanel.add(timeLabel);
        mainPanel.add(timeComboBox);
        mainPanel.add(roomLabel);
        mainPanel.add(roomField);

        // create avatar panel and add components
        JLabel posterLabel = imageResize(posterName, 125, 250);
        JPanel posterPanel = new JPanel();
        posterPanel.add(posterLabel);

        // create button panel and add components
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(orderButton);

        // add main panel and button panel to frame
        add(posterPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // update the price field whenever the user selects a different movie type
        typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                double price = typePriceMap.get(selectedType);
                priceField.setText(String.format("%.2f", price));
            }
        });
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        writeSeat(theater + " " + roomId + " " + selectedTime);
    }

    public String getRoomTheater(Movie movie, String time, String theaterLocation) {
        String res = "33";
        Showtime[] showTimes = movie.getMovieShowTime();
        for (Showtime showtime : showTimes) {
            if (showtime.getTime() == time && showtime.getTheater() == theaterLocation) {
                res = showtime.getRoom();
            }
        }
        return res;
    }

    public String[] getAllTheaterLocation(Movie movie) {
        ArrayList<String> temp = new ArrayList<>();
        for (Showtime showTime : movie.getMovieShowTime()) {
            String theater = showTime.getTheater();
            if (!temp.contains(theater)) {
                temp.add(theater);
            }
        }
        String[] res = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        return res;
    }

    public String[] getTime(Movie movie, String theater) {
        ArrayList<String> temp = new ArrayList<>();
        for (Showtime showTime : movie.getMovieShowTime()) {
            if (theater == showTime.getTheater()) {
                temp.add(showTime.getTime());
            }
        }
        String[] res = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            res[i] = temp.get(i);
        }
        ;
        return res;
    }

    // action listener for order button
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            // get values from GUI components
            String movieName = movieNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            String type = (String) typeComboBox.getSelectedItem();
            if (quantityField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the number of ticket you want to buy");
            }
            int quantity = Integer.parseInt(quantityField.getText());

            // perform order processing
            double total = price * quantity;
            new SeatAvailabilityUI(movieName,theater + " " + roomId + " "+ selectedTime, quantity, total, user);
            this.setVisible(false);
            ;
        }
    }

    public void writeSeat(String id) throws IOException {
        // Check if ID already exists in the file
        BufferedReader reader = new BufferedReader(new FileReader("seats.csv"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            if ((values[1].trim()).equals(id)) {
                reader.close();
                return; // ID already exists, return without writing anything
            }
        }
        reader.close();

        // ID does not exist, write to file
        String zeros = "0 ".repeat(80);
        String data = movieName +"," + id + "," + zeros.trim() + "\n";
        FileWriter writer = new FileWriter("seats.csv", true);
        writer.write(data);
        writer.close();
    }

    public JLabel imageResize(String imageLocation, int width, int height) {

        // Gets the image location and then gives it a new size
        ImageIcon image = new ImageIcon(imageLocation);
        Image image1 = image.getImage();
        ImageIcon image2 = new ImageIcon(image1.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel actualImage = new JLabel(image2);
        return actualImage;
    }
}
