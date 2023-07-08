package com.example.tramitec.controllers.util;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

import com.example.tramitec.controllers.controllerArchivo;
import com.example.tramitec.controllers.controllerLandingPage;
import com.example.tramitec.controllers.controllerStatus;

public class StageLoaderMatricula {

    public static void load(String url, Event event, MatriculaModel matriculaModel) throws IOException {
        Object eventSource = event.getSource();
        Node sourceAsNode = (Node) eventSource;
        Scene oldScene = sourceAsNode.getScene();
        Window window = oldScene.getWindow();
        Stage stage = (Stage) window;
        stage.hide();

        FXMLLoader loader = new FXMLLoader(StageLoaderMatricula.class.getResource(url));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);

        if (matriculaModel != null) {
            Object controller = loader.getController();

            if (controller instanceof controllerLandingPage) {
                controllerLandingPage landingPageController = (controllerLandingPage) controller;
                landingPageController.setMatriculaModel(matriculaModel);
            } else if (controller instanceof controllerStatus) {
                controllerStatus statusController = (controllerStatus) controller;
                statusController.setMatriculaModel(matriculaModel);
            } else if (controller instanceof controllerArchivo) {
                controllerArchivo archivoController = (controllerArchivo) controller;
                archivoController.setMatriculaModel(matriculaModel);
            }
        }

        stage.show();
    }
}

// public class StageLoaderMatricula {

//     public static void load(String url, Event event, MatriculaModel matriculaModel) throws IOException {
//         Object eventSource = event.getSource();
//         Node sourceAsNode = (Node) eventSource;
//         Scene oldScene = sourceAsNode.getScene();
//         Window window = oldScene.getWindow();
//         Stage stage = (Stage) window;
//         stage.hide();

//         FXMLLoader loader = new FXMLLoader(StageLoaderMatricula.class.getResource(url));
//         Parent root = loader.load();


//         if (matriculaModel != null) {
//             controllerLandingPage controller = loader.getController();
//             controller.setMatriculaModel(matriculaModel);
//         }

//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }
// }