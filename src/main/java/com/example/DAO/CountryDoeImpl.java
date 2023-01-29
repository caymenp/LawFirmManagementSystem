package com.example.DAO;

import com.example.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CountryDoeImpl {

    public static Country getCountry(String id) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.countries WHERE country_id = '"+id+"'";
        Query.makeQuery(sqlStatement);

        Country country;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int countryID = result.getInt("country_id");
            String countryName = result.getString("country");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updatedBy = result.getString("last_updated_by");

            country = new Country(countryID, countryName, createDate, createdBy, lastUpdate, updatedBy);
            return country;
        }
        return null;
    }

    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.countries";
        Query.makeQuery(sqlStatement);

        Country country;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int countryID = result.getInt("country_id");
            String countryName = result.getString("country");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updatedBy = result.getString("last_updated_by");

            country = new Country(countryID, countryName, createDate, createdBy, lastUpdate, updatedBy);
            allCountries.add(country);
        }
        return allCountries;
    }
}
