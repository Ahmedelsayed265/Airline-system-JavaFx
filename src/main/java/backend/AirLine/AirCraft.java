package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AirCraft extends Model {
    private static int counter;
    private String airCraftType;
    private int capacity;

    public AirCraft(String airCraftType, int capacity) {
        super(++counter);
        this.airCraftType = airCraftType;
        this.capacity = capacity;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO Aircrafts (type, capacity) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, airCraftType);
                ps.setInt(2, capacity);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAirCraftType() {
        return airCraftType;
    }

    public void setAirCraftType(String airCraftType) {
        this.airCraftType = airCraftType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //add addAirCraft - editAirCraft - deleteAirCraft - displayAirCraft
}
