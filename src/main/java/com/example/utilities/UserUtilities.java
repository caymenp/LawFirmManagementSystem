package com.example.utilities;

import java.time.ZoneId;

/**
 * Get Current Users UserUtilities (TimeZone, language, etc.).
 */
public class UserUtilities {
    /**Gets Current User's TimeZone
     *
     * @return System Default TimeZone Name as String
     */
    public String getTimeZone() {
        return String.valueOf(ZoneId.systemDefault());
    }

}
