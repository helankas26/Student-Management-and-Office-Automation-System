/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Admission;
import com.wisdom.util.DBConnectionUtil;
import com.wisdom.util.SQLDateUtil;
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
            preparedStatement.setDate(3, SQLDateUtil.parseDate(admission.getAdmissionDate()));
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
    
}
