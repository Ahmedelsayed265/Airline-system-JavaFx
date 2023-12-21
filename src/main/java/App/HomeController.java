package App;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    @FXML
    private Label adminNameLabel;
    @FXML
    private Label adminEmailLabel;

    private Main mainApp;

    public void setMain(Main main) {
        this.mainApp = main;
    }

    public void setAdminInfo(String adminEmail, String adminName) {
        adminNameLabel.setText(adminName);
        adminEmailLabel.setText(adminEmail);
    }

    @FXML
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
    }
}
