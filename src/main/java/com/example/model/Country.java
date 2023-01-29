package com.example.model;

import com.example.DAO.CountryDoeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
/** Country Class used for getting country objects from the DB*/

public class Country {
    private int countryID;
    private String countryName;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public Country(int countryID, String countryName, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    //Getters
    public int getCountryID() {
        return countryID;
    }

    public static int getCountryID(String cName) {
        ObservableList<Country> allCountryObjects = FXCollections.observableArrayList();
        allCountryObjects.addAll(allCountryDB());

        for (Country c : allCountryObjects) {
            if (c.countryName.equals(cName)) {
                return c.getCountryID();
            }
        }
        return -1;
    }

    public String getCountryName() {
        return countryName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    //Setters

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public static Country getCountry(String id) throws SQLException {
        Country returnedCountry = CountryDoeImpl.getCountry(id);
        return returnedCountry;
    }

    public static ObservableList<Country> allCountryDB() {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();

        try {
            allCountries.addAll(CountryDoeImpl.getAllCountries());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCountries;
    }

    public static ObservableList<String> getAllCountries() {
        ObservableList<String> allCountriesName = FXCollections.observableArrayList();
        ObservableList<Country> allCountryObjects = allCountryDB();
        assert allCountryObjects != null;
        for (Country str : allCountryObjects) {
            allCountriesName.add(str.getCountryName());
        }
        return allCountriesName;
    }



}
