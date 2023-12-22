package App;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class AirPortsController {
    @FXML
    private Label adminNameLabel;
    @FXML
    private Label adminEmailLabel;
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
}
