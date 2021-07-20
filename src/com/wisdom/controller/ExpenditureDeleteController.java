/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Expenditure;
import com.wisdom.model.dao.ExpenditureDAO;
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
public class ExpenditureDeleteController implements Initializable {

    @FXML
    private AnchorPane anchorPaneExpenditureDelete;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtExpenseID;
    @FXML
    private DatePicker dateExpenseDate;
    @FXML
    private TextField txtExpense;
    @FXML
    private TextField txtAmonut;
    @FXML
    private TableView<Expenditure> tblExpenditure;
    @FXML
    private TableColumn<Expenditure, String> tblExpense;
    @FXML
    private TableColumn<Expenditure, String> tblExpenseID;
    @FXML
    private TableColumn<Expenditure, String> tblAmount;
    @FXML
    private TableColumn<Expenditure, String> tblDate;
    @FXML
    private TableColumn<Expenditure, String> tblStaff;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnDelete;
    
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Expenditure expenditure;
       
    private ExpenditureDAO expenditureDAO = new ExpenditureDAO();
    
    
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
        dateExpenseDate.setValue(LocalDate.now());
        txtAmonut.setText("");
        
        setTableView();
    }    

    @FXML
    private void btnCenterCloseOnAction(ActionEvent event) {
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(anchorPaneMainCenter);
    }

    @FXML
    private void dateExpenseDateOnAction(ActionEvent event) {
        setTableView();
    }

    @FXML
    private void tblExpenditureOnMouseClicked(MouseEvent event) {
        expenditure = tblExpenditure.getSelectionModel().getSelectedItem();
        setFields();
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!txtExpenseID.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to delete expenditure : " + txtExpenseID.getText());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                expenditure = new Expenditure();

                getFields();

                if (expenditureDAO.deleteExpenditure(expenditure)) {
                    
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                    stage2.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert2.initModality(Modality.APPLICATION_MODAL);
                    alert2.setTitle("Information");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Delete successful");

                    Optional<ButtonType> result2 = alert2.showAndWait();
                    if (result2.isPresent() && result2.get() == ButtonType.OK) {
                        LocalDate date = dateExpenseDate.getValue();
                        clearFields();
                        dateExpenseDate.setValue(date);
                        setTableView();
                    } else {
                        LocalDate date = dateExpenseDate.getValue();
                        clearFields();
                        dateExpenseDate.setValue(date);
                        setTableView();
                    }

                } else {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    Stage stage3 = (Stage) alert3.getDialogPane().getScene().getWindow();
                    stage3.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                    alert3.initModality(Modality.APPLICATION_MODAL);
                    alert3.setTitle("Warning");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Invalid ExpenseID");
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
            alert.setContentText("ExpenseID required");
            alert.showAndWait();
        }
    }
    
    private void getFields() {
        expenditure.setExpenseID(txtExpenseID.getText());
    }
    
    private void setFields() {
        dateExpenseDate.setValue(LocalDate.parse(expenditure.getExpenseDate()));
        txtExpenseID.setText(expenditure.getExpenseID());
        txtExpense.setText(expenditure.getExpense());
        txtAmonut.setText(Double.toString(expenditure.getExpenseAmount()));
    }
    
    private void clearFields() {
        dateExpenseDate.setValue(null);
        dateExpenseDate.setValue(LocalDate.now());
        txtExpenseID.setText("");
        txtExpense.setText("");
        txtAmonut.setText("");
    }
    
    private void setTableView() {
        ObservableList<Expenditure> obslist = FXCollections.observableArrayList();
        ArrayList<Expenditure> expenditureList = null;
        if (dateExpenseDate.getValue() != null) {
            expenditureList = expenditureDAO.getExpenditureByDate(dateExpenseDate.getValue().toString());
            
            for(Expenditure expenditureRow : expenditureList){
                obslist.add(expenditureRow);
            }
        }
        
        tblExpenseID.setCellValueFactory(new PropertyValueFactory<>("expenseID"));
        tblExpense.setCellValueFactory(new PropertyValueFactory<>("expense"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("expenseAmount"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("expenseDate"));
        tblStaff.setCellValueFactory(cell -> Bindings.selectString(cell.getValue().getStaff(), "firstName"));
        
        tblExpenditure.setItems(obslist);
    }
    
}
