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
import com.wisdom.model.Staff;
import com.wisdom.model.Teacher;
import com.wisdom.model.User;
import com.wisdom.model.dao.LoginDAO;
import com.wisdom.model.dao.StaffDAO;
import com.wisdom.model.dao.TeacherDAO;
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
import javafx.scene.control.RadioButton;
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
public class UserInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneUserInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtLoginID;
    @FXML
    private TextField txtUserID;
    @FXML
    private TextField txtUsername;
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
    private JFXRadioButton radStaff;
    @FXML
    private ToggleGroup For;
    @FXML
    private JFXRadioButton radTeacher;
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
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Staff> tblStaff;
    @FXML
    private TableColumn<Staff, String> tblStaffID;
    @FXML
    private TableColumn<Staff, String> tblStaffName;
    @FXML
    private TableView<Teacher> tblTeacher;
    @FXML
    private TableColumn<Teacher, String> tblTeacherID;
    @FXML
    private TableColumn<Teacher, String> tblTeacherName;
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
    private JFXCheckBox chkShow;
    @FXML
    private TextField txtPasswordShow;
    
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
    
    private RadioButton getPrivilege() {
        RadioButton radGender =  (RadioButton) Privilege.getSelectedToggle();
        return radGender;
    }
    
    private RadioButton getFor() {
        RadioButton radGender =  (RadioButton) For.getSelectedToggle();
        return radGender;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setStaffUserTableView();
        setTeacherUserTableView();
    }    


    @FXML
    private void tblStaffOnMouseClicked(MouseEvent event) {
        Staff staff = tblStaff.getSelectionModel().getSelectedItem();
        txtUserID.setText(staff.getUserID());
        
    }

    @FXML
    private void tblTeacherOnMouseClicked(MouseEvent event) {
        Teacher teacher = tblTeacher.getSelectionModel().getSelectedItem();
        txtUserID.setText(teacher.getUserID());
    }
    
    @FXML
    private void radForOnAction(ActionEvent event) {
        if (radStaff.isSelected()) {
            txtUserID.setText("");
            tblTeacher.setVisible(false);
            tblStaff.setVisible(true);
            setStaffTableView();
        } else if (radTeacher.isSelected()) {
            txtUserID.setText("");
            tblStaff.setVisible(false);
            tblTeacher.setVisible(true);
            setTeacherTableView();
        }
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
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
    private void btnSaveOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            if (chkShow.isSelected()) {
                if (txtPasswordConfirm.getText().equals(txtPasswordShow.getText())) {
                    loginInsert();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Password does not match");
                    alert.showAndWait();            
                }

            } else {
                if (txtPasswordConfirm.getText().equals(txtPassword.getText())) {
                    loginInsert();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Password does not match");
                    alert.showAndWait();
                }
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

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private void loginInsert() {
        login = new Login();

        getFields();
        String loginFor = getFor().getText();

        if (loginDAO.insertLogin(login, loginFor)) {
            setStaffUserTableView();
            setTeacherUserTableView();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Insert Successful");
            alert.setHeaderText("Login ID : " + login.getLoginID());
            alert.setContentText("Login access granted Successfully");

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
            alert.setContentText("Username and Password must be unique");
            alert.showAndWait();
        }
    }
    
    private boolean isFieldsEmpty() {
        return txtUsername.getText().isEmpty() || (txtPassword.getText().isEmpty() && txtPasswordShow.getText().isEmpty()) ||
                txtPasswordConfirm.getText().isEmpty() || Privilege.getSelectedToggle() == null ||
                For.getSelectedToggle() == null || txtUserID.getText().isEmpty();
    }
    
    private void getFields() {
        login.setUserName(txtUsername.getText());
        login.setPassword(txtPasswordConfirm.getText());
        login.setPrivilege(getPrivilege().getText());
        login.setUser(new User());
        login.getUser().setUserID(txtUserID.getText());
    }
    
    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtPasswordShow.setText("");
        txtPasswordConfirm.setText("");
        chkShow.setSelected(false);
        Privilege.selectToggle(radGuess);
        For.selectToggle(null);
        txtUserID.setText("");
        tblStaff.setVisible(false);
        tblTeacher.setVisible(false);
    }
    
    private void setStaffTableView() {
        ObservableList<Staff> obslist = FXCollections.observableArrayList();
        ArrayList<Staff> staffList = new StaffDAO().getStaffNotInLogin();
        
        for(Staff staffRow : staffList){
            obslist.add(staffRow);
        }
        
        tblStaffID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblStaffName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        tblStaff.setItems(obslist);
    
    }
    
    private void setTeacherTableView() {
        ObservableList<Teacher> obslist = FXCollections.observableArrayList();
        ArrayList<Teacher> teacherList = new TeacherDAO().getTeacherNotInLogin();
        
        for(Teacher teacherRow : teacherList){
            obslist.add(teacherRow);
        }
        
        tblTeacherID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblTeacherName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        
        tblTeacher.setItems(obslist);
    
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

    
    
}
