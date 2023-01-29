package com.example.model;

import com.example.DAO.AppointmentDoeImpl;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

/** Appointemnt Class used for creation/updating/deleting Appointment objects from the DB*/
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

    /** New Appointment Contructor.
     * Takes all the params from the DB values, to create appointment objects from the DB.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param createDateTime
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
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

    /** Update Appointment Constructor.
     * Used when an existing appointment is being updated and not all DB values need to be updated.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
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


    /** Get All Appointments (In User Local Time).
     * Used to query the DB for ALL appointments and are stored in an Observable list.
     * @return list of appointments
     */
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try{
            allAppointments.addAll(AppointmentDoeImpl.getAllAppointments());
            return allAppointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** Appointments for Specific Customers.
     * Gets a list of appointments based on a passed param of customer id.
     * @param cxID customerID passed to query the DB for any appointments scheduled for this customerID
     * @return list of appointments if any match the customerID.
     */
    public static ObservableList<Appointment> customerAppointments(int cxID) {
        ObservableList<Appointment> cxAppointments = FXCollections.observableArrayList();

        try {
            cxAppointments.addAll(AppointmentDoeImpl.getCxAppointments(cxID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cxAppointments;
    }

    /** Add New Appointment.
     * Takes an Appointment object to send to the DB to be added to the appointment table.
     * @param app new appointment object to be added
     */
    public static void addNewAppointment(Appointment app) {
        try {
            AppointmentDoeImpl.addAppointment(app);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /** Modify Appointment.
     * Takes an Appointment object to send to the DB to be updated in the appointment table.
     * @param appointment existing appointment object to be updated
     */
    public static void updateAppointment(Appointment appointment) {
        try{
            AppointmentDoeImpl.updateAppointment(appointment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /** Get Filtered Appointments.
     * Takes a startDate and endDate to be applied to a select statement in the DB query to select appointments that are
     * between these two times.
     * @param startDate - start of filter
     * @param endDate - end of filter
     * @return - List of appointments between these two dates.
     */
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

    /** Get Appointment by contact.
     * Takes a contactID to query the DB to select appointments assigned with this contactID
     * @param contactId - contactID to be queried
     * @return - list of matching appointments that are assigned this contactID.
     */
    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) {
        try {
            return AppointmentDoeImpl.getAppointmentByContact(contactId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Get Winning Customer (Customer with most appointments in a passed Month).
     * Takes a Month as a param and queries this against a select statement that groups the results by month and customerID
     * @param month - month to group sqlStmt by
     * @return - String of matching customer name
     */
    public static String getWinningCustomer(Month month) {
        try{
           return AppointmentDoeImpl.getCustomerWithMostAppointments(month);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /** LAMBDA - Get All Appointment Types.
     * Returns an Observable List of Appointment Types from the db. The lambda expression is completed within the forEach
     * loop of the allAppointments list and takes each appointment and gets the type and adds it to the allTypes list.
     * @return list of appointment types as Strings.
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


}
