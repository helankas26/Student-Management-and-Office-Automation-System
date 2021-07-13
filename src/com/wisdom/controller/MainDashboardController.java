/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Login;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
public class MainDashboardController implements Initializable {
    
    @FXML
    private AnchorPane anchorPaneMain;
    @FXML
    private BorderPane borderPaneMain;
    @FXML
    private Button btnAttendance;
    @FXML
    private MenuItem btnMonthEnd;
    @FXML
    private MenuItem btnYearEnd;
    @FXML
    private MenuItem btnBatchEndOL;
    @FXML
    private MenuItem btnBatchEndAL;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private AnchorPane anchorPaneMainCenter;
    @FXML
    private MenuButton btnUser;
    @FXML
    private MenuButton btnStaff;
    @FXML
    private MenuItem btnDailySchedule;
    @FXML
    private MenuButton btnProcess;
    @FXML
    private MenuButton btnStudent;
    @FXML
    private Menu btnStudentClasses;
    @FXML
    private MenuItem btnStudentClassesInsert;
    @FXML
    private MenuItem btnStudentClassesDelete;
    @FXML
    private MenuItem btnFreeCard;
    @FXML
    private MenuItem btnAddToAttendance;
    @FXML
    private MenuButton btnTeacher;
    @FXML
    private MenuItem btnTeacherFee;
    @FXML
    private MenuButton btnClass;
    @FXML
    private MenuItem btnClassUpdate;
    @FXML
    private MenuItem btnClassDelete;
    @FXML
    private MenuItem btnCancelClass;
    @FXML
    private Menu btnPastClass;
    @FXML
    private MenuButton btnFinancial;
    @FXML
    private Menu btnAdmission;
    @FXML
    private Menu btnExpenditure;
    @FXML
    private MenuItem btnExpenditureUpdate;
    @FXML
    private MenuItem btnExpenditureDelete;
    @FXML
    private Menu btnTeacherAdvance;
    @FXML
    private MenuItem btnTeacherAdvanceUpdate;
    @FXML
    private MenuItem btnTeacherAdvanceDelete;
    @FXML
    private Menu btnStaffAdvance;
    @FXML
    private MenuItem btnStaffAdvanceUpdate;
    @FXML
    private MenuItem btnStaffAdvanceDelete;
    
    private Login loginUser;
    
    public AnchorPane getAnchorPaneMain() {
        return anchorPaneMain;
    }
    
    public void setloginUser(Login login) {
        this.loginUser = login;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     @FXML
    private void btnStudentOnMousePressed(MouseEvent event) {
        switch (loginUser.getPrivilege()) {
            case "Administrator":
                if (btnStudent.isShowing()) {
                    btnStudent.hide();
                } else {
                    btnStudent.show();
                }   
                break;
                
            case "Standard":
                if (btnStudent.isShowing()) {
                    btnStudent.hide();
                } else {
                    btnStudentClasses.getItems().remove(btnStudentClassesDelete);
                    btnStudentClasses.getItems().remove(btnFreeCard);
                    btnStudent.show();
                }   
                break;
                
            case "Guess":
                if (btnStudent.isShowing()) {
                    btnStudent.hide();
                } else {
                    btnStudent.getItems().clear();
                    btnStudent.getItems().add(btnStudentClasses);
                    btnStudentClasses.getItems().clear();
                    btnStudentClasses.getItems().add(btnStudentClassesInsert);
                    btnStudentClasses.getItems().add(btnAddToAttendance);
                    btnStudent.show();
                }   
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    private void btnStudentInsertOnAction(ActionEvent event) throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/StudentInsert.fxml"));
        AnchorPane pane = loder.load();
        
        StudentInsertController studentInsertController = loder.getController();
        studentInsertController.setloginUser(loginUser);
        studentInsertController.setBorderPaneClose(borderPaneMain);
        studentInsertController.setAnchorpaneClose(anchorPaneMainCenter);
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(pane);
    }

    @FXML
    private void btnStudentUpdateOnAction(ActionEvent event) throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/StudentUpdate.fxml"));
        AnchorPane pane = loder.load();
        
        StudentUpdateController studentUpdatetController = loder.getController();
        studentUpdatetController.setBorderPaneClose(borderPaneMain);
        studentUpdatetController.setAnchorpaneClose(anchorPaneMainCenter);
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(pane);
    }

    @FXML
    private void btnStudentDeleteOnAction(ActionEvent event) throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/StudentDelete.fxml"));
        AnchorPane pane = loder.load();
        
        StudentDeleteController studentDeleteController = loder.getController();
        studentDeleteController.setBorderPaneClose(borderPaneMain);
        studentDeleteController.setAnchorpaneClose(anchorPaneMainCenter);
        borderPaneMain.setCenter(null);
        borderPaneMain.setCenter(pane);
    }

    @FXML
    private void btnStudentClassesInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStudentClassesDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnFreeCardOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddToAttendanceOnAction(ActionEvent event) {
    }

