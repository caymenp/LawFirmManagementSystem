package com.example.model;

import com.example.DAO.JDBC;
import com.example.utilities.UserUtilities;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Main Login Model.
 */
public class MainLogin extends Application {
    static UserUtilities userInfo = new UserUtilities();

    public static String mainLabel;

    /** Start Method
     * Gets the resource bundle to apply to the program at laod.
     * @param stage
     * @throws IOException
     */
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

    /** Start Main Method
     * Determines the resource bundle to be used by the Start method above. Then, launches the JBDC controller and then the
     * application program.
     * @param args
     */
    public static void main(String[] args) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("com.example.appointmentmanager.localization.mainLoginUI", Locale.getDefault());

        } catch (MissingResourceException e) {

        }

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }
}
