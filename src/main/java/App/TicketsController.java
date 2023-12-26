package App;

import backend.AirLine.DatabaseConnector;
import backend.AirLine.Ticket;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TicketsController {
    // ----- Table View ----- //
    @FXML
    private Label adminNameLabel, adminEmailLabel;
    @FXML
    private TableView<Ticket> ticketsTable;
    @FXML
    private TableColumn<Ticket, Integer> idCol;
    @FXML
    private TableColumn<Ticket, Integer> resIdCol;
    @FXML
    private TableColumn<Ticket, String> cName;
    @FXML
    private TableColumn<Ticket, Boolean> status;
    ObservableList<Ticket> listTickets;
    // ----------------------- //
    private Main mainApp;
    public String adName, adEmail;

    public void setMain(Main main) {
        this.mainApp = main;
    }

    // ----- set Admin Information in side bar -----//
    @FXML
    public void setAdminInfo(String adminEmail, String adminName) {
        adName = adminName;
        adEmail = adminEmail;
        adminNameLabel.setText(adminName);
        adminEmailLabel.setText(adminEmail);
    }

    // ----- pages Navigations ----- //
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
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
    }

    @FXML
    public void goReservations() throws Exception {
        mainApp.showReservationsScene();
    }
    // ----------------------- //

    // ---- Initialize table view columns ----//
    @FXML
    public void initialize() throws Exception {
        //table view
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        resIdCol.setCellValueFactory(new PropertyValueFactory<>("reservationId"));
        cName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        status.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        listTickets = DatabaseConnector.fetchTickets();
        ticketsTable.setItems(listTickets);
    }

}
