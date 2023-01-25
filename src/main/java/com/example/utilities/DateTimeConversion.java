package com.example.utilities;

import java.sql.Timestamp;
import java.time.*;

public class DateTimeConversion {

    public static Timestamp currentSysTimeStamp = Timestamp.valueOf(java.time.LocalDateTime.now());
    public static String getCurrentDateTimeUTC() {
        OffsetDateTime odt = OffsetDateTime.now(ZoneOffset.UTC);
        return java.lang.String.valueOf(odt);
    }


    public static LocalDateTime getCurrentDateTimeLocal() {
        return currentSysTimeStamp.toLocalDateTime();
    }

    public static ZonedDateTime zonedDateTimeNow() {
        return getCurrentDateTimeLocal().atZone(ZoneId.of(ZoneId.systemDefault().toString()));
    }





    public static Timestamp ts = Timestamp.valueOf(java.time.LocalDateTime.now());
    public static LocalDateTime ldt = ts.toLocalDateTime();
    public static ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
    public static ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
    public static LocalDateTime ldtIn = utczdt.toLocalDateTime();

    public static ZonedDateTime zdtOut = ldtIn.atZone(ZoneId.of("UTC"));
    public static ZonedDateTime zdtOutToLocalTZ = zdtOut.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
    public static LocalDateTime ldtOutFinal = zdtOutToLocalTZ.toLocalDateTime();

}

