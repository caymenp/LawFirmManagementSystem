module com.example.appointmentmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.appointmentmanager to javafx.fxml;
    exports com.example.appointmentmanager;
}