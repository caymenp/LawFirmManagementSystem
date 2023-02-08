package com.example.controller;

import com.example.model.Appointment;
import com.example.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMeetingController implements Initializable {

    public Label customerName;
    public Label customerPhone;
    public Label customerAddress;
    public Label customerState;
    public Label cxCaseStatus;
    public Accordion previousNotes;
    public TextArea newMeetingNotes;
    public Button scheduleApt;
    public Button changeStatus;
    public Button close;
    public Button save;

    public void scheduleApt(ActionEvent actionEvent) {
    }

    public void changeStatus(ActionEvent actionEvent) {
    }

    public void close(ActionEvent actionEvent) {
    }

    public void save(ActionEvent actionEvent) {
    }

    public void getData(Appointment passedAppointment, int CXID) {
        Appointment passedClientAP = passedAppointment;
        Customer passedClient = Customer.getCustomer(String.valueOf(CXID));

        customerName.setText(passedClient.getName());
        customerPhone.setText(passedClient.getPhone());
        customerAddress.setText(passedClient.getAddress());
        customerState.setText(passedClient.getDivisionName());

        ObservableList<Appointment> prevAppointments = FXCollections.observableArrayList();
        prevAppointments.addAll(Appointment.customerAppointments(CXID));
        if (prevAppointments.isEmpty()) return;

        for (Appointment ap : prevAppointments) {

            if (ap.getAppointmentID() == passedClientAP.getAppointmentID()) continue;

            TitledPane newPane = new TitledPane();
            newPane.setText(ap.getCreateDateTime().toString());
            TextField prevNote = new TextField();
            prevNote.setText(ap.getAppointmentNote());
            prevNote.setEditable(false);
            newPane.setContent(prevNote);
            previousNotes.getPanes().add(newPane);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
