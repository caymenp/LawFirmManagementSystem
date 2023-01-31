package com.example.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**DateTimeConversion Class - Used to facilitate the time conversions throughout the Application */
public class DateTimeConversion {

    //UTC ZONE ID
    private static ZoneId utcZoneID = ZoneId.of("UTC");
    //EST ZONE ID
    private static ZoneId etcZoneId = ZoneId.of("America/New_York");
    // USERS SYSTEM DEFAULT ZONE IF
    private static ZoneId currentZoneID = ZoneId.systemDefault();

    /**Available Appointment Times.
     * This method creates a list of available appointment times to be displayed for user selection based on the available
     * business hours in EST converted to local time. The times are added to a list for easier user selection, and to ensure
     * the validation of appointments ONLY during business hours in EST.
     * @return observableList of appointment times determined from EST time to local user time.
     */
    public static ObservableList<LocalTime> availableAppointmentTimes() {

        LocalDate myLD = LocalDate.now();
        LocalTime myLT = LocalTime.of(8, 0);
        LocalDateTime myLDT = LocalDateTime.of(myLD,  myLT);


        ZonedDateTime etcZDT = ZonedDateTime.of(myLDT, etcZoneId);
        ZonedDateTime localToEst = ZonedDateTime.ofInstant(etcZDT.toInstant(), currentZoneID);


        LocalTime etcStartTime = localToEst.toLocalTime();

        ObservableList<LocalTime> appointmentTimesEST = FXCollections.observableArrayList();

        for (int i = 0; i <= 14; i++) {
            LocalTime incrTime = etcStartTime.plusHours(i);
            appointmentTimesEST.add(incrTime);
        }


        return appointmentTimesEST;

    }

    /**Get Local User Time.
     * Takes a timestamp as a parm, then creates a datetime format for proper display to the application users. It determines
     * the user zoneID and converts the UTC timestamp from the DB to EST time, and then converts that EST time to the
     * user local time zone for display.
     * @param sqlTime timestamp passed in to be converted to user local time
     * @return a timestamp formatted and converted to user local time.
     */
    public static Timestamp getUserDisplayTime(Timestamp sqlTime) {

        LocalDateTime sqlToJava = sqlTime.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



        ZonedDateTime utcTOest = ZonedDateTime.ofInstant(sqlTime.toInstant(), etcZoneId);

        ZonedDateTime estToLocal = ZonedDateTime.ofInstant(utcTOest.toInstant(), currentZoneID);

        Timestamp LocalDateTime = Timestamp.valueOf(estToLocal.toLocalDateTime().format(formatter));

        return LocalDateTime;

    }

    /** Converts time from user local time to be saved in the DB.
     * Takes a timestamp from the user local time, and formats it into a DateTime. It then takes the timestamp and
     * gets the ETC time conversion. With the ETC time, it then converts that into UTC time to be saved in the DB.
     * @param localtime current user local timestamp
     * @return timestamp converted to EST and then to UTC to be saved in the DB.
     */
    public static Timestamp saveToDB(Timestamp localtime) {
        LocalDateTime currentUser = localtime.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        ZonedDateTime currentToEST = ZonedDateTime.ofInstant(localtime.toInstant(), etcZoneId);

        ZonedDateTime estToUCT = ZonedDateTime.ofInstant(currentToEST.toInstant(), utcZoneID);

        Timestamp dbConv = Timestamp.valueOf(estToUCT.toLocalDateTime().format(formatter));

                return dbConv;
    }

    /** Determines the week of the current day
     * This method gets the full week values of the current user date. It determines how far the current day is from
     * Sunday (Start of the week) and Saturday (end of week) and gets those dates of the sunday and saturday of the current
     * week. It then returns a pair with these two values to be used to calendar/table filtering.
     * @return a pair containing the date of the start of the week and end of week for filtering calendar.
     */
    public static Pair<LocalDate, LocalDate> getWeek() {

        LocalDate currentUser = LocalDate.now();


        //Go backwards to get to Monday
        LocalDate sunday = currentUser;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.minusDays(1);
        }

        //Go forward to get to Saturday
        LocalDate saturday = currentUser;
        while (saturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
            saturday = saturday.plusDays(1);
        }

        sunday = sunday.minusDays(1);
        saturday = saturday.plusDays(1);



        return new Pair<>(sunday, saturday);

    }


}

