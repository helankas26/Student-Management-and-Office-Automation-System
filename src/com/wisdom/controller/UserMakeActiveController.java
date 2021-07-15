/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Login;
import com.wisdom.model.dao.LoginDAO;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class UserMakeActiveController implements Initializable {

    @FXML
    private AnchorPane anchorPaneUserMakeActive;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtLoginID;
    @FXML
    private JFXButton btnMakeActiveUser;
    @FXML
    private JFXCheckBox chkShow;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPasswordShow;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswordConfirm;
    @FXML
    private JFXRadioButton radAdmin;
    @FXML
    private ToggleGroup Privilege;
    @FXML
    private JFXRadioButton radStandard;
    @FXML
    private JFXRadioButton radGuess;
    @FXML
    private TableView<Login> tblStaffUser;
    @FXML
    private TableColumn<Login, String> tblStaffLoginID;
    @FXML
    private TableColumn<Login, String> tblStaffUserame;
    @FXML
    private TableColumn<Login, String> tblStaffPrivilege;
    @FXML
    private TableColumn<Login, String> tblStaffIDUser;
    @FXML
    private TableColumn<Login, String> tblStaffNameUser;
    @FXML
    private TableView<Login> tblTeacherUser;
    @FXML
    private TableColumn<Login, String> tblTeacherLoginID;
    @FXML
    private TableColumn<Login, String> tblTeacherUsername;
    @FXML
    private TableColumn<Login, String> tblTeacherPrivilege;
    @FXML
    private TableColumn<Login, String> tblTeacherIDUser;
    @FXML
    private TableColumn<Login, String> tblTeacherNameUser;
    @FXML
    private TableView<Login> tblDeactivateUser;
    @FXML
    private TableColumn<Login, String> tblDeactivateUserLoginID;
    @FXML
    private TableColumn<Login, String> tblDeactivateUserUsername;
    @FXML
    private TableColumn<Login, String> tblDeactivateUserPrivilege;
    @FXML
    private Button btnDelete;
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
        setStaffUserTableView();
        setTeacherUserTableView();
        setDeactivateLoginTableView();
    }    

    @FXML
    private void chkShowOnAction(ActionEvent event) {
        if (chkShow.isSelected()) {
            txtPasswordShow.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtPasswordShow.setVisible(true);
            
        } else {
            txtPassword.setText(txtPasswordShow.getText());
            txtPasswordShow.setVisible(false);
            txtPassword.setVisible(true);
        }
    }

    @FXML
    private void tblDeactivateUserOnMouseClicked(MouseEvent event) {
        login = tblDeactivateUser.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtLoginID.getText().isEmpty()) {
            login = new Login();
            
            login.setLoginID(txtLoginID.getText());
            login.setStatus("deactivate");
            
            if (loginDAO.getLogin(login)) {
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid LoginID or active user");
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
    private void btnMakeActiveUserOnAction(ActionEvent event) {
        if (!txtLoginID.getText().isEmpty()) {
            login = new Login();

            login.setLoginID(txtLoginID.getText());
            login.setStatus("active");
            
            if (loginDAO.changeLoginStatus(login)) {
                setStaffUserTableView();
                setTeacherUserTableView();
                setDeactivateLoginTableView();
                
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
                alert.setContentText("Invalid LoginID or active user");
                alert.showAndWait();
            }
   
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("LoginID required");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!txtLoginID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete user : " + txtLoginID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                login = new Login();

                getFields();

                if (loginDAO.deleteUser(login)) {
                    setDeactivateLoginTableView();

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
                    alert3.setContentText("Invalid LoginID or active user");
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
            alert.setContentText("LoginID required");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtLoginID.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtPasswordShow.setText("");
        txtPasswordConfirm.setText("");
        chkShow.setSelected(false);
        Privilege.selectToggle(null);
    }
    
    private void getFields() {
        login.setLoginID(txtLoginID.getText());
        login.setStatus("deactivate");
    }
    
    private void setFields() {
        txtLoginID.setText(login.getLoginID());
        txtUsername.setText(login.getUsername());
        Privilege.selectToggle((login.getPrivilege().equals("Administrator")) ? radAdmin : 
                (login.getPrivilege().equals("Standard")) ? radStandard : radGuess);
    }
    
    private void setStaffUserTableView() {
        ObservableList<Login> obslist = FXCollections.observableArrayList();
        ArrayList<Login> loginList = loginDAO.getLoginStaffActive();
        
        for(Login loginRow : loginList){
            obslist.add(loginRow);
        }
        
        tblStaffLoginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));  
        tblStaffUserame.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblStaffPrivilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        tblStaffIDUser.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getUser(), "userID"));
        tblStaffNameUser.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getUser(), "firstName"));
        
        tblStaffUser.setItems(obslist);
    }
    
    private void setTeacherUserTableView() {
        ObservableList<Login> obslist = FXCollections.observableArrayList();
        ArrayList<Login> loginList = loginDAO.getLoginTeacherActive();
        
        for(Login loginRow : loginList){
            obslist.add(loginRow);
        }
        
        tblTeacherLoginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));  
        tblTeacherUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblTeacherPrivilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        tblTeacherIDUser.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getUser(), "userID"));
        tblTeacherNameUser.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getUser(), "firstName"));
        
        tblTeacherUser.setItems(obslist);
    }
    
    private void setDeactivateLoginTableView() {
        ObservableList<Login> obslist = FXCollections.observableArrayList();
        ArrayList<Login> loginList = loginDAO.getDeactivateLogin();
        
        for(Login loginRow : loginList){
            obslist.add(loginRow);
        }
        
        tblDeactivateUserLoginID.setCellValueFactory(new PropertyValueFactory<>("loginID"));  
        tblDeactivateUserUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tblDeactivateUserPrivilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        
        tblDeactivateUser.setItems(obslist);
    }
    
}
