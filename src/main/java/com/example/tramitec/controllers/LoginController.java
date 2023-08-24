package com.example.tramitec.controllers;
import com.example.tramitec.util.AlertUtil;
import com.example.tramitec.util.StageLoaderAlumno;
import com.example.tramitec.model.Alumno;
import com.example.tramitec.model.Carrera;
import com.example.tramitec.model.implementations.AlumnoRepositoryImplement;
import com.example.tramitec.model.implementations.CarreraRepositoryImplement;
import com.example.tramitec.model.interfaces.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;



public class LoginController {


    @FXML
    private ComboBox<?> boxCarrera;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRecoverBack;

    @FXML
    private Button btnRecoverValidate;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnSwitchLogin;

    @FXML
    private Button btnSwitchRegister;

    @FXML
    private Pane containerLogin;

    @FXML
    private Pane containerRecoverUser;

    @FXML
    private Pane containerRegister;

    @FXML
    private Label labelRecover;

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
    private TextField txtRecoverEmail;

    @FXML
    private TextField txtRecoverMatricula;

    @FXML
    private TextField txtUser;
    
    AlumnoRepositoryImplement rp = new AlumnoRepositoryImplement();

    CarreraRepositoryImplement cp = new CarreraRepositoryImplement();

    // private String[] arrCarreras = {"Ing. Sistemas Computacionales", "Ing. Electrica", "Ing. Mecatronica"};
    
    

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

    @FXML
    void backLogin(ActionEvent event) {
        containerRecoverUser.setVisible(false);
        containerLogin.setVisible(true);
    }

    @FXML
    void labelRecover(MouseEvent event) {
        containerLogin.setVisible(false);
        containerRecoverUser.setVisible(true);
        
    }

    @FXML
    void inOverMouse(MouseEvent event) {
        labelRecover.setUnderline(true);
    }

    @FXML
    void outOverMouse(MouseEvent event) {
        labelRecover.setUnderline(false);
    }

    
    public void loadCarrerasInComboBox(){
            // List<String> carreras = new ArrayList<>();
    
            // cp.listar().forEach(item -> {
            //     String c;
            //     c = item.getCarrera();
            //     carreras.add(c);
            // });
    
        ObservableList ObservableListCarrera = FXCollections.observableArrayList(cp.listar());
        boxCarrera.setItems(ObservableListCarrera);
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
        loadCarrerasInComboBox();
    }
    //Esta funcion maneja la logica de mandar los datos de los formularios a la base de datos
    private void handleRegister() {
        if (!txtNombre.getText().isEmpty() && !txtNumeroControl.getText().isEmpty() && !txtNewPassword.getText().isEmpty() 
            && !txtEmail.getText().isEmpty() && boxCarrera.getValue() != null) {

            if (txtNewPassword.getText().length() > 5) {

                Alumno a = new Alumno();
                a.setNombre(txtNombre.getText());
                a.setNumeroControl(txtNumeroControl.getText());
                a.setCorreo(txtEmail.getText());
                a.setPassword(txtNewPassword.getText());
                a.setCarrera((Carrera) boxCarrera.getValue());

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

            int state = rp.login(matricula, pass);
            
            if (state != -1) {
                if (state == 1) {
                    try {
                        // MatriculaModel matriculaModel = new MatriculaModel(matricula);
                        StageLoaderAlumno.load("viewLandingPage.fxml", event, rp.porMatricula(matricula));
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
        boxCarrera.setValue(null);
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

