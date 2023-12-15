package AirLine;

import java.sql.Date;

public class Flight extends Model {
    private static int counter;
    private AirPort departureLocation;
    private AirPort arrivalLocation;
    private Date departureTime;
    private Date arrivalTime;
    private AirCraft airCraft;
    private int availableSeats;

    public Flight(AirPort departureLocation, AirPort arrivalLocation, Date departureTime, Date arrivalTime, AirCraft airCraft, int availableSeats) {
        super(++counter);
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airCraft = airCraft;
        this.availableSeats = availableSeats;
    }

    public AirPort getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(AirPort departureLocation) {
        this.departureLocation = departureLocation;
    }

    public AirPort getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(AirPort arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
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

    public void display(){
        // return flight info by id from database
    }

}
