module com.example.managementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.controller to javafx.fxml;
    exports com.example.model;
}