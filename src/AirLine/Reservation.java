package AirLine;

import java.sql.Date;

public class Reservation extends Model {
    private static int counter;
    private Admin admin;
    private Flight flight;
    private int seatNumber;
    private Date reservationTime;

    public Reservation(Admin admin, Flight flight, int seatNumber, Date reservationTime) {
        super(++counter);
        this.admin = admin;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.reservationTime = reservationTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Date reservationTime) {
        this.reservationTime = reservationTime;
    }

    // makeReservation - cancelReservation - viewReservation
}
