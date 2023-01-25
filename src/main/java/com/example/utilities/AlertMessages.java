package com.example.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ResourceBundle;

public class AlertMessages {

    public ButtonType errorMessageUsername(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("alertErrorTitleUserName"));
        alert.setHeaderText("");
        alert.setContentText(rb.getString("alertErrorDescUserName"));
        alert.showAndWait();
        return alert.getResult();
    }

    public ButtonType errorMessagePassword(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("alertErrorTitlePassword"));
        alert.setHeaderText("");
        alert.setContentText(rb.getString("alertErrorDescPassword"));
        alert.showAndWait();
        return alert.getResult();
    }

    public ButtonType errorMessageVerify(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("alertErrorTitleVerify"));
        alert.setHeaderText("");
        alert.setContentText(rb.getString("alertErrorDescVerify"));
        alert.showAndWait();
        return alert.getResult();
    }

    public ButtonType errorMessage(String alertTitle, String alertDescription) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setHeaderText("");
        alert.setContentText(alertDescription);
        alert.showAndWait();
        return alert.getResult();
    }


    public String confirmationMessage(String alertTitle, String alertDescription) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText("");
        alert.setContentText(alertDescription);
        alert.showAndWait();
        return alert.getResult().getText();
    };

    public void informationMessageLang(ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(rb.getString("alertInfoTitle"));
        alert.setHeaderText("");
        alert.setContentText(rb.getString("alertInfoDesc"));
        alert.showAndWait();
    };

    public void informationMessage(String alertTitle, String alertDescription) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText("");
        alert.setContentText(alertDescription);
        alert.showAndWait();
    };
}
