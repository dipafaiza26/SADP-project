import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomForm extends JFrame 
{

    private CustomTextField txtRoomNo, txtPrice;
    private JComboBox<RoomType> cmbType;
    private JTable table;
    private DefaultTableModel model;
    
    private HotelFacade facade;

    public RoomForm(HotelFacade facade) {
        this.facade = facade;

        setTitle("AURORA HAVEN | Suite Management");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(25, 25));
        UITheme.stylePanel(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

       
        RoundedPanel formCard = new RoundedPanel(25);
        formCard.setBackground(Color.WHITE);
        formCard.setLayout(new BorderLayout(15, 25));
        formCard.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Suite Information");
        UITheme.styleTitle(title);
        formCard.add(title, BorderLayout.NORTH);

        JPanel inputGrid = new JPanel(new GridLayout(3, 2, 15, 20));
        inputGrid.setOpaque(false);

        JLabel lblRoomNo = new JLabel("Suite No:");
        UITheme.styleLabel(lblRoomNo);
        txtRoomNo = new CustomTextField();

        JLabel lblType = new JLabel("Collection Type:");
        UITheme.styleLabel(lblType);
        cmbType = new JComboBox<>(RoomType.values());
        cmbType.setFont(UITheme.FONT_BODY);
        cmbType.setBackground(Color.WHITE);

        JLabel lblPrice = new JLabel("Rate per Night ($):");
        UITheme.styleLabel(lblPrice);
        txtPrice = new CustomTextField();

        inputGrid.add(lblRoomNo);
        inputGrid.add(txtRoomNo);
        inputGrid.add(lblType);
        inputGrid.add(cmbType);
        inputGrid.add(lblPrice);
        inputGrid.add(txtPrice);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(inputGrid, BorderLayout.NORTH);
        formCard.add(centerWrapper, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        actionPanel.setOpaque(false);
        
        RoundedButton btnSave = new RoundedButton("Save Suite");
        RoundedButton btnDelete = new RoundedButton("Remove");
        RoundedButton btnRefresh = new RoundedButton("Refresh");
        
        btnDelete.setColor(new Color(180, 80, 80));
        btnDelete.setColorOver(new Color(200, 100, 100));

        actionPanel.add(btnRefresh);
        actionPanel.add(btnDelete);
        actionPanel.add(btnSave);
        formCard.add(actionPanel, BorderLayout.SOUTH);

       
        model = new DefaultTableModel(new String[]{"Suite No", "Collection", "Nightly Rate"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        UITheme.styleTable(table, scrollPane);

        mainPanel.add(formCard, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        refresh();

        btnSave.addActionListener(e -> saveRoom());
        btnDelete.addActionListener(e -> deleteRoom());
        btnRefresh.addActionListener(e -> refresh());

        setVisible(true);
    }

    private void saveRoom() {
        try {
            int roomNo = Integer.parseInt(txtRoomNo.getText());
            RoomType type = (RoomType) cmbType.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            Room newRoom = HotelFactory.createRoom(roomNo, type, price);
            facade.addRoom(newRoom);
            
            refresh();
            txtRoomNo.setText("");
            txtPrice.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }

    private void deleteRoom() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            facade.deleteRoom(row);
            refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Select a record first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        Iterator<Room> iterator = facade.getRooms();
        while (iterator.hasNext()) {
            Room r = iterator.next();
            model.addRow(new Object[]{
                    r.getRoomNo(),
                    r.getRoomType(),
                    "$" + String.format("%.2f", r.getPrice())
            });
        }
    }
}