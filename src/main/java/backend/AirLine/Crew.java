package backend.AirLine;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Crew extends People {
    private static  int counter;
    private String capitanName;

    public Crew(String name, String capitanName) {
        super(++counter, name);
        this.capitanName = capitanName;
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