package App;

import backend.AirLine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;

public class FlightsController {
    @FXML
    private Label adminNameLabel, adminEmailLabel, seats;
    @FXML
    private Label arrAirportSelected, depAirportSelected, aircraftSelected, depTimeRequired, arrTimeRequired, crewSelected;
    @FXML
    private ComboBox<String> depAirports, arrAirports, airCraftsNames, crewNames;
    @FXML
    private DatePicker depTime, arrTime;
    @FXML
    private TableView<Flight> flightsTable;
    @FXML
    private TableColumn<Flight, Integer> idCol;
    @FXML
    private TableColumn<Flight, String> depPort;
    @FXML
    private TableColumn<Flight, String> arrPort;
    @FXML
    private TableColumn<Flight, Date> depTimeCol;
    @FXML
    private TableColumn<Flight, Date> arrivalTime;
    @FXML
    private TableColumn<Flight, String> crewCol;
    ObservableList<Flight> listF;
    private Main mainApp;
    public String adName, adEmail;
    private int cap;

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
    public void airPortsNames() throws Exception {
        ArrayList<String> airports = AirPort.fetchAirportsNames();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(airports);
        depAirports.setItems(comboBoxItems);
        arrAirports.setItems(comboBoxItems);
    }

    @FXML
    public void airCraftsNames() throws Exception {
        ArrayList<String> airCrafts = AirCraft.fetchAirCraftsNames();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(airCrafts);
        airCraftsNames.setItems(comboBoxItems);
    }

    @FXML
    public void crewsNames() throws Exception {
        ArrayList<String> crews = Crew.fetchCrewsNames();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(crews);
        crewNames.setItems(comboBoxItems);
    }

    @FXML
    public void getCapacity() throws Exception {
        cap = Flight.getAircraftCapacity(airCraftsNames.getValue());
        seats.setText(String.valueOf(cap));
    }

    @FXML
    public void addFlight() throws Exception {
        String depAirport = depAirports.getValue();
        String arrAirport = arrAirports.getValue();
        String aircraftName = airCraftsNames.getValue();
        String crewName = crewNames.getValue();
        Date depDate = (depTime.getValue() != null) ? Date.valueOf(depTime.getValue()) : null;
        Date arrDate = (arrTime.getValue() != null) ? Date.valueOf(arrTime.getValue()) : null;

        // inputs validation
        boolean v1, v2, v3, v4, v5, v6;
        if (depAirport == null) {
            depAirportSelected.setText("Departure airport must be selected*");
            v1 = false;
        } else {
            depAirportSelected.setText("");
            v1 = true;
        }
        if (arrAirport == null) {
            arrAirportSelected.setText("Arrival airport must be selected*");
            v2 = false;
        } else {
            arrAirportSelected.setText("");
            v2 = true;
        }
        if (aircraftName == null) {
            aircraftSelected.setText("Aircraft must be selected*");
            v3 = false;
        } else {
            aircraftSelected.setText("");
            v3 = true;
        }
        if (crewName == null) {
            crewSelected.setText("Crew must be selected*");
            v6 = false;
        } else {
            crewSelected.setText("");
            v6 = true;
        }
        if (depDate == null) {
            depTimeRequired.setText("Departure time is required*");
            v4 = false;
        } else {
            depTimeRequired.setText("");
            v4 = true;
        }
        if (arrDate == null) {
            arrTimeRequired.setText("Arrival time is required*");
            v5 = false;
        } else {
            arrTimeRequired.setText("");
            v5 = true;
        }
        if (v1 && v2 && v3 && v4 && v5 && v6) {
            if (depAirport.equals(arrAirport)) {
                errorBox("You can't choose same value for departure airport and arrival airport ", "Error");
            } else {
                // add new flight in db
                Flight flight = new Flight(depAirport, arrAirport, depDate, arrDate, aircraftName, crewName, cap);
                infoBox("Flight Added Successfully", "Success");

                // update table
                Flight lFlight = new Flight(DatabaseConnector.tablesCounter("flights"), depAirport, arrAirport, depDate, arrDate, crewName);
                listF.add(lFlight);
                flightsTable.setItems(listF);

                //reset form
                depTime.setValue(null);
                arrTime.setValue(null);
            }
        }
    }

    @FXML
    public void initialize() throws Exception {
        airPortsNames();
        crewsNames();
        airCraftsNames();
        //table view
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        depPort.setCellValueFactory(new PropertyValueFactory<>("departureAirPort"));
        arrPort.setCellValueFactory(new PropertyValueFactory<>("arrivalAirPort"));
        depTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        crewCol.setCellValueFactory(new PropertyValueFactory<>("crewName"));
        listF = DatabaseConnector.fetchFlights();
        flightsTable.setItems(listF);
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }

    public static void errorBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}