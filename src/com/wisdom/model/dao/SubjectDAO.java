/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Category;
import com.wisdom.model.Subject;
import com.wisdom.util.DBConnectionUtil;
import com.wisdom.util.IDGeneratorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class SubjectDAO {
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private IDGeneratorUtil idGeneratorUtil = IDGeneratorUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertSubject(Subject subject) {
        subject.setSubjectID(idGeneratorUtil.getSubjectID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertSubject = "INSERT INTO subject (SubjectID, SubjectName, Medium, CategoryID) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertSubject, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, subject.getSubjectID());
            preparedStatement.setString(2, subject.getSubjectName());
            preparedStatement.setString(3, subject.getMedium());
            preparedStatement.setString(4, subject.getCategory().getCategoryID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Subject> getSubject() {
        ArrayList<Subject> SubjectList = new ArrayList<Subject>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getSubject = "SELECT subject.SubjectID, subject.SubjectName, subject.Medium, subject.CategoryID, category.CategoryName "
                    + "FROM subject INNER JOIN category ON subject.CategoryID = category.CategoryID ORDER BY SubjectID ASC";
            preparedStatement = con.prepareStatement(getSubject, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Subject subject = new Subject();
                
                subject.setSubjectID(resultSet.getString("SubjectID"));
                subject.setSubjectName(resultSet.getString("SubjectName"));
                subject.setMedium(resultSet.getString("Medium"));
                subject.setCategory(new Category());
                subject.getCategory().setCategoryID(resultSet.getString("CategoryID"));
                subject.getCategory().setCategoryName(resultSet.getString("CategoryName"));
                
                SubjectList.add(subject);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return SubjectList;
    }
    
    public boolean getSubject(Subject subject) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getSubject = "SELECT * FROM subject WHERE SubjectID = ?";
            preparedStatement = con.prepareStatement(getSubject, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, subject.getSubjectID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                subject.setSubjectName(resultSet.getString("SubjectName"));
                subject.setMedium(resultSet.getString("Medium"));
                subject.setCategory(new Category());
                subject.getCategory().setCategoryID(resultSet.getString("CategoryID"));
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateSubject(Subject subject) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateSubject = "UPDATE subject SET SubjectName = ?, Medium = ?, CategoryID = ? WHERE SubjectID = ?";
            preparedStatement = con.prepareStatement(updateSubject, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, subject.getSubjectName());
            preparedStatement.setString(2, subject.getMedium());
            preparedStatement.setString(3, subject.getCategory().getCategoryID());
            preparedStatement.setString(4, subject.getSubjectID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteSubject(Subject subject) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteSubject = "DELETE FROM subject WHERE SubjectID = ?";
            preparedStatement = con.prepareStatement(deleteSubject, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, subject.getSubjectID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
}
