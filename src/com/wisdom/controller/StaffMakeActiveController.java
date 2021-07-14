/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Staff;
import com.wisdom.model.dao.StaffDAO;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StaffMakeActiveController implements Initializable {

    @FXML
    private AnchorPane anchorPaneStaffMakeActive;
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
    private JFXButton btnMakeAcive;
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
    private Button btnCancel;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
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
    private ComboBox<String> cmbTitle;
    @FXML
    private TextArea txaAddress;
    
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
        
        setTableView();
    }    
    
    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
        staff = table.getSelectionModel().getSelectedItem();
        setFields();
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
    private void btnMakeAciveOnAction(ActionEvent event) {
        if (!txtStaffID.getText().isEmpty()) {
            staff = new Staff();

            staff.setUserID(txtStaffID.getText());
            staff.setStatus("active");
            
            if (staffDAO.changeStaffStatus(staff)) {
                setTableView();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Successful");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    clearFields();
                } else {
                    clearFields();
                }
            
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid StaffID");
                alert.showAndWait();
            }
   
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("StaffID required");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtStaffID.getText().isEmpty()) {
            staff = new Staff();
            
            staff.setUserID(txtStaffID.getText());
            
            if (staffDAO.getDeactivateStaff(staff)) {
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid StaffID or active staff member");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("StaffID required to search");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void btnEditOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            staff = new Staff();
            
            getFields();
            
            if (staffDAO.updateStaff(staff)) {
                setTableView();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Update successful");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    clearFields();
                } else {
                    clearFields();
                }
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid StaffID or active staff member");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Some fields are missing");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!txtStaffID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete staff member : " + txtStaffID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                staff = new Staff();

                staff.setUserID(txtStaffID.getText());
                staff.setStatus("deactivate");

                if (staffDAO.deleteStaff(staff)) {
                    setTableView();

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                    stage2.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.setTitle("Information");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Delete successful");

                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        clearFields();
                    } else {
                        clearFields();
                    }

                } else {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    Stage stage3 = (Stage) alert3.getDialogPane().getScene().getWindow();
                    stage3.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert3.initModality(Modality.APPLICATION_MODAL);
                    alert3.setTitle("Warning");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Invalid StaffID or active staff member");
                    alert3.showAndWait();
                }
                
            }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("StaffID required");
            alert.showAndWait();
        }
    }
    
    private boolean isFieldsEmpty() {
        return txtStaffID.getText().isEmpty() || cmbTitle.getValue() == null || 
                txtFirstName.getText().isEmpty() || dateDoB.getValue() == null || 
                Gender.getSelectedToggle() == null  || txtTelNo.getText().isEmpty() || 
                txaAddress.getText().isEmpty() || txtEmail.getText().isEmpty();
    }
    
    private void getFields() {
        staff.setUserID(txtStaffID.getText());
        staff.setTitle(cmbTitle.getValue());
        staff.setInitial(txtInitial.getText().toUpperCase().trim());
        staff.setFirstName(txtFirstName.getText());
        staff.setLastName(txtLastName.getText().trim());
        staff.setDateOfBirth((dateDoB.getValue().toString()));
        staff.setSex(getGender().getText());
        staff.setTelNo(txtTelNo.getText());
        staff.setAddress(txaAddress.getText());
        staff.setEmail(txtEmail.getText());
        staff.setStatus("deactivate");
    }
    
    private void setFields() {
        txtStaffID.setText(staff.getUserID());
        cmbTitle.setValue(staff.getTitle());
        txtInitial.setText(staff.getInitial());
        txtFirstName.setText(staff.getFirstName());
        txtLastName.setText(staff.getLastName());
        dateDoB.setValue(LocalDate.parse(staff.getDateOfBirth()));
        Gender.selectToggle((staff.getSex().equals("Male")) ? radMale : radFemale);  
        txtTelNo.setText(staff.getTelNo());
        txaAddress.setText(staff.getAddress());
        txtEmail.setText(staff.getEmail());
        dateJoinedDate.setValue(LocalDate.parse(staff.getJoinedDate()));
    }
    
    private void clearFields() {
        txtStaffID.setText("");
        cmbTitle.setValue(null);
        txtInitial.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        dateDoB.setValue(null);
        Gender.selectToggle(null);
        txtTelNo.setText("");
        txaAddress.setText("");
        txtEmail.setText("");
        dateJoinedDate.setValue(null);
    }
    
    private void setTableView() {
        ObservableList<Staff> obslist = FXCollections.observableArrayList();
        ArrayList<Staff> staffList = staffDAO.getStaffDeactivate();
        
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
