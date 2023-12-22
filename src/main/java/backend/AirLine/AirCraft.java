package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    //add addAirCraft - editAirCraft - deleteAirCraft - displayAirCraft
}
