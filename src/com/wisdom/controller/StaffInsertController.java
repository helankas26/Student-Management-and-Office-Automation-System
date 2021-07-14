/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Staff;
import com.wisdom.model.Teacher;
import com.wisdom.model.dao.StaffDAO;
import com.wisdom.model.dao.TeacherDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StaffInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneStaffInsert;
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
    private TextField txtTelNo;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dateJoinedDate;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Staff> table;
    @FXML
    private TableColumn<Staff, String> tblStaffID;
    @FXML
    private TableColumn<Staff, String> tblTitle;
    @FXML
    private TableColumn<Staff, String> tblInitial;
    @FXML
    private TableColumn<Staff, String> tblFirstName;
    @FXML
    private TableColumn<Staff, String> tblLastName;
    @FXML
    private TableColumn<Staff, String> tblDoB;
    @FXML
    private TableColumn<Staff, String> tblSex;
    @FXML
    private TableColumn<Staff, String> tblTelNo;
    @FXML
    private TableColumn<Staff, String> tblAddress;
    @FXML
    private TableColumn<Staff, String> tblEmail;
    @FXML
    private TableColumn<Staff, String> tblJoinedDate;
    @FXML
    private TextArea txaAddress;
    @FXML
    private ComboBox<String> cmbTitle;
    
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Staff staff;
       
    private StaffDAO staffDAO = new StaffDAO();
    
    
    public void setAnchorpaneClose(AnchorPane anchorPaneMainCenter) {
        this.anchorPaneMainCenter = anchorPaneMainCenter;
    }

    public void setBorderPaneClose(BorderPane borderPaneMain) {
        this.borderPaneMain = borderPaneMain;
    }
    
    private RadioButton getGender() {
        RadioButton radGender =  (RadioButton) Gender.getSelectedToggle();
        return radGender;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTitle.getItems().addAll("Mr.", "Miss.", "Mrs.");
        dateJoinedDate.setValue(LocalDate.now());
        
        setTableView();
    }    

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnImgStaffOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            staff = new Staff();
            
            getFields();
            
            if (staffDAO.insertStaff(staff)) {
                setTableView();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Insert Successful");
                alert.setHeaderText("Staff ID : " + staff.getUserID());
                alert.setContentText("Staff insert Successfully");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    clearFields();
                } else {
                    clearFields();
                }
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required except Initial & Last Name");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private boolean isFieldsEmpty() {
        return cmbTitle.getValue() == null || txtFirstName.getText().isEmpty() ||
                dateDoB.getValue() == null || Gender.getSelectedToggle() == null  ||
                txtTelNo.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txaAddress.getText().isEmpty() || dateJoinedDate.getValue() == null;
    }
    
    private void getFields() {
        staff.setTitle(cmbTitle.getValue());
        staff.setInitial(txtInitial.getText().toUpperCase().trim());
        staff.setFirstName(txtFirstName.getText());
        staff.setLastName(txtLastName.getText().trim());
        staff.setDateOfBirth((dateDoB.getValue().toString()));
        staff.setSex(getGender().getText());
        staff.setTelNo(txtTelNo.getText());
        staff.setEmail(txtEmail.getText());
        staff.setAddress(txaAddress.getText());      
        staff.setJoinedDate(dateJoinedDate.getValue().toString());
    }
    
    private void clearFields() {
        cmbTitle.setValue(null);
        txtInitial.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        dateDoB.setValue(null);
        Gender.selectToggle(radMale);
        txtTelNo.setText("");
        txtEmail.setText("");
        txaAddress.setText("");
        dateJoinedDate.setValue(LocalDate.now());
    }
    
    private void setTableView() {
        ObservableList<Staff> obslist = FXCollections.observableArrayList();
        ArrayList<Staff> staffList = staffDAO.getStaffActive();
        
        for(Staff staffRow : staffList){
            obslist.add(staffRow);
        }
        
        tblStaffID.setCellValueFactory(new PropertyValueFactory<>("userID"));  
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblInitial.setCellValueFactory(new PropertyValueFactory<>("initial"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblDoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tblTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        
        table.setItems(obslist);
    
    }
    
    
}
