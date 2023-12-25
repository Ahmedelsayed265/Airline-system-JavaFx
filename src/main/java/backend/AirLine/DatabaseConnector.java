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

    public static ObservableList<Flight> fetchFlights() throws Exception {
        ObservableList<Flight> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM flights");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            int id = res.getInt("id");
            int depAirportId = res.getInt("departureAirport_id");
            int arrAirportId = res.getInt("arrivalAirport_id");
            Date depDate = res.getDate("departureTime");
            Date arrDate = res.getDate("arrivalTime");
            int crewId = res.getInt("crew_id");

            String depAirport = AirPort.getAirportName(depAirportId);
            String arrAirport = AirPort.getAirportName(arrAirportId);
            String crewName = Crew.getCrewName(crewId);

            list.add(new Flight(id, depAirport, arrAirport, depDate, arrDate, crewName));
        }
        return list;
    }

    public static ObservableList<Reservation> fetchReservations() throws Exception {
        ObservableList<Reservation> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM reservations");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            int id = res.getInt("id");
            int adminId = res.getInt("admin_id");
            int flightId = res.getInt("flight_id");
            int seatNo = res.getInt("seatNumber");
            Date reserveDate = res.getDate("reservationTime");
            double price = res.getDouble("price");

            String adminName = Admin.getAdminName(adminId);

            list.add(new Reservation(id, adminName, flightId, seatNo, reserveDate, price));
        }
        return list;
    }


    public static ObservableList<Ticket> fetchTickets() throws Exception {
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement("SELECT * FROM tickets");
        ResultSet res = st.executeQuery();
        while (res.next()) {
            int id = res.getInt("id");
            int reservationId = res.getInt("reservation_id");
            int customerId = res.getInt("customer_id");
            boolean paymentStatus = res.getBoolean("paymentStatus");
            String customerName = Customer.getCustomerName(customerId);
            list.add(new Ticket(id, reservationId, customerName, paymentStatus));
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
