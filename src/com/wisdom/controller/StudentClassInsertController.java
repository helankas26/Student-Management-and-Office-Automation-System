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
public class StudentClassInsertController implements Initializable {

    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private ComboBox<?> cmbCategory;
    @FXML
    private ComboBox<?> cmbSubject;
    @FXML
    private TextField txtStudentID;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<?> tblRegisteredClass;
    @FXML
    private TableColumn<?, ?> tblStudentName;
    @FXML
    private TableColumn<?, ?> tblStudentID;
    @FXML
    private TableColumn<?, ?> tblRegisteredClassID;
    @FXML
    private TableColumn<?, ?> tblRegisteredDate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<?> tblAvailableClass;
    @FXML
    private TableColumn<?, ?> tblClassID;
    @FXML
    private TableColumn<?, ?> tblClass;
    @FXML
    private AnchorPane anchorPaneStudentClassInsert;

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
    private void cmbCategoryActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmbSubjectActionPerformed(ActionEvent event) {
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
    private void tblAvailableClassOnMouseClicked(MouseEvent event) {
    }
    
}
