/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StudentDeleteController implements Initializable {

    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private ImageView imgStudent;
    @FXML
    private JFXButton btnImgStudent;
    @FXML
    private TextField txtStudentID;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtInitial;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private ComboBox<?> cmbGrade;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private TextField txtSchool;
    @FXML
    private JFXRadioButton radFemale;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private JFXRadioButton radMale;
    @FXML
    private JFXRadioButton radSinhala;
    @FXML
    private ToggleGroup Medium;
    @FXML
    private JFXRadioButton radEnglish;
    @FXML
    private JFXRadioButton radTamil;
    @FXML
    private TextField txtParentName;
    @FXML
    private DatePicker dateJoinedDate;
    @FXML
    private ComboBox<?> cmdTitle;
    @FXML
    private TextField txtTelNo;
    @FXML
    private TextArea txtAddress;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tblStudentID;
    @FXML
    private TableColumn<?, ?> tblInitial;
    @FXML
    private TableColumn<?, ?> tblFirstName;
    @FXML
    private TableColumn<?, ?> tblLastName;
    @FXML
    private TableColumn<?, ?> tblDoB;
    @FXML
    private TableColumn<?, ?> tblSex;
    @FXML
    private TableColumn<?, ?> tblMedium;
    @FXML
    private TableColumn<?, ?> tblTelNo;
    @FXML
    private TableColumn<?, ?> tblAddress;
    @FXML
    private TableColumn<?, ?> tblEmail;
    @FXML
    private TableColumn<?, ?> tblGrade;
    @FXML
    private TableColumn<?, ?> tblSchool;
    @FXML
    private TableColumn<?, ?> tblJoinedDate;
    @FXML
    private TableColumn<?, ?> tblTitle;
    @FXML
    private TableColumn<?, ?> tblParentName;
    @FXML
    private AnchorPane anchorPaneStudentDelete;

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
    private void btnImgStudentActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
    }
    
}
