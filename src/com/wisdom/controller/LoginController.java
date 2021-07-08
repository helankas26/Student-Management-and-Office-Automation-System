/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.controller;

import com.jfoenix.controls.JFXButton;
import com.wisdom.model.Login;
import com.wisdom.model.dao.LoginDAO;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Helanka
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private AnchorPane anchorPaneMain;
    
    Login login = null;
    LoginDAO loginDAO = new LoginDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void txtSetDefaultColor(InputMethodEvent event) {
        txtUsername.setStyle("-fx-border-color: #2e2e2e;");
        txtPassword.setStyle("-fx-border-color: #2e2e2e;");
    }
    
    @FXML
    private void btnLoginActionPerformed(ActionEvent event) throws IOException {
        if (isFieldEmpty()) {
            login = new Login();
            login.setUserName(txtUsername.getText().trim());
            login.setPassword(txtPassword.getText().trim());

            loginDAO.verifyLogin(login);

            if (login.getLoginID() != null) {
                changeField(txtUsername, "#2e2e2e", lblUsername, "");
                
                if (login.getPassword() != null) {
                    changeField(txtPassword, "#2e2e2e", lblPassword, "");
                    
                    if (login.getStatus().equals("active")) {
                        loginDAO.recordLogin(login);
                        
                        FXMLLoader loder = new FXMLLoader(getClass().getResource("/com/wisdom/view/MainDashboard.fxml"));
                        Parent root = loder.load();
                        
                        MainDashboardController mainDashboardController = loder.getController();
                        mainDashboardController.setloginUser(login);
                        
                        Scene scene = new Scene(root);
                        Stage primaryStage = new Stage();
                        primaryStage.setScene(scene);
                        primaryStage.setMaximized(true);
                        primaryStage.initStyle(StageStyle.DECORATED);
                        primaryStage.setTitle("WISDOM Institution [" + login.getUser().getFirstName() + "]");
                        primaryStage.getIcons().add(new Image("com/wisdom/resources/wisdom-title.png"));
                        primaryStage.show();
                        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                Stage primaryStage = (Stage) mainDashboardController.getAnchorPaneMain().getScene().getWindow();

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.initModality(Modality.APPLICATION_MODAL);
                                alert.initOwner(primaryStage);
                                alert.setTitle("Exit Program");
                                alert.setHeaderText("Confirm Exit");
                                alert.setContentText("Do you really want to logout?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    primaryStage = (Stage) mainDashboardController.getAnchorPaneMain().getScene().getWindow();
                                    primaryStage.close();

                                    Stage stage = new Stage();

                                    Parent root = null;
                                    try {
                                        root = FXMLLoader.load(getClass().getResource("/com/wisdom/view/Login.fxml"));
                                    } catch (IOException ex) {
                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    Scene scene = new Scene(root);
                                    stage.setTitle("WISDOM Institution");
                                    stage.getIcons().add(new Image("com/wisdom/resources/wisdom-title.png"));
                                    login = null;
                                    stage.setScene(scene);
                                    stage.show();
                                    
                                } else {
                                    event.consume();
                                }
                            }
                        });

                        Stage stage = (Stage) anchorPaneMain.getScene().getWindow();
                        stage.close();
                        
                    } else {
                        Stage stage = (Stage) anchorPaneMain.getScene().getWindow();
                        
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.initOwner(stage);
                        alert.setTitle("Error");
                        alert.setHeaderText("Access denied");
                        alert.setContentText("you are not authorized to access the system.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            txtUsername.setText("");
                            txtUsername.requestFocus();
                            txtPassword.setText("");
                        }
                    }

                } else {
                    txtPassword.setText("");
                    changeField(txtPassword, "#FF5733;", lblPassword, "Incorrect Password");
                    txtPassword.requestFocus();
                }
                
            } else {
                txtUsername.setText("");
                changeField(txtUsername, "#de2700", lblUsername, "Incorrect Username");
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        }
    }
    
    private boolean isFieldEmpty() {
        if (txtUsername.getText().equalsIgnoreCase("")) {
            changeField(txtUsername, "#de2700", lblUsername, "Username requied");
            if (txtPassword.getText().equalsIgnoreCase("")) {
                changeField(txtPassword, "#de2700", lblPassword, "Password requied");
            } else {
                changeField(txtPassword, "#2e2e2e", lblPassword, "");
            }
            txtUsername.requestFocus();
            return false;
        } else {
            changeField(txtUsername, "#2e2e2e", lblUsername, "");
            if (txtPassword.getText().equalsIgnoreCase("")) {
                changeField(txtPassword, "#de2700", lblPassword, "Password requied");
                txtPassword.requestFocus();
                return false;
            } else {
                changeField(txtPassword, "#2e2e2e", lblPassword, "");
                return true;
            }
        }
    }
    
    private void changeField(TextField field, String colorCode, Label lable, String msg) {
        field.setStyle("-fx-border-color: " + colorCode + ";");
        lable.setText(msg);
    }

}
