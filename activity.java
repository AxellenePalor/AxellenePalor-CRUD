package finals;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class activity extends JFrame {
    private JTextField txtId, txtName, txtPrice, txtCategory;
    private JTable table;
    private mayo db = new mayo();

    public activity() {
        setTitle("Restaurant POS System");
        setLayout(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(30, 20, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(120, 20, 200, 25);
        add(txtId);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(30, 60, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 60, 200, 25);
        add(txtName);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(30, 100, 100, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(120, 100, 200, 25);
        add(txtPrice);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(30, 140, 100, 25);
        add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setBounds(120, 140, 200, 25);
        add(txtCategory);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBounds(30, 180, 90, 25);
        add(btnAdd);

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.setBounds(130, 180, 90, 25);
        add(btnUpdate);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setBounds(230, 180, 90, 25);
        add(btnDelete);

        table = new JTable(new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Category"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 220, 500, 200);
        add(scrollPane);

        db.loadMenuItems(table);

        btnAdd.addActionListener(e -> {
            String id = txtId.getText();
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String category = txtCategory.getText();
            db.addMenuItem(id, name, price, category);
            db.loadMenuItems(table);
        });

        btnUpdate.addActionListener(e -> {
            String id = txtId.getText();
            String name = txtName.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String category = txtCategory.getText();
            db.updateMenuItem(id, name, price, category);
            db.loadMenuItems(table);
        });

        btnDelete.addActionListener(e -> {
            String id = txtId.getText();
            db.deleteMenuItem(id);
            db.loadMenuItems(table);
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtId.setText(table.getValueAt(row, 0).toString());
                txtName.setText(table.getValueAt(row, 1).toString());
                txtPrice.setText(table.getValueAt(row, 2).toString());
                txtCategory.setText(table.getValueAt(row, 3).toString());
            }
        });

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new activity();
    }
}
