module com.example.airlinesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens App to javafx.fxml;
    exports App;
}