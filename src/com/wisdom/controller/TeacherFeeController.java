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
public class TeacherFeeController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTeacherFee;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private ComboBox<?> cmbTeacher;
    @FXML
    private ComboBox<?> cmbClass;
    @FXML
    private Label lblTeacherID;
    @FXML
    private Label lblClassID;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtAmount;
    @FXML
    private TableView<?> tblTeacherFee;
    @FXML
    private TableColumn<?, ?> tblTeacherID;
    @FXML
    private TableColumn<?, ?> tblTeacherName;
    @FXML
    private TableColumn<?, ?> tblClassID;
    @FXML
    private TableColumn<?, ?> tblClassName;
    @FXML
    private TableColumn<?, ?> tblFeeAmount;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<?> tblFees;
    @FXML
    private TableColumn<?, ?> tblTeacher;
    @FXML
    private TableColumn<?, ?> tblClass;
    @FXML
    private TableColumn<?, ?> tblAmount;

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
    private void cmbTeacherActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmbClassActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSaveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblFeesOnMouseClicked(MouseEvent event) {
    }
    
}
