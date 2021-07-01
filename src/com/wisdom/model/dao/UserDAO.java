/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Login;
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
public class UserDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public void getLoginUser(Login login, String user) {
        try {
            con = dbConnectionUtil.getConnection();
            switch (user) {
                case "Staff":
                    String Staff = "SELECT FirstName FROM staff WHERE StaffID = ?";
                    preparedStatement = con.prepareStatement(Staff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setString(1, login.getUser().getUserID());
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()) {
                        login.getUser().setFirstName(resultSet.getString("FirstName"));
                    }
                    
                    break;
                    
                case "Teacher":
                    String Teacher = "SELECT FirstName FROM teacher WHERE TeacherID = ?";
                    preparedStatement = con.prepareStatement(Teacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setString(1, login.getUser().getUserID());
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()) {
                        login.getUser().setFirstName(resultSet.getString("FirstName"));
                    }
                    
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
}
