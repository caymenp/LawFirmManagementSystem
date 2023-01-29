package com.example.controller;

import com.example.model.Appointment;
import com.example.model.Contact;
import com.example.utilities.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

/** Controller for Reports - Handles the displaying of reports and allows for modification of reports */
public class ReportController implements Initializable {
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<?, ?> colAppID;
    @FXML
    private TableColumn<?, ?> colAptTitle;
    @FXML
    private TableColumn<?, ?> colAptDesc;
    @FXML
    private TableColumn<?, ?> colAptLocation;
    @FXML
    private TableColumn<?, ?> colAptContact;
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
    public ComboBox selectType;
    public ComboBox selectMonthType;
    public ComboBox selectMonthCustomer;
    public ComboBox selectContact;
    public Button closeBTN;
    public Text appointmentsByMonthType;
    public Text appointmentsByCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectContact.setItems(Contact.getAllContactStrings());
        selectMonthCustomer.setItems(allMonths());
        selectMonthType.setItems(allMonths());
        selectType.setItems(Appointment.allTypes());
        selectType.setDisable(true);
    }


    //Appointments By Type and Month
    Month selectedMonth;
    public void selectType(ActionEvent actionEvent) {
        String selectedType = selectType.getValue().toString();

        ObservableList<Appointment> allAppointments = Appointment.getAllAppointments();

        int total = 0;
        for (Appointment a : allAppointments) {
            if (a.getStartDateTime().toLocalDateTime().getMonth().equals(selectedMonth) && a.getType().equals(selectedType)) {
                total++;
            }
        }
        appointmentsByMonthType.setText(String.valueOf(total));
        appointmentsByMonthType.setVisible(true);
    }

    public void selectMonthType(ActionEvent actionEvent) {
        selectedMonth = Month.valueOf(selectMonthType.getValue().toString());
        selectType.setDisable(false);
    }

    public void selectMonthCustomer(ActionEvent actionEvent) {
        String winningCX = Appointment.getWinningCustomer(Month.valueOf(selectMonthCustomer.getValue().toString()));
        if (winningCX == null) {
            appointmentsByCustomer.setText("No appointments this month");
            appointmentsByCustomer.setVisible(true);
            return;
        }
        appointmentsByCustomer.setText(winningCX);
        appointmentsByCustomer.setVisible(true);
    }

    public static ObservableList<Month> allMonths() {
        ObservableList<Month> allMonths = FXCollections.observableArrayList();

        for (int i = 1; i <= 12; i++) {
            Month month = Month.of(i);
            allMonths.add(month);
        }
        return allMonths;
    }



    public void selectContact(ActionEvent actionEvent) {
        Contact selectedContact = Contact.getContactByName(selectContact.getValue().toString());

        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

        allAppointments.addAll(Appointment.getAppointmentsByContact(selectedContact.getContactID()));

        if (allAppointments.isEmpty()) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.informationMessage("No Scheduled Appointments", "No Scheduled Appointments for this contact.");
            return;
        }

        colAppID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colAptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAptContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        colAptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAptStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        colAptEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        colAptCXid.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colAptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        appointmentTable.setItems(allAppointments);
    }

    public void closeBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
