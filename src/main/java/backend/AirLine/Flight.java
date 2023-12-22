package backend.AirLine;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Flight extends Model {
    private static int counter;

    private AirPort departureAirPort;

    private AirPort arrivalAirPort;
    private Date departureTime;
    private Date arrivalTime;
    private AirCraft airCraft;
    private int availableSeats;

    public Flight(AirPort departureAirPort, AirPort arrivalAirPort, Date departureTime, Date arrivalTime, AirCraft airCraft, int availableSeats) {
        super(++counter);
        this.departureAirPort = departureAirPort;
        this.arrivalAirPort = arrivalAirPort;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airCraft = airCraft;
        this.availableSeats = availableSeats;
    }

    public AirPort getDepartureAirPort() {
        return departureAirPort;
    }

    public void setDepartureAirPort(AirPort departureAirPort) {
        this.departureAirPort = departureAirPort;
    }

    public AirPort getArrivalAirPort() {
        return arrivalAirPort;
    }

    public void setArrivalAirPort(AirPort arrivalAirPort) {
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

    public AirCraft getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(AirCraft airCraft) {
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

    public static int getAircraftCapacity(String type) throws Exception {
        String query = "SELECT capacity From Aircrafts WHERE type = \"" + type + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("capacity");
        }
        return 0;
    }
}
