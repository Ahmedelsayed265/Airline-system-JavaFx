package App;

import backend.AirLine.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class FlightsController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, seats;
    @FXML
    private ComboBox<String> depAirports, arrAirports, airCraftsNames;
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
    public void initialize() throws Exception {
        airPortsNames();
        airCraftsNames();
    }

    @FXML
    public void airPortsNames() throws Exception {
        ArrayList<String> airports = Flight.fetchAirportsNames();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(airports);
        depAirports.setItems(comboBoxItems);
        arrAirports.setItems(comboBoxItems);
    }

    @FXML
    public void airCraftsNames() throws Exception {
        ArrayList<String> airCrafts = Flight.fetchAirCraftsNames();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(airCrafts);
        airCraftsNames.setItems(comboBoxItems);
    }

    @FXML
    public void getCapacity() throws Exception {
        int cap = Flight.getAircraftCapacity(airCraftsNames.getValue());
        seats.setText(String.valueOf(cap));
    }

}
