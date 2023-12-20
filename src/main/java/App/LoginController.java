package App;

import backend.AirLine.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label emailRequiredLabel;
    @FXML
    private Label passwordRequiredLabel;

    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }

    @FXML
    protected void handleLogin() {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        if (email == null || email.trim().isEmpty()) {
            emailRequiredLabel.setText("Email address is required*");
        } else {
            emailRequiredLabel.setText("");
        }
        if (password == null || password.trim().isEmpty()) {
            passwordRequiredLabel.setText("Password is required*");
        } else {
            passwordRequiredLabel.setText("");
        }

        Admin ad = new Admin();
        ad.login(email, password);

        if (ad.isLoggedIn()) {
            mainApp.showHomeScene();
        } else {
            infoBox("Incorrect Email OR Password", "Login Failed");
        }
    }
}
