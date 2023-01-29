package com.example.DAO;

import com.example.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
/** Division DOE used for creation/updating/deleting division objects from the DB*/

public class DivisionDaoImpl {

    public static Division getDivision(String id) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions WHERE division_id = '"+id+"'";
        Query.makeQuery(sqlStatement);

        Division division;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int divisionID = result.getInt("division_id");
            String divisionName = result.getString("division");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updatedBy = result.getString("last_updated_by");
            int countryID = result.getInt("country_id");

            division = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, updatedBy, countryID);
            return division;
        }
        return null;
    }

    public static ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> allDivisions= FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.first_level_divisions";
        Query.makeQuery(sqlStatement);

        Division division;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int divisionID = result.getInt("division_id");
            String divisionName = result.getString("division");
            Timestamp createDate = result.getTimestamp("create_date");
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String updatedBy = result.getString("last_updated_by");
            int countryID = result.getInt("country_id");

            division = new Division(divisionID, divisionName, createDate, createdBy, lastUpdate, updatedBy, countryID);
            allDivisions.add(division);
        }
        return allDivisions;
    }

}
