package com.example.tramitec.controllers;
import com.example.tramitec.util.MatriculaModel;
import com.example.tramitec.util.StageLoaderMatricula;
import com.example.tramitec.model.AlumnoDAO;

import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private void eventAction(ActionEvent event) {
        

        if (event.getSource().equals(btnLogin)) {
            
            if (!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                
                String matricula = txtUser.getText();
                String pass = txtPassword.getText();

                int state = ADAO.login(matricula, pass);
                
                if (state != -1) {
                    if (state == 1) {
                        JOptionPane.showMessageDialog(null, "Datos correctos", null, JOptionPane.WARNING_MESSAGE);
                        try {
                            MatriculaModel matriculaModel = new MatriculaModel(matricula);
                            StageLoaderMatricula.load("viewLandingPage.fxml", event, matriculaModel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos correctos incorrectos", null, JOptionPane.WARNING_MESSAGE);
                    }
                } 

            } else {
                JOptionPane.showMessageDialog(null, "Esta vacio bro", null, JOptionPane.WARNING_MESSAGE);
            }

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

