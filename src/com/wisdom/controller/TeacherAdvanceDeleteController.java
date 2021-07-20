/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Advance;
import com.wisdom.model.Teacher;
import com.wisdom.model.dao.AdvanceDAO;
import com.wisdom.model.dao.TeacherDAO;
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
public class TeacherAdvanceDeleteController implements Initializable {

    @FXML
    private AnchorPane anchorPaneTeacherAdvanceDelete;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private DatePicker dateAdvanceDate;
    @FXML
    private ComboBox<Teacher> cmbTeacher;
    @FXML
    private TextField txtAdvanceID;
    @FXML
    private TextField txtAmonut;
    @FXML
    private TableView<Advance> tblAdvance;
    @FXML
    private TableColumn<Advance, String> tblAdvanceID;
    @FXML
    private TableColumn<Advance, Teacher> tblTeacher;
    @FXML
    private TableColumn<Advance, String> tblTeacherID;
    @FXML
    private TableColumn<Advance, String> tblDescription;
    @FXML
    private TableColumn<Advance, String> tblAmount;
    @FXML
    private TableColumn<Advance, String> tblAdvanceDate;
    @FXML
    private TableColumn<Advance, String> tblStaff;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    @FXML
    private TextArea txaDescription;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Advance advance;
       
    private AdvanceDAO advanceDAO = new AdvanceDAO();
    private TeacherDAO teacherDAO = new TeacherDAO();
    
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
        ObservableList<Teacher> cmbTeacherName = FXCollections.observableArrayList(teacherDAO.getTeacherActive());
        cmbTeacher.setItems(cmbTeacherName);
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
    private void btnDeleteOnAction(ActionEvent event) {
        if (!txtAdvanceID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete advance : " + txtAdvanceID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                advance = new Advance();

                getFields();

                if (advanceDAO.deleteAdvance(advance)) {
                    
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                    stage2.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.setTitle("Information");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Delete successful");

                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
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
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    Stage stage3 = (Stage) alert3.getDialogPane().getScene().getWindow();
                    stage3.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert3.initModality(Modality.APPLICATION_MODAL);
                    alert3.setTitle("Warning");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Invalid AdvanceID");
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
            alert.setContentText("AdvanceID required");
            alert.showAndWait();
        }
    }
    
    private void getFields() {
        advance.setAdvanceID(txtAdvanceID.getText());;
    }
    
    private void setFields() {
        cmbTeacher.setValue(advance.getTeacher());
        dateAdvanceDate.setValue(LocalDate.parse(advance.getAdvanceDate()));
        txtAdvanceID.setText(advance.getAdvanceID());
        txaDescription.setText(advance.getDiscription());
        txtAmonut.setText(Double.toString(advance.getAdvanceAmount()));
    }
    
    private void clearFields() {
        cmbTeacher.setValue(null);
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
            advanceList = advanceDAO.getTeacherAdvanceByDate(dateAdvanceDate.getValue().toString());
            
            for(Advance advanceRow : advanceList){
                obslist.add(advanceRow);
            }
        }
        
        tblAdvanceID.setCellValueFactory(new PropertyValueFactory<>("advanceID"));
        tblTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tblTeacherID.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getTeacher(), "userID"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("discription"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("advanceAmount"));
        tblAdvanceDate.setCellValueFactory(new PropertyValueFactory<>("advanceDate"));
        tblStaff.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaffHandler(), "firstName"));
        
        tblAdvance.setItems(obslist);
    }
    
}
