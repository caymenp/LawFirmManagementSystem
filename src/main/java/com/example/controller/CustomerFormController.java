package com.example.controller;

import com.example.model.Country;
import com.example.model.Customer;
import com.example.model.Division;
import com.example.model.User;
import com.example.utilities.AlertMessages;
import com.example.utilities.CountryDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/** Controller for Customer Form - Handles all Customer additions/modifications/deletion */

public class CustomerFormController implements Initializable {
    public Label customerFormLabel;
    public TextField customerID;
    public TextField customerName;
    public TextField customerStreetAddress;
    public TextField customerPostal;
    public TextField customerPhone;
    @FXML
    private ComboBox<String> countryList;
    public ComboBox<String> stateList;
    public Button cancelBTN;
    public Button saveNewCustomer;


    String selectedCountry;
    int selectedCountryID;

    /** Country List ComboBox Action.
     * Listens for an action event on the combobox, and when clicked this method retrieves the value of the selected country,
     * it assigns the selected country string to a local variable for other methods to use. It then takes that string value
     * of the country name, and calls the getter on Country.getCountryID to get the Country object's ID. Upon the selection
     * of a country, this method initializes the Division list to ensure proper filtering of the divisions based on the
     * value of the selected country.
     * @param actionEvent
     */
    public void countryList(ActionEvent actionEvent) {
        selectedCountry = countryList.getValue();
        selectedCountryID = Country.getCountryID(selectedCountry);
        initilizeDivisons();

    }

    /** Action Event Listener on the Cancel Button of the Customer Form.
     * Listens for click on the close button, and when the action is triggered, closes the current stage, and reassigns the
     * main stage to the source of the original action event that displayed the customer form.
     * @param actionEvent click event on the close button
     */
    public void cancelBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    Customer passedCustomer;
    User passedUser;

    /** Getting Data From AppointmentOverView Controller Upon Stage creation and scene change.
     * This method is called from the AppointmentOverview controller when "modify customer" button is pressed. Takes two
     * parameters to get the data from the selected customer on the table and the current logged-in user for update logging.
     * Sets the customerForm fields to pre-populate the passed customer object's data.
     * @param customer Customer object selected on the customer table when the modify customer button is clicked
     * @param user Current logged-in user passed from the AppointmentOverview Controller for update logging.
     */
    public void getData(Customer customer, User user) {
        passedCustomer = customer;
        passedUser = user;

        selectedCountry = CountryDivision.getCountryName(customer.getDivisionID());
        selectedCountryID = Country.getCountryID(selectedCountry);

        customerID.setText(String.valueOf(customer.getCustomerID()));
        customerName.setText(customer.getName());
        customerPhone.setText(customer.getPhone());
        customerStreetAddress.setText(customer.getAddress());
        customerPostal.setText(customer.getPostalCode());
        countryList.getSelectionModel().select(selectedCountry);
        initilizeDivisons();
        stateList.getSelectionModel().select(CountryDivision.getDivisionName(customer.getDivisionID()));
    }

    /**Gets User Object from AppointmentOverview Controller upon "AddCustomer" being clicked.
     * When there is no customer selected on the customer table and the add new customer button is clicked, the
     * AppointmentOverview Controller sends the current logged-in user to ensure logging of updates.
     * @param user current logged-in user.
     */
    public void getUser(User user) {
        passedUser = user;
    }

    /** Save/Modify Customer Form Submission Method.
     * Listens for an action event on the "save" button of the customerForm. When the action is heard, this method performs
     * basic validation on the form fields to ensure no empty fields or empty comboBox values. If validation passes,
     * it assigns all customer fields to the form field values and creates a new customer object using the Customer
     * constructor. If then checks to see if this form is for modifying an existing customer of for adding a new customer
     * based on the form label text value. It then calls the appropriate method to pass the newly created customer to the
     * appropriate method for processing with the DB.
     * @param actionEvent
     */
    public void saveNewCustomer(ActionEvent actionEvent) {
        AlertMessages alertMessages = new AlertMessages();
        if (customerName.getText().isEmpty()) {
            alertMessages.errorMessage("Validation Error", "Customer name cannot be blank");
            return;
        }
        if (customerStreetAddress.getText().isEmpty()) {
            alertMessages.errorMessage("Validation Error", "Address cannot be blank");
            return;
        }
        if (customerPostal.getText().isEmpty()) {
            alertMessages.errorMessage("Validation Error", "Postal Code cannot be blank");
            return;
        }
        if (customerPhone.getText().isEmpty()) {
            alertMessages.errorMessage("Validation Error", "Phone cannot be blank");
            return;
        }
        if (countryList.getValue() == null) {
            alertMessages.errorMessage("Validation Error", "Please select a country from the drop down list.");
            return;
        }
        if (stateList.getValue() == null) {
            alertMessages.errorMessage("Validation Error", "Please select a state/province from the drop down list.");
            return;
        }


        int selectedDivID = Division.getDivisionID(stateList.getValue());

        if (customerFormLabel.getText().equals("Add Customer")) {

            String name = customerName.getText();
            String address = customerStreetAddress.getText();
            String zipcode = customerPostal.getText();
            String phone = customerPhone.getText();
            Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
            String createdBy = passedUser.getUserName();
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String updatedBy = passedUser.getUserName();
            int divisionID = selectedDivID;
            Customer newCustomer = new Customer(name, address, zipcode, phone, createDate, createdBy, lastUpdate, updatedBy, divisionID);
            CountryDivision.addCustomer(newCustomer);
        } else if (customerFormLabel.getText().equals("Modify Customer")) {
            int custID = passedCustomer.getCustomerID();

            String name = customerName.getText();
            String address = customerStreetAddress.getText();
            String zipcode = customerPostal.getText();
            String phone = customerPhone.getText();
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String updatedBy = passedUser.getUserName();
            int divisionID = selectedDivID;

            //Old Data to NOT update
            Timestamp createdDate = passedCustomer.getCreateDate();
            String createdBy = passedCustomer.getCreatedBy();

            Customer newCustomer = new Customer(custID, name, address, zipcode, phone, createdDate, createdBy, lastUpdate, updatedBy, divisionID);

            Customer.updateCX(newCustomer);

        }

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
    @FXML
    private void initilizeCountry() {
        countryList.setItems(Country.getAllCountries());
    }

    private void initilizeDivisons() {
        stateList.setItems(Division.getAllDivisionNamesStrings(selectedCountryID));
        stateList.setDisable(false);
    }

    /** Initializes the JavaFX CustomerForm Controller
     * Calls the initializeCountry() method to ensure the country combobox is populated upon loading.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            initilizeCountry();
    }

}
