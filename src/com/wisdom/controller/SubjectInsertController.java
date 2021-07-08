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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class SubjectInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneSubjectInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSubjectID;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private RadioButton radSinhala;
    @FXML
    private ToggleGroup Medium;
    @FXML
    private RadioButton radEnglish;
    @FXML
    private RadioButton radTamil;
    @FXML
    private ComboBox<?> cmbCategory;
    @FXML
    private TableView<?> tblSubject;
    @FXML
    private TableColumn<?, ?> tblSubjectID;
    @FXML
    private TableColumn<?, ?> tblSubjectName;
    @FXML
    private TableColumn<?, ?> tblMedium;
    @FXML
    private TableColumn<?, ?> tblCategoryID;
    @FXML
    private TableColumn<?, ?> tblCategoryName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

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
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSaveActionPerformed(ActionEvent event) {
    }
    
}
