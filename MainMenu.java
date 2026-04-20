import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

public class MainMenu extends JFrame 
{

    private HotelFacade facade;

    public MainMenu() 
    {
        StorageStrategy strategy = new FileStorageStrategy("hotel_data.dat");
        facade = new HotelFacade(strategy);

        setTitle("AURORA HAVEN | Hotel Reservation System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(UITheme.BG_CREAM);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 40));
        UITheme.stylePanel(mainPanel);
        mainPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

       
        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        headerPanel.setOpaque(false);
        
        JLabel lblTitle = new JLabel("AURORA HAVEN", SwingConstants.CENTER);
        UITheme.styleTitle(lblTitle);
        lblTitle.setFont(lblTitle.getFont().deriveFont(42f));
        
        JLabel lblSubtitle = new JLabel("Bespoke Reservations", SwingConstants.CENTER);
        UITheme.styleLabel(lblSubtitle);
        
        headerPanel.add(lblTitle);
        headerPanel.add(lblSubtitle);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 0, 20));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(new EmptyBorder(20, 150, 20, 150));

        RoundedButton btnRoom = new RoundedButton("Manage Suites");
        RoundedButton btnCustomer = new RoundedButton("Guest Directory");
        RoundedButton btnReservation = new RoundedButton("Bookings");
        RoundedButton btnUndo = new RoundedButton("Undo Last Action");

       
        btnUndo.setColor(new Color(190, 150, 120));
        btnUndo.setColorOver(new Color(210, 170, 140));

        buttonsPanel.add(btnRoom);
        buttonsPanel.add(btnCustomer);
        buttonsPanel.add(btnReservation);
        buttonsPanel.add(btnUndo);

       
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        add(mainPanel);

        btnRoom.addActionListener(e -> new RoomForm(facade));
        btnCustomer.addActionListener(e -> new CustomerForm(facade));
        btnReservation.addActionListener(e -> new ReservationForm(facade));
        
        btnUndo.addActionListener(e -> {
            facade.undoLastAction();
            JOptionPane.showMessageDialog(this, "Last action undone successfully.");
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}