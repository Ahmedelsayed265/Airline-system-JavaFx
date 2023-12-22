package App;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReservationsController {
    @FXML
    private Label adminNameLabel ,adminEmailLabel;
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
    public void goCrows() throws Exception {
        mainApp.showCrewsScene();
    }



}
