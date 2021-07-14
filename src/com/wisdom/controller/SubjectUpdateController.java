/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Category;
import com.wisdom.model.Subject;
import com.wisdom.model.dao.CategoryDAO;
import com.wisdom.model.dao.SubjectDAO;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class SubjectUpdateController implements Initializable {

    @FXML
    private AnchorPane anchorPaneSubjectUpdate;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSubjectID;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private RadioButton radSinhala;
    @FXML
    private ToggleGroup Medium;
    @FXML
    private RadioButton radEnglish;
    @FXML
    private RadioButton radTamil;
    @FXML
    private ComboBox<Category> cmbCategory;
    @FXML
    private TableView<Subject> tblSubject;
    @FXML
    private TableColumn<Subject, String> tblSubjectID;
    @FXML
    private TableColumn<Subject, String> tblSubjectName;
    @FXML
    private TableColumn<Subject, String> tblMedium;
    @FXML
    private TableColumn<Subject, String> tblCategoryID;
    @FXML
    private TableColumn<Subject, String> tblCategoryName;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEdit;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Subject subject;
       
    private SubjectDAO subjectDAO = new SubjectDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    
    public void setAnchorpaneClose(AnchorPane anchorPaneMainCenter) {
        this.anchorPaneMainCenter = anchorPaneMainCenter;
    }

    public void setBorderPaneClose(BorderPane borderPaneMain) {
        this.borderPaneMain = borderPaneMain;
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
        ObservableList<Category> cmbCategoryName = FXCollections.observableArrayList(categoryDAO.getCategory());
        cmbCategory.setItems(cmbCategoryName);
        
        setTableView();
    }    


    @FXML
    private void tblSubjectOnMouseClicked(MouseEvent event) {
        subject = tblSubject.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void btnSearchOnAction(ActionEvent event) {
        if (!txtSubjectID.getText().isEmpty()) {
            subject = new Subject();
            
            subject.setSubjectID(txtSubjectID.getText());
            
            if (subjectDAO.getSubject(subject)) {
                setFields();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Invalid SubjectID");
                alert.showAndWait();
            }
  
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("SubjectID required to search");
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
            subject = new Subject();
            
            getFields();
            
            if (subjectDAO.updateSubject(subject)) {
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
                alert.setContentText("Invalid SubjectID");
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
        return txtSubjectID.getText().isEmpty() || txtSubjectName.getText().isEmpty() || 
                cmbCategory.getValue() == null || Medium.getSelectedToggle() == null;
    }
    
    private void getFields() {
        subject.setSubjectID(txtSubjectID.getText());
        subject.setSubjectName(txtSubjectName.getText() + " ( " + getMedium().getText() + " )");
        subject.setCategory(new Category());
        subject.getCategory().setCategoryID(cmbCategory.getSelectionModel().getSelectedItem().getCategoryID());
        subject.setMedium(getMedium().getText());
    }
    
    private void setFields() {
        txtSubjectID.setText(subject.getSubjectID());
        txtSubjectName.setText(subject.getSubjectName());
        cmbCategory.setValue(subject.getCategory());
        Medium.selectToggle((subject.getMedium().equals("Sinhala")) ? radSinhala : 
                (subject.getMedium().equals("English")) ? radEnglish : radTamil);
    }
    
    private void clearFields() {
        txtSubjectID.setText("");
        txtSubjectName.setText("");
        cmbCategory.setValue(null);
        Medium.selectToggle(null);
    }
    
    private void setTableView() {
        ObservableList<Subject> obslist = FXCollections.observableArrayList();
        ArrayList<Subject> subjectList = subjectDAO.getSubject();
        
        for(Subject subjectRow : subjectList){
            obslist.add(subjectRow);
        }
        
        tblSubjectID.setCellValueFactory(new PropertyValueFactory<>("SubjectID"));
        tblSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tblMedium.setCellValueFactory(new PropertyValueFactory<>("medium"));
        tblCategoryID.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getCategory(), "categoryID"));
        tblCategoryName.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getCategory(), "categoryName"));
        
        tblSubject.setItems(obslist);
    
    }
    
}
