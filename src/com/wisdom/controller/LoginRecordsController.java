/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Login;
import com.wisdom.model.dao.LoginDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class LoginRecordsController implements Initializable {

    @FXML
    private AnchorPane anchorPaneLoginRecords;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearchByID;
    @FXML
    private TextField txtLoginID;
    @FXML
    private Button btnSearchByDate;
    @FXML
    private DatePicker dateLoginDate;
    @FXML
    private TableView<Login> tblLoginRecords;
    @FXML
    private TableColumn<Login, String> tblLoginID;
    @FXML
    private TableColumn<Login, String> tblUserame;
    @FXML
    private TableColumn<Login, String> tblDate;
    @FXML
    private TableColumn<Login, String> tblTime;
    @FXML
    private Button btnCancel;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Login login;
       
    private LoginDAO loginDAO = new LoginDAO();
    
    
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
        dateLoginDate.setValue(LocalDate.now());
        setTableViewByDate();
    }    


    @FXML
    private void tblLoginRecordsOnMouseClicked(MouseEvent event) {
        login = tblLoginRecords.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }
    
    @FXML
    private void dateLoginDateOnAction(ActionEvent event) {
        setTableViewByDate();
    }

    @FXML
    private void btnSearchByIDOnAction(ActionEvent event) {
        setTableViewByID();
         if (!txtLoginID.getText().isEmpty()) {
            
            if (!setTableViewByID()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid LoginID");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("LoginID required to search");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSearchByDateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private void setFields() {
        txtLoginID.setText(login.getLoginID());
        dateLoginDate.setValue(LocalDate.parse(login.getDate()));
    }
    
    private void clearFields() {
        txtLoginID.setText("");
        dateLoginDate.setValue(LocalDate.now());
        setTableViewByDate();
    }

    private void setTableViewByDate() {
        ObservableList<Login> obslist = FXCollections.observableArrayList();
        ArrayList<Login> loginList = loginDAO.getLoginRecordsByDate(dateLoginDate.getValue().toString());
        
        for(Login loginRow : loginList){
            obslist.add(loginRow);
        }
        
        tblLoginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));  
        tblUserame.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        tblLoginRecords.setItems(obslist);
    
    }
    
    private boolean setTableViewByID() {
        boolean valid  = false;
        
        ObservableList<Login> obslist = FXCollections.observableArrayList();
        ArrayList<Login> loginList = loginDAO.getLoginRecordsByLoginID(txtLoginID.getText());
        
        if (!loginList.isEmpty()) {
            for(Login loginRow : loginList){
                obslist.add(loginRow);
                valid  = true;
            }

            tblLoginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));  
            tblUserame.setCellValueFactory(new PropertyValueFactory<>("username"));
            tblDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tblTime.setCellValueFactory(new PropertyValueFactory<>("time"));

            tblLoginRecords.setItems(obslist);
        }

        return valid;
    }
    
}
