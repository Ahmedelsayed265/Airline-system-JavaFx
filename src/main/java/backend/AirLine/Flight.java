package backend.AirLine;

import java.sql.*;
import java.util.ArrayList;

public class Flight extends Model {
    private static int counter;
    static {
        try {
            counter = DatabaseConnector.tablesCounter("flights");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String departureAirPort;
    private String arrivalAirPort;
    private Date departureDate;
    private Date arrivalDate;
    private String crewName;


    public Flight(String departureAirPort, String arrivalAirPort, Date departureTime
            , Date arrivalTime, String airCraft, String crew, int availableSeats) throws Exception {
        super(++counter);

        int depAirportId = getAirportId(departureAirPort);
        int arrAirportId = getAirportId(arrivalAirPort);
        int aircraftId = getAircraftId(airCraft);
        int crewID = getCrewID(crew);

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Flights (departureAirport_id ,arrivalAirport_id," +
                    "departureTime,arrivalTime,airCraftId,crew_id,availableSeats) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, depAirportId);
                ps.setInt(2, arrAirportId);
                ps.setDate(3, departureTime);
                ps.setDate(4, arrivalTime);
                ps.setInt(5, aircraftId);
                ps.setInt(6, crewID);
                ps.setInt(7, availableSeats);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Flight(int id, String departureAirPort, String arrivalAirPort, Date departureDate, Date arrivalDate, String crewName) {
        super(id);
        this.departureAirPort = departureAirPort;
        this.arrivalAirPort = arrivalAirPort;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.crewName = crewName;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
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

    //get crew ID by using crew name
    public int getCrewID(String crewName) throws Exception {
        String query = "SELECT id From crews WHERE name = \"" + crewName + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }

    // fetch flight data
    public static ArrayList<String> fetchFlights() throws Exception {
        ArrayList<String> flights = new ArrayList<>();
        String query = "SELECT id, departureAirport_id, arrivalAirport_id FROM Flights";
        ResultSet res = DatabaseConnector.fetchData(query);
        while (res.next()) {
            int flightId = res.getInt("id");
            int departureAirport_id = res.getInt("departureAirport_id");
            int arrivalAirport_id = res.getInt("arrivalAirport_id");
            flights.add(flightId + "- from ( " + fetchAirportLocation(departureAirport_id) + " ) to ( " + fetchAirportLocation(arrivalAirport_id) + " )");
        }
        return flights;
    }

    private static String fetchAirportLocation(int id) throws Exception {
        String query = "SELECT location From Airports WHERE id = \"" + id + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getString("location");
        }
        return "";
    }
}