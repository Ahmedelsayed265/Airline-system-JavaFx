package backend.AirLine;

import java.sql.*;

public class Reservation extends Model {
    private static int counter;

    static {
        try {
            counter = DatabaseConnector.tablesCounter("reservations");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int adminId;
    private String adminName;
    private int flightId;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    private int seatNumber;
    private Date reservationDate;
    private double price;

    public Reservation(int adminId, int flightId, int seatNumber, Date reservationDate, double price) {
        super(++counter);
        this.adminId = adminId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.reservationDate = reservationDate;
        this.price = price;

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO reservations (admin_id, flight_id, seatNumber, reservationTime, price) VALUES (?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, adminId);
                ps.setInt(2, flightId);
                ps.setInt(3, seatNumber);
                ps.setDate(4, reservationDate);
                ps.setDouble(5, price);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation(int id, String adminName, int flightId, int seatNumber, Date reservationDate, double price) {
        super(id);
        this.adminName = adminName;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.reservationDate = reservationDate;
        this.price = price;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //get reservation ID by using admin id
    public static int getReservationId(int adminId) throws Exception {
        String query = "SELECT id From reservations WHERE admin_id = \"" + adminId + "\";";
        ResultSet res = DatabaseConnector.fetchData(query);
        if (res.next()) {
            return res.getInt("id");
        }
        return 0;
    }

}
