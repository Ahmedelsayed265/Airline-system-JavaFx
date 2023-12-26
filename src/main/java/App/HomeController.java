package App;

import backend.AirLine.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, AirportsCount,
            AirCraftsCount, crewsCount, reservationsCount, flightsCount, passengersCount;
    private Main mainApp;

    public void setMain(Main main) {
        this.mainApp = main;
    }
    // ----- set Admin Information in side bar -----//
    @FXML
    public void setAdminInfo(String adminEmail, String adminName) {
        adminNameLabel.setText(adminName);
        adminEmailLabel.setText(adminEmail);
    }
    // ----- pages Navigations ----- //
    @FXML
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
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
    public void goReservations() throws Exception {
        mainApp.showReservationsScene();
    }

    @FXML
    public void goTickets() throws Exception {
        mainApp.showTicketsScene();
    }

    @FXML
    public void initialize() throws Exception {
        AirportsCount.setText(DatabaseConnector.tablesCounter("airports") + "");
        AirCraftsCount.setText(DatabaseConnector.tablesCounter("aircrafts") + "");
        crewsCount.setText(DatabaseConnector.tablesCounter("crews") + "");
        reservationsCount.setText(DatabaseConnector.tablesCounter("reservations") + "");
        flightsCount.setText(DatabaseConnector.tablesCounter("flights") + "");
        passengersCount.setText(DatabaseConnector.tablesCounter("customers") + "");
    }
}
