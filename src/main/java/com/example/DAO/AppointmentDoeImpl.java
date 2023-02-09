package com.example.DAO;

import com.example.model.Appointment;
import com.example.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;

/** Appointemnt DOE used for creation/updating/deleting Appointment objects from the DB*/

public class AppointmentDoeImpl {

    /** getAppointment
     * Used to query a specific record based on the parameter passed
     * @param appointmentID
     * @return appointment record found
     * @throws SQLException
     */
    public static Appointment getAppointment(String appointmentID) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE appointment_id = '"+appointmentID+"'";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            Timestamp startDateTime = result.getTimestamp("start");
            Timestamp endDateTime = result.getTimestamp("end");
            Timestamp createDateTime = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            String appointmentNote = result.getString("appointment_note");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, appointmentNote);
            return appointment;
        }
        return null;
    }

    /**Get All Appointments
     * Used to query the DB to get all appointment records.
     * @return an Observable list of all Appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.appointments";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            Timestamp startDateTime = result.getTimestamp("start");
            Timestamp endDateTime = result.getTimestamp("end");
            Timestamp createDateTime = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            String appointmentNote = result.getString("appointment_note");

                    appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                            endDateTime, createDateTime, createdBy, lastUpdate,
                            lastUpdatedBy, customerID, userID, appointmentNote);


                allAppointments.add(appointment);

        }
        return allAppointments;
    }

    /** Get Filtered Appointments
     * Used to query the DB for records that are between two dates that are passed as params
     * @param startDate - beginning date to query
     * @param endDate - end date to query
     * @return observable list of appointments that are between the two passed dates
     * @throws SQLException
     */
    public static ObservableList<Appointment> getFilteredAppointments(String startDate, String endDate) throws SQLException {
        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE Start BETWEEN '"+startDate+"' AND '"+endDate+"'";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            Timestamp startDateTime = result.getTimestamp("start");
            Timestamp endDateTime = result.getTimestamp("end");
            Timestamp createDateTime = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            String appointmentNote = result.getString("appointment_note");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, appointmentNote);

                filteredAppointments.add(appointment);
        }
        return filteredAppointments;
    }

    /** Get Customer Appointments
     * Querys DB for appointments that include the passed customer id
     * @param customerId - customer id to query
     * @return observable list of customer appointments
     * @throws SQLException
     */
    public static ObservableList<Appointment> getCxAppointments(int customerId) throws SQLException {
        ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE customer_id = '"+customerId+"'";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            Timestamp startDateTime = result.getTimestamp("start");
            Timestamp endDateTime = result.getTimestamp("end");
            Timestamp createDateTime = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            String appointmentNote = result.getString("appointment_note");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, appointmentNote);

            customerAppointments.add(appointment);
        }
        return customerAppointments;
    }

    /** Get Appointments By Contact
     * returns a list of appointments that include the passed contactID
     * @param contactid - contactID to query against
     * @return observable list of appointments that match that contactID
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentByContact(int contactid) throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE contact_id = '"+contactid+"'";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            Timestamp startDateTime = result.getTimestamp("start");
            Timestamp endDateTime = result.getTimestamp("end");
            Timestamp createDateTime = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            String appointmentNote = result.getString("appointment_note");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, appointmentNote);

            allAppointments.add(appointment);
        }
        return allAppointments;

    }

    /** Get Customer with Most Appointments
     * Used for the report that provides the winning customer. Uses the passed Month record to get a count of appointments
     * in that passed month by customer.
     * @param month - month to check for largest number of appointments
     * @return - String customer name
     * @throws SQLException
     */
    public static String getCustomerWithMostAppointments(Month month) throws SQLException {
        int monthN = month.getValue();
        String sqlStatement = "SELECT customer_id, COUNT(*) FROM client_schedule.appointments WHERE YEAR(start) = 2023 " +
                "AND MONTH(start) = '"+monthN+"' GROUP BY customer_id;";
        Query.makeQuery(sqlStatement);

        String customerName;
        int counter = 0;
        int winningCX = 0;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int cxID = result.getInt("customer_id");
            int count = result.getInt("COUNT(*)");
            if (count > counter) {
                counter = count;
                winningCX = cxID;
            }
        }
        if (winningCX == 0) return null;
        return Customer.getCustomer(String.valueOf(winningCX)).getName() + " : " + counter;
    }

    /** Add Appointment
     * Used to add passed appointment to the DB
     * @param appointment
     */
    public static void addAppointment(Appointment appointment) {

        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();
        Timestamp startDateTime = appointment.getStartDateTime();
        Timestamp endDateTime = appointment.getEndDateTime();
        Timestamp createdDate = appointment.getCreateDateTime();
        String createdBy = appointment.getCreatedBy();
        Timestamp lastUpdated = appointment.getLastUpdate();
        String lastUpdatedBy = appointment.getLastUpdatedBy();
        int customerID = appointment.getCustomerID();
        int userID = appointment.getUserID();



        String sqlStatement = "INSERT INTO client_schedule.appointments (title, description, location, type, start, end, " +
                "create_date, created_by, last_update, last_updated_by, customer_id, user_id)"+
                "VALUES ('"+title+"', '"+description+"', '"+location+"', '"+type+"', '"+startDateTime+"', " +
                "'"+endDateTime+"', '"+createdDate+"', '"+createdBy+"', '"+lastUpdated+"', '"+lastUpdatedBy+"', " +
                "'"+customerID+"', '"+userID+"')";

        Query.makeQuery(sqlStatement);

    }

    /**
     * Used to update passed appointment in DB.
     * @param appointment
     */
    public static void updateAppointment(Appointment appointment) {
        int appointmentID = appointment.getAppointmentID();

        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();
        Timestamp startDateTime = appointment.getStartDateTime();
        Timestamp endDateTime = appointment.getEndDateTime();
        Timestamp lastUpdated = appointment.getLastUpdate();
        String lastUpdatedBy = appointment.getLastUpdatedBy();
        int customerID = appointment.getCustomerID();
        int userID = appointment.getUserID();



        String sqlStatement = "UPDATE client_schedule.appointments " +
                "SET title = '"+title+"', description = '"+description+"', location = '"+location+"', " +
                "type = '"+type+"', start = '"+startDateTime+"', end = '"+endDateTime+"', last_update = '"+lastUpdated+"', " +
                "last_updated_by = '"+lastUpdatedBy+"', customer_id = '"+customerID+"', user_id = '"+userID+"' WHERE appointment_id = '"+appointmentID+"'";

        Query.makeQuery(sqlStatement);
    }

    public static void updateMeeting(Appointment appointment) {
        int appointmentID = appointment.getAppointmentID();
        String type = appointment.getType();
        Timestamp lastUpdated = appointment.getLastUpdate();
        String lastUpdatedBy = appointment.getLastUpdatedBy();
        String appointmentNote = appointment.getAppointmentNote();

        String sqlStatement = "UPDATE client_schedule.appointments " +
                "SET type = '"+type+"', last_update = '"+lastUpdated+"', last_updated_by = '"+lastUpdatedBy+"', appointment_note = '"+appointmentNote+"' WHERE appointment_id = '"+appointmentID+"'";

        Query.makeQuery(sqlStatement);
    }

    /**
     * Used to delete appointment in DB that matches the passed appointmentID.
     * @param appointmentid
     */
    public static void deleteAppointment(int appointmentid) {
        String sqlStatement = "DELETE FROM client_schedule.appointments WHERE appointment_id = '"+appointmentid+"'";
        Query.makeQuery(sqlStatement);
    }

}
