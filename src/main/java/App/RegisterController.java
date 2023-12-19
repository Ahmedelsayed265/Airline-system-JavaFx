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
    public void handleRegister() {
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
            ad.register(name, email, password);
            infoBox("Registration successful!", "Success");
            validationFailed = true;
        }

    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.show();
    }
}
