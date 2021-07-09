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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class ExpenditureInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneExpenditureInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtExpense;
    @FXML
    private DatePicker dateExpenseDate;
    @FXML
    private TextField txtAmonut;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<?> tblExpense;
    @FXML
    private TableColumn<?, ?> tblExpenseID;
    @FXML
    private TableColumn<?, ?> tblAmount;
    @FXML
    private TableColumn<?, ?> tblDate;
    @FXML
    private TableColumn<?, ?> tblStaff;

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
    private void btnCancelActionPerformed(ActionEvent event) {
    }
    
}
