/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Student;
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
public class ParentDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertParent(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertParent = "INSERT INTO parent (StudentID, Title, ParentName) VALUES (?, ?, ?)";
            preparedStatement = con.prepareStatement(insertParent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getUserID());
            preparedStatement.setString(2, student.getParent().getTitle());
            preparedStatement.setString(3, student.getParent().getParentName());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return valid;
    }
    
    public boolean updateParent(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateParent = "UPDATE parent SET Title = ?, ParentName = ? WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(updateParent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getParent().getTitle());
            preparedStatement.setString(2, student.getParent().getParentName());
            preparedStatement.setString(3, student.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ParentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return valid;
    }
    
}
