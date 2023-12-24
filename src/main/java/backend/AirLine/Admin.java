package backend.AirLine;

import java.sql.*;

public class Admin extends Model {
    private static int counter;
    private String name;
    private String emailAddress;
    private static boolean login;
    private String password;


    public Admin(String name, String emailAddress, String password) {
        super(++counter);
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Admin() {
        super(++counter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* -------- Start Login Methods -------- */
    public boolean isLoggedIn() {
        return login;
    }

    public String[] whoLoggedIn(String email) {
        if (isLoggedIn()) {
            try (Connection connection = DatabaseConnector.getConnection()) {
                String query = "SELECT name FROM Admins WHERE email = ?";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, email);
                    try (ResultSet res = ps.executeQuery()) {
                        if (res.next()) {
                            String adminName = res.getString("name");
                            return new String[]{email, adminName};
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void login(String email, String password) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM Admins WHERE email = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, email);
                ps.setString(2, password);
                try (ResultSet res = ps.executeQuery()) {
                    login = res.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* -------- end Login Methods -------- */

    /* -------- Start Register Methods -------- */
    public boolean isEmailExists(String email) throws Exception {
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT COUNT(*) FROM Admins WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public void register(String name, String email, String password) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Admins (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get admin ID by using admin name
    public static int adminId(String adminName) throws Exception {
        String query = "SELECT id From admins WHERE name = \"" + adminName + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }
}
