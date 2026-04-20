import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Hotel Reservation System");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Hotel Reservation System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title);

        JButton btnRoom = new JButton("Manage Rooms (Factory Pattern)");
        JButton btnCustomer = new JButton("Manage Customers (Facade Pattern)");
        JButton btnExit = new JButton("Exit");

        Color btnColor = Color.GRAY;
        btnRoom.setBackground(btnColor);
        btnRoom.setForeground(Color.WHITE);
        btnCustomer.setBackground(btnColor);
        btnCustomer.setForeground(Color.WHITE);
        btnExit.setBackground(btnColor);
        btnExit.setForeground(Color.WHITE);

        panel.add(btnRoom);
        panel.add(btnCustomer);
        panel.add(btnExit);

        add(panel);

        // Factory Pattern এর জন্য RoomForm খোলা হবে
        btnRoom.addActionListener(e -> new RoomForm());
        
        // Facade Pattern এর জন্য CustomerForm খোলা হবে
        btnCustomer.addActionListener(e -> new CustomerForm());
        
        // Exit বাটন
        btnExit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}
        