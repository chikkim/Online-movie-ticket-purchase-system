import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ITDeptOptionList extends JFrame implements ActionListener{
    JButton blockUserButton, awardTicketButton, updateSeatButton, btnSignOut;
    public ITDeptOptionList(ITDepartment itDept) {

        setTitle("IT Department");
        setResizable(false);
        setSize(new Dimension(450, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout(5,1, 10,10));
        panel.setSize(new Dimension(350, 360));

        blockUserButton = new JButton("Block user");
        blockUserButton.setFont(new Font("Arial", Font.BOLD, 24));
        blockUserButton.setPreferredSize(new Dimension(350, 50));
        blockUserButton.addActionListener(this);

        awardTicketButton = new JButton("Award ticket");
        awardTicketButton.setFont(new Font("Arial", Font.BOLD, 24));
        awardTicketButton.setPreferredSize(new Dimension(350, 50));
        awardTicketButton.addActionListener(this);

        updateSeatButton = new JButton("Update seat availability");
        updateSeatButton.setFont(new Font("Arial", Font.BOLD, 24));
        updateSeatButton.setPreferredSize(new Dimension(350, 50));
        updateSeatButton.addActionListener(this);

        btnSignOut = new JButton("Sign out");
        btnSignOut.setFont(new Font("Arial", Font.BOLD, 24));
        btnSignOut.setPreferredSize(new Dimension(350, 50));
        btnSignOut.addActionListener(this);

        panel.add(blockUserButton);
        panel.add(updateSeatButton);
        panel.add(awardTicketButton);
        panel.add(btnSignOut);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    BlockUserUI blockUser = new BlockUserUI();
    AwardTicketGUI awardTicket = new AwardTicketGUI();
    UpdateSeatUI updateSeat = new UpdateSeatUI();
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == blockUserButton) {
            blockUser.setVisible(true);
            awardTicket.setVisible(false);
            updateSeat.setVisible(false);
        }
        else if (e.getSource() == awardTicketButton) {
            blockUser.setVisible(false);
            awardTicket.setVisible(true);
            updateSeat.setVisible(false);
        }
        else if (e.getSource() == updateSeatButton) {
            blockUser.setVisible(false);
            awardTicket.setVisible(false);
            updateSeat.setVisible(true);
        }
        else if (e.getSource() == btnSignOut) {
            this.dispose();
            HomePage homepage = new HomePage();
            homepage.setVisible(true);
            homepage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
    }
}
