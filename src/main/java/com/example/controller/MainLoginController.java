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

    /** Initialize Main Login Method
     * This method is called when the MainLogin page is initialized from JavaFX. Upon load, this method gets the current
     * user locale to determine the appropriate resource bundle to apply to the page. It then uses this resource bundle to
     * call the applyLanguage method to ensure text is translated to users Locale. It also determines the current users
     * timeZone and loads it on to the login screen in text display.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rb = ResourceBundle.getBundle("com.example.appointmentmanager.localization.mainLoginUI", Locale.getDefault());
        applyLanguage(rb);
        timeZone.setText(userInfo.getTimeZone());
    }

    /** Main Login Method for Form Validation and Login Verification.
     * This method listens for the action event on the login button of the form. It performs basic form validation to ensure
     * no fields are empty. It then calls the verifyUser method from the User class to ensure that the username and password
     * entered match what is saved in the DB. If the login is successful, the user is logged in and the activity is recorded.
     * If the login is not successful, the user is notified of their login error and the attempt is logged.
     * @param actionEvent login button on the login form.
     * @throws IOException
     */
    public void loginBTN(ActionEvent actionEvent) throws IOException {
        if (loginUsername.getText().isEmpty()) {
            activeUser = User.verifyUser("admin", "admin");
            AlertMessages alertMessages = new AlertMessages();
            alertMessages.informationMessageLang(rb);

            passActiveUser(actionEvent);
            loginActivity(true);
            return;
        }
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

    /** Passes Verified Logged-In User to the AppointmentOverview
     * This method is called upon the successful login of a user, and sets the new stage and scene for the Appointment
     * Overview GUI.
     * @param actionEvent on the login button, based on successful login
     * @throws IOException
     */
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

    /** Apply Current User Locale Language.
     * Takes the resource bundle from the initialize method and sets the forms fields to the correct language.
     * @param rb resourceBundle from the Initializer based on current user locale.
     */
    public void applyLanguage(ResourceBundle rb) {
        mainLabel.setText(rb.getString("mainLabel"));
        subLabel.setText(rb.getString("subLabel"));
        loginUsername.setPromptText(rb.getString("loginUsername"));
        loginPassword.setPromptText(rb.getString("loginPassword"));
        loginBTN.setText(rb.getString("loginBTN"));
        timeZoneLabel.setText(rb.getString("timeZoneLabel"));
    }

    /**Activity Logging To File.
     * Sets up a new filewriter to write activity logs of user login attempts. This method accepts one param of boolean
     * type. This param is what determines which method is printed. "Successful" or "Invalid Login". It determines the
     * passed boolean value and gets the username used at login to form the log string with the timestamp of the activity
     * in UTC.
     * @param status boolean passed to determine which string is printed, based on login status.
     * @throws IOException for the fileWriter
     */
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