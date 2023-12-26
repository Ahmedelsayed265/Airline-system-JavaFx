package backend.AirLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ticket extends Model {
    private static int counter;
    private int reservationId;
    private int customerId;
    private String customerName;
    private boolean paymentStatus;
    // main constructors
    public Ticket(int reservationId, int customerId, boolean paymentStatus) {
        super(++counter);
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.paymentStatus = paymentStatus;
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO tickets (reservation_id ,customer_id, paymentStatus) VALUES (?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, reservationId);
                ps.setInt(2, customerId);
                ps.setBoolean(3, paymentStatus);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // custom constructor
    public Ticket(int id, int reservationId ,String customerName, boolean paymentStatus){
        super(id);
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.paymentStatus = paymentStatus;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
