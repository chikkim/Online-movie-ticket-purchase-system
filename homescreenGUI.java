import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class homescreenGUI extends JFrame implements ActionListener {
    private JPanel contentPanel = new JPanel();
    private JLabel title = new JLabel("Available Movies");

    private JButton btnSignOut = new JButton("Sign out");

    private ArrayList<Movie> allAvailableMovies = new ArrayList<Movie>();

    private JButton movie1btn, movie2btn, movie3btn, movie4btn, movie5btn, movie6btn, movie7btn, movie8btn;

    private User user;

    private JButton accountSettings = new JButton("Account Settings");

    private JPanel movieDisplayPanel = new JPanel();
    private ArrayList<JLabel> movieImages = new ArrayList<JLabel>();

    public homescreenGUI(User user) {
        populateMoviesArrayList();
        this.user = user;
        panelSetup();
        buttonsSetup();
        frameSetup();

    }

    public void panelSetup() {

        contentPanel.setLayout(null);
        contentPanel.setVisible(true);
        contentPanel.setBounds(0, 0, 1280, 720);
        contentPanel.setBackground(new Color(147, 196, 125));

        movieDisplayPanel.setLayout(null);
        movieDisplayPanel.setVisible(true);
        movieDisplayPanel.setBounds(50, 80, 1170, 520);
        movieDisplayPanel.setBackground(Color.WHITE);

        title.setFont(new Font("Ultra", Font.BOLD, 40));
        title.setBounds(450, 10, 500, 60);
        title.setForeground(Color.black);

        contentPanel.add(title);
        contentPanel.add(movieDisplayPanel);
        displayTheMovie();
    }

    private void populateMoviesArrayList() {
        //The location of the csv file
        String filepath = "StoreState/movies.csv";
        Scanner x;

        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n::]");
            String movieName = " ";
            String movieDescription = " ";
            String movieRuntime = " ";
            String movieID = " ";
            String moviePosterLocation = " ";

            while (x.hasNext()) {
                movieName = x.next();
                movieDescription = x.next();
                movieRuntime = x.next();
                movieID = x.next();
                moviePosterLocation = x.next();

                allAvailableMovies.add(new Movie(movieName, movieDescription, Integer.parseInt(movieRuntime), Integer.parseInt(movieID), moviePosterLocation)); // , tempArr.get(0)

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error reading existing movies");
            e.printStackTrace();
        }

        Showtime[] movieShowTime = { new Showtime("May", "2", "2023", "15", "Oedon Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Hamilton Cinemas","380")};
        allAvailableMovies.get(0).assignShowtime(movieShowTime);

        Showtime[] movieShowTime1 = { new Showtime("May", "2", "2023", "11", "Hamilton Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Himalyas Cinemas","380")};
        allAvailableMovies.get(1).assignShowtime(movieShowTime1);

        Showtime[] movieShowTime2 = { new Showtime("May", "2", "2023", "14", "Oedon Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Falcon Cinemas","380")};
        allAvailableMovies.get(2).assignShowtime(movieShowTime2);

        Showtime[] movieShowTime3 = { new Showtime("May", "2", "2023", "18", "Oedon Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Himalyas Cinemas","380")};
        allAvailableMovies.get(3).assignShowtime(movieShowTime3);

        Showtime[] movieShowTime4 = { new Showtime("May", "2", "2023", "11", "Oedon Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Falcon Cinemas","380")};
        allAvailableMovies.get(4).assignShowtime(movieShowTime4);

        Showtime[] movieShowTime5 = { new Showtime("May", "2", "2023", "21", "Hamilton Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Himalyas Cinemas","380")};
        allAvailableMovies.get(5).assignShowtime(movieShowTime5);

        Showtime[] movieShowTime6 = { new Showtime("May", "2", "2023", "15", "Oedon Cinemas","180"),  new Showtime("May", "2", "2023", "9", "Himalyas Cinemas","480"), new Showtime("May", "2", "2023", "8", "Hamilton Cinemas","580")};
        allAvailableMovies.get(6).assignShowtime(movieShowTime6);

        Showtime[] movieShowTime7 = { new Showtime("May", "2", "2023", "11", "Hamilton Cinemas","580")};
        allAvailableMovies.get(7).assignShowtime(movieShowTime7);

    }

    public void displayTheMovie() {
//        //Adds the images to the panel
        movieImages.add(imageResize("images/movie1.jpeg", 125, 250));
        movieImages.get(0).setBounds(10, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(0));
        movie1btn = new JButton("Buy Ticket(s)");
        movie1btn.setBounds(10, 280, 100, 50);
        movie1btn.addActionListener(this);
        movieDisplayPanel.add(movie1btn);
        repaint();
        revalidate();
        movieImages.add(imageResize("images/movie2.jpg", 125, 250));
        movieImages.get(1).setBounds(140, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(1));
        movie2btn = new JButton("Buy Ticket(s)");
        movie2btn.setBounds(140, 280, 100, 50);
        movie2btn.addActionListener(this);
        movieDisplayPanel.add(movie2btn);
        repaint();
        revalidate();
        movieImages.add(imageResize("images/movie3.jpg", 125, 250));
        movieImages.get(2).setBounds(300, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(2));
        movie3btn = new JButton("Buy Ticket(s)");
        movie3btn.setBounds(300, 280, 100, 50);
        movie3btn.addActionListener(this);
        movieDisplayPanel.add(movie3btn);
        repaint();
        revalidate();
        movieImages.add(imageResize("images/movie4.jpg", 125, 250));
        movieImages.get(3).setBounds(435, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(3));
        movie4btn = new JButton("Buy Ticket(s)");
        movie4btn.setBounds(435, 280, 100, 50);
        movie4btn.addActionListener(this);
        movieDisplayPanel.add(movie4btn);

        movieImages.add(imageResize("images/movie5.jpg", 125, 250));
        movieImages.get(4).setBounds(570, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(4));
        movie5btn = new JButton("Buy Ticket(s)");
        movie5btn.setBounds(570, 280, 100, 50);
        movie5btn.addActionListener(this);
        movieDisplayPanel.add(movie5btn);

        movieImages.add(imageResize("images/movie6.jpg", 125, 250));
        movieImages.get(5).setBounds(705, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(5));
        movie6btn = new JButton("Buy Ticket(s)");
        movie6btn.setBounds(705, 280, 100, 50);
        movie6btn.addActionListener(this);
        movieDisplayPanel.add(movie6btn);

        movieImages.add(imageResize("images/movie7.jpg", 125, 250));
        movieImages.get(6).setBounds(835, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(6));
        movie7btn = new JButton("Buy Ticket(s)");
        movie7btn.setBounds(835, 280, 100, 50);
        movie7btn.addActionListener(this);
        movieDisplayPanel.add(movie7btn);

        movieImages.add(imageResize("images/movie8.jpg", 125, 250));
        movieImages.get(7).setBounds(980, 10, 125, 250);
        movieDisplayPanel.add(movieImages.get(7));
        movie8btn = new JButton("Buy Ticket(s)");
        movie8btn.setBounds(980, 280, 100, 50);
        movie8btn.addActionListener(this);
        movieDisplayPanel.add(movie8btn);
        repaint();
        revalidate();
    }

    public void buttonsSetup() {

        btnSignOut.setBounds(950, 7, 150, 60);
        btnSignOut.addActionListener(this);
        contentPanel.add(btnSignOut);

        accountSettings.setBounds(1120, 7, 150, 60);
        accountSettings.setForeground(Color.black);
        accountSettings.addActionListener(this);
        contentPanel.add(accountSettings);

    }

    public void frameSetup() {

        add(contentPanel);
        setTitle("Movie Ticket Purchasing System");
        setBounds(0, 0, 1280, 720);

        setSize(1280, 720);
        setLayout(null);
        getContentPane().setBackground(new Color(147, 196, 125));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

    }

    public JLabel imageResize(String imageLocation, int width, int height) {

        // Gets the image location and then gives it a new size
        ImageIcon image = new ImageIcon(imageLocation);
        Image image1 = image.getImage();
        ImageIcon image2 = new ImageIcon(image1.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel actualImage = new JLabel(image2);
        return actualImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSignOut) {

            this.dispose();
            HomePage homepage = new HomePage();
            homepage.setVisible(true);
            homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);

        } else if (e.getSource() == accountSettings) {

            new AccountSettingsPage(user);

        } 
        if (e.getSource() == movie1btn) {
            try {
                new OrderUI(allAvailableMovies.get(0), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie2btn) {
            try {
                new OrderUI(allAvailableMovies.get(1), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie3btn) {
            try {
                new OrderUI(allAvailableMovies.get(2), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie4btn) {
            try {
                new OrderUI(allAvailableMovies.get(3), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie5btn) {
            try {
                new OrderUI(allAvailableMovies.get(4), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie6btn) {
            try {
                new OrderUI(allAvailableMovies.get(5), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie7btn) {
            try {
                new OrderUI(allAvailableMovies.get(6), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == movie8btn) {
            try {
                new OrderUI(allAvailableMovies.get(7), user);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
