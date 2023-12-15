package AirLine;

public class Route extends Model {
    private static int counter;
    private AirPort departureAirPort;
    private AirPort arrivalAirPort;
    private double distance;

    public Route(AirPort departureAirPort, AirPort arrivalAirPort, double distance) {
        super(++counter);
        this.departureAirPort = departureAirPort;
        this.arrivalAirPort = arrivalAirPort;
        this.distance = distance;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    //add CRUD
}
