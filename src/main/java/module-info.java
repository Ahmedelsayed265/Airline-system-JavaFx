module com.example.airlinesystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.airlinesystem to javafx.fxml;
    exports com.example.airlinesystem;
}