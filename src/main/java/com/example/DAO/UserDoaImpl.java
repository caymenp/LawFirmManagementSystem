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
            int userRole = result.getInt("user_role");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
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
            int userRole = result.getInt("user_role");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
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
            int userRole = result.getInt("user_role");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
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
            int userRole = result.getInt("user_role");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
            return userResult;
        }
        return null;
    }

    public static ObservableList<User> getAssociates() throws SQLException {
        ObservableList<User> allAssociates = FXCollections.observableArrayList();
        String sqlStatement = "SELECT * FROM client_schedule.users WHERE user_role = 1";
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
            int userRole = result.getInt("user_role");

            userResult = new User(userID, userNameR, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
            allAssociates.add(userResult);
        }
        return allAssociates;
    }

    /**
     * Adds new users to DB using the passed user object.
     * @param user
     */
    public static void addUser(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        String createdDate = user.getCreateDate();
        String createdBy = user.getCreatedBy();
        Timestamp lastUpdate = user.getLastUpdate();
        String lastUpdatedBy = user.getLastUpdatedBy();
        int userRole = user.getUserRole();

        String sqlStatement = "INSERT INTO client_schedule.users (user_name, password, create_date, created_by, last_update, last_updated_by, user_role)" +
                "VALUES ('"+userName+"', '"+password+"', '"+createdDate+"', '"+createdBy+"', '"+lastUpdate+"', '"+lastUpdatedBy+"', '"+userRole+"')";

        Query.makeQuery(sqlStatement);

    }

    /**
     * Updates user record that matches the passed user object
     * @param user
     */
    public static void updateUser(User user) {

        int userID = user.getUserID();
        String userName = user.getUserName();
        String password = user.getPassword();
        String createdDate = user.getCreateDate();
        String createdBy = user.getCreatedBy();
        Timestamp lastUpdate = user.getLastUpdate();
        String lastUpdatedBy = user.getLastUpdatedBy();
        int userRole = user.getUserRole();

        String sqlStatement = "UPDATE client_schedule.users " +
                "SET user_name = '"+userName+"', password = '"+password+"', create_date = '"+createdDate+"', " +
                "created_by = '"+createdBy+"', last_update = '"+lastUpdate+"', last_updated_by = '"+lastUpdatedBy+"', user_role = '"+userRole+"'" +
                " WHERE user_id = '"+userID+"'";

        Query.makeQuery(sqlStatement);

    }
}
