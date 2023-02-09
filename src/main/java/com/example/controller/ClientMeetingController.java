package com.example.controller;

import com.example.model.Appointment;
import com.example.model.Customer;
import com.example.model.User;
import com.example.utilities.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ClientMeetingController implements Initializable {

    public Label customerName;
    public Label customerPhone;
    public Label customerAddress;
    public Label lastMeeting;
    public Label cxCaseStatus;
    public Accordion previousNotes;
    public TextArea newMeetingNotes;
    public Button scheduleApt;
    public Button changeStatus;
    public Button close;
    public Button save;
    public ComboBox caseStatus;

    public void scheduleApt(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/appointmentmanager/AppointmentForm-view.fxml"));

        loader.load();


        AppointmentFormController afc = loader.getController();
        afc.getData(passedClient, currentUser);
        afc.appointmentFormLabel.setText("Add Appointment");


        //New Window(Stage)
        Stage appointmentFormWindow = new Stage();
        Parent formScene = loader.getRoot();
        appointmentFormWindow.setTitle("Add Appointment");
        appointmentFormWindow.setScene(new Scene(formScene));

        //Modality For New Window
        appointmentFormWindow.initModality(Modality.WINDOW_MODAL);

        //Owner stage
        Stage mainStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        appointmentFormWindow.initOwner(mainStage);

        //Set Position Of New Window
        appointmentFormWindow.setX(mainStage.getX()+20);
        appointmentFormWindow.setY(mainStage.getY()+20);

        appointmentFormWindow.showAndWait();

    }

    public void changeStatus(ActionEvent actionEvent) {
            ObservableList<String> caseStatuses = FXCollections.observableArrayList();
            caseStatuses.add("Initial Meeting");
            caseStatuses.add("Discovery");
            caseStatuses.add("Deposition");
            caseStatuses.add("Court Date Set");
            caseStatuses.add("Won");
            caseStatuses.add("Lost");
            caseStatuses.add("Appeal");
            caseStatus.setItems(caseStatuses);


        caseStatus.setValue(passedClientAP.getType());

        caseStatus.setVisible(true);
        cxCaseStatus.setVisible(false);
    }

    String newCaseStatus;
    public void caseStatus(ActionEvent actionEvent) {
        newCaseStatus = caseStatus.getValue().toString();
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void save(ActionEvent actionEvent) {
        int appointmentID = passedClientAP.getAppointmentID();
        String type = cxCaseStatus.getText();
        Timestamp updatedDate = Timestamp.valueOf(LocalDateTime.now());
        String updatedBy = currentUser.getUserName();
        String appointmentNote = newMeetingNotes.getText();

        if (caseStatus.isVisible()) type = caseStatus.getValue().toString();

        if (appointmentNote.length() > 250) {
            new AlertMessages().errorMessage("Meeting Note Error",
                    "Meeting note cannot be larger than 250 characters.");
            return;
        }

        Appointment appointment = new Appointment(appointmentID, type, updatedDate, updatedBy, appointmentNote);
        Appointment.updateMeeting(appointment);

        new AlertMessages().informationMessage("Meeting Saved",
                "Meeting saved successfully");

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
    Appointment passedClientAP;
    Customer passedClient;
    User currentUser;

    public void getData(Appointment passedAppointment, int CXID, User passedUser) {
        passedClientAP = passedAppointment;
        passedClient = Customer.getCustomer(String.valueOf(CXID));
        currentUser = passedUser;

        customerName.setText(passedClient.getName());
        customerPhone.setText(passedClient.getPhone());
        customerAddress.setText(passedClient.getAddress());
        cxCaseStatus.setText(passedAppointment.getType());

        if (!passedAppointment.getAppointmentNote().equals("Pending Meeting")) {
            newMeetingNotes.setText(passedAppointment.getAppointmentNote());
        } else {

        }

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

            Timestamp recentMeeting = passedAppointment.getStartDateTime();
            if (recentMeeting.after(ap.getStartDateTime())) recentMeeting = ap.getStartDateTime();
            lastMeeting.setText(recentMeeting.toString());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
