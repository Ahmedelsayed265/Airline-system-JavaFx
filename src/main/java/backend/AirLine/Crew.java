package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Crew extends People {
    private static  int counter;
    private String capitanName;

    public Crew(String name, String capitanName) {
        super(++counter, name);
        this.capitanName = capitanName;

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO crews (name, captainName) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, capitanName);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCapitanName() {
        return capitanName;
    }

    public void setCapitanName(String capitanName) {
        this.capitanName = capitanName;
    }

    public static ArrayList<String> fetchCrewsNames() throws Exception {
        ArrayList<String> crewsNames = new ArrayList<>();
        String query = "SELECT name FROM crews";
        ResultSet resultSet = DatabaseConnector.fetchData(query);
        while (resultSet.next()) {
            String crewName = resultSet.getString("name");
            crewsNames.add(crewName);
        }
        return crewsNames;
    }

}