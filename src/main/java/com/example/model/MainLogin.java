package com.example.model;

import com.example.helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main Login Model.
 */
public class MainLogin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainLogin.class.getResource("/com/example/appointmentmanager/MainLogin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Appointment Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        JDBC.openConnection();

        launch();

        JDBC.closeConnection();

    }
}