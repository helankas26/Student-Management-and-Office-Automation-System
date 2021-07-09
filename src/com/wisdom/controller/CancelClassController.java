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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class CancelClassController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCancelClass;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private JFXDatePicker dateDate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSearch;
    @FXML
    private TableView<?> tblSchedule;
    @FXML
    private TableColumn<?, ?> tblClassID;
    @FXML
    private TableColumn<?, ?> tblClass;
    @FXML
    private TableColumn<?, ?> tblDate;
    @FXML
    private TableColumn<?, ?> tblDay;

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
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblScheduleOnMouseClicked(MouseEvent event) {
    }
    
}
