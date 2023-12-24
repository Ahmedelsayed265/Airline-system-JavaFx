package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends People {
    private static  int counter;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        super(++counter, name);
        this.phoneNumber = phoneNumber;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO customers (name, phone) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, phoneNumber);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //get customer ID by using customer name
    public static int getCustomerId(String customerName) throws Exception {
        String query = "SELECT id From customers WHERE name = \"" + customerName + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }
}
