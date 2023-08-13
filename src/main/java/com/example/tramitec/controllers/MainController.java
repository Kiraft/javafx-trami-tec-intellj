package com.example.tramitec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.tramitec.App;
import com.example.tramitec.util.MatriculaModel;
import com.example.tramitec.util.StageLoaderMatricula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainController implements Initializable{

    @FXML
    private Button btnClose;

    @FXML
    private Button btnFinish;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnMyDocs;

    @FXML
    private Button btnSubir;

    @FXML
    private ImageView logoHome;

    @FXML
    private Pane mainContainer;



    private MatriculaModel matriculaModel;

    public void setMatriculaModel(MatriculaModel matriculaModel) {
        this.matriculaModel = matriculaModel;
    }


    @FXML
    void MouseClicked(MouseEvent event) {
        try {
            StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickBtn(ActionEvent event) {
        
        if (event.getSource().equals(btnMenu)) {
            try {
                StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
            } catch (IOException e) {

                e.printStackTrace();
            }
        } else if(event.getSource().equals(btnSubir)) {
            
            FXMLLoader archivoLoader = new FXMLLoader(App.class.getResource("viewArchivos.fxml"));

            try {
                Parent archivoRoot = archivoLoader.load();
                ArchivoController archivoController = archivoLoader.getController();
                archivoController.setMatriculaModel(matriculaModel);
                mainContainer.getChildren().clear();
                mainContainer.getChildren().add(archivoRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(event.getSource().equals(btnMyDocs)){

            FXMLLoader statusLoader = new FXMLLoader(App.class.getResource("viewStatus.fxml"));

            try {
                Parent statusRoot = statusLoader.load();
                StatusController statusController = statusLoader.getController();
                statusController.setMatriculaModel(matriculaModel);
                mainContainer.getChildren().clear();
                mainContainer.getChildren().add(statusRoot);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(event.getSource().equals(btnFinish)){

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("TRAMITE REALIZADO CON EXITO");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else {
            try {
                StageLoaderMatricula.load("ViewLogin.fxml", event, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
 

}
