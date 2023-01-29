package com.example.model;

import com.example.DAO.AppointmentDoeImpl;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp startDateTime;
    private Timestamp endDateTime;
    private Timestamp createDateTime;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    //New Appointment Constructor
    public Appointment(int appointmentID, String title, String description,
                       String location, String type, Timestamp startDateTime,
                       Timestamp endDateTime, Timestamp createDateTime, String createdBy,
                       Timestamp lastUpdate, String lastUpdatedBy, int customerID,
                       int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = DateTimeConversion.getUserDisplayTime(startDateTime);
        this.endDateTime = DateTimeConversion.getUserDisplayTime(endDateTime);
        this.createDateTime = DateTimeConversion.getUserDisplayTime(createDateTime);
        this.createdBy = createdBy;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    //Update Appointment Constructor
    public Appointment(int appointmentID, String title, String description, String location,
                       String type, Timestamp startDateTime, Timestamp endDateTime, Timestamp lastUpdate,
                       String lastUpdatedBy, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = DateTimeConversion.getUserDisplayTime(startDateTime);
        this.endDateTime = DateTimeConversion.getUserDisplayTime(endDateTime);
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public Appointment(String title, String description,
                       String location, String type, Timestamp startDateTime,
                       Timestamp endDateTime, Timestamp createDateTime, String createdBy,
                       Timestamp lastUpdate, String lastUpdatedBy, int customerID,
                       int userID, int contactID) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = DateTimeConversion.getUserDisplayTime(startDateTime);
        this.endDateTime = DateTimeConversion.getUserDisplayTime(endDateTime);
        this.createDateTime = DateTimeConversion.getUserDisplayTime(createDateTime);
        this.createdBy = createdBy;
        this.lastUpdate = DateTimeConversion.getUserDisplayTime(lastUpdate);
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }


    // Get All Appointments Local Time
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();


        try{
            allAppointments.addAll(AppointmentDoeImpl.getAllAppointments());
            return allAppointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ObservableList<Appointment> customerAppointments(int cxID) {
        ObservableList<Appointment> cxAppointments = FXCollections.observableArrayList();

        try {
            cxAppointments.addAll(AppointmentDoeImpl.getCxAppointments(cxID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cxAppointments;
    }

    public static void addNewAppointment(Appointment app) {
        try {
            AppointmentDoeImpl.addAppointment(app);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAppointment(Appointment appointment) {
        try{
            AppointmentDoeImpl.updateAppointment(appointment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Appointment getAppointment(String AppointmentID) {
        try {
            Appointment gotAppointment = AppointmentDoeImpl.getAppointment(AppointmentID);
            return gotAppointment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Appointment> getFilteredAppointments(LocalDate startDate, LocalDate endDate) {

        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();

        String startDateString = startDate.toString();
        String endDateString = endDate.toString();

        try {
            filteredAppointments.addAll(AppointmentDoeImpl.getFilteredAppointments(startDateString, endDateString));
            return filteredAppointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) {
        try {
            return AppointmentDoeImpl.getAppointmentByContact(contactId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWinningCustomer(Month month) {
        try{
           return AppointmentDoeImpl.getCustomerWithMostAppointments(month);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns an Observable List of Appointment Types - LAMBDA
     * @return
     */

    public static ObservableList<String> allTypes() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        allAppointments.addAll(getAllAppointments());

        ObservableList<String> allTypes = FXCollections.observableArrayList();

        /**
         * LAMBDA - Takes Appointment from list, gets the appointment Type, and adds it to a separate list.
         * This new list it added the type to @allTypes, will be used to populate the "types" comboBox on the reports page.
         */
        allAppointments.forEach((a) -> {allTypes.add(a.getType());});

        return allTypes;
    }

    public static void deleteAppointment(int appointmentid) {
        try {
            AppointmentDoeImpl.deleteAppointment(appointmentid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Getters

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public Timestamp getCreateDateTime() {
        return createDateTime;
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

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }

    public int getContactID() {
        return contactID;
    }

    //Setters

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setCreateDateTime(Timestamp createDateTime) {
        this.createDateTime = createDateTime;
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

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }


}
