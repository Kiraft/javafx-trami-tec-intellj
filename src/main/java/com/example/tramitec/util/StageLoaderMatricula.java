package com.example.tramitec.util;
import com.example.tramitec.App;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

import com.example.tramitec.controllers.ArchivoController;
import com.example.tramitec.controllers.LandingPageController;
import com.example.tramitec.controllers.MainController;
import com.example.tramitec.controllers.StatusController;
import com.example.tramitec.model.Alumno;

public class StageLoaderMatricula {

    public static void load(String url, Event event, Alumno alumno) throws IOException {
        Object eventSource = event.getSource();
        Node sourceAsNode = (Node) eventSource;
        Scene oldScene = sourceAsNode.getScene();
        Window window = oldScene.getWindow();
        Stage stage = (Stage) window;
        stage.hide();

        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        if (alumno != null) {
            Object controller = loader.getController();

            if (controller instanceof LandingPageController) {
                LandingPageController landingPageController = (LandingPageController) controller;
                landingPageController.setAlumno(alumno);
            } else if (controller instanceof MainController) {
                MainController archivoController = (MainController) controller;
                archivoController.setAlumno(alumno);
            } else if (controller instanceof StatusController) {
                StatusController statusController = (StatusController) controller;
                statusController.setAlumno(alumno);
            } else if (controller instanceof ArchivoController) {
                ArchivoController archivoController = (ArchivoController) controller;
                archivoController.setAlumno(alumno);
            }
        }

        stage.show();
    }
}
