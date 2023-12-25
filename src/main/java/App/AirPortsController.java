package App;

import backend.AirLine.AirPort;
import backend.AirLine.DatabaseConnector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AirPortsController {
    @FXML
    private TableView<AirPort> airportsTable;
    @FXML
    private TableColumn<AirPort, Integer> idCol;
    @FXML
    private TableColumn<AirPort, String> nameCol;
    @FXML
    private TableColumn<AirPort, String> locationCol;
    @FXML
    private Label adminNameLabel, adminEmailLabel, locationRequiredLabel, nameRequiredLabel;
    @FXML
    private TextField airPortName, airPortLocation;
    private Main mainApp;
    public String adName, adEmail;
    boolean valid1 = true, valid2 = true;
    ObservableList<AirPort> listA;

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
    public void goTickets() throws Exception {
        mainApp.showTicketsScene();
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
            listA.add(airPort);
            airportsTable.setItems(listA);
            airPortName.setText(null);
            airPortLocation.setText(null);
        }
    }

    public void initialize() throws Exception {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        listA = DatabaseConnector.fetchAirports();
        airportsTable.setItems(listA);
    }
    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
