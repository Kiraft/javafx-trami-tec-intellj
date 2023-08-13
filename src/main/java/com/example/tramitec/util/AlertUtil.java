package com.example.tramitec.util;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
    public static void showAlert(AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
