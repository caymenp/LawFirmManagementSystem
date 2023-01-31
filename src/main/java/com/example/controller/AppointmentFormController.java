package com.example.controller;

import com.example.model.Appointment;
import com.example.model.Contact;
import com.example.model.Customer;
import com.example.model.User;
import com.example.utilities.AlertMessages;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** Controller for Appointment Form - Handles all Appointment additions/modifications/deletion */
public class AppointmentFormController implements Initializable {

    @FXML
    public Label appointmentFormLabel;
    @FXML
    private TextField appointmentID;
    @FXML
    private TextField appointmentTitle;
    @FXML
    private TextField appointmentDescription;
    @FXML
    private ComboBox<String> contactList;
    @FXML
    private TextField appointmentLocation;
    @FXML
    private TextField appointmentType;
    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox<String> customerList;
    @FXML
    private ComboBox<String> userIDList;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<LocalTime> startTimeList;
    @FXML
    private ComboBox<LocalTime> endTimeList;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button saveNewAppointment;
    @FXML

    Appointment passApt;
    User passUser;

    /** Gets data passed from the AppointmentOverviewForm.
     * When the scene changes from the main stage (AppointmentOverview) from am action event, this method gets all the
     * data (objects) passed to it, so it can reference this data in the new scene. Used to grab the info from an existing
     * appointment and display the existing data in the fields.
     * @param passedApt Appointment that was selected on the appointment table when "Modify Appointment" was clicked
     * @param user Current logged in user to ensure logging of updates are handled correctly.
     */
    public void getData(Appointment passedApt, User user) {
        passApt = passedApt;
        passUser = user;

        String aptContactID = String.valueOf(passApt.getContactID());
        Contact passedContact = Contact.getContact(aptContactID);

        String customerID = String.valueOf(passApt.getCustomerID());
        Customer passedCustomer;
        passedCustomer = Customer.getCustomer(customerID);

        appointmentID.setText(String.valueOf(passApt.getAppointmentID()));
        appointmentTitle.setText(passApt.getTitle());
        appointmentDescription.setText(passApt.getDescription());
        appointmentLocation.setText(passApt.getLocation());
        appointmentType.setText(passApt.getType());
        contactList.setValue(passedContact.getContactName());
        customerList.setValue(passedCustomer.getName());
        userIDList.setValue(User.getUser(passApt.getUserID()).getUserName());
        startDate.setValue(passApt.getStartDateTime().toLocalDateTime().toLocalDate());
        startTimeList.setValue(passApt.getStartDateTime().toLocalDateTime().toLocalTime());
        endDate.setValue(passApt.getEndDateTime().toLocalDateTime().toLocalDate());
        endTimeList.setValue(passApt.getEndDateTime().toLocalDateTime().toLocalTime());

        if (!(startDate.getValue() == null)) {
            endDate.setDisable(false);
            endTimeList.setDisable(false);
        }

    }


    /** Getting Data from mainStage when "Add Appointment" is clicked.
     * This is a overridden method to allow the reuse of the same appointment form whether "Modify Appointment" or "Add
     *  Appointment" is clicked. This method allows the passing of JUST the current user, since there is no appointment
     *   to pass when a new appointment is being made.
     * @param user Current logged in user. To ensure accurate reporting on updates
     */
    public void getData(User user) {
        passUser = user;
    }



    LocalDate selectedStartDate;
    LocalDate selectedEndDate;

    /**Gets value of StartDate date picker field.
     * Sets the value of datepicker field to a local variable @selectedStartDate to be used by other methods
     * Then sets the endDate picker as enabled (validation), and pre-sets the date as the same day (but can be changed).
     * @param actionEvent triggered when the date picker is clicked and a date is selected.
     */
    public void startDate(ActionEvent actionEvent) {
        selectedStartDate = startDate.getValue();
        endDate.setDisable(false);
        endDate.setValue(selectedStartDate.plusDays(0));
    }

