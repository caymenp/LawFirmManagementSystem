package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Appointments Overview Controller.
 */
public class AppointmentsOverviewController implements Initializable {
    @FXML
    private Text currentUser;
    public Button logout;
    public Text localTimeZone;
    public Button reports;
    public Button addAppointment;
    public Button modifyAppointment;
    public Button deleteAppointment;
    public Button addCustomer;
    public Button modifyCustomer;
    public Button deleteCustomer;
    public TableView appointmentTable;
    public RadioButton viewWeekRadio;
    public ToggleGroup mainCalendar;
    public RadioButton viewMonthRadio;
    public RadioButton viewAllRadio;
    public DatePicker dateSelector;
    public Button viewCX;
    public TableView customerTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void viewCX(ActionEvent actionEvent) {
    }

    public void dateSelector(ActionEvent actionEvent) {
    }

    public void viewAllRadio(ActionEvent actionEvent) {
    }

    public void viewMonthRadio(ActionEvent actionEvent) {
    }

    public void viewWeekRadio(ActionEvent actionEvent) {
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void modifyCustomer(ActionEvent actionEvent) {
    }

    public void addCustomer(ActionEvent actionEvent) {
    }

    public void deleteAppointment(ActionEvent actionEvent) {
    }

    public void modifyAppointment(ActionEvent actionEvent) {
    }

    public void addAppointment(ActionEvent actionEvent) {
    }

    public void reports(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }
}
