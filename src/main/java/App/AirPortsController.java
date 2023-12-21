package App;

import javafx.fxml.FXML;

public class AirPortsController {
    private Main mainApp;

    public void setMain(Main main) {
        this.mainApp = main;
    }

    @FXML
    public void goLogin() throws Exception {
        mainApp.showLoginScene();
    }
}