    @FXML
    private void btnPastStudentInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnPastStudentMakeActiveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherOnMousePressed(MouseEvent event) {
        switch (loginUser.getPrivilege()) {
            case "Administrator":
                if (btnTeacher.isShowing()) {
                    btnTeacher.hide();
                } else {
                    btnTeacher.show();
                }   
                break;
                
            case "Standard":
                if (btnTeacher.isShowing()) {
                    btnTeacher.hide();
                } else {
                    btnTeacher.getItems().remove(btnTeacherFee);
                    btnTeacher.show();
                }   
                break;
                
            case "Guess":
                btnTeacher.hide();
                Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You are not authorized to access");
                alert.showAndWait();  
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    private void btnTeacherInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherClassesOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherFeeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateTeacherInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateTeacherMakeActiveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnClassOnMousePressed(MouseEvent event) {
        switch (loginUser.getPrivilege()) {
            case "Administrator":
                if (btnClass.isShowing()) {
                    btnClass.hide();
                } else {
                    btnClass.show();
                }   
                break;
                
            case "Standard":
                if (btnClass.isShowing()) {
                    btnClass.hide();
                } else {
                    btnClass.getItems().remove(btnClassUpdate);
                    btnClass.getItems().remove(btnClassDelete);
                    btnClass.getItems().remove(btnCancelClass);
                    btnClass.getItems().remove(btnPastClass);
                    btnClass.show();
                }   
                break;
                
            case "Guess":
                btnClass.hide();
                Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You are not authorized to access");
                alert.showAndWait();  
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    private void btnClassInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnClassUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnClassDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCategoryInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCategoryUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCategoryDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSubjectInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSubjectUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSubjectDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExtraClassOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelClassOnAction(ActionEvent event) {
    }

    @FXML
    private void btnPastClassInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnPastClassMakeActiveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnFinancialOnMousePressed(MouseEvent event) {
        switch (loginUser.getPrivilege()) {
            case "Administrator":
                if (btnFinancial.isShowing()) {
                    btnFinancial.hide();
                } else {
                    btnFinancial.show();
                }   
                break;
                
            case "Standard":
                if (btnFinancial.isShowing()) {
                    btnFinancial.hide();
                } else {
                    btnFinancial.getItems().remove(btnAdmission);
                    btnExpenditure.getItems().remove(btnExpenditureUpdate);
                    btnExpenditure.getItems().remove(btnExpenditureDelete);
                    btnTeacherAdvance.getItems().remove(btnTeacherAdvanceUpdate);
                    btnTeacherAdvance.getItems().remove(btnTeacherAdvanceDelete);
                    btnStaffAdvance.getItems().remove(btnStaffAdvanceUpdate);
                    btnStaffAdvance.getItems().remove(btnStaffAdvanceDelete);
                    btnFinancial.show();
                }   
                break;
                
            case "Guess":
                btnFinancial.hide();
                Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(primaryStage);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You are not authorized to access");
                alert.showAndWait();  
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    private void btnFeesOnAction(ActionEvent event) {
    }

    @FXML
    private void btnFeesOverviewOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAdmissionUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAdmissionDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAttendanceOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffOnMousePressed(MouseEvent event) {
        switch (loginUser.getPrivilege()) {
            case "Administrator":
                if (btnStaff.isShowing()) {
                    btnStaff.hide();
                } else {
                    btnStaff.show();
                }   
                break;
                
            case "Standard":
                if (btnStaff.isShowing()) {
                    btnStaff.hide();
                } else {
                    btnStaff.getItems().clear();
                    btnStaff.getItems().add(btnDailySchedule);
                    btnStaff.show();
                }   
                break;
                
            case "Guess":
                if (btnStaff.isShowing()) {
                    btnStaff.hide();
                } else {
                    btnStaff.getItems().clear();
                    btnStaff.getItems().add(btnDailySchedule);
                    btnStaff.show();
                }   
                break;
                
            default:
                break;
        }
    }
    
    @FXML
    private void btnStaffInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnStaffDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDailyScheduleOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateStaffInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateStaffMakeActiveOnAction(ActionEvent event) {
    }
    
    @FXML
    private void btnUserOnMousePressed(MouseEvent event) {
        if (loginUser.getPrivilege().equals("Administrator")) {
            if (btnUser.isShowing()) {
                btnUser.hide();
            } else {
                btnUser.show();
            }
        } else {
            btnUser.hide();
            Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();
        
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(primaryStage);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You are not authorized to access");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnUserInsertOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUserUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUserDeleteOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateUserOnAction(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateUserMakeActiveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnLoginRecordsOnAction(ActionEvent event) {
    }

     @FXML
    private void btnProcessOnMousePressed(MouseEvent event) {
        if (loginUser.getPrivilege().equals("Administrator")) {
            if (btnProcess.isShowing()) {
                btnProcess.hide();
            } else {
                btnProcess.show();
            }
        } else {
            btnProcess.hide();
            Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();
        
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(primaryStage);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You are not authorized to access");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void btnMonthEndOnAction(ActionEvent event) {
    }

    @FXML
    private void btnYearEndOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBatchEndOLOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBatchEndALOnAction(ActionEvent event) {
    }

    @FXML
    private void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(primaryStage);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            primaryStage = (Stage) anchorPaneMain.getScene().getWindow();
            primaryStage.close();
            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/wisdom/view/Login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("WISDOM Institution");
            stage.getIcons().add(new Image("com/wisdom/resources/wisdom-title.png"));
            loginUser = null;
            stage.setScene(scene);
            stage.show();
            
        } else {
            event.consume();
        }
    }

}
