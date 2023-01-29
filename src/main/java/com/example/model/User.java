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

    /**LAMBDA - Gets an Observable List of Usersnames as Strings.
     * Gets all the users from the DB. The lambda expression is then performed within the forEach method of this list,
     * to get the username from each user object within the list and add that string to the allUserStrings list.
     * @return ObservableList of Strings to display customer names.
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
