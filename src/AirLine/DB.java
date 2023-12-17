package AirLine;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection connector() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_system", "root", "");
            JOptionPane.showMessageDialog(null, "Connection succeeded");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void main(String[] args) {
        connector();
    }
}
