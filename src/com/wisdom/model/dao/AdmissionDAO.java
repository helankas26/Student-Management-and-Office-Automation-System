/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Admission;
import com.wisdom.model.Staff;
import com.wisdom.model.Student;
import com.wisdom.util.DBConnectionUtil;
import com.wisdom.util.SQLDateTimeUtil;
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
public class AdmissionDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertAdmission(Admission admission) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertAdmission = "INSERT INTO admission (StudentID, StaffID, Date, Fee) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertAdmission, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, admission.getStudent().getUserID());
            preparedStatement.setString(2, admission.getStaff().getUserID());
            preparedStatement.setDate(3, SQLDateTimeUtil.parseDate(admission.getAdmissionDate()));
            preparedStatement.setDouble(4,admission.getAdmissionFee());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return valid;
    }
    
    public boolean updateAdmission(Admission admission) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateAdmission = "UPDATE admission SET Fee = ?, StaffID = ?  WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(updateAdmission, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDouble(1, admission.getAdmissionFee());
            preparedStatement.setString(2, admission.getStaff().getUserID());
            preparedStatement.setString(3, admission.getStudent().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteAdmission(Admission admission) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteAdmission = "DELETE FROM admission WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(deleteAdmission, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, admission.getStudent().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Admission> getAdmissionByDate(String date) {
        ArrayList<Admission> admissionList = new ArrayList<Admission>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getAdmissionByDate = "SELECT * FROM admission_view WHERE Date = ?";
            preparedStatement = con.prepareStatement(getAdmissionByDate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, SQLDateTimeUtil.parseDate(date));
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Admission admission = new Admission();
                
                admission.setStudent(new Student());
                admission.getStudent().setUserID(resultSet.getString("StudentID"));
                admission.getStudent().setInitial(resultSet.getString("Initial"));
                admission.getStudent().setFirstName(resultSet.getString("FirstName"));
                admission.getStudent().setLastName(resultSet.getString("LastName"));
                admission.setAdmissionFee(resultSet.getDouble("Fee"));
                admission.setAdmissionDate(resultSet.getString("Date"));
                admission.setStaff(new Staff());
                admission.getStaff().setUserID(resultSet.getString("StaffID"));
                admission.getStaff().setFirstName(resultSet.getString("StaffName"));
                
                admissionList.add(admission);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return admissionList;
    }
    
    public boolean getAdmissionByStudentID(Admission admission) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getAdmissionByStudentID = "SELECT * FROM admission_view WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(getAdmissionByStudentID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, admission.getStudent().getUserID());
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                
                admission.setStudent(new Student());
                admission.getStudent().setUserID(resultSet.getString("StudentID"));
                admission.getStudent().setInitial(resultSet.getString("Initial"));
                admission.getStudent().setFirstName(resultSet.getString("FirstName"));
                admission.getStudent().setLastName(resultSet.getString("LastName"));
                admission.setAdmissionFee(resultSet.getDouble("Fee"));
                admission.setAdmissionDate(resultSet.getString("Date"));
                admission.setStaff(new Staff());
                admission.getStaff().setUserID(resultSet.getString("StaffID"));
                admission.getStaff().setFirstName(resultSet.getString("StaffName"));
                
                valid = true;
               
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
}
