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
    public ComboBox<String> countryList;
    public ComboBox<String> stateList;
    public Button cancelBTN;
    public Button saveNewCustomer;


    String selectedCountry;
    int selectedCountryID;

    public void countryList(ActionEvent actionEvent) {
        selectedCountry = countryList.getValue();
        selectedCountryID = Country.getCountryID(selectedCountry);
        initilizeDivisons();

    }

    public void stateList(ActionEvent actionEvent) {
        System.out.println(stateList.getValue());
    }

    public void cancelBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    Customer passedCustomer;
    User passedUser;
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

    public void getUser(User user) {
        passedUser = user;
    }

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
            System.out.println("Sending to Customer Class");

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            initilizeCountry();

    }

}
