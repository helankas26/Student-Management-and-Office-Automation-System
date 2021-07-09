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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class TeacherAdvanceUpdateController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTeacherAdvanceUpdate;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private DatePicker dateAdvanceDate;
    @FXML
    private ComboBox<?> cmbTeacher;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtAdvanceID;
    @FXML
    private TextField txtAmonut;
    @FXML
    private TableView<?> tblAdvance;
    @FXML
    private TableColumn<?, ?> tblAdvanceID;
    @FXML
    private TableColumn<?, ?> tblTeacher;
    @FXML
    private TableColumn<?, ?> tblTeacherID;
    @FXML
    private TableColumn<?, ?> tblDescription;
    @FXML
    private TableColumn<?, ?> tblAmount;
    @FXML
    private TableColumn<?, ?> tblAdvanceDate;
    @FXML
    private TableColumn<?, ?> tblStaff;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEdit;

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
    private void dateAdvanceDateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmbTeacherActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tblAdvanceOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnEditActionPerformed(ActionEvent event) {
    }
    
}
