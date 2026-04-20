import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JOptionPane;

import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Date;

public class ReservationForm extends JFrame {

    private JTextField txtResId, txtRoomNo, txtCustomerId;
    private JSpinner dateSpinner;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Reservation> reservationList = new ArrayList<>();

    public ReservationForm() {

        setTitle("Reservation Form");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,5,5));
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Reservation Information");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.BLACK);

        panel.add(title);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Reservation ID:"));
        txtResId = new JTextField();
        panel.add(txtResId);

        panel.add(new JLabel("Room No:"));
        txtRoomNo = new JTextField();
        panel.add(txtRoomNo);

        panel.add(new JLabel("Customer ID:"));
        txtCustomerId = new JTextField();
        panel.add(txtCustomerId);

        panel.add(new JLabel("Reservation Date:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        panel.add(dateSpinner);

        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");

        btnSave.setBackground(Color.GRAY);
        btnSave.setForeground(Color.WHITE);

        btnDelete.setBackground(Color.GRAY);
        btnDelete.setForeground(Color.WHITE);

        panel.add(btnSave);
        panel.add(btnDelete);

        model = new DefaultTableModel(
                new String[]{"Res ID","Room","Customer","Date"},0);
        table = new JTable(model);

        add(panel,"North");
        add(new JScrollPane(table),"Center");

        load();

        btnSave.addActionListener(e -> saveReservation());
        btnDelete.addActionListener(e -> deleteReservation());

        setVisible(true);
    }

    private void saveReservation() {
        Reservation r = new Reservation(
                Integer.parseInt(txtResId.getText()),
                Integer.parseInt(txtRoomNo.getText()),
                Integer.parseInt(txtCustomerId.getText()),
                (Date) dateSpinner.getValue()
        );
        reservationList.add(r);
        save();
        refresh();
    }

    private void deleteReservation() {
        int row = table.getSelectedRow();
        if(row >= 0){
            reservationList.remove(row);
            save();
            refresh();
        } else {
            JOptionPane.showMessageDialog(this,"Select a row first");
        }
    }

    private void refresh() {
        model.setRowCount(0);
        for(Reservation r : reservationList){
            model.addRow(new Object[]{
                    r.getReservationId(),
                    r.getRoomNo(),
                    r.getCustomerId(),
                    r.getReservationDate()
            });
        }
    }

    private void save() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("reservations.dat"));
            oos.writeObject(reservationList);
            oos.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("reservations.dat"));
            reservationList = (ArrayList<Reservation>) ois.readObject();
            ois.close();
            refresh();
        } catch(Exception e){
            reservationList = new ArrayList<>();
        }
    }
}