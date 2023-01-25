package com.example.controller;

import com.example.DAO.AppointmentDoeImpl;
import com.example.model.Appointment;
import com.example.model.User;
import com.example.utilities.UserUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Appointments Overview Controller.
 */
public class AppointmentsOverviewController implements Initializable {

    public Text currentUser;
    public Button logout;
    public Text localTimeZone;
    public Button reports;
    public Button addAppointment;
    public Button modifyAppointment;
    public Button deleteAppointment;
    public Button addCustomer;
    public Button modifyCustomer;
    public Button deleteCustomer;
    public TableView appointmentTable;
    public RadioButton viewWeekRadio;
    public ToggleGroup mainCalendar;
    public RadioButton viewMonthRadio;
    public RadioButton viewAllRadio;
    public DatePicker dateSelector;
    public Button viewCX;
    @FXML
    private TableView<Appointment> customerTable;
    @FXML
    private TableColumn<?, ?> colAppID;
    @FXML
    private TableColumn<?, ?> colAptTitle;
    @FXML
    private TableColumn<?, ?> colAptDesc;
    @FXML
    private TableColumn<?, ?> colAptLocation;
    @FXML
    private TableColumn<?, ?> colAptContact;
    @FXML
    private TableColumn<?, ?> colAptType;
    @FXML
    private TableColumn<?, ?> colAptStart;
    @FXML
    private TableColumn<?, ?> colAptEnd;
    @FXML
    private TableColumn<?, ?> colAptCXid;
    @FXML
    private TableColumn<?, ?> colAptUserID;


    // Initializing User Data
    UserUtilities userUtilities = new UserUtilities();
    //Current Logged In User
    private User loggedInUser;

    public void getActiveUser(User activeUser) {
        loggedInUser = activeUser;
    }


    public void viewCX(ActionEvent actionEvent) {
    }

    public void dateSelector(ActionEvent actionEvent) {
    }

    public void viewAllRadio(ActionEvent actionEvent) {
    }

    public void viewMonthRadio(ActionEvent actionEvent) {
    }

    public void viewWeekRadio(ActionEvent actionEvent) {
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void modifyCustomer(ActionEvent actionEvent) {
    }

    public void addCustomer(ActionEvent actionEvent) {
    }

    public void deleteAppointment(ActionEvent actionEvent) {
    }

    public void modifyAppointment(ActionEvent actionEvent) {
    }

    public void addAppointment(ActionEvent actionEvent) {
    }

    public void reports(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) {
    }

    ObservableList<Appointment> appointmentsView = FXCollections.observableArrayList();
    boolean viewAll = false;
    boolean viewWeek = false;
    boolean viewMonth = false;
    public void appointmentTableView() {
        colAppID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        colAptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAptDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAptContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        colAptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAptStart.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        colAptEnd.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        colAptCXid.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colAptUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        try {
            if (viewAll) {
                appointmentsView.removeAll();
                appointmentsView.addAll(AppointmentDoeImpl.getAllAppointments());
            } else if (viewWeek) {
                appointmentsView.removeAll();
                appointmentsView.addAll(AppointmentDoeImpl.getFilteredAppointments());
            } else if (viewMonth) {
                appointmentsView.removeAll();

            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
        appointmentTable.setItems(appointmentsView);
    }

    public void aptCalFilter(ActionEvent actionEvent) {
        viewAll = mainCalendar.getSelectedToggle().equals(viewAllRadio);
        viewWeek = mainCalendar.getSelectedToggle().equals(viewWeekRadio);
        viewMonth = mainCalendar.getSelectedToggle().equals(viewMonthRadio);
        appointmentTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localTimeZone.setText(userUtilities.getTimeZone());
        viewAll = true;
        appointmentTableView();


    }

}
