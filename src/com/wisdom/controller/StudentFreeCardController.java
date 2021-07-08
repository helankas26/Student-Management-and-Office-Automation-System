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
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StudentFreeCardController implements Initializable {

    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtStudentID;
    @FXML
    private Button btnSearch;
    @FXML
    private Pane dateFree;
    @FXML
    private ComboBox<?> cmbClassName;
    @FXML
    private TableView<?> tblClass;
    @FXML
    private TableColumn<?, ?> tblStudentName;
    @FXML
    private TableColumn<?, ?> tblStudentID;
    @FXML
    private TableColumn<?, ?> tblRegisteredClass;
    @FXML
    private TableColumn<?, ?> tblRegisteredClassID;
    @FXML
    private TableColumn<?, ?> tblRegisteredDate;
    @FXML
    private Button btnAddToFree;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnActive;
    @FXML
    private TableView<?> tblFreeClass;
    @FXML
    private TableColumn<?, ?> tblStudentIDFee;
    @FXML
    private TableColumn<?, ?> tblStudentNameFree;
    @FXML
    private TableColumn<?, ?> tblClassFree;
    @FXML
    private AnchorPane anchorPaneStudentFreeClass;

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
    private void cmbClassNameActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblClassOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnAddToFreeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblFreeClassOnMouseClicked(MouseEvent event) {
    }
    
}
