package AirLine;

import java.sql.Date;

public class Payment extends Model {
    private static int counter;
    private Ticket ticket;
    private Customer customer;
    private double amount;
    private Date paymentTime;
    private String paymentMethod;

    public Payment(Ticket ticket, Customer customer, double amount, Date paymentTime, String paymentMethod) {
        super(++counter);
        this.ticket = ticket;
        this.customer = customer;
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    //add processPayment - refundPayment -  viewPayment
}
