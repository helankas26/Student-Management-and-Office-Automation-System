/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Admission;
import com.wisdom.model.Student;
import com.wisdom.model.dao.AdmissionDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
public class AdmissionDeleteController implements Initializable {

    @FXML
    private AnchorPane anchorPaneAdmissionDelete;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtStudentID;
    @FXML
    private TextField txtAdmissionFee;
    @FXML
    private TableView<Admission> tblAdmission;
    @FXML
    private TableColumn<Admission, String> tblStudentID;
    @FXML
    private TableColumn<Admission, String> tblStudentName;
    @FXML
    private TableColumn<Admission, String> tblAdmissionFee;
    @FXML
    private TableColumn<Admission, String> tblDate;
    @FXML
    private TableColumn<Admission, String> tblStaff;
    @FXML
    private DatePicker dateDate;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Admission admission;
       
    private AdmissionDAO admissionDAO = new AdmissionDAO();
    
    public void setAnchorpaneClose(AnchorPane anchorPaneMainCenter) {
        this.anchorPaneMainCenter = anchorPaneMainCenter;
    }

    public void setBorderPaneClose(BorderPane borderPaneMain) {
        this.borderPaneMain = borderPaneMain;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtAdmissionFee.setText("0.00");
        dateDate.setValue(LocalDate.now());
        
        setTableViewByDate();
    }    


    @FXML
    private void tblAdmissionOnMouseClicked(MouseEvent event) {
        admission = tblAdmission.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtStudentID.getText().isEmpty()) {
            
            if (setTableViewByID()) {
                admission = tblAdmission.getFocusModel().getFocusedItem();
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid StudentID");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("StudentID required to search");
            alert.showAndWait();
        }
    }

    @FXML
    private void dateDateOnAction(ActionEvent event) {
        setTableViewByDate();
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!txtStudentID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete admission of student : " + txtStudentID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                admission = new Admission();

                getFields();

                if (admissionDAO.deleteAdmission(admission)) {
                    
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                    stage2.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.setTitle("Information");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Delete successful");

                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        LocalDate date = dateDate.getValue();
                        clearFields();
                        dateDate.setValue(date);
                        setTableViewByDate();
                    } else {
                        LocalDate date = dateDate.getValue();
                        clearFields();
                        dateDate.setValue(date);
                        setTableViewByDate();
                    }

                } else {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    Stage stage3 = (Stage) alert3.getDialogPane().getScene().getWindow();
                    stage3.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert3.initModality(Modality.APPLICATION_MODAL);
                    alert3.setTitle("Warning");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Invalid StudentID");
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
            alert.setContentText("StudentID required");
            alert.showAndWait();
        }
    }
    
    private boolean isFieldsEmpty() {
        return txtStudentID.getText().isEmpty() || txtAdmissionFee.getText().isEmpty();
    }
    
    private void getFields() {
        admission.setStudent(new Student());
        admission.getStudent().setUserID(txtStudentID.getText());
    }
    
    private void clearFields() {
        txtStudentID.setText("");
        txtAdmissionFee.setText("0.00");
        dateDate.setValue(LocalDate.now());
    }
    
    private void setFields() {
        txtStudentID.setText(admission.getStudent().getUserID());
        txtAdmissionFee.setText(Double.toString(admission.getAdmissionFee()));
        dateDate.setValue(LocalDate.parse(admission.getAdmissionDate()));
    }
    
    private void setTableViewByDate() {
        ObservableList<Admission> obslist = FXCollections.observableArrayList();
        ArrayList<Admission> admissionList = admissionDAO.getAdmissionByDate(dateDate.getValue().toString());
        
        for(Admission admissionRow : admissionList){
            obslist.add(admissionRow);
        }
        
        tblStudentID.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStudent(), "userID"));
        tblStudentName.setCellValueFactory(new PropertyValueFactory<>("student")); 
        tblAdmissionFee.setCellValueFactory(new PropertyValueFactory<>("admissionFee")); 
        tblDate.setCellValueFactory(new PropertyValueFactory<>("admissionDate")); 
        tblStaff.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaff(), "firstName"));
        
        tblAdmission.setItems(obslist);
    
    }
    
    private boolean setTableViewByID() {
        boolean valid  = false;
        
        ObservableList<Admission> obslist = FXCollections.observableArrayList();
        Admission admission = new Admission();
        admission.setStudent(new Student());
        admission.getStudent().setUserID(txtStudentID.getText());
        
        if (admissionDAO.getAdmissionByStudentID(admission)) {
            obslist.add(admission);
            valid = true;
            
            tblStudentID.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStudent(), "userID"));
            tblStudentName.setCellValueFactory(new PropertyValueFactory<>("student")); 
            tblAdmissionFee.setCellValueFactory(new PropertyValueFactory<>("admissionFee")); 
            tblDate.setCellValueFactory(new PropertyValueFactory<>("admissionDate")); 
            tblStaff.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaff(), "firstName"));

            tblAdmission.setItems(obslist);
        }

        return valid;
    }
    
}
