package com.example.DAO;

import com.example.model.Appointment;
import com.example.model.Customer;
import com.example.utilities.DateTimeConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;

public class AppointmentDoeImpl {

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
            int contactID = result.getInt("contact_id");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, contactID);
            return appointment;
        }
        return null;
    }

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
            int contactID = result.getInt("contact_id");

                    appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                            endDateTime, createDateTime, createdBy, lastUpdate,
                            lastUpdatedBy, customerID, userID, contactID);


                allAppointments.add(appointment);

        }
        return allAppointments;
    }

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
            int contactID = result.getInt("contact_id");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, contactID);

                filteredAppointments.add(appointment);
        }
        return filteredAppointments;
    }

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
            int contactID = result.getInt("contact_id");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, contactID);

            customerAppointments.add(appointment);
        }
        return customerAppointments;
    }

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
            int contactID = result.getInt("contact_id");

            appointment = new Appointment(appointment_ID, title, description, location, type, startDateTime,
                    endDateTime, createDateTime, createdBy, lastUpdate,
                    lastUpdatedBy, customerID, userID, contactID);

            allAppointments.add(appointment);
        }
        return allAppointments;

    }

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
        return Customer.getCustomer(String.valueOf(winningCX)).getName() + " : " + String.valueOf(counter);
    }

    public static void addAppointment(Appointment appointment) {

        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();
        Timestamp startDateTime = DateTimeConversion.saveToDB(appointment.getStartDateTime());
        Timestamp endDateTime = DateTimeConversion.saveToDB(appointment.getEndDateTime());
        Timestamp createdDate = DateTimeConversion.saveToDB(appointment.getCreateDateTime());
        String createdBy = appointment.getCreatedBy();
        Timestamp lastUpdated = DateTimeConversion.saveToDB(appointment.getLastUpdate());
        String lastUpdatedBy = appointment.getLastUpdatedBy();
        int customerID = appointment.getCustomerID();
        int userID = appointment.getUserID();
        int contactID = appointment.getContactID();



        String sqlStatement = "INSERT INTO client_schedule.appointments (title, description, location, type, start, end, " +
                "create_date, created_by, last_update, last_updated_by, customer_id, user_id, contact_id)"+
                "VALUES ('"+title+"', '"+description+"', '"+location+"', '"+type+"', '"+startDateTime+"', " +
                "'"+endDateTime+"', '"+createdDate+"', '"+createdBy+"', '"+lastUpdated+"', '"+lastUpdatedBy+"', " +
                "'"+customerID+"', '"+userID+"', '"+contactID+"')";

        Query.makeQuery(sqlStatement);

    }

    public static void updateAppointment(Appointment appointment) {
        int appointmentID = appointment.getAppointmentID();

        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();
        Timestamp startDateTime = DateTimeConversion.saveToDB(appointment.getStartDateTime());
        Timestamp endDateTime = DateTimeConversion.saveToDB(appointment.getEndDateTime());
        Timestamp lastUpdated = DateTimeConversion.saveToDB(appointment.getLastUpdate());
        String lastUpdatedBy = appointment.getLastUpdatedBy();
        int customerID = appointment.getCustomerID();
        int userID = appointment.getUserID();
        int contactID = appointment.getContactID();



        String sqlStatement = "UPDATE client_schedule.appointments " +
                "SET title = '"+title+"', description = '"+description+"', location = '"+location+"', " +
                "type = '"+type+"', start = '"+startDateTime+"', end = '"+endDateTime+"', last_update = '"+lastUpdated+"', " +
                "last_updated_by = '"+lastUpdatedBy+"', customer_id = '"+customerID+"', user_id = '"+userID+"', " +
                "contact_id = '"+contactID+"' WHERE appointment_id = '"+appointmentID+"'";

        Query.makeQuery(sqlStatement);
    }

    public static void deleteAppointment(int appointmentid) {
        String sqlStatement = "DELETE FROM client_schedule.appointments WHERE appointment_id = '"+appointmentid+"'";
        Query.makeQuery(sqlStatement);
    }

}
