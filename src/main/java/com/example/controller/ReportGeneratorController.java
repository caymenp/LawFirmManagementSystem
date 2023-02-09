package com.example.controller;

import com.example.model.User;
import com.example.utilities.AlertMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportGeneratorController implements Initializable {
    public Button closeBTN;
    public ComboBox<String> associateList;
    public Button generateReport;

    public void closeBTN(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public String selectedAssociate;
    public void associateList(ActionEvent actionEvent) {
        selectedAssociate = associateList.getValue();
    }

    public void generateReport(ActionEvent actionEvent) throws IOException {
        if (associateList.getValue() == null) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.errorMessage("No Associate Selected",
                    "You must select an Associate from the list before generating a report");
            return;
        }

        changeScreens();
    }

    public void changeScreens() throws IOException {
        User selectedReport = User.getUser(selectedAssociate);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/appointmentmanager/Reports-view.fxml"));

        loader.load();


        ReportController rc = loader.getController();
        rc.getAssociate(selectedReport);


        //New Window(Stage)
        Stage reportWindow = new Stage();
        Parent formScene = loader.getRoot();
        reportWindow.setTitle("Generated Associate Report");
        reportWindow.setScene(new Scene(formScene));

        //Modality For New Window
        reportWindow.initModality(Modality.WINDOW_MODAL);

        //Owner stage
        Stage mainStage = (Stage)associateList.getScene().getWindow();
        reportWindow.initOwner(mainStage);

        //Set Position Of New Window
        reportWindow.setX(mainStage.getX()+20);
        reportWindow.setY(mainStage.getY()+20);

        reportWindow.showAndWait();
    }

    private void initializeAssociateList() {
        associateList.setItems(User.getAllAssociateStrings());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeAssociateList();

    }
}
