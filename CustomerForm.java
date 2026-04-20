import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class CustomerForm extends JFrame {

    private JTextField txtId, txtName;
    private JComboBox<Gender> cmbGender;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Customer> customerList = new ArrayList<>();
    private HotelFacade facade = new HotelFacade();   

    public CustomerForm() {
        setTitle("Customer Form");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Customer Information");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.BLACK);

        panel.add(title);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Customer ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Customer Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Gender:"));
        cmbGender = new JComboBox<>(Gender.values());
        panel.add(cmbGender);

        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");

        btnSave.setBackground(Color.GRAY);
        btnSave.setForeground(Color.WHITE);
        btnDelete.setBackground(Color.GRAY);
        btnDelete.setForeground(Color.WHITE);

        panel.add(btnSave);
        panel.add(btnDelete);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender"}, 0);
        table = new JTable(model);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        load();

        btnSave.addActionListener(e -> saveCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());

        setVisible(true);
    }

    private void saveCustomer() {
        try {
            Customer c = new Customer(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    (Gender) cmbGender.getSelectedItem()
            );
            customerList.add(c);
            facade.saveCustomers(customerList);
            refresh();
            clearFields();
            JOptionPane.showMessageDialog(this, "Customer saved!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID");
        }
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            customerList.remove(row);
            facade.saveCustomers(customerList);
            refresh();
            JOptionPane.showMessageDialog(this, "Customer deleted!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a row first");
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        cmbGender.setSelectedIndex(0);
    }

    private void refresh() {
        model.setRowCount(0);
        for (Customer c : customerList) {
            model.addRow(new Object[]{c.getCustomerId(), c.getName(), c.getGender()});
        }
    }

    private void load() {
        customerList = facade.loadCustomers();
        refresh();
    }
}
    