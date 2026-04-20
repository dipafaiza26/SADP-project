import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {

        setTitle("Hotel Reservation System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.WHITE);

        JButton btnRoom = new JButton("Manage Rooms");
        JButton btnCustomer = new JButton("Manage Customers");
        JButton btnReservation = new JButton("Manage Reservations");

        btnRoom.setBackground(Color.GRAY);
        btnRoom.setForeground(Color.WHITE);

        btnCustomer.setBackground(Color.GRAY);
        btnCustomer.setForeground(Color.WHITE);

        btnReservation.setBackground(Color.GRAY);
        btnReservation.setForeground(Color.WHITE);

        panel.add(btnRoom);
        panel.add(btnCustomer);
        panel.add(btnReservation);

        add(panel);

        // Dummy safe actions (no crash even if other forms missing)
        btnRoom.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Room Module Opened"));

        btnCustomer.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Customer Module Opened"));

        btnReservation.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Reservation Module Opened"));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}