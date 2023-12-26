package App;

import backend.AirLine.Crew;
import backend.AirLine.DatabaseConnector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CrewsController {
    // ----- Table View ----- //
    @FXML
    private TableView<Crew> crewTable;
    @FXML
    private TableColumn<Crew, Integer> idCol;
    @FXML
    private TableColumn<Crew, String> nameCol;
    @FXML
    private TableColumn<Crew, String> cNameCol;
    ObservableList<Crew> listC;
    // ----------------------- //
    @FXML
    private TextField crewName, captainName;
    @FXML
    private Label crewRequired, captainRequired, adminNameLabel, adminEmailLabel;
    private Main mainApp;
    public String adName, adEmail;
    boolean valid1, valid2;

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
    public void goTickets() throws Exception {
        mainApp.showTicketsScene();
    }

    // ----------------------- //

    // ----- Add Crew Form ----- //
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
            Crew cr = new Crew(crew, captain);
            infoBox("Crew Added Successfully", "Success");
            // update table
            listC.add(cr);
            crewTable.setItems(listC);
            // reset form
            crewName.setText(null);
            captainName.setText(null);
        }

    }

    // ---- Initialize table view columns ----//
    public void initialize() throws SQLException {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cNameCol.setCellValueFactory(new PropertyValueFactory<>("capitanName"));
        listC = DatabaseConnector.fetchCrews();
        crewTable.setItems(listC);
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
