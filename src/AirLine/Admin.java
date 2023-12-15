package AirLine;

import java.util.Objects;

public class Admin extends People {
    private static int counter;
    private String emailAddress;

    private boolean login;

    private String password;

    public Admin(String name, String emailAddress, String password) {
        super(++counter, name);
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(String email, String password) {
        login = Objects.equals(this.emailAddress, email) && Objects.equals(this.password, password);
    }

    public void register(String name, String email, String password) {

    }
//add   bookFlight - updateFlight - displayFlight - deleteFlight
}
