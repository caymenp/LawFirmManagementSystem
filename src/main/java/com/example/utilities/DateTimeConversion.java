package com.example.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeConversion {

    //UTC ZONE ID
    private static ZoneId utcZoneID = ZoneId.of("UTC");
    //EST ZONE ID
    private static ZoneId etcZoneId = ZoneId.of("America/New_York");
    // USERS SYSTEM DEFAULT ZONE IF
    private static ZoneId currentZoneID = ZoneId.systemDefault();

    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(getCurrentLocalDate(), getCurrentLocalTime());
    }

    public static ZonedDateTime getLocalZonedDateTime() {
        return ZonedDateTime.of(getLocalDateTime(), currentZoneID);
    }

    //-------------------Conversion Methods--------------------

    // User Time -> UTC
    public static ZonedDateTime localToUTC() {
        return ZonedDateTime.ofInstant(getLocalZonedDateTime().toInstant(), utcZoneID).truncatedTo(ChronoUnit.SECONDS);
    }

    //UTC -> User Time
    public static ZonedDateTime utcToLocal() {
        return ZonedDateTime.ofInstant(localToUTC().toInstant(), currentZoneID).truncatedTo(ChronoUnit.SECONDS);
    }


    // Convert To Local Time FROM SQL to display
//    public static LocalDateTime getLocalDisplayString(Timestamp sqlDateTime) {
//        LocalDateTime utcLocalDate = sqlDateTime.toLocalDateTime();
//        ZonedDateTime zdt = utcLocalDate.atZone(utcZoneID);
//        ZonedDateTime newZDT = zdt.withZoneSameInstant(currentZoneID);
//        LocalDateTime localDateTimeString = newZDT.toLocalDateTime();
//        return Timestamp.valueOf(localDateTimeString);
//
//    }

    public static String getUTCdateTime(String localString) {
        Timestamp convStr = Timestamp.valueOf(localString);
        LocalDateTime ldt = convStr.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(currentZoneID);
        ZonedDateTime newZDT = zdt.withZoneSameInstant(utcZoneID);
        LocalDateTime utcDateTimeString = newZDT.toLocalDateTime();
        return Timestamp.valueOf(utcDateTimeString).toString();

    }

    public static ObservableList<LocalTime> availableAppointmentTimes() {
        ObservableList<String> estTimes = FXCollections.observableArrayList();

        LocalDate myLD = LocalDate.now();
        LocalTime myLT = LocalTime.of(7, 0);
        LocalDateTime myLDT = LocalDateTime.of(myLD,  myLT);

        ZonedDateTime myZDT = ZonedDateTime.of(myLDT, currentZoneID);


        ZonedDateTime etcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), etcZoneId);

        LocalTime etcStartTime = etcZDT.toLocalTime();

        ObservableList<LocalTime> appointmentTimesEST = FXCollections.observableArrayList();

        for (int i = 0; i <= 14; i++) {
            LocalTime incrTime = etcStartTime.plusHours(i);
            appointmentTimesEST.add(incrTime);
        }

        ObservableList<LocalTime> appointmentTimesLocal = FXCollections.observableArrayList();

        for (LocalTime lm : appointmentTimesEST) {
            ZonedDateTime currentUserTimeAvailable = ZonedDateTime.of(LocalDate.now(), lm, etcZoneId);
            ZonedDateTime availableTimeConv = ZonedDateTime.ofInstant(currentUserTimeAvailable.toInstant(), currentZoneID);
            LocalTime localUserTimes = availableTimeConv.toLocalTime();
            appointmentTimesLocal.add(localUserTimes);
        }

        return appointmentTimesLocal;

    }

    public static Timestamp getUserDisplayTime(Timestamp sqlTime) {

        LocalDateTime sqlToJava = sqlTime.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



        ZonedDateTime utcTOest = ZonedDateTime.ofInstant(sqlTime.toInstant(), etcZoneId);

        ZonedDateTime estToLocal = ZonedDateTime.ofInstant(utcTOest.toInstant(), currentZoneID);

        Timestamp LocalDateTime = Timestamp.valueOf(estToLocal.toLocalDateTime().format(formatter));

        return LocalDateTime;

    }

    public static Timestamp saveToDB(Timestamp localtime) {
        LocalDateTime currentUser = localtime.toLocalDateTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        ZonedDateTime currentToEST = ZonedDateTime.ofInstant(localtime.toInstant(), etcZoneId);

        ZonedDateTime estToUCT = ZonedDateTime.ofInstant(currentToEST.toInstant(), utcZoneID);

        Timestamp dbConv = Timestamp.valueOf(estToUCT.toLocalDateTime().format(formatter));

                return dbConv;
    }

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


    //toInstant() -> UTC
    // ofInstant() -> Local (pass in a Zone ID as 2nd Argument)

}

