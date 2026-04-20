import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerForm extends JFrame {

    private CustomTextField txtId, txtName;
    private JComboBox<Gender> cmbGender;
    private JTable table;
    private DefaultTableModel model;
    
    private HotelFacade facade;

    public CustomerForm(HotelFacade facade) {
        this.facade = facade;

        setTitle("AURORA HAVEN | Guest Directory");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(25, 25));
        UITheme.stylePanel(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Rounded Form Card
        RoundedPanel formCard = new RoundedPanel(25);
        formCard.setBackground(Color.WHITE);
        formCard.setLayout(new BorderLayout(15, 25));
        formCard.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Guest Information");
        UITheme.styleTitle(title);
        formCard.add(title, BorderLayout.NORTH);

        // Input Grid
        JPanel inputGrid = new JPanel(new GridLayout(3, 2, 15, 20));
        inputGrid.setOpaque(false);

        JLabel lblId = new JLabel("Guest ID:");
        UITheme.styleLabel(lblId);
        txtId = new CustomTextField();

        JLabel lblName = new JLabel("Guest Name:");
        UITheme.styleLabel(lblName);
        txtName = new CustomTextField();

        JLabel lblGender = new JLabel("Gender:");
        UITheme.styleLabel(lblGender);
        cmbGender = new JComboBox<>(Gender.values());
        cmbGender.setFont(UITheme.FONT_BODY);
        cmbGender.setBackground(Color.WHITE);

        inputGrid.add(lblId);
        inputGrid.add(txtId);
        inputGrid.add(lblName);
        inputGrid.add(txtName);
        inputGrid.add(lblGender);
        inputGrid.add(cmbGender);

        // Wrapper to prevent vertical stretching
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(inputGrid, BorderLayout.NORTH);
        formCard.add(centerWrapper, BorderLayout.CENTER);

        // Buttons
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        actionPanel.setOpaque(false);
        
        RoundedButton btnSave = new RoundedButton("Save Guest");
        RoundedButton btnDelete = new RoundedButton("Remove");
        RoundedButton btnRefresh = new RoundedButton("Refresh");
        
        btnDelete.setColor(new Color(180, 80, 80));
        btnDelete.setColorOver(new Color(200, 100, 100));

        actionPanel.add(btnRefresh);
        actionPanel.add(btnDelete);
        actionPanel.add(btnSave);
        formCard.add(actionPanel, BorderLayout.SOUTH);

        // Table Area
        model = new DefaultTableModel(new String[]{"ID", "Guest Name", "Gender"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        UITheme.styleTable(table, scrollPane);

        mainPanel.add(formCard, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        refresh();

        btnSave.addActionListener(e -> saveCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnRefresh.addActionListener(e -> refresh());

        setVisible(true);
    }

    private void saveCustomer() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            Gender gender = (Gender) cmbGender.getSelectedItem();

            Customer newCustomer = HotelFactory.createCustomer(id, name, gender);
            facade.addCustomer(newCustomer);
            
            refresh();
            txtId.setText("");
            txtName.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.");
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            facade.deleteCustomer(row);
            refresh();
        } else {
            JOptionPane.showMessageDialog(this, "Select a record first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        Iterator<Customer> iterator = facade.getCustomers();
        while (iterator.hasNext()) {
            Customer c = iterator.next();
            model.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getName(),
                    c.getGender()
            });
        }
    }
}