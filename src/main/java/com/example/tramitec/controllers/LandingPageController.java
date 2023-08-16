package com.example.tramitec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.tramitec.util.AlertUtil;
import com.example.tramitec.util.StageLoaderAlumno;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import com.example.tramitec.model.Alumno;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class LandingPageController implements Initializable {

    @FXML
    private Label LabelCarrera;

    @FXML
    private Label LabelCorreo;
    
    @FXML
    private Button btnCerrar;

    @FXML
    private Label LabelControl;

    @FXML
    private Label LabelNombre;

    @FXML
    private Button btnService;

    @FXML
    private Button btnTitle;

    @FXML
    private ImageView logoHome;

    private Alumno alumno;

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }


    @FXML
    void clickBtn(ActionEvent event) {
        if (event.getSource().equals(btnService)) {
            try {
                StageLoaderAlumno.load("viewMain.fxml", event, alumno);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource().equals(btnCerrar)) {
                try {
                    StageLoaderAlumno.load("ViewLogin.fxml", event, null);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        } else {
            AlertUtil.showAlert(AlertType.INFORMATION, "Fuera de servicio", "En desarrollo intentelo mas tarde");
        }
    }

    @FXML
    void clickLogo(MouseEvent event) {
        try {
            StageLoaderAlumno.load("viewLandingPage.fxml", event, alumno);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            Platform.runLater(() -> {
                // LabelNombre.setText(String.valueOf(ADAO.getNombre(matriculaModel.getMatricula())));
                // LabelControl.setText(String.valueOf(matriculaModel.getMatricula()));
                // LabelCarrera.setText(String.valueOf(ADAO.getCarrera(matriculaModel.getMatricula())));
                // LabelCorreo.setText(String.valueOf(ADAO.getCorreo(matriculaModel.getMatricula())));

                LabelNombre.setText(alumno.getNombre());
                LabelControl.setText(alumno.getNumeroControl());
                LabelCarrera.setText(alumno.getCarrera());
                LabelCorreo.setText(alumno.getCorreo());
            });
        });
    
        hilo.start();
    }

}
