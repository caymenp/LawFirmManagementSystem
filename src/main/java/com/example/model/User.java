package com.example.model;

import com.example.DAO.UserDoaImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;

    public User(int userID, String userName, String password, String createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCreateDate() {
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

    /////Setters


    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreateDate(String createDate) {
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

    public static User verifyUser(String userName, String password) throws SQLException {

        try {
            User verifiedUser = UserDoaImpl.verifyUser(userName, password);
            if (!verifiedUser.equals(null)) {
                return verifiedUser;
            }
        } catch (NullPointerException e) {

        }
        return null;
    }

    /**
     * LAMBDA - List of String Customer Names
     * @return returns ObservableList of Strings to display customer names.
     */

    public static ObservableList<String> getAllUserStrings() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        ObservableList<String> allUserStrings = FXCollections.observableArrayList();

        try {
            allUsers.addAll(UserDoaImpl.getAllUsers());
            /**
             * LAMBDA - Adds Usernames to list in String form to display on the GUI.
             */
            allUsers.forEach((u) -> {allUserStrings.add(u.getUserName());});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allUserStrings;
    }

    public static User getUser(String username) {
        try {
            return UserDoaImpl.getUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(int userID) {
        try {
            return UserDoaImpl.getUser(userID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
