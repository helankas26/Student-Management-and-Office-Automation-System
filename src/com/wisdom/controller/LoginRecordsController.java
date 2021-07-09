/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class LoginRecordsController implements Initializable {

    @FXML
    private AnchorPane anchorPaneLoginRecords;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearchByID;
    @FXML
    private TextField txtLoginID;
    @FXML
    private Button btnSearchByDate;
    @FXML
    private JFXDatePicker dateLoginDate;
    @FXML
    private TableView<?> tblLoginRecords;
    @FXML
    private TableColumn<?, ?> tblLoginID;
    @FXML
    private TableColumn<?, ?> tblUserame;
    @FXML
    private TableColumn<?, ?> tblDate;
    @FXML
    private TableColumn<?, ?> tblTime;
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
    private void btnSearchByIDActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSearchByDateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblLoginRecordsOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }
    
}
