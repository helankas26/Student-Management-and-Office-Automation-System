/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Login;
import com.wisdom.model.User;
import com.wisdom.util.DBConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class LoginDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public void verifyLogin(Login login) {
        try {
            con = dbConnectionUtil.getConnection();
            
            String loginData = "SELECT * FROM login WHERE UserName = ?";
            preparedStatement = con.prepareStatement(loginData, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getUsername());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                login.setLoginID(resultSet.getString("LoginID"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setStatus(resultSet.getString("Status"));
                
                if (login.getPassword().equals(resultSet.getString("Password"))) {
                    login.setPassword("");
                    String loginStaff = "SELECT * FROM login_staff WHERE LoginID = ?";
                    preparedStatement = con.prepareStatement(loginStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setString(1, login.getLoginID());
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()) {
                        login.setUser(new User());
                        login.getUser().setUserID(resultSet.getString("StaffID"));
                        new UserDAO().getLoginUser(login, "Staff");
                    } else {
                        String loginTeacher = "SELECT * FROM login_teacher WHERE LoginID = ?";
                        preparedStatement = con.prepareStatement(loginTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        preparedStatement.setString(1, login.getLoginID());
                        resultSet = preparedStatement.executeQuery();
                        
                        if (resultSet.next()) {
                            login.setUser(new User());
                            login.getUser().setUserID(resultSet.getString("TeacherID"));
                            new UserDAO().getLoginUser(login, "Teacher");
                        }
                    }
                    
                } else {
                    login.setPassword(null);
                }

            } else {
                login.setLoginID(null);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void recordLogin(Login login) {
        try {
            con = dbConnectionUtil.getConnection();
            
            String loginRecord = "INSERT INTO login_record (LoginID) VALUES (?)";
            preparedStatement = con.prepareStatement(loginRecord, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean verifyPassword(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String password = "SELECT Password FROM login WHERE LoginID = ?";
            preparedStatement = con.prepareStatement(password, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                if (login.getPassword().equals(resultSet.getString("Password"))) {
                    login.setPassword(null);
                    valid = true;
                }
            } else {
                valid = false;
                login.setPassword(null);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
}
