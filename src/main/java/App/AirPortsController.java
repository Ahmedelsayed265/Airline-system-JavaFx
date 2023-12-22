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
    boolean validationFailed = false;

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
    public void goAirCrafts() throws Exception {
        mainApp.showAirCraftsScene();
    }

    @FXML
    public void addAirport() {
        String name = airPortName.getText();
        String location = airPortLocation.getText();

        if (name == null || name.trim().isEmpty()) {
            nameRequiredLabel.setText("Airport name is required*");
            validationFailed = true;
        } else {
            nameRequiredLabel.setText("");
        }
        if (location == null || location.trim().isEmpty()) {
            locationRequiredLabel.setText("Airport location is required*");
            validationFailed = true;
        } else {
            locationRequiredLabel.setText("");
        }

        if (!validationFailed) {
            AirPort airPort = new AirPort();
            airPort.AddAirport(name , location);
            infoBox("Airport Added Successfully", "Success");
        }
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
