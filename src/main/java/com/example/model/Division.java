package com.example.model;

import com.example.DAO.DivisionDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
/** Division Class used for creation/updating/deleting Division objects from the DB*/

public class Division{

    private int divisionID;
    private String divisionName;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String updatedBy;
    private int countryID;

    //Constructor

    public Division(int divisionID, String divisionName, Timestamp createDate, String createdBy, Timestamp lastUpdate,
                    String updatedBy, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.updatedBy = updatedBy;
        this.countryID = countryID;
    }

    //Getters

    public int getDivisionID() {
        return divisionID;
    }

    public static int getDivisionID(String diviName) {
        ObservableList<Division> allDivi = FXCollections.observableArrayList();
        allDivi.addAll(allDivisionsDB());

        for (Division div : allDivi) {
            if (div.getDivisionName().equals(diviName)) {
                return div.getDivisionID();
            }
        }
        return -1;
    }

    public String getDivisionName() {
        return divisionName;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public int getCountryID() {
        return countryID;
    }

    //Setter

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public static Division getDivision(String dvID) {
        Division division;
        try {
           division = DivisionDaoImpl.getDivision(dvID);
            return division;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Division> allDivisionsDB() {
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();

        try {
            allDivisions.addAll(DivisionDaoImpl.getAllDivisions());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allDivisions;
    }

    public static ObservableList<String> getAllDivisionNamesStrings(int countryID) {
        ObservableList<String> allDivisionNames = FXCollections.observableArrayList();
        ObservableList<Division> allDivisionObjects = allDivisionsDB();
        assert allDivisionObjects != null;
        for (Division dv : allDivisionObjects) {
            if (dv.getCountryID() == countryID)
            allDivisionNames.add(dv.getDivisionName());
        }
        return allDivisionNames;
    }


    // Custom Methods

}
