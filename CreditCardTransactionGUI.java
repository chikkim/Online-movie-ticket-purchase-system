import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.*;

public class CreditCardTransactionGUI extends JFrame implements ActionListener {
    
    private JLabel cardNumberLabel, expiryDateLabel, cvvLabel, nameHolder;
    private JTextField cardNumberField, expiryDateField, cvvField, nameHolderField;
    private JButton submitButton, clearButton;
    private double a;
    private String seatString, id;
    private User user;
    private SeatAvailability seats;
    
    public CreditCardTransactionGUI(User user, double amount, String seatString, String id) {
        this.seatString = seatString;
        this.user = user;
        this.id = id;
        this.a = amount;
        this.seats = new SeatAvailability(id);

        jFrameSetUp();
        addLabelAndButton();
    }

    public void jFrameSetUp() {
        setTitle("Credit Card Transaction");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        setVisible(true);
    }

    public void addLabelAndButton() {
        cardNumberLabel = new JLabel("Card Number (16 digits):");
        expiryDateLabel = new JLabel("Expiry Date (mm/yy):");
        cvvLabel = new JLabel("CVV:");
        nameHolder = new JLabel("Name Holder:");
        
        cardNumberField = new JTextField(16);
        expiryDateField = new JFormattedTextField(createFormatter("##/##"));
        cvvField = new JTextField(3);
        nameHolderField = new JTextField(10);
        
        submitButton = new JButton("Check out");
        clearButton = new JButton("Clear");
        
        add(nameHolder);
        add(nameHolderField);
        add(cardNumberLabel);
        add(cardNumberField);
        add(expiryDateLabel);
        add(expiryDateField);
        add(cvvLabel);
        add(cvvField);
        add(submitButton);
        add(clearButton);
        
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();
            String nameHolder = nameHolderField.getText();
            // perform transaction processing here
            CreditCardTransaction creditcard = new CreditCardTransaction(nameHolder, expiryDate, cardNumber, cvv);
            if (creditcard.authorized()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to complete the transaction of $" + String.valueOf(a) + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, "Transaction completed.");
                    seats.reserveSeat(seatString);
                    user.updateMovieList(seats.getMovieName() +","+ id, seatString, "bought");
                    dispose();
                };
            }
            else {
                JOptionPane.showMessageDialog(this, "Transaction failed. Please try again");
            }
        } else if (e.getSource() == clearButton) {
            cardNumberField.setText("");
            expiryDateField.setText("");
            cvvField.setText("");
            nameHolderField.setText("");
        }
    }
    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("Error creating formatter: " + exc.getMessage());
        }
        return formatter;
    }
}
