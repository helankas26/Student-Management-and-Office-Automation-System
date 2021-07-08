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
import javafx.scene.control.MenuItem;
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
    private void btnStudentInsertActionPerformed(ActionEvent event) throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/StudentInsert.fxml"));
        Parent pane = loder.load();
        borderPaneMain.setCenter(pane);
    }

    @FXML
    private void btnStudentUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStudentDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStudentClassesInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStudentClassesDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnFreeCardActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnAddToAttendanceActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnPastStudentInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnPastStudentMakeActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherClassesActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherFeeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateTeacherInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateTeacherMakeActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnClassInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnClassUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnClassDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCategoryInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCategoryUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCategoryDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSubjectInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSubjectUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnSubjectDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnExtraClassActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnCancelClassActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnPastClassInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnPastClassMakeActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnFeesActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnFeesOverviewActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnAdmissionUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnAdmissionDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnExpenditureDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnTeacherAdvanceDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffAdvanceDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnAttendanceActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnStaffDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDailyScheduleActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateStaffInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateStaffMakeActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnUserInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnUserUpdateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnUserDeleteActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateUserInsertActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnDeactivateUserMakeActiveActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnLoginRecordsActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnMonthEndActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnYearEndActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnBatchEndOLActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnBatchEndALActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnLogoutActionPerformed(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) anchorPaneMain.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(primaryStage);
        alert.setTitle("Exit Program");
        alert.setHeaderText("Confirm Exit");
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
