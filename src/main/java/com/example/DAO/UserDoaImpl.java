package com.example.DAO;

import com.example.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
/** User DOE used for creation/updating/deleting user objects from the DB*/

public class UserDoaImpl {
    public int currentUserID;

    /**
     * Returns a user record from the DB that matches the passed userName.
     * @param userName
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static User getUser(String userName) throws SQLException, Exception{
        String sqlStatement = "SELECT * FROM client_schedule.users WHERE user_name = '"+userName+"'";
        Query.makeQuery(sqlStatement);

        User userResult;
        ResultSet result = Query.getResult();
        while(result.next()) {
            int userID = result.getInt("user_id");
            String userNameR = result.getString("user_name");
            String password = result.getString("password");
            String createDate = result.getDate("create_date").toString();
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            return userResult;
        }
        return null;
    }

    /**
     * Returns a user record from the DB that matches the passed userID.
     * @param userid
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static User getUser(int userid) throws SQLException, Exception{
        String sqlStatement = "SELECT * FROM client_schedule.users WHERE user_id = '"+userid+"'";
        Query.makeQuery(sqlStatement);

        User userResult;
        ResultSet result = Query.getResult();
        while(result.next()) {
            int userID = result.getInt("user_id");
            String userNameR = result.getString("user_name");
            String password = result.getString("password");
            String createDate = result.getDate("create_date").toString();
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            return userResult;
        }
        return null;
    }

    /**
     * Returns a list of ALL user records in the DB.
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.users";
        Query.makeQuery(sqlStatement);

        User userResult;
        ResultSet result = Query.getResult();
        while(result.next()) {
            int userID = result.getInt("user_id");
            String userNameR = result.getString("user_name");
            String password = result.getString("password");
            String createDate = result.getDate("create_date").toString();
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            allUsers.add(userResult);
        }
        return allUsers;
    }

    /**
     * Used to verify the user login with the DB, matching a user record from the passed params.
     * @param usernamePassed
     * @param passwordPassed
     * @return
     * @throws SQLException
     */
    public static User verifyUser(String usernamePassed, String passwordPassed) throws SQLException {
        String sqlStatement = "SELECT * FROM client_schedule.users WHERE user_name = '"+usernamePassed+"' AND password = '"+passwordPassed+"'";
        Query.makeQuery(sqlStatement);

        User userResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int userID = result.getInt("user_id");
            String userNameR = result.getString("user_name");
            String password = result.getString("password");
            String createDate = result.getDate("create_date").toString();
            String createdBy = result.getString("created_by");
            Timestamp lastUpdate = result.getTimestamp("last_update");
            String lastUpdatedBy = result.getString("last_updated_by");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
            return userResult;
        }
        return null;
    }
}
