package AirLine;

public class Customer extends People {
    private static  int counter;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        super(++counter, name);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
