package App;

import backend.AirLine.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private Label nameRequired;
    @FXML
    private Label emailRequired;
    @FXML
    private Label passwordRequired;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField passwordInput;

    boolean validationFailed = false;
    private Main mainApp;

    public void setMain(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleRegister() throws Exception {
        String name = nameInput.getText();
        String email = emailInput.getText();
        String password = passwordInput.getText();

        if (name == null || name.trim().isEmpty()) {
            nameRequired.setText("Name is required*");
            validationFailed = true;
        } else {
            nameRequired.setText("");
        }

        if (email == null || email.trim().isEmpty()) {
            emailRequired.setText("Email address is required*");
            validationFailed = true;
        } else if (!email.contains("@")) {
            emailRequired.setText("Invalid Email");
            validationFailed = true;
        } else {
            emailRequired.setText("");
        }

        if (password == null || password.trim().isEmpty()) {
            passwordRequired.setText("Password is required*");
            validationFailed = true;
        } else {
            passwordRequired.setText("");
        }

        if (!validationFailed) {
            Admin ad = new Admin();
            if (!ad.isEmailExists(email)) {
                ad.register(name, email, password);
                mainApp.showLoginScene();
            } else {
                errorBox("Email address is already exist..!", "Validation Error");

            }
        }
    }

    @FXML
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
    }

    public static void errorBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
