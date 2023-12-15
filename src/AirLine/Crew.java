package AirLine;

public class Crew extends People {
    private static  int counter;
    private Flight flight;
    private String capitanName;

    public Crew(String name, Flight flight, String capitanName) {
        super(++counter, name);
        this.flight = flight;
        this.capitanName = capitanName;
    }

    public Flight getflight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getCapitanName() {
        return capitanName;
    }

    public void setCapitanName(String capitanName) {
        this.capitanName = capitanName;
    }

    //add update - remove - assign

}

