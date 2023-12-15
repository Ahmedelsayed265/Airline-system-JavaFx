package AirLine;

import java.sql.Date;

public class Notification extends Model {
    private static int counter;
    private Customer customer;
    private String message;
    private Date time;

    public Notification(Customer customer, String message, Date time) {
        super(++counter);
        this.customer = customer;
        this.message = message;
        this.time = time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    //sendNotification - viewNotification
}
