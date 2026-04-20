import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class RoomForm extends JFrame {

    private JTextField txtRoomNo, txtPrice;
    private JComboBox<RoomType> cmbRoomType;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Room> roomList = new ArrayList<>();
    private HotelFacade facade = new HotelFacade();

    public RoomForm() {
        setTitle("Room Management (Factory Pattern)");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
        load();
        setVisible(true);
    }

    private void initUI() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Room Management System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        inputPanel.add(title);
        inputPanel.add(new JLabel(""));

        inputPanel.add(new JLabel("Room Number:"));
        txtRoomNo = new JTextField();
        inputPanel.add(txtRoomNo);

        inputPanel.add(new JLabel("Room Type:"));
        cmbRoomType = new JComboBox<>(RoomType.values());
        inputPanel.add(cmbRoomType);

        inputPanel.add(new JLabel("Price:"));
        txtPrice = new JTextField();
        txtPrice.setEditable(false);
        txtPrice.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(txtPrice);

        JButton btnStandard = new JButton("Standard Room");
        JButton btnCustom = new JButton("Custom Price");
        JButton btnDiscount = new JButton("Discounted Room");
        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");

        Color btnColor = Color.GRAY;
        for (JButton b : new JButton[]{btnStandard, btnCustom, btnDiscount, btnSave, btnDelete}) {
            b.setBackground(btnColor);
            b.setForeground(Color.WHITE);
        }

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        buttonPanel.add(btnStandard);
        buttonPanel.add(btnCustom);
        buttonPanel.add(btnDiscount);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnDelete);

        inputPanel.add(buttonPanel);
        inputPanel.add(new JLabel(""));

        model = new DefaultTableModel(new String[]{"Room No", "Type", "Price"}, 0);
        table = new JTable(model);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        cmbRoomType.addActionListener(e -> updateDefaultPrice());
        btnStandard.addActionListener(e -> createStandardRoom());
        btnCustom.addActionListener(e -> createCustomPriceRoom());
        btnDiscount.addActionListener(e -> createDiscountedRoom());
        btnSave.addActionListener(e -> saveRoom());
        btnDelete.addActionListener(e -> deleteRoom());

        updateDefaultPrice();
    }

    private void updateDefaultPrice() {
        RoomType type = (RoomType) cmbRoomType.getSelectedItem();
        double price = (type == RoomType.SINGLE) ? 100 : (type == RoomType.DOUBLE) ? 150 : 250;
        txtPrice.setText(String.valueOf(price));
    }

    private void createStandardRoom() {
        try {
            int no = Integer.parseInt(txtRoomNo.getText());
            RoomType type = (RoomType) cmbRoomType.getSelectedItem();
            Room room = RoomFactory.createRoom(no, type);
            roomList.add(room);
            saveAndRefresh();
            clearFields();
            JOptionPane.showMessageDialog(this, "Standard room added");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid room number");
        }
    }

    private void createCustomPriceRoom() {
        try {
            int no = Integer.parseInt(txtRoomNo.getText());
            RoomType type = (RoomType) cmbRoomType.getSelectedItem();
            String priceStr = JOptionPane.showInputDialog(this, "Enter custom price:");
            if (priceStr != null && !priceStr.isEmpty()) {
                double price = Double.parseDouble(priceStr);
                Room room = RoomFactory.createRoomWithCustomPrice(no, type, price);
                roomList.add(room);
                saveAndRefresh();
                clearFields();
                JOptionPane.showMessageDialog(this, "Custom price room added");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void createDiscountedRoom() {
        try {
            int no = Integer.parseInt(txtRoomNo.getText());
            RoomType type = (RoomType) cmbRoomType.getSelectedItem();
            String discStr = JOptionPane.showInputDialog(this, "Discount percentage:");
            if (discStr != null && !discStr.isEmpty()) {
                double disc = Double.parseDouble(discStr);
                Room room = RoomFactory.createRoomWithDiscount(no, type, disc);
                roomList.add(room);
                saveAndRefresh();
                clearFields();
                JOptionPane.showMessageDialog(this, "Discounted room added (" + disc + "%)");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    private void saveRoom() {
        try {
            int no = Integer.parseInt(txtRoomNo.getText());
            RoomType type = (RoomType) cmbRoomType.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());
            Room room = new Room(no, type, price);
            roomList.add(room);
            saveAndRefresh();
            clearFields();
            JOptionPane.showMessageDialog(this, "Room saved");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid data");
        }
    }

    private void deleteRoom() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            roomList.remove(row);
            saveAndRefresh();
            JOptionPane.showMessageDialog(this, "Room deleted");
        } else {
            JOptionPane.showMessageDialog(this, "Select a room");
        }
    }

    private void saveAndRefresh() {
        facade.saveRooms(roomList);
        refresh();
    }

    private void refresh() {
        model.setRowCount(0);
        for (Room r : roomList) {
            model.addRow(new Object[]{r.getRoomNo(), r.getRoomType(), r.getPrice()});
        }
    }

    private void load() {
        roomList = facade.loadRooms();
        refresh();
    }

    private void clearFields() {
        txtRoomNo.setText("");
        cmbRoomType.setSelectedIndex(0);
        updateDefaultPrice();
    }
}

