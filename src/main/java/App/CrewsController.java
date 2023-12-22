package App;

import backend.AirLine.AirPort;
import backend.AirLine.Crew;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CrewsController {
    @FXML
    private TextField crewName, captainName;
    @FXML
    private Label crewRequired, captainRequired;
    @FXML
    private Label adminNameLabel;
    @FXML
    private Label adminEmailLabel;
    private Main mainApp;
    public String adName, adEmail;
    boolean valid1,valid2;

    public void setMain(Main main) {
        this.mainApp = main;
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
    public void goAirCrafts() throws Exception {
        mainApp.showAirCraftsScene();
    }
    @FXML
    public void goReservations() throws Exception {
        mainApp.showReservationsScene();
    }

    @FXML
    public void setAdminInfo(String adminEmail, String adminName) {
        adminNameLabel.setText(adminName);
        adminEmailLabel.setText(adminEmail);
    }

    @FXML
    public void addCrew() {
        String crew = crewName.getText();
        String captain = captainName.getText();

        if (crew == null || crew.trim().isEmpty()) {
            crewRequired.setText("Crew name is required*");
            valid1 = false;
        } else {
            crewRequired.setText("");
            valid1 = true;
        }
        if (captain == null || captain.trim().isEmpty()) {
            captainRequired.setText("Captain name is required*");
            valid2 = false;
        } else {
            captainRequired.setText("");
            valid2 = true;
        }

        if (valid1 && valid2) {
            Crew cr =  new Crew(crew , captain);
            infoBox("Crew Added Successfully", "Success");
            crewName.setText(null);
            captainName.setText(null);
        }

    }
    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
