package AirLine;

public class AirPort extends Model {
    private static int counter;
    private String name;
    private String location;

    public AirPort(String name, String location) {
        super(++counter);
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
}
