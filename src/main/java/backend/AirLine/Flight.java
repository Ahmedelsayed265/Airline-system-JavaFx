package backend.AirLine;
import java.sql.*;
import java.util.ArrayList;

public class Flight extends Model {
    private static int counter;

    private String departureAirPort;

    private String arrivalAirPort;
    private Date departureTime;
    private Date arrivalTime;
    private String airCraft;
    private int availableSeats;

    public Flight(String departureAirPort, String arrivalAirPort, Date departureTime, Date arrivalTime, String airCraft, int availableSeats) throws Exception {
        super(++counter);
        this.departureAirPort = departureAirPort;
        this.arrivalAirPort = arrivalAirPort;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airCraft = airCraft;
        this.availableSeats = availableSeats;

        int depAirportId = getAirportId(departureAirPort);
        int arrAirportId = getAirportId(arrivalAirPort);
        int aircraftId = getAircraftId(airCraft);
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Flights (departureAirport_id ,arrivalAirport_id," +
                    "departureTime,arrivalTime,airCraftId,availableSeats) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, depAirportId);
                ps.setInt(2, arrAirportId);
                ps.setDate(3, departureTime);
                ps.setDate(4, arrivalTime);
                ps.setInt(5, aircraftId);
                ps.setInt(6, availableSeats);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDepartureAirPort() {
        return departureAirPort;
    }

    public void setDepartureAirPort(String departureAirPort) {
        this.departureAirPort = departureAirPort;
    }

    public String getArrivalAirPort() {
        return arrivalAirPort;
    }

    public void setArrivalAirPort(String arrivalAirPort) {
        this.arrivalAirPort = arrivalAirPort;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(String airCraft) {
        this.airCraft = airCraft;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public static ArrayList<String> fetchAirportsNames() throws Exception {
        ArrayList<String> airportNames = new ArrayList<>();
        String query = "SELECT name FROM Airports";
        ResultSet resultSet = DatabaseConnector.fetchData(query);
        while (resultSet.next()) {
            String airportName = resultSet.getString("name");
            airportNames.add(airportName);
        }
        return airportNames;
    }

    public static ArrayList<String> fetchAirCraftsNames() throws Exception {
        ArrayList<String> airCraftsNames = new ArrayList<>();
        String query = "SELECT type FROM Aircrafts";
        ResultSet resultSet = DatabaseConnector.fetchData(query);
        while (resultSet.next()) {
            String airCraftName = resultSet.getString("type");
            airCraftsNames.add(airCraftName);
        }
        return airCraftsNames;
    }

    //get aircraft capacity by using aircraft type
    public static int getAircraftCapacity(String type) throws Exception {
        String query = "SELECT capacity From Aircrafts WHERE type = \"" + type + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("capacity");
        }
        return 0;
    }

    //get airport ID by using airport name
    public int getAirportId(String airportName) throws Exception {
        String query = "SELECT id From airports WHERE name = \"" + airportName + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }

    //get aircraft ID by using aircraft type
    public int getAircraftId(String airCraftType) throws Exception {
        String query = "SELECT id From aircrafts WHERE type = \"" + airCraftType + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }


}
