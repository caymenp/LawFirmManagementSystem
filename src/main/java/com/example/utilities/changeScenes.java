package com.example.utilities;

import com.example.controller.AppointmentsOverviewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class changeScenes {

    public void changeScene(ActionEvent actionEvent, String sceneURL) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneURL));
        loader.load();
        AppointmentsOverviewController aoc = loader.getController();
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    };

}
