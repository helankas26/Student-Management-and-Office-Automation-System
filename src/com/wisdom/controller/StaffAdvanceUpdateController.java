/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Advance;
import com.wisdom.model.Login;
import com.wisdom.model.Staff;
import com.wisdom.model.dao.AdvanceDAO;
import com.wisdom.model.dao.StaffDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class StaffAdvanceUpdateController implements Initializable {

    @FXML
    private AnchorPane anchorPaneStaffAdvanceUpdate;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private DatePicker dateAdvanceDate;
    @FXML
    private ComboBox<Staff> cmbStaff;
    @FXML
    private TextField txtAdvanceID;
    @FXML
    private TextField txtAmonut;
    @FXML
    private TableView<Advance> tblAdvance;
    @FXML
    private TableColumn<Advance, String> tblAdvanceID;
    @FXML
    private TableColumn<Advance, Staff> tblStaff;
    @FXML
    private TableColumn<Advance, String> tblStaffID;
    @FXML
    private TableColumn<Advance, String> tblDescription;
    @FXML
    private TableColumn<Advance, String> tblAmount;
    @FXML
    private TableColumn<Advance, String> tblAdvanceDate;
    @FXML
    private TableColumn<Advance, String> tblStaffHandle;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEdit;
    @FXML
    private TextArea txaDescription;
    
    private Login loginUser;
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Advance advance;
       
    private AdvanceDAO advanceDAO = new AdvanceDAO();
    private StaffDAO staffDAO = new StaffDAO();
    
    public void setloginUser(Login login) {
        this.loginUser = login;
    }
    
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
        ObservableList<Staff> cmbStaffName = FXCollections.observableArrayList(staffDAO.getStaffActive());
        cmbStaff.setItems(cmbStaffName);
        dateAdvanceDate.setValue(LocalDate.now());
        txtAmonut.setText("0.00");
        
        setTableView();
    }    


    @FXML
    private void tblAdvanceOnMouseClicked(MouseEvent event) {
        advance = tblAdvance.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void dateAdvanceDateOnAction(ActionEvent event) {
        setTableView();
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void btnEditOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            advance = new Advance();
            
            getFields();
            
            if (advanceDAO.updateStaffAdvance(advance)) {
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Update successful");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    LocalDate date = dateAdvanceDate.getValue();
                    clearFields();
                    dateAdvanceDate.setValue(date);
                    setTableView();
                } else {
                    LocalDate date = dateAdvanceDate.getValue();
                    clearFields();
                    dateAdvanceDate.setValue(date);
                    setTableView();
                }
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid AdvanceID or AdvanceID and Date doesn't match");
                alert.showAndWait();
                
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }
    }
    
    private boolean isFieldsEmpty() {
        return cmbStaff.getValue() == null || dateAdvanceDate.getValue() == null || 
                txtAdvanceID.getText().isEmpty() || txaDescription.getText().isEmpty() || 
                txtAmonut.getText().isEmpty();
    }
    
    private void getFields() {
        advance.setAdvanceID(txtAdvanceID.getText());
        advance.setStaffReceiver(new Staff());
        advance.getStaffReceiver().setUserID(cmbStaff.getSelectionModel().getSelectedItem().getUserID());
        advance.setAdvanceDate(dateAdvanceDate.getValue().toString());
        advance.setDiscription(txaDescription.getText());
        advance.setAdvanceAmount(Double.parseDouble(txtAmonut.getText()));
        advance.setStaffHandler(new Staff());
        advance.getStaffHandler().setUserID(loginUser.getUser().getUserID());
    }
    
    private void setFields() {
        cmbStaff.setValue(advance.getStaffReceiver());
        dateAdvanceDate.setValue(LocalDate.parse(advance.getAdvanceDate()));
        txtAdvanceID.setText(advance.getAdvanceID());
        txaDescription.setText(advance.getDiscription());
        txtAmonut.setText(Double.toString(advance.getAdvanceAmount()));
    }
    
    private void clearFields() {
        cmbStaff.setValue(null);
        dateAdvanceDate.setValue(null);
        dateAdvanceDate.setValue(LocalDate.now());
        txtAdvanceID.setText("");
        txaDescription.setText("");
        txtAmonut.setText("0.00");
    }
    
    private void setTableView() {
        ObservableList<Advance> obslist = FXCollections.observableArrayList();
        ArrayList<Advance> advanceList = null;
        if (dateAdvanceDate.getValue() != null) {
            advanceList = advanceDAO.getStaffAdvanceByDate(dateAdvanceDate.getValue().toString());
            
            for(Advance advanceRow : advanceList){
                obslist.add(advanceRow);
            }
        }
        
        tblAdvanceID.setCellValueFactory(new PropertyValueFactory<>("advanceID"));
        tblStaff.setCellValueFactory(new PropertyValueFactory<>("staffReceiver"));
        tblStaffID.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaffReceiver(), "userID"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("advanceAmount"));
        tblAdvanceDate.setCellValueFactory(new PropertyValueFactory<>("advanceDate"));
        tblStaffHandle.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaffHandler(), "firstName"));
        
        tblAdvance.setItems(obslist);
    }

    
}
