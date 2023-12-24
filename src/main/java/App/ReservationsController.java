package App;

import backend.AirLine.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.util.ArrayList;

public class ReservationsController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, custNameReq, custPhoneReq, flightReq, seatNumReq, resDateReq, priceReq;
    @FXML
    private ComboBox<String> flight;
    @FXML
    private TextField customerName, customerPhone, seatNum, price;
    @FXML
    private DatePicker resDate;
    @FXML
    private CheckBox payStatus;
    private Main mainApp;
    public String adName, adEmail;

    public void setMain(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void setAdminInfo(String adminEmail, String adminName) {
        adName = adminName;
        adEmail = adminEmail;
        adminNameLabel.setText(adminName);
        adminEmailLabel.setText(adminEmail);
    }

    @FXML
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
    }

    @FXML
    public void goHome() throws Exception {
        mainApp.showHomeScene(adEmail, adName);
    }

    @FXML
    public void goAirports() throws Exception {
        mainApp.showAirportsScene();
    }

    @FXML
    public void goAirCrafts() throws Exception {
        mainApp.showAirCraftsScene();
    }

    @FXML
    public void goFlights() throws Exception {
        mainApp.showFlightsScene();
    }

    @FXML
    public void goCrews() throws Exception {
        mainApp.showCrewsScene();
    }

    @FXML
    public void initialize() throws Exception {
        flights();
    }

    @FXML
    public void flights() throws Exception {
        ArrayList<String> flightsDetails = Flight.fetchFlights();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(flightsDetails);
        flight.setItems(comboBoxItems);
    }

    @FXML
    public void addReservation() throws Exception {
        String cName = customerName.getText();
        String cPhone = customerPhone.getText();
        int seatNO = Integer.parseInt(seatNum.getText());
        int priceAmount = Integer.parseInt(price.getText());
        Date reservationDate = (resDate.getValue() != null) ? Date.valueOf(resDate.getValue()) : null;
        boolean paid = payStatus.isSelected();

        boolean v1, v2, v3, v4, v5, v6;
        if (cName == null || cName.trim().isEmpty()) {
            custNameReq.setText("Customer name is required*");
            v1 = false;
        } else {
            custNameReq.setText("");
            v1 = true;
        }
        if (cPhone == null || cPhone.trim().isEmpty()) {
            custPhoneReq.setText("Customer phone is required*");
            v2 = false;
        } else {
            custNameReq.setText("");
            v2 = true;
        }
        if (reservationDate == null) {
            resDateReq.setText("Reservation time is required*");
            v3 = false;
        } else {
            resDateReq.setText("");
            v3 = true;
        }
        if (flight == null) {
            flightReq.setText("Flight must be selected*");
            v4 = false;
        } else {
            flightReq.setText("");
            v4 = true;
        }
    }
}
