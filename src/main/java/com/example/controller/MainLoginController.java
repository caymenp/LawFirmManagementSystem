package com.example.controller;

import com.example.helper.changeScenes;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main Login Controller.
 */
public class MainLoginController implements Initializable {
    public TextField loginUsername;
    public TextField loginPassword;
    public Button loginBTN;
    public MenuItem english;
    public MenuItem french;
    public Text timeZone;
    public Hyperlink forgotPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginBTN(ActionEvent actionEvent) throws IOException {
        changeScenes changeScenes = new changeScenes();
        changeScenes.changeScene(actionEvent, "/com/example/appointmentmanager/AppointmentsOverview-view.fxml");
    }

    public void english(ActionEvent actionEvent) {
    }

    public void french(ActionEvent actionEvent) {
    }

    public void forgotPassword(ActionEvent actionEvent) {
    }
}