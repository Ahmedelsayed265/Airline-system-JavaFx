package App;

import backend.AirLine.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label emailRequiredLabel, passwordRequiredLabel;
    private Main mainApp;
    public String adminEmail, adminName;

    boolean validationFailed = false;

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
    protected void handleLogin() throws Exception {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        if (email == null || email.trim().isEmpty()) {
            emailRequiredLabel.setText("Email address is required*");
            validationFailed = true;
        } else {
            emailRequiredLabel.setText("");
        }
        if (password == null || password.trim().isEmpty()) {
            passwordRequiredLabel.setText("Password is required*");
            validationFailed = true;
        } else {
            passwordRequiredLabel.setText("");
        }

        Admin ad = new Admin();
        ad.login(email, password);

        if (ad.isLoggedIn()) {

            String[] userInfo = ad.whoLoggedIn(email);
            adminEmail = userInfo[0];
            adminName = userInfo[1];
            mainApp.showHomeScene(adminEmail, adminName);

        } else if (!validationFailed) {
            infoBox("Incorrect Email OR Password", "Login Failed");
        }
    }

    @FXML
    protected void goRegister() throws Exception {
        mainApp.showRegisterScene();
    }
}
