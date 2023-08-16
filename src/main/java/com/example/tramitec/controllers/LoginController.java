package com.example.tramitec.controllers;
import com.example.tramitec.util.AlertUtil;
import com.example.tramitec.util.MatriculaModel;
import com.example.tramitec.util.StageLoaderMatricula;
import com.example.tramitec.interfaces.Repository;
import com.example.tramitec.model.Alumno;
import com.example.tramitec.model.AlumnoDAO;
import com.example.tramitec.model.AlumnoRepositoryImplement;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;



public class LoginController {

    private AlumnoDAO AlumnoDAO = new AlumnoDAO();

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSwitchLogin;

    @FXML
    private Button btnSwitchRegister;

    @FXML
    private Pane containerLogin;

    @FXML
    private Pane containerRegister;

    @FXML
    private TextField txtCarrera;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumeroControl;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    AlumnoRepositoryImplement rp = new AlumnoRepositoryImplement();


    @FXML
    private void LoginAndRegister(ActionEvent event) {
        
        Node source = (Node) event.getSource();
        switch (source.getId()) {
            case "btnLogin": 
                handleLogin(event);
                break;
            case "btnRegister": 
                handleRegister();
                break;
        
            default:
                break;
        }

        // if (event.getSource().equals(btnLogin)) {
        //     handleLogin(event);
        // } else { 
        //     handleRegister();
        // }

    }

    
    @FXML
    void switchForm(ActionEvent event) {

        Node source = (Node) event.getSource();

        switch (source.getId()) {
            case "btnSwitchRegister":
                showRegisterForm();
                break;
            case "btnSwitchLogin":
                showLoginForm();
                break;
            default:
                break;
        }
        
        // if (event.getSource().equals(btnSwitchRegister)) {
        //     showRegisterForm();
        // } else {
        //     showLoginForm();
        // }
        
    }


    private void showLoginForm() {
        containerRegister.setVisible(false);
        containerLogin.setVisible(true);
        btnSwitchLogin.setStyle("-fx-background-color: #1B396A;");
        btnSwitchRegister.setStyle("-fx-background-color: #343131;");
    }


    private void showRegisterForm() {
        containerLogin.setVisible(false);
        containerRegister.setVisible(true);
        btnSwitchRegister.setStyle("-fx-background-color: #1B396A;");
        btnSwitchLogin.setStyle("-fx-background-color: #343131;");
    }
    //Esta funcion maneja la logica de mandar los datos de los formularios a la base de datos
    private void handleRegister() {
        if (!txtNombre.getText().isEmpty() && !txtNumeroControl.getText().isEmpty() && !txtNewPassword.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtCarrera.getText().isEmpty()) {

            if (txtNewPassword.getText().length() > 5) {
            Alumno a = new Alumno();
            a.setNombre(txtNombre.getText());
            a.setNumeroControl(txtNumeroControl.getText());
            a.setCorreo(txtEmail.getText());
            a.setPassword(txtNewPassword.getText());
            a.setCarrera(txtCarrera.getText());

            System.out.println(rp.porMatricula(txtNumeroControl.getText()));

                if (rp.porMatricula(txtNumeroControl.getText()) == null) {
                    if (rp.porCorreo(txtEmail.getText()) == null) {
                        rp.guardar(a);
                        AlertUtil.showAlert(AlertType.INFORMATION, "Congratulations", "Te has registrado exitosamente");
                    } else {
                        AlertUtil.showAlert(AlertType.ERROR, "Error registro", "Intente con otro correo");
                    }
                } else {
                    AlertUtil.showAlert(AlertType.ERROR, "Error registro", "Usuario ya existe, Intente con otra matricula");
                }

                clearFields();


            } else {
                AlertUtil.showAlert(AlertType.ERROR, "Error de Registro", "Tu contraseña debe ser mayor a 5");
            }

        } else {
            AlertUtil.showAlert(AlertType.ERROR, "Error de Registro", "Llena todos los campos");

            clearFields();
            
        }
    }

    //Esta funcion se encarga de contener la logica que verifica si un usuario existe
    private void handleLogin(ActionEvent event) {
        if (!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
        
            String matricula = txtUser.getText();
            String pass = txtPassword.getText();

            int state = AlumnoDAO.login(matricula, pass);
            
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

    //Esta funcion se encarga de limpiar los fields
    private void clearFields() {
        txtNombre.clear();
        txtNumeroControl.clear();
        txtNewPassword.clear();
        txtEmail.clear();
        txtCarrera.clear();
    }

    // @FXML
    // private void eventKey(KeyEvent event){
        
        
        
    //     if(event.getSource().equals(txtUser)){
            
    //         if(event.getCharacter().equals(" ")){
    //             event.consume();
    //         }

    //     }else if(event.getSource().equals(txtPassword)){
            
    //         if(event.getCharacter().equals(" ")){
    //             event.consume();
    //         }
            
    //     }
        
        
        
    // }
    

}

