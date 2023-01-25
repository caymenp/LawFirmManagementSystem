package com.example.utilities;

import java.util.Locale;
import java.util.TimeZone;

/**
 * Get Current Users UserUtilities (TimeZone, language, etc.).
 */
public class UserUtilities {

    public String getTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getID();
    }

    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getCountry() {
      return Locale.getDefault().getCountry();
    }

}
