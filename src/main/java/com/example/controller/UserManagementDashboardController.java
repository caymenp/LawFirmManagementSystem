package com.example.controller;

import com.example.model.User;
import com.example.utilities.AlertMessages;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementDashboardController implements Initializable {
    public TableView<User> userTable;
    public TableColumn userIDcol;
    public TableColumn userPermissionsCol;
    public TableColumn usernameCol;
    public TableColumn userCreatedCol;
    public TableColumn userCreatedByCol;
    public TableColumn userLastModifiedCol;
    public TableColumn lastModifiedDateCol;
    public Button closeBTN;
    public Button modifyUser;
    public Button addNewUser;


    User loggedInUser;
    public void getData(User currentUser) {
        loggedInUser = currentUser;
    }

    public void generateUserTable() {
        ObservableList<User> allUsers = User.getAllUsers();

        userIDcol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        userPermissionsCol.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userCreatedCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        userCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        userLastModifiedCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastModifiedDateCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));

        userTable.setItems(allUsers);
    }

    public void closeBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void modifyUser(ActionEvent actionEvent) throws IOException {
        if (userTable.getSelectionModel().isEmpty()) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.errorMessage("No User Selected", "Please select a user on the table to modify");
            return;
        }

        User selectedUser = userTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/appointmentmanager/UserForm-view.fxml"));

        loader.load();


        UserFormController ufc = loader.getController();
        ufc.getData(selectedUser, loggedInUser);
        ufc.formLabel.setText("Modify User");

        //New Window(Stage)
        Stage reportWindow = new Stage();
        Parent formScene = loader.getRoot();
        reportWindow.setTitle("User Management Dashboard");
        reportWindow.setScene(new Scene(formScene));

        //Modality For New Window
        reportWindow.initModality(Modality.WINDOW_MODAL);

        //Owner stage
        Stage mainStage = (Stage) userTable.getScene().getWindow();
        reportWindow.initOwner(mainStage);

        //Set Position Of New Window
        reportWindow.setX(mainStage.getX()+20);
        reportWindow.setY(mainStage.getY()+20);

        reportWindow.showAndWait();
        generateUserTable();
    }

    public void addNewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/appointmentmanager/UserForm-view.fxml"));

        loader.load();


        UserFormController ufc = loader.getController();
        ufc.getData(loggedInUser);

        //New Window(Stage)
        Stage reportWindow = new Stage();
        Parent formScene = loader.getRoot();
        reportWindow.setTitle("User Management Dashboard");
        reportWindow.setScene(new Scene(formScene));

        //Modality For New Window
        reportWindow.initModality(Modality.WINDOW_MODAL);

        //Owner stage
        Stage mainStage = (Stage) userTable.getScene().getWindow();
        reportWindow.initOwner(mainStage);

        //Set Position Of New Window
        reportWindow.setX(mainStage.getX()+20);
        reportWindow.setY(mainStage.getY()+20);

        reportWindow.showAndWait();
        generateUserTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateUserTable();
    }
}
