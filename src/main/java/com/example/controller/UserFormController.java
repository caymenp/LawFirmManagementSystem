package com.example.controller;

import com.example.model.User;
import com.example.utilities.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    public TextField userIDfield;
    public TextField userNameField;
    public TextField password1field;
    public TextField password2Field;
    public ComboBox permissionLevel;
    public Button cancelBTN;
    public Button saveBTN;
    public Label formLabel;


    AlertMessages alertMessages = new AlertMessages();
    User passedUser;
    User loggedInUser;
    int permissionLevelSelected;

    public void permissionLevel(ActionEvent actionEvent) {
        switch (permissionLevel.getValue().toString()) {
            case "2 : Administrative" -> permissionLevelSelected = 2;
            case "3 : Partner" -> permissionLevelSelected = 3;
            default -> permissionLevelSelected = 1;
        }
    }

    public void getData(User modifyUser, User currentUser) {
        passedUser = modifyUser;
        loggedInUser = currentUser;

        userIDfield.setText(String.valueOf(passedUser.getUserID()));
        userNameField.setText(passedUser.getUserName());
        password1field.setText(passedUser.getPassword());
        password2Field.setText(passedUser.getPassword());
        permissionLevel.setValue(passedUser.getUserRole());
        permissionLevelSelected = passedUser.getUserRole();
    }

    public void getData(User currentUser) {
        loggedInUser = currentUser;
    }

    public void cancelBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveBTN(ActionEvent actionEvent) {
        String userName = userNameField.getText();
        String password = password1field.getText();
        String createDate = Timestamp.valueOf(LocalDateTime.now()).toString();
        String createdBy = loggedInUser.getUserName();
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = loggedInUser.getUserName();
        int userRole = permissionLevelSelected;

        if (formLabel.getText().equals("Modify User")) {
            createdBy = passedUser.getCreatedBy();
            createDate = passedUser.getCreateDate();
        }

        if (checkPassword() && checkUserNames()) {
            if (formLabel.getText().equals("Modify User")) {
                int userID = passedUser.getUserID();
                User user = new User(userID, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
                User.updateUser(user);
            } else {
                User user = new User(userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy, userRole);
                User.addUser(user);
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    public boolean checkUserNames() {
        String username = userNameField.getText();

        if (username.isEmpty()) {
            alertMessages.errorMessage("Username Error", "Username field is empty.\n" +
                    "Retry inputting your username.");
            return false;
        }

        for (User user : User.getAllUsers()) {
            if (String.valueOf(user.getUserID()).equals(userIDfield.getText())) continue;
            if (user.getUserName().equals(username)) {
                alertMessages.errorMessage("Username Error", "Username already exists.\n" +
                        "Usernames must be unique. Please try again.");
                return false;
            }
        }

        return true;
    }

    public boolean checkPassword() {
        String pass1 = password1field.getText();
        String pass2 = password2Field.getText();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            alertMessages.errorMessage("Password Error", "One or both password fields are empty.\n" +
                    "Retry inputting your passwords.");
            password1field.setText("");
            password2Field.setText("");
            return false;
        }

        if (pass1.equals(pass2)) return true;



        alertMessages.errorMessage("Password Error", "Passwords do not match.\n" +
                "Retry inputting your passwords.");
        password1field.setText("");
        password2Field.setText("");
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> permissionLevels = FXCollections.observableArrayList();
        permissionLevels.add("1 : Associate");
        permissionLevels.add("2 : Administrative");
        permissionLevels.add("3 : Partner");
        permissionLevel.setItems(permissionLevels);

    }
}
