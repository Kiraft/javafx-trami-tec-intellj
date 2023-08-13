package com.example.tramitec.controllers;
import com.example.tramitec.util.AlertUtil;
import com.example.tramitec.util.MatriculaModel;
import com.example.tramitec.util.StageLoaderMatricula;
import com.example.tramitec.model.AlumnoDAO;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;



public class LoginController {

    private AlumnoDAO ADAO = new AlumnoDAO();

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    private void Login(ActionEvent event) {
        
            
            if (!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                
                String matricula = txtUser.getText();
                String pass = txtPassword.getText();

                int state = ADAO.login(matricula, pass);
                
                if (state != -1) {
                    if (state == 1) {
                        try {
                            MatriculaModel matriculaModel = new MatriculaModel(matricula);
                            StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        
                        AlertUtil.showAlert(AlertType.ERROR, "Error inicio de sesion", "Datos incorrectos intentalo de nuevo");
                    }
                }else {
                    
                    AlertUtil.showAlert(AlertType.ERROR, "Error inicio de sesion", "Problema servidores intentelo mas tarde");
                }

            } else {
                AlertUtil.showAlert(AlertType.ERROR, "Error inicio de sesion", "Campos vacios porfavor llenarlos");
            }

        
    }



    
    
    
    @FXML
    private void eventKey(KeyEvent event){
        
        
        
        if(event.getSource().equals(txtUser)){
            
            if(event.getCharacter().equals(" ")){
                event.consume();
            }

        }else if(event.getSource().equals(txtPassword)){
            
            if(event.getCharacter().equals(" ")){
                event.consume();
            }
            
        }
        
        
        
    }
    

}

