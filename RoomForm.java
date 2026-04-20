import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

public class MainMenu extends JFrame {

    public MainMenu() {

        setTitle("Hotel Reservation System");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBackground( Color.WHITE);

        JButton btnRoom = new JButton("Manage Rooms");
        JButton btnCustomer = new JButton("Manage Customers");
        JButton btnReservation = new JButton("Manage Reservations");

        btnRoom.setBackground(Color.GRAY);
        btnRoom.setForeground(Color.WHITE);

        btnCustomer.setBackground( Color.GRAY);
        btnCustomer.setForeground(Color.WHITE);

        btnReservation.setBackground(Color.GRAY);
        btnReservation.setForeground(Color.WHITE);

        panel.add(btnRoom);
        panel.add(btnCustomer);
        panel.add(btnReservation);

        add(panel);

        btnRoom.addActionListener(e -> new RoomForm());
        btnCustomer.addActionListener(e -> new CustomerForm());
        btnReservation.addActionListener(e -> new ReservationForm());

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}