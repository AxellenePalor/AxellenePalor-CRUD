package finals;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class mayo {
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_db", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void addMenuItem(String id, String name, double price, String category) {
        try {
            pst = getConnection().prepareStatement("INSERT INTO menu_items (id, name, price, category) VALUES (?, ?, ?, ?)");
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setDouble(3, price);
            pst.setString(4, category);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(String id, String name, double price, String category) {
        try {
            pst = getConnection().prepareStatement("UPDATE menu_items SET name=?, price=?, category=? WHERE id=?");
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setString(3, category);
            pst.setString(4, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem(String id) {
        try {
            pst = getConnection().prepareStatement("DELETE FROM menu_items WHERE id=?");
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMenuItems(JTable table) {
        try {
            pst = getConnection().prepareStatement("SELECT * FROM menu_items");
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("category")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
