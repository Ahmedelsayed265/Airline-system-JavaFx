package backend.AirLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airline_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static ResultSet fetchData(String query) throws Exception {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public static ObservableList<Crew> fetchCrews() {
        ObservableList<Crew> list = FXCollections.observableArrayList();
        Connection con = null;

        try {
            con = getConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM crews");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Crew(res.getInt("id"), res.getString("name"), res.getString("captainName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
