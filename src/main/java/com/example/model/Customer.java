package com.example.model;

import com.example.DAO.CustomerDoeImpl;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;

/** Customer Class used for creation/updating/deleting customer objects from the DB*/

public class Customer {

    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private String divisionName;


    public Customer(int customerID, String name, String address, String postalCode, String phone,
                    Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy,
                    int divisionID) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = DateTimeConversion.getUserDisplayTime(createDate);
        this.createdBy = createdBy;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public Customer(String name, String address, String postalCode, String phone, Timestamp createDate,
                    String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = DateTimeConversion.getUserDisplayTime(createDate);
        this.createdBy = createdBy;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public Customer(String name, String address, String postalCode, String phone, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public Customer(int customerID, String name, String address, String postalCode, String phone,
                    Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy,
                    int divisionID, String divisionName) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = DateTimeConversion.getUserDisplayTime(createDate);
        this.createdBy = createdBy;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    //Getters
    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    //Setters

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            allCustomers.addAll(CustomerDoeImpl.getAllCustomers());
            return allCustomers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<String> getAllCustomerStrings() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        ObservableList<String> allCustomerString = FXCollections.observableArrayList();

        try {
            allCustomers.addAll(CustomerDoeImpl.getAllCustomers());
            for (Customer cx : allCustomers) {
                String name = cx.getName();
                allCustomerString.add(name);
            }
            return allCustomerString;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateCX(Customer upCX) {
        CustomerDoeImpl.updateCustomer(upCX);
    }

    public static void deleteCX(int customerID) {

        CustomerDoeImpl.deleteCustomer(customerID);
    }

    public static Customer getCustomer(String customerID) {
        Customer customer;
        try {
            customer = CustomerDoeImpl.getCustomer(customerID);
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Customer getCustomerByName(String customern) {
        Customer customer;
        try {
            customer = CustomerDoeImpl.getCustomerByName(customern);
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
