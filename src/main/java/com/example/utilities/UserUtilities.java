package com.example.utilities;

import java.time.ZoneId;
import java.util.Locale;

/**
 * Get Current Users UserUtilities (TimeZone, language, etc.).
 */
public class UserUtilities {

    public String getTimeZone() {
        return String.valueOf(ZoneId.systemDefault());
    }

    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public String getCountry() {
      return Locale.getDefault().getCountry();
    }

}
