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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class AdmissionDeleteController implements Initializable {

    @FXML
    private AnchorPane anchorPaneAdmissionDelete;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtAdmissionFee;
    @FXML
    private TableView<?> tblAdmission;
    @FXML
    private TableColumn<?, ?> tblStudentID;
    @FXML
    private TableColumn<?, ?> tblStudentName;
    @FXML
    private TableColumn<?, ?> tblDate;
    @FXML
    private TableColumn<?, ?> tblStaff;
    @FXML
    private DatePicker dateDate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;

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
    private void btnSearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblAdmissionOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeleteActionPerformed(ActionEvent event) {
    }
    
}
