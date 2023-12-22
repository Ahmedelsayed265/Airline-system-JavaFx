package App;

import backend.AirLine.AirPort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AirPortsController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, locationRequiredLabel, nameRequiredLabel;
    @FXML
    private TextField airPortName, airPortLocation;
    private Main mainApp;
    public String adName, adEmail;
    boolean valid1 = true, valid2 = true;

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
    public void goFlights() throws Exception {
        mainApp.showFlightsScene();
    }

    @FXML
    public void goAirCrafts() throws Exception {
        mainApp.showAirCraftsScene();
    }

    @FXML
    public void goCrews() throws Exception {
        mainApp.showCrewsScene();
    }
    @FXML
    public void goReservations() throws Exception {
        mainApp.showReservationsScene();
    }

    @FXML
    public void addAirport() {
        String name = airPortName.getText();
        String location = airPortLocation.getText();

        if (name == null || name.trim().isEmpty()) {
            nameRequiredLabel.setText("Airport name is required*");
            valid1 = false;
        } else {
            nameRequiredLabel.setText("");
            valid1 = true;
        }
        if (location == null || location.trim().isEmpty()) {
            locationRequiredLabel.setText("Airport location is required*");
            valid2 = false;
        } else {
            locationRequiredLabel.setText("");
            valid2 = true;
        }

        if (valid1 && valid2) {
            AirPort airPort = new AirPort(name, location);
            infoBox("Airport Added Successfully", "Success");
            airPortName.setText(null);
            airPortLocation.setText(null);
        }
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
