module com.example.airlinesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens login to javafx.fxml;
    exports login;
}