/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void txtSetDefaultColor(InputMethodEvent event) {
        txtUsername.setStyle("-fx-border-color: #2e2e2e;");
        txtPassword.setStyle("-fx-border-color: #2e2e2e;");
    }
    
    @FXML
    private void btnLoginActionPerformed(ActionEvent event) {
    }
    
    private boolean isFieldEmpty() {
        if (txtUsername.getText().equalsIgnoreCase("")) {
            changeField(txtUsername, "#de2700", lblUsername, "Username is Requied");
            if (txtPassword.getText().equalsIgnoreCase("")) {
                changeField(txtPassword, "#de2700", lblPassword, "Password is Requied");
            } else {
                changeField(txtPassword, "#2e2e2e", lblPassword, "");
            }
            txtUsername.requestFocus();
            return false;
        } else {
            changeField(txtUsername, "#2e2e2e", lblUsername, "");
            if (txtPassword.getText().equalsIgnoreCase("")) {
                changeField(txtPassword, "#de2700", lblPassword, "Password is Requied");
                txtPassword.requestFocus();
                return false;
            } else {
                changeField(txtPassword, "#2e2e2e", lblPassword, "");
                return true;
            }
        }
    }
    
    private void changeField(TextField field, String colorCode, Label lable, String msg) {
        field.setStyle("-fx-border-color: " + colorCode + ";");
        lable.setText(msg);
    }

    
    
}
