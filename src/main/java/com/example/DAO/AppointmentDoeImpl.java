package com.example.DAO;

import com.example.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            String startDateTime = result.getString("start");
            String endDateTime = result.getString("end");
            String createDateTime = result.getString("create_date");
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
            String startDateTime = result.getString("start");
            String endDateTime = result.getString("end");
            String createDateTime = result.getString("create_date");
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

    public static ObservableList<Appointment> getFilteredAppointments() throws SQLException {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime weekFilter = curDate.minusDays(7);


        ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.appointments WHERE date(start) BETWEEN '"+curDate+"' AND '"+weekFilter+"' ";
        Query.makeQuery(sqlStatement);

        Appointment appointment;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int appointment_ID = result.getInt("appointment_id");
            String title = result.getString("title");
            String description = result.getString("description");
            String location = result.getString("location");
            String type = result.getString("type");
            String startDateTime = result.getString("start");
            String endDateTime = result.getString("end");
            String createDateTime = result.getString("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");
            int customerID = result.getInt("customer_id");
            int userID = result.getInt("user_id");
            int contactID = result.getInt("contact_id");

            appointment = getAppointment(String.valueOf(appointment_ID));
            filteredAppointments.add(appointment);
        }
        return filteredAppointments;
    }
}
