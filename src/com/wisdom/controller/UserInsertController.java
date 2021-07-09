/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class UserInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneUserInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtLoginID;
    @FXML
    private TextField txtUserID;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordConfirm;
    @FXML
    private JFXRadioButton radAdmin;
    @FXML
    private ToggleGroup Privilege;
    @FXML
    private JFXRadioButton radStandard;
    @FXML
    private JFXRadioButton radGuess;
    @FXML
    private JFXRadioButton radStaff;
    @FXML
    private ToggleGroup For;
    @FXML
    private JFXRadioButton radTeacher;
    @FXML
    private TableView<?> tblStaffUser;
    @FXML
    private TableColumn<?, ?> tblStaffLoginID;
    @FXML
    private TableColumn<?, ?> tblStaffUserame;
    @FXML
    private TableColumn<?, ?> tblStaffPrivilege;
    @FXML
    private TableColumn<?, ?> tblStaffIDUser;
    @FXML
    private TableColumn<?, ?> tblStaffNameUser;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<?> tblStaff;
    @FXML
    private TableColumn<?, ?> tblStaffID;
    @FXML
    private TableColumn<?, ?> tblStaffName;
    @FXML
    private TableView<?> tblTeacher;
    @FXML
    private TableColumn<?, ?> tblTeacherID;
    @FXML
    private TableColumn<?, ?> tblTeacherName;
    @FXML
    private TableView<?> tblTeacherUser;
    @FXML
    private TableColumn<?, ?> tblTeacherLoginID;
    @FXML
    private TableColumn<?, ?> tblTeacherUsername;
    @FXML
    private TableColumn<?, ?> tblTeacherPrivilege;
    @FXML
    private TableColumn<?, ?> tblTeacherIDUser;
    @FXML
    private TableColumn<?, ?> tblTeacherNameUser;
    @FXML
    private JFXCheckBox chkShow;
    @FXML
    private TextField txtPasswordShow;

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
    private void btnSaveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblStaffOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void tblTeacherOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void chkShowActionPerformed(ActionEvent event) {
    }
    
}
