package com.example.controller;

import com.example.model.User;
import com.example.utilities.AlertMessages;
import com.example.utilities.DateTimeConversion;
import com.example.utilities.UserUtilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/** Controller for Main Login - Handles all login details/validation/verification */
public class MainLoginController implements Initializable {
    public TextField loginUsername;
    public PasswordField loginPassword;
    public Button loginBTN;
    public Text timeZone;
    public  Label subLabel;
    public Label timeZoneLabel;
    public Label mainLabel;

    //Getting instance of userutilities to access user info.
    UserUtilities userInfo = new UserUtilities();
    User activeUser;
    ResourceBundle rb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = ResourceBundle.getBundle("com.example.appointmentmanager.localization.mainLoginUI", Locale.getDefault());
        applyLanguage(rb);
        /**
         * TimeZone Dynamic Text-UserUtilities
         */
        timeZone.setText(userInfo.getTimeZone());



    }

    /**
     * UserUtilities Dynamic Text
     */

    public void loginBTN(ActionEvent actionEvent) throws IOException, SQLException {
        //Checks If UserName field is empty
        if (loginUsername.getText().isEmpty()) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.errorMessageUsername(rb);
            return;
        }
        //Checks If Password Field Is Empty
        if (loginPassword.getText().isEmpty()) {
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.errorMessagePassword(rb);
            return;
        }
        //Assigning user input strings to local variables.
        String userName = loginUsername.getText();
        String userPass = loginPassword.getText();

        //Checking userName+Pass against DB users. Rest of method invocations are based on this flag 'loginVerified'.
        try{
            activeUser = User.verifyUser(userName, userPass);
            //If Login Credentials are Valid
            if (!activeUser.equals(null)) {
                AlertMessages alertMessages = new AlertMessages();
                alertMessages.informationMessageLang(rb);

                passActiveUser(actionEvent);
                loginActivity(true);
                return;
            }
        } catch (NullPointerException e) {

        }
        // If login credentials are invalid. Sign in refused, error alert window.
        AlertMessages alertMessages = new AlertMessages();
        alertMessages.errorMessageVerify(rb);
        loginActivity(false);
    }

    public void passActiveUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/appointmentmanager/AppointmentsOverview-view.fxml"));
        loader.load();
        AppointmentsOverviewController aoc = loader.getController();
        aoc.getActiveUser(activeUser);
        aoc.currentUser.setText("Welcome, " + activeUser.getUserName());
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void applyLanguage(ResourceBundle rb) {
        mainLabel.setText(rb.getString("mainLabel"));
        subLabel.setText(rb.getString("subLabel"));
        loginUsername.setPromptText(rb.getString("loginUsername"));
        loginPassword.setPromptText(rb.getString("loginPassword"));
        loginBTN.setText(rb.getString("loginBTN"));
        timeZoneLabel.setText(rb.getString("timeZoneLabel"));
    }

    public void loginActivity(boolean status) throws IOException {

        FileWriter fwVariable = new FileWriter("login_activity", true);
        PrintWriter pwVariable = new PrintWriter(fwVariable);

        if (status) {
            pwVariable.println("User " + activeUser.getUserName() + " successfully logged in at " + DateTimeConversion.saveToDB(Timestamp.valueOf(LocalDateTime.now())) + " UTC");
            pwVariable.close();
            fwVariable.close();
            return;
        }

        pwVariable.println("User " + loginUsername.getText() + " gave invalid login at " + DateTimeConversion.saveToDB(Timestamp.valueOf(LocalDateTime.now())) + " UTC");
        pwVariable.close();
        fwVariable.close();

    }

}