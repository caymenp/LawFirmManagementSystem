package com.example.controller;

import com.example.model.Appointment;
import com.example.model.User;
import com.example.utilities.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** Controller for Reports - Handles the displaying of reports and allows for modification of reports */
public class ReportController implements Initializable {
    public Label reportGeneratedDate;
    public Label assocName;
    public Label assocAptTotal;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<?, ?> colAppID;
    @FXML
    private TableColumn<?, ?> colAptTitle;
    @FXML
    private TableColumn<?, ?> colAptLocation;
    @FXML
    private TableColumn<?, ?> colAptType;
    @FXML
    private TableColumn<?, ?> colAptStart;
    @FXML
    private TableColumn<?, ?> colAptEnd;
    @FXML
    private TableColumn<?, ?> colAptCXid;
    @FXML
    private TableColumn<?, ?> colAptUserID;
    public ComboBox selectContact;
    public Button closeBTN;
    public Text appointmentsByMonthType;
    public Text appointmentsByCustomer;
    
    User associate;
    LocalDateTime reportGenerated = LocalDateTime.now();
    public void getAssociate(User passedAssociate) {
        associate = passedAssociate;
        assocName.setText(associate.getUserName());
        reportGeneratedDate.setText(reportGenerated.toString());
        generateReportTable();
    }
    public ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    public void generateReportTable() {
        ObservableList<Appointment> associateAppointments = FXCollections.observableArrayList();

        allAppointments.addAll(Appointment.getAllAppointments());


        for (Appointment ap : allAppointments) {
            LocalDateTime apTime = ap.getStartDateTime().toLocalDateTime();
            if (ap.getUserID() == associate.getUserID()) {
                if (apTime.getMonth().getValue() == reportGenerated.getMonth().getValue() && apTime.getYear() == reportGenerated.getYear()) {
                    associateAppointments.add(ap);
                }
            }
        }

        if (associateAppointments.isEmpty()) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.informationMessage("No Scheduled Appointments", "No Scheduled Appointments for this Associate in the current month.");
            return;
        }

        colAppID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colAptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAptStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        colAptEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        colAptCXid.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        appointmentTable.setItems(associateAppointments);
        assocAptTotal.setText(String.valueOf(associateAppointments.size()));
    }

    /** Close Button on Report Page.
     * Listens for an action event on the close button. If heard, sets the stage back to the main stage and closes the
     * report stage.
     * @param actionEvent close button on report page.
     */
    public void closeBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
