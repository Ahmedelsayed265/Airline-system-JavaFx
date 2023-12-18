package backend.AirLine;

import java.sql.*;

public class Admin extends People {
    private static int counter;
    private String emailAddress;
    private static boolean login;
    private String password;

    public Admin(String name, String emailAddress, String password) {
        super(++counter, name);
        this.emailAddress = emailAddress;
        this.password = password;
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
    public static boolean isLoggedIn() {
        return login;
    }

    public static void login(String email, String password) {
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

    private static boolean isEmailExists(Connection connection, String email) throws SQLException {
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

    private static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static void register(String name, String email, String password) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email address");
            return;
        }
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (isEmailExists(connection, email)) {
                System.out.println("Email address is already registered");
                return;
            }
            // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String query = "INSERT INTO Admins (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);

                ps.executeUpdate();
                System.out.println("Hello " + name + ", your account registered successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* -------- end Register Methods -------- */
}
