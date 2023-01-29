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

    /**Initializes the Report Page.
     * Upon initial load, this method is called to get populate the combobox's values.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectContact.setItems(Contact.getAllContactStrings());
        selectMonthCustomer.setItems(allMonths());
        selectMonthType.setItems(allMonths());
        selectType.setItems(Appointment.allTypes());
        selectType.setDisable(true);
    }


    /** Select Appointment Type ComboBox.
     * Used in the total appointment by type & month report. This comboBox is initially set as disabled until the month
     * comboBox has a value selected to help with validation issues. This method gets the value of the selected type within
     * the comboBox. It then calls a method on the Appointment class to query all appointments from the DB. Sets a counter
     * variable, and does a for loop through the Observable List of appointments. In this loop, it checks if the month of
     * the appointment matched the selected month comboBox and if the Appointment type matches the selected type. If it does,
     * it adds to the counter. It then takes the results of the counter and sets the text of the result on the GUI and sets
     * that text as visible.
     */
    Month selectedMonth;
    public void selectType(ActionEvent actionEvent) {
        if (selectType.getValue() == null) {
            return;
        }
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

    /** Month ComboBox for Appointment by Month/Type report.
     * Gets value of selected month, and stores it in a local variable to be processed by the selectedType method. Then
     * sets the type comboBox as visible.
     * @param actionEvent
     */
    public void selectMonthType(ActionEvent actionEvent) {
        if (!selectType.isDisable()) {
            selectType.setValue(null);
        }

        selectedMonth = Month.valueOf(selectMonthType.getValue().toString());
        selectType.setDisable(false);
    }

    /** Additional Custom Report.
     * This method listens for the action event on the custom additional report. It takes the value of the comboBox and
     * uses that value in a method call to the Appointment class for further processing. The return value of this method
     * call is a string. Once the string value is determined this method check to see if the value = null, if so - it
     * displays a message in the text field of the report to say "No Appointments this Month". If the value of the return is
     * not null, it assigns the text of the report to the returned String.
     * @param actionEvent
     */
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

    /** Populates the Month values for both month comboBoxes.
     * Sets a new observable list, then loops with an iterator from 1-12. Upon each loop, it calls the Month object method
     * to get the month value associated with the current iterator value. It adds this month object to the allMonths
     * Observable List.
     * @return allMonths observable List.
     */
    public static ObservableList<Month> allMonths() {
        ObservableList<Month> allMonths = FXCollections.observableArrayList();

        for (int i = 1; i <= 12; i++) {
            Month month = Month.of(i);
            allMonths.add(month);
        }
        return allMonths;
    }

    /**Contact Schedule Report.
     * Listens for an action event on the contact comboBox. When an action is heard, it gets the value of the comboBox
     * selection and calls a method on the Contact class to get the Contact object in reference. If then adds the result
     * of a method call to the Appointments Class to get a list of Appointments by Contact using the value of the Contact
     * object's id. It then populates the table with the results of the calls.
     * @param actionEvent
     */
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

    /** Close Button on Report Page.
     * Listens for an action event on the close button. If heard, sets the stage back to the main stage and closes the
     * report stage.
     * @param actionEvent close button on report page.
     */
    public void closeBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
