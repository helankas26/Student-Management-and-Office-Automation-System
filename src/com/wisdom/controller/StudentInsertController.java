/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.wisdom.model.Admission;
import com.wisdom.model.Login;
import com.wisdom.model.Staff;
import com.wisdom.model.Student;
import com.wisdom.model.dao.LoginDAO;
import com.wisdom.model.dao.StudentDAO;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class StudentInsertController implements Initializable {

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
    private ComboBox<String> cmbTitle;
    @FXML
    private TextField txtParentName;
    @FXML
    private DatePicker dateJoinedDate;
    @FXML
    private TextField txtTelNo;
    @FXML
    private TextArea txaAddress;
    @FXML
    private TextField txtAdmissionFee;
    @FXML
    private JFXCheckBox chkAdmissionAuthorized;
    @FXML
    private Button btnSave;
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
    private AnchorPane anchorPaneStudentInsert;
    
    private Login loginUser;
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Student student;
    private Admission admission;
       
    private LoginDAO loginDAO = new LoginDAO();
    private StudentDAO studentDAO = new StudentDAO();
    
    public void setloginUser(Login login) {
        this.loginUser = login;
    }
    
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
        dateJoinedDate.setValue(LocalDate.now());
        
        setTableView();
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
    private void chkAdmissionAuthorizedOnAction(ActionEvent event) {
        if (loginUser.getPrivilege().equals("Administrator")) {
            
            if (chkAdmissionAuthorized.isSelected()) {
                txtAdmissionFee.setDisable(false);
            } else {
                txtAdmissionFee.setText("700.00");
                txtAdmissionFee.setDisable(true);
            }
            
        } else if (loginUser.getPrivilege().equals("Standard")) {
            
            if (chkAdmissionAuthorized.isSelected()) {
                
                TextInputDialog dialog = new TextInputDialog();
                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/com/wisdom/resources/wisdom-title.png").toString()));
                dialog.setTitle("Confirm Password");
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.setHeaderText("Please confirm password!");
                dialog.setGraphic(new ImageView(this.getClass().getResource("/com/wisdom/resources/icons8-locked-with-key-48.png").toString()));

                dialog.getDialogPane().getButtonTypes().clear();
                ButtonType confirm = new ButtonType("Confirm ", ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                PasswordField password = new PasswordField();
                password.setPromptText("Password");

                grid.add(new Label("Password:"), 0, 0);
                grid.add(password, 1, 0);

                Node confirmButton = dialog.getDialogPane().lookupButton(confirm);
                confirmButton.setDisable(true);

                password.textProperty().addListener((observable, oldValue, newValue) -> {
                    confirmButton.setDisable(newValue.trim().isEmpty());
                });

                dialog.getDialogPane().setContent(grid);

                Optional<String> result = dialog.showAndWait();

                if (result.isPresent() && result.get() != null){
                    loginUser.setPassword(password.getText().trim());
                    
                    if (loginDAO.verifyPassword(loginUser)) {
                        if (chkAdmissionAuthorized.isSelected()) {
                            txtAdmissionFee.setDisable(false);
                            txtAdmissionFee.requestFocus();
                        } else {
                            txtAdmissionFee.setText("700.00");
                            txtAdmissionFee.setDisable(true);
                        }
                    } else {
                        chkAdmissionAuthorized.setSelected(false);
                    }

                } else {

                    if (chkAdmissionAuthorized.isSelected()) {
                        chkAdmissionAuthorized.setSelected(false);
                    } else {
                        chkAdmissionAuthorized.setSelected(true);
                    }
                }
                
            } else {
                txtAdmissionFee.setText("700.00");
                txtAdmissionFee.setDisable(true);
                chkAdmissionAuthorized.setSelected(false);
            }
        }
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws IOException {
        if (!isFieldsEmpty()) {
            student = new Student();
            admission = new Admission();
            admission.setStaff(new Staff());
            
            getFields();
            
            if (studentDAO.insertStudent(student, admission)) {
                setTableView();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Insert Successful");
                alert.setHeaderText("Student ID : " + student.getUserID());
                alert.setContentText("Click Next to add to classes");
                ButtonType Next = new ButtonType("Next");
                alert.getDialogPane().getButtonTypes().setAll(Next, ButtonType.CANCEL);
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == Next) {
                    FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/StudentClassInsert.fxml"));
                    AnchorPane pane = loder.load();

                    StudentClassInsertController studentClassInsertController = loder.getController();
                    studentClassInsertController.setBorderPaneClose(borderPaneMain);
                    studentClassInsertController.setAnchorpaneClose(anchorPaneMainCenter);
                    borderPaneMain.setCenter(null);
                    borderPaneMain.setCenter(pane);
                    
                } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
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
        return txtFirstName.getText().isEmpty() || cmbGrade.getValue() == null ||
                dateDoB.getValue() == null || txtSchool.getText().isEmpty() ||
                Gender.getSelectedToggle() == null || Medium.getSelectedToggle() == null ||
                txtEmail.getText().isEmpty() || cmbTitle.getValue() == null ||
                txtParentName.getText().isEmpty() || txtTelNo.getText().isEmpty() ||
                txaAddress.getText().isEmpty() || dateJoinedDate.getValue() == null ||
                txtAdmissionFee.getText().isEmpty() || loginUser.getUser().getUserID().isEmpty();
    }
    
    private void getFields() {
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
        admission.getStaff().setUserID(loginUser.getUser().getUserID());
        admission.setAdmissionDate(dateJoinedDate.getValue().toString());
        admission.setAdmissionFee(Double.parseDouble(txtAdmissionFee.getText()));
    }
    
    private void clearFields() {
        txtInitial.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        cmbGrade.setValue(null);
        dateDoB.setValue(null);
        txtSchool.setText("");
        Gender.selectToggle(radMale);
        Medium.selectToggle(radSinhala);
        txtEmail.setText("");
        cmbTitle.setValue(null);
        txtParentName.setText("");
        txtTelNo.setText("");
        txaAddress.setText("");
        dateJoinedDate.setValue(LocalDate.now());
        txtAdmissionFee.setText("700.00");
        txtAdmissionFee.setDisable(true);
        chkAdmissionAuthorized.setSelected(false);
    }
    
    private void setTableView() {
        ObservableList<Student> obslist = FXCollections.observableArrayList();
        ArrayList<Student> studentList = studentDAO.getStudentActive();
        
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
