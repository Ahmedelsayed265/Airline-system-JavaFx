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

    public static ObservableList<Crew> fetchCrews() throws SQLException {
        ObservableList<Crew> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM crews");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            list.add(new Crew(res.getInt("id"), res.getString("name"), res.getString("captainName")));
        }
        return list;
    }

    public static ObservableList<AirPort> fetchAirports() throws Exception {
        ObservableList<AirPort> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM airports");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            list.add(new AirPort(res.getInt("id"), res.getString("name"), res.getString("location")));
        }
        return list;
    }

    public static ObservableList<AirCraft> fetchAirCrafts() throws Exception {
        ObservableList<AirCraft> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM aircrafts");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            list.add(new AirCraft(res.getInt("id"), res.getString("type"), res.getInt("capacity")));
        }
        return list;
    }

    public static int tablesCounter(String table) throws Exception {
        String query = "SELECT COUNT(*) FROM " + table;
        ResultSet res = fetchData(query);
        if (res.next()) {
            return res.getInt(1);
        }
        return 0;
    }

}
