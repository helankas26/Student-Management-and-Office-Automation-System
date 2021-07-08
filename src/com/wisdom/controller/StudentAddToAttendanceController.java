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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StudentAddToAttendanceController implements Initializable {

    @FXML
    private AnchorPane anchorPaneStudentAddToAttendance;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSave;
    @FXML
    private ComboBox<?> cmdDay;
    @FXML
    private ComboBox<?> cmdClass;
    @FXML
    private ComboBox<?> cmdStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCenterCloseActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSaveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmdDayActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmdClassActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmdStudentActionPerformed(ActionEvent event) {
    }
    
}
