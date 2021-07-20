/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Expenditure;
import com.wisdom.model.Staff;
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
public class ExpenditureDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertExpenditure(Expenditure expenditure) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertExpenditure = "INSERT INTO expenditure (Expense, Amount, Date, HandlerStaffID) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertExpenditure, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, expenditure.getExpense());
            preparedStatement.setDouble(2, expenditure.getExpenseAmount());
            preparedStatement.setDate(3, SQLDateTimeUtil.parseDate(expenditure.getExpenseDate()));
            preparedStatement.setString(4, expenditure.getStaff().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        return valid;
    }
    
    public ArrayList<Expenditure> getExpenditureByDate(String date) {
        ArrayList<Expenditure> expenditureList = new ArrayList<Expenditure>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getExpenditureByDate = "SELECT * FROM expenditure_view WHERE Date = ?";
            preparedStatement = con.prepareStatement(getExpenditureByDate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, SQLDateTimeUtil.parseDate(date));
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Expenditure expenditure = new Expenditure();
                
                expenditure.setExpenseID(resultSet.getString("ExpenseID"));
                expenditure.setExpense(resultSet.getString("Expense"));
                expenditure.setExpenseAmount(resultSet.getDouble("Amount"));
                expenditure.setExpenseDate(resultSet.getString("Date"));
                expenditure.setStaff(new Staff());
                expenditure.getStaff().setUserID(resultSet.getString("HandlerStaffID"));
                expenditure.getStaff().setFirstName(resultSet.getString("StaffName"));
                
                expenditureList.add(expenditure);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return expenditureList;
    }
    
}
