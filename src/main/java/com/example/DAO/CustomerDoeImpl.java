package com.example.DAO;

import com.example.model.Customer;
import com.example.utilities.CountryDivision;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CustomerDoeImpl {

    public static Customer getCustomer(String customerId) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.customers WHERE customer_id = '"+customerId+"'";
        Query.makeQuery(sqlStatement);

        Customer customer;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customerID = result.getInt("customer_id");
            String name = result.getString("customer_name");
            String address = result.getString("address");
            String zipcode = result.getString("postal_code");
            String phone = result.getString("phone");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updated_by = result.getString("last_updated_by");
            int divisionID = result.getInt("division_id");

            customer = new Customer(customerID, name, address, zipcode, phone, createDate, createdBy,
                    lastUpdate, updated_by, divisionID);

                return customer;

        }
        return null;
    }

    public static Customer getCustomerByName(String customerN) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.customers WHERE customer_name = '"+customerN+"'";
        Query.makeQuery(sqlStatement);

        Customer customer;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customerID = result.getInt("customer_id");
            String name = result.getString("customer_name");
            String address = result.getString("address");
            String zipcode = result.getString("postal_code");
            String phone = result.getString("phone");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updated_by = result.getString("last_updated_by");
            int divisionID = result.getInt("division_id");

            customer = new Customer(customerID, name, address, zipcode, phone, createDate, createdBy,
                    lastUpdate, updated_by, divisionID);

            return customer;

        }
        return null;
    }

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.customers";
        Query.makeQuery(sqlStatement);

        Customer customer;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int customerID = result.getInt("customer_id");
            String name = result.getString("customer_name");
            String address = result.getString("address");
            String zipcode = result.getString("postal_code");
            String phone = result.getString("phone");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updated_by = result.getString("last_updated_by");
            int divisionID = result.getInt("division_id");
            String divisionName = CountryDivision.getDivisionName(divisionID);

            customer = new Customer(customerID, name, address, zipcode, phone, createDate, createdBy,
                    lastUpdate, updated_by, divisionID, divisionName);

                allCustomers.add(customer);

        }
        return allCustomers;
    }

    public static void addCustomer(Customer customer) {
        String customerName = customer.getName();
        String address = customer.getAddress();
        String zipcode = customer.getPostalCode();
        String phone = customer.getPhone();
        Timestamp createDate = DateTimeConversion.saveToDB(customer.getCreateDate());
        String createdBy = customer.getCreatedBy();
        Timestamp lastUpdate = DateTimeConversion.saveToDB(customer.getLastUpdate());
        String updatedBy = customer.getLastUpdatedBy();
        int divisionID = customer.getDivisionID();

        String sqlStatement = "INSERT INTO client_schedule.customers (customer_name, address, postal_code, phone, create_date, created_by, last_update, last_updated_by, division_id)" +
                "VALUES ('"+customerName+"', '"+address+"', '"+zipcode+"', '"+phone+"', '"+createDate+"', '"+createdBy+"', '"+lastUpdate+"','"+updatedBy+"','"+divisionID+"')";

        Query.makeQuery(sqlStatement);

    }

    public static void updateCustomer(Customer customer) {

        int customerID = customer.getCustomerID();
        String customerName = customer.getName();
        String address = customer.getAddress();
        String zipcode = customer.getPostalCode();
        String phone = customer.getPhone();
        Timestamp lastUpdate = DateTimeConversion.saveToDB(customer.getLastUpdate());
        String updatedBy = customer.getLastUpdatedBy();
        int divisionID = customer.getDivisionID();

        String sqlStatement = "UPDATE client_schedule.customers " +
                "SET customer_name = '"+customerName+"', address = '"+address+"', postal_code = '"+zipcode+"', " +
                "phone = '"+phone+"', last_update = '"+lastUpdate+"', last_updated_by = '"+updatedBy+"', division_id = '"+divisionID+"'" +
                " WHERE customer_id = '"+customerID+"'";

        Query.makeQuery(sqlStatement);

    }

    public static void deleteCustomer(int customerID) {
        String sqlStatement = "DELETE FROM client_schedule.customers WHERE customer_id = '"+customerID+"'";

        try {
            Query.makeQuery(sqlStatement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
