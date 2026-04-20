import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReservationForm extends JFrame 
{

    private CustomTextField txtResId, txtRoomNo, txtCustomerId;
    private JSpinner dateSpinner;
    private JTable table;
    private DefaultTableModel model;
    
    private HotelFacade facade;

    public ReservationForm(HotelFacade facade) {
        this.facade = facade;

        setTitle("AURORA HAVEN | Booking Operations");
        setSize(850, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(25, 25));
        UITheme.stylePanel(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        
        RoundedPanel formCard = new RoundedPanel(25);
        formCard.setBackground(Color.WHITE);
        formCard.setLayout(new BorderLayout(15, 25));
        formCard.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Reservation Creation");
        UITheme.styleTitle(title);
        formCard.add(title, BorderLayout.NORTH);

        JPanel inputGrid = new JPanel(new GridLayout(4, 2, 15, 20));
        inputGrid.setOpaque(false);

        JLabel lblResId = new JLabel("Booking ID:");
        UITheme.styleLabel(lblResId);
        txtResId = new CustomTextField();

        JLabel lblRoomNo = new JLabel("Suite No:");
        UITheme.styleLabel(lblRoomNo);
        txtRoomNo = new CustomTextField();

        JLabel lblCustomer = new JLabel("Guest ID:");
        UITheme.styleLabel(lblCustomer);
        txtCustomerId = new CustomTextField();

        JLabel lblDate = new JLabel("Check-in Date:");
        UITheme.styleLabel(lblDate);
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "MMM dd, yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setFont(UITheme.FONT_BODY);
        
        JComponent editorComp = editor.getTextField();
        editorComp.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        dateSpinner.setBorder(BorderFactory.createLineBorder(UITheme.BORDER_GRAY, 1));

        inputGrid.add(lblResId);
        inputGrid.add(txtResId);
        inputGrid.add(lblRoomNo);
        inputGrid.add(txtRoomNo);
        inputGrid.add(lblCustomer);
        inputGrid.add(txtCustomerId);
        inputGrid.add(lblDate);
        inputGrid.add(dateSpinner);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(inputGrid, BorderLayout.NORTH);
        formCard.add(centerWrapper, BorderLayout.CENTER);

        
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        actionPanel.setOpaque(false);
        
        RoundedButton btnSave = new RoundedButton("Confirm Booking");
        RoundedButton btnDelete = new RoundedButton("Cancel Booking");
        RoundedButton btnRefresh = new RoundedButton("Refresh");
        
        btnDelete.setColor(new Color(180, 80, 80));
        btnDelete.setColorOver(new Color(200, 100, 100));

        actionPanel.add(btnRefresh);
        actionPanel.add(btnDelete);
        actionPanel.add(btnSave);
        formCard.add(actionPanel, BorderLayout.SOUTH);

        
        model = new DefaultTableModel(new String[]{"Booking ID", "Suite No", "Guest ID", "Date"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        UITheme.styleTable(table, scrollPane);

        mainPanel.add(formCard, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        refresh();

        btnSave.addActionListener(e -> saveReservation());
        btnDelete.addActionListener(e -> deleteReservation());
        btnRefresh.addActionListener(e -> refresh());

        setVisible(true);
    }

    private void saveReservation() {
        try {
            int resId = Integer.parseInt(txtResId.getText());
            int roomNo = Integer.parseInt(txtRoomNo.getText());
            int customerId = Integer.parseInt(txtCustomerId.getText());
            Date date = (Date) dateSpinner.getValue();

            Reservation newRes = HotelFactory.createReservation(resId, roomNo, customerId, date);
            facade.addReservation(newRes);
            
            refresh();
            txtResId.setText("");
            txtRoomNo.setText("");
            txtCustomerId.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric IDs.");
        }
    }

    private void deleteReservation() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            facade.deleteReservation(row);
            refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Select a booking first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        Iterator<Reservation> iterator = facade.getReservations();
        while (iterator.hasNext()) {
            Reservation r = iterator.next();
            model.addRow(new Object[]{
                    r.getReservationId(),
                    r.getRoomNo(),
                    r.getCustomerId(),
                    sdf.format(r.getReservationDate())
            });
        }
    }
}