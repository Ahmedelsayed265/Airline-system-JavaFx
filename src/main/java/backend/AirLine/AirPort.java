package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirPort extends Model {
    private static int counter;
    private String name;
    private String location;

    public AirPort(String name, String location) {
        super(++counter);
        this.name = name;
        this.location = location;
    }
    public AirPort() {
        super(++counter);
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

    public void AddAirport(String name, String location) {
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

}
