package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        showLoginScene();
    }

    private FXMLLoader sceneShow(String nameView, String stageTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(nameView));
        Scene scene = new Scene(loader.load(), 750, 560);
        primaryStage.setTitle(stageTitle);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png"))));
        primaryStage.setScene(scene);
        return loader;
    }

    //-----------------*Auth*---------------------//
    public void showLoginScene() throws Exception {
        FXMLLoader loader = sceneShow("login-view.fxml", "Login to your account");
        LoginController controller = loader.getController();
        controller.setMain(this);
        primaryStage.show();

    }

    public void showRegisterScene() throws Exception {
        FXMLLoader loader = sceneShow("Register-view.fxml", "Register");
        RegisterController controller = loader.getController();
        controller.setMain(this);
        primaryStage.show();
    }

    //-------------------**------------------------//
    public HomeController homeController;
    public String adName, adEmail;

    public void showHomeScene(String adminEmail, String adminName) throws Exception {
        adName = adminName;
        adEmail = adminEmail;
        FXMLLoader loader = sceneShow("Home-view.fxml", "Home");
        homeController = loader.getController();
        homeController.setMain(this);
        homeController.setAdminInfo(adminEmail, adminName);
        primaryStage.show();
    }

    public void showAirportsScene() throws Exception {
        FXMLLoader loader = sceneShow("AirPorts-view.fxml", "Airports");
        AirPortsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public void showAirCraftsScene() throws Exception {
        FXMLLoader loader = sceneShow("AirCrafts-view.fxml", "Air crafts");
        AirCraftsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public void showFlightsScene() throws Exception {
        FXMLLoader loader = sceneShow("Flights-view.fxml", "Flights");
        FlightsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public void showCrewsScene() throws Exception {
        FXMLLoader loader = sceneShow("Crews-view.fxml", "Crews");
        CrewsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public void showReservationsScene() throws Exception {
        FXMLLoader loader = sceneShow("Reservations-view.fxml", "Reservations");
        ReservationsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public void showTicketsScene() throws Exception {
        FXMLLoader loader = sceneShow("Tickets-view.fxml", "Tickets");
        TicketsController controller = loader.getController();
        controller.setMain(this);
        controller.setAdminInfo(adEmail, adName);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
