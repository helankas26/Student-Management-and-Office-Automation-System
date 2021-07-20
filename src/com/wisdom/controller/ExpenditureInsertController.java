/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Expenditure;
import com.wisdom.model.Login;
import com.wisdom.model.Staff;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class ExpenditureInsertController implements Initializable {

    @FXML
    private AnchorPane anchorPaneExpenditureInsert;
    @FXML
    private JFXButton btnCenterClose;
    @FXML
    private Label lblTitle;
    @FXML
    private TextField txtExpense;
    @FXML
    private DatePicker dateExpenseDate;
    @FXML
    private TextField txtAmonut;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TableView<Expenditure> tblExpenditure;
    @FXML
    private TableColumn<Expenditure, String> tblExpense;
    @FXML
    private TableColumn<Expenditure, String> tblExpenseID;
    @FXML
    private TableColumn<Expenditure, Double> tblAmount;
    @FXML
    private TableColumn<Expenditure, String> tblDate;
    @FXML
    private TableColumn<Expenditure, String> tblStaff;
    
    private Login loginUser;
    private AnchorPane anchorPaneMainCenter;
    private BorderPane borderPaneMain;
    
    private Expenditure expenditure;
       
    private ExpenditureDAO expenditureDAO = new ExpenditureDAO();
    
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
        dateExpenseDate.setValue(LocalDate.now());
        txtAmonut.setText("0.00");
        
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
    private void btnSaveOnAction(ActionEvent event) {
        if (!isFieldsEmpty()) {
            expenditure = new Expenditure();
            
            getFields();
            
            if (expenditureDAO.insertExpenditure(expenditure)) {
                setTableView();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("/com/wisdom/resources/wisdom-title.png"));
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Insert Successful");
                alert.setHeaderText(null);
                alert.setContentText("Expenditure insert Successfully");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    clearFields();
                } else {
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
            alert.setContentText("All fields are required");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }
    
    private boolean isFieldsEmpty() {
        return txtExpense.getText().isEmpty() || dateExpenseDate.getValue() == null || txtAmonut.getText().isEmpty();
    }
    
    private void getFields() {
        expenditure.setExpense(txtExpense.getText());
        expenditure.setExpenseDate(dateExpenseDate.getValue().toString());
        expenditure.setExpenseAmount(Double.parseDouble(txtAmonut.getText()));
        expenditure.setStaff(new Staff());
        expenditure.getStaff().setUserID(loginUser.getUser().getUserID());
    }
    
    private void clearFields() {
        txtExpense.setText("");
        dateExpenseDate.setValue(null);
        dateExpenseDate.setValue(LocalDate.now());
        txtAmonut.setText("0.00");
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
