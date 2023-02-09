package com.example.model;

import com.example.DAO.UserDoaImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
/** User Class used for creation/updating/deleting User objects from the DB*/
public class User {
    private int userID;
    private String userName;
    private String password;
    private String createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int userRole;

    /**User Constructor.
     * Based on the stored DB values.
     * @param userID
     * @param userName
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public User(int userID, String userName, String password, String createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int userRole) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.userRole = userRole;
    }

    public User(String userName, String password, String createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int userRole) {
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.userRole = userRole;
    }

    public int getUserRole() {
        return userRole;
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


    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

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

    public static void addUser(User user) {
        try {
            UserDoaImpl.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUser(User user) {
        try {
            UserDoaImpl.updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Verify User Login.
     * Method used to verify the user credientials upon login with the saved valued from the DB
     * @param userName - username typed into the login form
     * @param password - password typed into the login form
     * @return - returns the User object if verified, returns null if not verified.
     */
    public static User verifyUser(String userName, String password)  {

        try {
            User verifiedUser = UserDoaImpl.verifyUser(userName, password);
            if (!verifiedUser.equals(null)) {
                return verifiedUser;
            }
        } catch (NullPointerException | SQLException e) {

        }
        return null;
    }

    /**
     * Get All User Objects
     */
    public static ObservableList<User> getAllUsers() {
        try {
            return UserDoaImpl.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns all Associate Usernames based off of role assigned.
     * @return
     */
    public static ObservableList<String> getAllAssociateStrings() {
        ObservableList<String> allAssociateUserNames = FXCollections.observableArrayList();
        try {
            for (User user : UserDoaImpl.getAssociates()) {
                allAssociateUserNames.add(user.getUserName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return allAssociateUserNames;
    }

    /** Gets User based on username String.
     * Takes a string of username, and then queries the DB to find the user that matches this username
     * @param username - string of the username to check
     * @return - returns the user object, if found
     */
    public static User getUser(String username) {
        try {
            return UserDoaImpl.getUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /** Gets User based on userID int.
     * Takes an int of the userID, and then queries the DB to find the user that matches this userID.
     * @param userID - int of the userID to check
     * @return - returns the user object, if found
     */
    public static User getUser(int userID) {
        try {
            return UserDoaImpl.getUser(userID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
