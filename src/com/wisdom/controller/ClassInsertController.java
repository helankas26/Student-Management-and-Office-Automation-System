/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class ClassInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneClassInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtClassID;
    @FXML
    private ComboBox<?> cmbCategory;
    @FXML
    private ComboBox<?> cmbSubject;
    @FXML
    private ComboBox<?> cmbDay;
    @FXML
    private JFXTimePicker timeStart;
    @FXML
    private JFXTimePicker timeEnd;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<?> cmbTeacher;
    @FXML
    private ComboBox<?> cmbRoom;
    @FXML
    private TextField txtClassFee;
    @FXML
    private JFXCheckBox chkEditName;
    @FXML
    private TableView<?> tblClass;
    @FXML
    private TableColumn<?, ?> tblClassID;
    @FXML
    private TableColumn<?, ?> tblClassName;
    @FXML
    private TableColumn<?, ?> tblDay;
    @FXML
    private TableColumn<?, ?> tblStartTime;
    @FXML
    private TableColumn<?, ?> tblEndTime;
    @FXML
    private TableColumn<?, ?> tblRoom;
    @FXML
    private TableColumn<?, ?> tblClassFee;
    @FXML
    private TableColumn<?, ?> tblSubjectID;
    @FXML
    private TableColumn<?, ?> tblTeacherID;
    @FXML
    private TableColumn<?, ?> tblCategoryID;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

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
    private void timeEndActionPerformed(ActionEvent event) {
    }

    @FXML
    private void chkEditNameActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSaveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }
    
}
