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
public class StaffDeleteController implements Initializable {

    @FXML
    private AnchorPane anchorPaneStaffDelete;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private ImageView imgStaff;
    @FXML
    private JFXButton btnImgStaff;
    @FXML
    private TextField txtStaffID;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtInitial;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtFirstName;
    @FXML
    private DatePicker dateDoB;
    @FXML
    private JFXRadioButton radFemale;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private JFXRadioButton radMale;
    @FXML
    private ComboBox<?> cmdTitle;
    @FXML
    private TextField txtTelNo;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dateJoinedDate;
    @FXML
    private TextArea txtAddress;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tblStaffID;
    @FXML
    private TableColumn<?, ?> tblTeacherTitle;
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
    private TableColumn<?, ?> tblTelNo;
    @FXML
    private TableColumn<?, ?> tblAddress;
    @FXML
    private TableColumn<?, ?> tblEmail;
    @FXML
    private TableColumn<?, ?> tblJoinedDate;

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
    private void btnImgStaffActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSearchActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
    }
    
}
