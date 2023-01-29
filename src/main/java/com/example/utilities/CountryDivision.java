package com.example.utilities;

import com.example.DAO.CustomerDoeImpl;
import com.example.model.Country;
import com.example.model.Customer;
import com.example.model.Division;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CountryDivision {

    public static String getCountryName(int dvID) {
        Division passedDivision = Division.getDivision(String.valueOf(dvID));
        int countryID = passedDivision.getCountryID();
        try {
            Country getCountry = Country.getCountry(String.valueOf(countryID));
            return getCountry.getCountryName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getDivisionName(int dvID) {
        Division passedDivision = Division.getDivision(String.valueOf(dvID));

        return passedDivision.getDivisionName();

    }

    public static void addCustomer(Customer newCX) {
        Timestamp currentTimeStamp = Timestamp.valueOf(LocalDateTime.now());

        CustomerDoeImpl.addCustomer(newCX);
        System.out.println("Made it to the CountryDivision");
    }

}
