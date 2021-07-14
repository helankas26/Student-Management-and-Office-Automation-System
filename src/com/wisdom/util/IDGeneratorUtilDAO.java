/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.util;

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
public final class IDGeneratorUtilDAO {
    
    private static DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static Connection con;

    private IDGeneratorUtilDAO() {
    }

    //To get current loginID set from login table
    public static ArrayList<String> getCurrentLoginID() {
        ArrayList<String> currentLoginID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String loginIDCol = "SELECT LoginID FROM login ORDER BY LoginID ASC";
            preparedStatement = con.prepareStatement(loginIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentLoginID.add(resultSet.getString("LoginID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentLoginID;
    }
    
    //To get current staffID set from staff table
    public static ArrayList<String> getCurrentStaffID() {
        ArrayList<String> currentStaffID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String staffIDCol = "SELECT StaffID FROM staff";
            preparedStatement = con.prepareStatement(staffIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentStaffID.add(resultSet.getString("StaffID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentStaffID;
    }
    
    //To get current teacherID set from teacher table
    public static ArrayList<String> getCurrentTeacherID() {
        ArrayList<String> currentTeacherID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String teacherIDCol = "SELECT TeacherID FROM teacher";
            preparedStatement = con.prepareStatement(teacherIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentTeacherID.add(resultSet.getString("TeacherID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentTeacherID;
    }
    
    //To get current classID set from class table
    public static ArrayList<String> getCurrentClassID() {
        ArrayList<String> currentClassID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String classIDCol = "SELECT ClassID FROM class ORDER BY ClassID ASC";
            preparedStatement = con.prepareStatement(classIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentClassID.add(resultSet.getString("ClassID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentClassID;
    }
    
    //To get current categoryID set from category table
    public static ArrayList<String> getCurrentCategoryID() {
        ArrayList<String> currentCategoryID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String categoryIDCol = "SELECT CategoryID FROM category ORDER BY CategoryID ASC";
            preparedStatement = con.prepareStatement(categoryIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentCategoryID.add(resultSet.getString("CategoryID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentCategoryID;
    }
    
    //To get current subjectID set from subject table
    public static ArrayList<String> getCurrentSubjectID() {
        ArrayList<String> currentSubjectID= new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String subjectIDCol = "SELECT SubjectID FROM subject ORDER BY SubjectID ASC";
            preparedStatement = con.prepareStatement(subjectIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentSubjectID.add(resultSet.getString("SubjectID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentSubjectID;
    }
    
    //To get current studentID set from student table
    public static ArrayList<String> getCurrentStudentID() {
        ArrayList<String> currentStudentID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String studentIDCol = "SELECT StudentID FROM student";
            preparedStatement = con.prepareStatement(studentIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentStudentID.add(resultSet.getString("StudentID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentStudentID;
    }
    
    //To get current studentID set for specified date from student table
    public static ArrayList<String> getCurrentStudentID(String date) {
        ArrayList<String> currentStudentID = new ArrayList<String>();
        try {
            con = dbConnectionUtil.getConnection();
           
            String studentIDCol = "SELECT StudentID FROM student WHERE JoinedDate = ?";
            preparedStatement = con.prepareStatement(studentIDCol, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, date);
            resultSet = preparedStatement.executeQuery();
         
            while (resultSet.next()) {
                currentStudentID.add(resultSet.getString("StudentID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IDGeneratorUtilDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return currentStudentID;
    }
    
}
