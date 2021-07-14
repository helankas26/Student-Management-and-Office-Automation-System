/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Teacher;
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
public class TeacherUpdateController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTeacherUpdate;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private JFXButton btnImgTeacher;
    @FXML
    private TextField txtTeacherID;
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
    private DatePicker dateJoinedDate;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Teacher> table;
    @FXML
    private TableColumn<Teacher, String> tblTeacherID;
    @FXML
    private TableColumn<Teacher, String> tblTitle;
    @FXML
    private TableColumn<Teacher, String> tblInitial;
    @FXML
    private TableColumn<Teacher, String> tblFirstName;
    @FXML
    private TableColumn<Teacher, String> tblLastName;
    @FXML
    private TableColumn<Teacher, String> tblDoB;
    @FXML
    private TableColumn<Teacher, String> tblSex;
    @FXML
    private TableColumn<Teacher, String> tblTelNo;
    @FXML
    private TableColumn<Teacher, String> tblAddress;
    @FXML
    private TableColumn<Teacher, String> tblEmail;
    @FXML
    private TableColumn<Teacher, String> tblJoinedDate;
    @FXML
    private TableColumn<Teacher, String> tblQualification;
    @FXML
    private JFXButton btnDeactivateTeacher;
    @FXML
    private ImageView imgTeacher;
    @FXML
    private ComboBox<String> cmbTitle;
    @FXML
    private TextArea txaAddress;
    @FXML
    private TextArea txaQualifictions;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Teacher teacher;
       
    private TeacherDAO teacherDAO = new TeacherDAO();
    
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
        teacher = table.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnImgTeacherOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateTeacherOnAction(ActionEvent event) {
        if (!txtTeacherID.getText().isEmpty()) {
            teacher = new Teacher();

            teacher.setUserID(txtTeacherID.getText());
            teacher.setStatus("deactivate");
            
            if (teacherDAO.changeTeacherStatus(teacher)) {
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
                alert.setContentText("Invalid TeacherID");
                alert.showAndWait();
            }
   
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("TeacherID required");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtTeacherID.getText().isEmpty()) {
            teacher = new Teacher();
            
            teacher.setUserID(txtTeacherID.getText());
            
            if (teacherDAO.getTeacher(teacher)) {
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid TeacherID or deactivated teacher");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("TeacherID required to search");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void btnEditOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            teacher = new Teacher();
            
            getFields();
            
            if (teacherDAO.updateTeacher(teacher)) {
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
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private boolean isFieldsEmpty() {
        return txtTeacherID.getText().isEmpty() || cmbTitle.getValue() == null || 
                txtFirstName.getText().isEmpty() || dateDoB.getValue() == null || 
                Gender.getSelectedToggle() == null  || txtTelNo.getText().isEmpty() || 
                txaAddress.getText().isEmpty() || txtEmail.getText().isEmpty() || 
                txaQualifictions.getText().isEmpty();
    }
    
    private void getFields() {
        teacher.setUserID(txtTeacherID.getText());
        teacher.setTitle(cmbTitle.getValue());
        teacher.setInitial(txtInitial.getText().toUpperCase().trim());
        teacher.setFirstName(txtFirstName.getText());
        teacher.setLastName(txtLastName.getText().trim());
        teacher.setDateOfBirth((dateDoB.getValue().toString()));
        teacher.setSex(getGender().getText());
        teacher.setTelNo(txtTelNo.getText());
        teacher.setAddress(txaAddress.getText());
        teacher.setEmail(txtEmail.getText());
        teacher.setQualification(txaQualifictions.getText());
    }
    
    private void setFields() {
        txtTeacherID.setText(teacher.getUserID());
        cmbTitle.setValue(teacher.getTitle());
        txtInitial.setText(teacher.getInitial());
        txtFirstName.setText(teacher.getFirstName());
        txtLastName.setText(teacher.getLastName());
        dateDoB.setValue(LocalDate.parse(teacher.getDateOfBirth()));
        Gender.selectToggle((teacher.getSex().equals("Male")) ? radMale : radFemale);  
        txtTelNo.setText(teacher.getTelNo());
        txaAddress.setText(teacher.getAddress());
        txtEmail.setText(teacher.getEmail());
        dateJoinedDate.setValue(LocalDate.parse(teacher.getJoinedDate()));
        txaQualifictions.setText(teacher.getQualification());
    }
    
    private void clearFields() {
        txtTeacherID.setText("");
        cmbTitle.setValue(null);
        txtInitial.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        dateDoB.setValue(null);
        Gender.selectToggle(null);
        txtTelNo.setText("");
        txaAddress.setText("");
        txtEmail.setText("");
        txaQualifictions.setText("");
        dateJoinedDate.setValue(null);
    }
    
    private void setTableView() {
        ObservableList<Teacher> obslist = FXCollections.observableArrayList();
        ArrayList<Teacher> teacherList = teacherDAO.getTeacherActive();
        
        for(Teacher teacherRow : teacherList){
            obslist.add(teacherRow);
        }
        
        tblTeacherID.setCellValueFactory(new PropertyValueFactory<>("userID"));  
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
        tblQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        
        table.setItems(obslist);
    
    }

 
}
