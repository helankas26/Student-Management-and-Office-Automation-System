/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Student;
import com.wisdom.model.dao.StudentDAO;
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
public class StudentMakeActiveController implements Initializable {

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
    private JFXButton btnMakeActiveStudent;
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
    private ComboBox<String> cmbGrade;
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
    private TextField txtTelNo;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> tblStudentID;
    @FXML
    private TableColumn<Student, String> tblInitial;
    @FXML
    private TableColumn<Student, String> tblFirstName;
    @FXML
    private TableColumn<Student, String> tblLastName;
    @FXML
    private TableColumn<Student, String> tblDoB;
    @FXML
    private TableColumn<Student, String> tblSex;
    @FXML
    private TableColumn<Student, String> tblMedium;
    @FXML
    private TableColumn<Student, String> tblTelNo;
    @FXML
    private TableColumn<Student, String> tblAddress;
    @FXML
    private TableColumn<Student, String> tblEmail;
    @FXML
    private TableColumn<Student, String> tblGrade;
    @FXML
    private TableColumn<Student, String> tblSchool;
    @FXML
    private TableColumn<Student, String> tblJoinedDate;
    @FXML
    private TableColumn<Student, String> tblTitle;
    @FXML
    private TableColumn<Student, String> tblParentName;
    @FXML
    private AnchorPane anchorPaneStudentMakeActive;
    @FXML
    private ComboBox<String> cmbTitle;
    @FXML
    private TextArea txaAddress;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Student student;
       
    private StudentDAO studentDAO = new StudentDAO();
    
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

    private RadioButton getMedium() {
        RadioButton radMedium =  (RadioButton) Medium.getSelectedToggle();
        return radMedium;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbGrade.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", 
                "9", "10", "11", "12", "13", "Other");
        
        cmbTitle.getItems().addAll("Mr.", "Mrs.");
        
        setTableView();
    } 
    
    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
        student = table.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnImgStudentOnAction(ActionEvent event) {
    }

    @FXML
    private void btnMakeActiveStudentOnAction(ActionEvent event) {
        if (!txtStudentID.getText().isEmpty()) {
            student = new Student();

            student.setUserID(txtStudentID.getText());
            student.setStatus("active");
            
            if (studentDAO.changeStudentStatus(student)) {
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
                alert.setContentText("Invalid studentID");
                alert.showAndWait();
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

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtStudentID.getText().isEmpty()) {
            student = new Student();
            
            student.setUserID(txtStudentID.getText());
            
            if (studentDAO.getPastStudent(student)) {
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid studentID or active student");
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
    private void btnEditOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            student = new Student();
            
            getFields();
            
            if (studentDAO.updateStudent(student)) {
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
                alert.setContentText("Invalid studentID or active student");
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
        if (!txtStudentID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete student : " + txtStudentID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                student = new Student();

                student.setUserID(txtStudentID.getText());
                student.setStatus("past");

                if (studentDAO.deleteStudent(student)) {
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
                    alert3.setContentText("Invalid studentID or active student");
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

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private boolean isFieldsEmpty() {
        return txtStudentID.getText().isEmpty() || txtFirstName.getText().isEmpty() || 
                cmbGrade.getValue() == null || dateDoB.getValue() == null || 
                txtSchool.getText().isEmpty() || Gender.getSelectedToggle() == null || 
                Medium.getSelectedToggle() == null || txtEmail.getText().isEmpty() || 
                cmbTitle.getValue() == null || txtParentName.getText().isEmpty() || 
                txtTelNo.getText().isEmpty() || txaAddress.getText().isEmpty();
    }
    
    private void getFields() {
        student.setUserID(txtStudentID.getText());
        student.setInitial(txtInitial.getText().toUpperCase().trim());
        student.setFirstName(txtFirstName.getText());
        student.setLastName(txtLastName.getText().trim());
        student.setGrade(cmbGrade.getValue());
        student.setDateOfBirth((dateDoB.getValue().toString()));
        student.setSchool(txtSchool.getText());
        student.setSex(getGender().getText());
        student.setMedium(getMedium().getText());
        student.setEmail(txtEmail.getText());
        student.getParent().setTitle(cmbTitle.getValue());
        student.getParent().setParentName(txtParentName.getText());
        student.setTelNo(txtTelNo.getText());
        student.setAddress(txaAddress.getText());
        student.setJoinedDate(dateJoinedDate.getValue().toString());
        student.setStatus("past");
    }
    
    private void setFields() {
        txtStudentID.setText(student.getUserID());
        txtInitial.setText(student.getInitial());
        txtFirstName.setText(student.getFirstName());
        txtLastName.setText(student.getLastName());
        cmbGrade.setValue(student.getGrade());
        dateDoB.setValue(LocalDate.parse(student.getDateOfBirth()));
        txtSchool.setText(student.getSchool());
        Gender.selectToggle((student.getSex().equals("Male")) ? radMale : radFemale);
        Medium.selectToggle((student.getMedium().equals("Sinhala")) ? radSinhala : 
                (student.getMedium().equals("English")) ? radEnglish : radTamil);
        
        txtEmail.setText(student.getEmail());
        cmbTitle.setValue(student.getParent().getTitle());
        txtParentName.setText(student.getParent().getParentName());
        txtTelNo.setText(student.getTelNo());
        txaAddress.setText(student.getAddress());
        dateJoinedDate.setValue(LocalDate.parse(student.getJoinedDate()));
    }
    
    private void clearFields() {
        txtStudentID.setText("");
        txtInitial.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        cmbGrade.setValue(null);
        dateDoB.setValue(null);
        txtSchool.setText("");
        Gender.selectToggle(null);
        Medium.selectToggle(null);
        txtEmail.setText("");
        cmbTitle.setValue(null);
        txtParentName.setText("");
        txtTelNo.setText("");
        txaAddress.setText("");
        dateJoinedDate.setValue(null);
    }
    
    private void setTableView() {
        ObservableList<Student> obslist = FXCollections.observableArrayList();
        ArrayList<Student> studentList = studentDAO.getStudentPast();
        
        for(Student studentRow : studentList){
            obslist.add(studentRow);
        }
        
        tblStudentID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblInitial.setCellValueFactory(new PropertyValueFactory<>("initial"));
        tblFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblDoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        tblMedium.setCellValueFactory(new PropertyValueFactory<>("medium"));
        tblTelNo.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblSchool.setCellValueFactory(new PropertyValueFactory<>("school"));
        tblJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        tblTitle.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getParent(), "title"));
        tblParentName.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getParent(), "parentName"));
        
        table.setItems(obslist);
    
    }
    
}
