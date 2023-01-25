package com.example.model;

import com.example.DAO.JDBC;
import com.example.utilities.UserUtilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Main Login Model.
 */
public class MainLogin extends Application {
    static UserUtilities userInfo = new UserUtilities();

    public static String mainLabel;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle rb = ResourceBundle.getBundle("com.example.appointmentmanager.localization.mainLoginUI", Locale.getDefault());
        Parent root = fxmlLoader.load(getClass().getResource("/com/example/appointmentmanager/MainLogin-view.fxml"));

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(rb.getString("mainLabel"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("com.example.appointmentmanager.localization.mainLoginUI", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("fr")) {

            }
        } catch (MissingResourceException e) {

        }

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }
}
