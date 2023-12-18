package backend.AirLine;

public class Ticket extends Model {
    private static int counter;
    private Reservation reservation;
    private Customer customer;
    private double price;
    private boolean paymentStatus;

    public Ticket(Reservation reservation, Customer customer, double price, boolean paymentStatus) {
        super(++counter);
        this.reservation = reservation;
        this.customer = customer;
        this.price = price;
        this.paymentStatus = paymentStatus;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    //add   issueTicket - cancelTicket - viewTicket
}
