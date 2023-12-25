package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirPort extends Model {
    private static int counter;

    static {
        try {
            counter = DatabaseConnector.tablesCounter("airports");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String name;
    private String location;

    public AirPort(String name, String location) {
        super(++counter);
        this.name = name;
        this.location = location;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Airports (name, location) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, location);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AirPort(int id, String name, String location) {
        super(id);
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    //get airport name by using airport ID
    public static String getAirportName(int id) throws Exception {
        String query = "SELECT name FROM Airports WHERE id = " + id;
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getString("name");
        }
        return "";
    }
}
