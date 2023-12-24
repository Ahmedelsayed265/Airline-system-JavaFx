package App;

import backend.AirLine.AirCraft;
import backend.AirLine.AirPort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AirCraftsController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, typeRequired, capacityRequired;
    @FXML
    private TextField type, capacity;
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
    public void goAirports() throws Exception {
        mainApp.showAirportsScene();
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
    public void goTickets() throws Exception {
        mainApp.showTicketsScene();
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }

    public void addAirCraft() {
        String craftType = type.getText();
        String capacityText = capacity.getText();

        if (craftType == null || craftType.trim().isEmpty()) {
            typeRequired.setText("Air craft name is required*");
            valid1 = false;
        } else {
            typeRequired.setText("");
            valid1 = true;
        }
        if (capacityText == null || capacityText.trim().isEmpty()) {
            capacityRequired.setText("Air craft capacity is required*");
            valid2 = false;
        } else {
            try {
                int craftCapacity = Integer.parseInt(capacityText);
                if (craftCapacity <= 0) {
                    capacityRequired.setText("Invalid capacity*");
                    valid2 = false;
                } else {
                    capacityRequired.setText("");
                    valid2 = true;
                    if (valid1) {
                        AirCraft craft = new AirCraft(craftType, craftCapacity);
                        infoBox("Aircraft Added Successfully", "Success");
                        type.setText(null);
                        capacity.setText(null);
                    }
                }
            } catch (NumberFormatException e) {
                capacityRequired.setText("Invalid capacity*");
                valid2 = false;
            }
        }
    }
}
