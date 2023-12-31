package App;

import backend.AirLine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ReservationsController {
    // ----- Table View ----- //
    @FXML
    private TableView<Reservation> reservationsTable;
    @FXML
    private TableColumn<Reservation, Integer> idCol;
    @FXML
    private TableColumn<Reservation, String> adNameCol;
    @FXML
    private TableColumn<Reservation, Integer> flIdCol;
    @FXML
    private TableColumn<Reservation, Integer> seatNo;
    @FXML
    private TableColumn<Reservation, Date> dateCol;
    @FXML
    private TableColumn<Reservation, String> priceCol;
    ObservableList<Reservation> listReservations;
    // ----------------------- //

    @FXML
    private Label adminNameLabel, adminEmailLabel, custNameReq, custPhoneReq, flightReq,
            seatNumReq, resDateReq, priceReq;
    @FXML
    private ComboBox<String> flight;
    @FXML
    private TextField customerName, customerPhone, seatNum, price;
    @FXML
    private DatePicker resDate;
    @FXML
    private CheckBox payStatus;
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
    public void goCrews() throws Exception {
        mainApp.showCrewsScene();
    }

    @FXML
    public void goTickets() throws Exception {
        mainApp.showTicketsScene();
    }
    // ----------------------- //

    // ----- Add Reservation Form ----- //
    @FXML
    public void addReservation() throws Exception {
        String cName = customerName.getText();
        String cPhone = customerPhone.getText();
        String fl = flight.getValue();
        String seatText = seatNum.getText();
        String priceTest = price.getText();
        Date reservationDate = (resDate.getValue() != null) ? Date.valueOf(resDate.getValue()) : null;
        boolean paid = payStatus.isSelected();

        int flightId = 0, seatNO = 0;
        double priceAmount = 0.0;
        boolean v1, v2, v3, v4, v5, v6;

        if (cName == null || cName.trim().isEmpty()) {
            custNameReq.setText("Customer name is required*");
            v1 = false;
        } else {
            custNameReq.setText("");
            v1 = true;
        }
        if (cPhone == null || cPhone.trim().isEmpty()) {
            custPhoneReq.setText("Customer phone is required*");
            v2 = false;
        } else {
            custPhoneReq.setText("");
            v2 = true;
        }
        if (reservationDate == null) {
            resDateReq.setText("Reservation time is required*");
            v3 = false;
        } else {
            resDateReq.setText("");
            v3 = true;
        }
        if (fl == null) {
            flightReq.setText("Flight must be selected*");
            v4 = false;
        } else {
            flightReq.setText("");
            v4 = true;
            String[] parts = fl.split("-");
            String numStr = parts[0].trim();
            flightId = parseInt(numStr);
        }
        if (priceTest == null || priceTest.trim().isEmpty()) {
            priceReq.setText("Price is required*");
            v5 = false;
        } else {
            priceAmount = Double.parseDouble(priceTest);
            if (priceAmount <= 0) {
                priceReq.setText("Invalid price*");
                v5 = false;
            } else {
                priceReq.setText("");
                v5 = true;
            }
        }
        if (seatText == null || seatText.trim().isEmpty()) {
            seatNumReq.setText("Seat number is required*");
            v6 = false;
        } else {
            seatNO = parseInt(seatText);
            if (seatNO <= 0) {
                seatNumReq.setText("Invalid seat number*");
                v6 = false;
            } else {
                seatNumReq.setText("");
                v6 = true;
            }
        }

        if (v1 && v2 && v3 && v4 & v5 & v6) {
            // add customer on adding reservation
            Customer customer = new Customer(cName, cPhone);

            // add reservation
            int adId = Admin.adminId(adName);
            Reservation reservation = new Reservation(adId, flightId, seatNO, reservationDate, priceAmount);

            // add ticket on adding reservation
            int res_id = Reservation.getReservationId(adId);
            int cus_id = Customer.getCustomerId(cName);
            Ticket ticket = new Ticket(res_id, cus_id, paid);

            // updating table date by different constructor to retrieve other data
            Reservation reserve = new Reservation(DatabaseConnector.tablesCounter("reservations"), adName, flightId, seatNO, reservationDate, priceAmount);
            listReservations.add(reserve);
            reservationsTable.setItems(listReservations);

            infoBox("Reservation Added Successfully", "Success");

            // reset the form
            customerName.setText(null);
            customerPhone.setText(null);
            seatNum.setText(null);
            price.setText(null);
            resDate.setValue(null);
            payStatus.setText(null);
        }

    }

    // ---- Initialize table view columns and list available flights ----//
    @FXML
    public void initialize() throws Exception {
        flights();
        //table view
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        adNameCol.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        flIdCol.setCellValueFactory(new PropertyValueFactory<>("flightId"));
        seatNo.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        listReservations = DatabaseConnector.fetchReservations();
        reservationsTable.setItems(listReservations);
    }

    // retrieve flights in comboBox
    @FXML
    public void flights() throws Exception {
        ArrayList<String> flightsDetails = Flight.fetchFlights();
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList(flightsDetails);
        flight.setItems(comboBoxItems);
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }

}