    /** Gets value of EndDate from datepicker
     * Sets the selected endDate to a local variable for use by other methods
     * @param actionEvent triggered when the date picker is clicked and a date is selected.
     */
    public void endDate(ActionEvent actionEvent) {
        selectedEndDate = endDate.getValue();
    }

    User selectedUser;

    /**Gets selected User ID form the userID comboBox
     * Gets the value of the comboBox and uses it as an argument of a method on the Users class that returns the
     * referenced user object. Then assigns this user object to a local variable to be used by other methods.
     * @param actionEvent
     */
    public void userIDList(ActionEvent actionEvent) {
        selectedUser = User.getUser(userIDList.getValue());
    }


    LocalTime startTimeSelected;

    /**Start Time Selected
     * Gets the start time value from the comboBox, and sets end time to be 1 hour ahead of selected start time and
     * sets the end time combobox as visibile.
     * @param actionEvent
     */
    public void startTimeList(ActionEvent actionEvent) {

        startTimeSelected = startTimeList.getValue();
        endTimeList.setDisable(false);
        endTimeList.setValue(startTimeSelected.plusHours(1));
    }

    /**Cancel Button on the Appointment Form
     * Closes out of the pop-up window, and sets the stage back to the main stage
     * @param actionEvent cancel button on the form
     */
    public void cancelBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /** Appointment Form Handler When Saved
     * Validates the form to ensure no empty text fields or comboBoxes. Also validates the appoitnment dates/times to
     * reduce error. If validation passes, it determines is this is a modification or addition by checking the form label
     * text value. Then submits the form data to the Appointment handler to be updated/added to the DB.
     * @param actionEvent
     */
    public void saveNewAppointment(ActionEvent actionEvent) {
        AlertMessages validationAlert = new AlertMessages();

        if (appointmentTitle.getText().isEmpty()) {
            validationAlert.errorMessage("Validation Error", "Title cannot be blank");
            return;
        }
        if (appointmentDescription.getText().isEmpty()) {
            validationAlert.errorMessage("Validation Error", "Description cannot be blank");
            return;
        }
        if (appointmentLocation.getText().isEmpty()) {
            validationAlert.errorMessage("Validation Error", "Location cannot be blank");
            return;
        }
        if (appointmentType.getText().isEmpty()) {
            validationAlert.errorMessage("Validation Error", "Type cannot be blank");
            return;
        }
        if (contactList.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must select a contact");
            return;
        }
        if (customerList.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must select a customer");
            return;
        }
        if (userIDList.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must select a user");
            return;
        }
        if (startDate.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must choose an Appointment Start Date");
            return;
        }
        if (endDate.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must choose an Appointment End Date");
            return;
        }
        if (startTimeList.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must choose an Appointment Start Time");
            return;
        }
        if (endTimeList.getValue() == null) {
            validationAlert.errorMessage("Validation Error", "You must choose an Appointment End Time");
            return;
        }

        if (endDate.getValue().isBefore(startDate.getValue())) {
            validationAlert.errorMessage("Validation Error", "Appointment Start Date Must Occur BEFORE End Date");
            return;
        }
        if (LocalTime.parse(endTimeList.getValue().toString()).isBefore(LocalTime.parse(startTimeList.getValue().toString()))) {
            validationAlert.errorMessage("Validation Error", "Appointment Start Time Must Occur BEFORE End Time");
            return;
        }
        if (LocalTime.parse(startTimeList.getValue().toString()) == LocalTime.parse(endTimeList.getValue().toString())) {
            validationAlert.errorMessage("Validation Error", "Appointment length must be at least 1 hour.");
            return;
        }

        Customer customer = Customer.getCustomerByName(customerList.getValue());
        Contact contact = Contact.getContactByName(contactList.getValue());
        User user = User.getUser(userIDList.getValue());

            String title = appointmentTitle.getText();
            String description = appointmentDescription.getText();
            String location = appointmentLocation.getText();
            String type = appointmentType.getText();
            Timestamp startDateTime = Timestamp.valueOf(LocalDateTime.of(startDate.getValue(), startTimeList.getValue()));
            Timestamp endDateTime = Timestamp.valueOf(LocalDateTime.of(endDate.getValue(), endTimeList.getValue()));

            Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
            String createdBy = passUser.getUserName();

            if (appointmentFormLabel.getText().equals("Modify Appointment")) {
                createdDate = passApt.getCreateDateTime();
                createdBy = passApt.getCreatedBy();
            }

            Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedBy = passUser.getUserName();
            int customerID = customer.getCustomerID();
            int userID = User.getUser(userIDList.getValue()).getUserID();
            int contactID = contact.getContactID();




            if (appointmentFormLabel.getText().equals("Add Appointment")) {
                Appointment appointment = new Appointment(title, description, location,
                        type, startDateTime, endDateTime, createdDate, createdBy,
                        lastUpdated, lastUpdatedBy, customerID, userID, contactID);

                if (!checkAppointmentOverlap(customerID, appointment)) {
                    Appointment.addNewAppointment(appointment);
                } else {
                    AlertMessages alertMessages = new AlertMessages();
                    alertMessages.errorMessage("Scheduling Interference", "Customer has an existing appointment between your selected date and times.");
                    return;
                }

            } else {
                int oldAptID = passApt.getAppointmentID();

                Appointment appointment = new Appointment(oldAptID, title, description, location,
                        type, startDateTime, endDateTime, createdDate, createdBy,
                        lastUpdated, lastUpdatedBy, customerID, userID, contactID);

                if (!checkAppointmentOverlap(customerID, appointment)) {
                    Appointment.updateAppointment(appointment);
                }else {
                    AlertMessages alertMessages = new AlertMessages();
                    alertMessages.errorMessage("Scheduling Interference", "Customer has an existing appointment between your selected date and times.");
                    return;
                }

            }




        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**Checks for overlapping appointments on customers.
     * Checks for existing appointments by the customer, and ensures that the customer doesnt have an overlap.
     * @param cxID
     * @param cxAP
     * @return
     */
    public static boolean checkAppointmentOverlap(int cxID, Appointment cxAP) {
        ObservableList<Appointment> allCustomerAppointments = FXCollections.observableArrayList();

        allCustomerAppointments.addAll(Appointment.customerAppointments(cxID));

        LocalDateTime newApStart = cxAP.getStartDateTime().toLocalDateTime();
        LocalDateTime newApEnd = cxAP.getEndDateTime().toLocalDateTime();

        for (Appointment ap : allCustomerAppointments) {
            LocalDateTime apStart = ap.getStartDateTime().toLocalDateTime();
            LocalDateTime apEnd = ap.getEndDateTime().toLocalDateTime();

            if (ap.getAppointmentID() == cxAP.getAppointmentID()) {
                if (apStart.isEqual(newApStart) && apEnd.isEqual(newApEnd)) {
                    continue;
                }
            }

            if (newApStart.toLocalDate().equals(apStart.toLocalDate())) {
                    if ((newApStart.isAfter(apStart) && newApEnd.isBefore(apEnd)) || (newApStart.isBefore(apStart) && newApEnd.isAfter(apEnd))) {
                        return true;
                    } else if (newApStart.toLocalTime() == apStart.toLocalTime() || newApEnd.toLocalTime() == apEnd.toLocalTime()) {
                        return true;
                    } else if ((apStart.isAfter(newApStart) && apStart.isBefore(newApEnd)) || (apStart.isBefore(newApStart) && apEnd.isAfter(newApEnd))) {
                        return true;
                    }
            }
        }
        return false;
    }

    private void initializeContacts() {
        contactList.setItems(Contact.getAllContactStrings());
    }

    private void initializeCustomers() {
        customerList.setItems(Customer.getAllCustomerStrings());
    }

    private void initializeUsers() {
        userIDList.setItems(User.getAllUserStrings());
    }

    private void initializeTimeBox() {
        startTimeList.setItems(DateTimeConversion.availableAppointmentTimes());
        endTimeList.setItems(DateTimeConversion.availableAppointmentTimes());
    }

    /**Initializes comboBoxes on load
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTimeBox();
        initializeUsers();
        initializeCustomers();
        initializeContacts();
    }

}
