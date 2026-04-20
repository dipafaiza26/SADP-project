
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class CustomerForm extends JFrame {

    private JTextField txtId, txtName;
    private JComboBox<Gender> cmbGender;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Customer> customerList = new ArrayList<>();

    public CustomerForm() {

        setTitle("Customer Form");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5,2,5,5));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Customer Information");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground( Color.BLACK);

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

        model = new DefaultTableModel(
                new String[]{"ID","Name","Gender"},0);
        table = new JTable(model);

        add(panel,"North");
        add(new JScrollPane(table),"Center");

        load();

        btnSave.addActionListener(e -> saveCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());

        setVisible(true);
    }

    private void saveCustomer() {
        Customer c = new Customer(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                (Gender)cmbGender.getSelectedItem()
        );
        customerList.add(c);
        save();
        refresh();
    }

    private void deleteCustomer() {
        int row = table.getSelectedRow();
        if(row >= 0){
            customerList.remove(row);
            save();
            refresh();
        } else {
            JOptionPane.showMessageDialog(this,"Select a row first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        for(Customer c : customerList){
            model.addRow(new Object[]{
                    c.getCustomerId(),
                    c.getName(),
                    c.getGender()
            });
        }
    }

    private void save() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("customers.dat"));
            oos.writeObject(customerList);
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("customers.dat"));
            customerList = (ArrayList<Customer>) ois.readObject();
            ois.close();
            refresh();
        } catch(Exception e){
            customerList = new ArrayList<>();
        }
    }
}