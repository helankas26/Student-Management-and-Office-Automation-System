/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Advance;
import com.wisdom.model.Staff;
import com.wisdom.model.Teacher;
import com.wisdom.util.DBConnectionUtil;
import com.wisdom.util.SQLDateTimeUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class AdvanceDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertAdvance(Advance advance, String user) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertAdvance = "INSERT INTO advance (Description, Amount, Date, HandlerStaffID) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertAdvance, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, advance.getDiscription());
            preparedStatement.setDouble(2, advance.getAdvanceAmount());
            preparedStatement.setDate(3, SQLDateTimeUtil.parseDate(advance.getAdvanceDate()));
            preparedStatement.setString(4, advance.getStaffHandler().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                resultSet = preparedStatement.getGeneratedKeys();
                
                if (resultSet.next()) {
                    advance.setAdvanceID(resultSet.getString(1));
                    
                    if (user.equals("Teacher")) {
                        valid = insertAdvanceTeacher(advance);
                    } if (user.equals("Staff")) {
                        valid = insertAdvanceStaff(advance);
                    }
                }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean insertAdvanceTeacher(Advance advance) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertAdvanceTeacher = "INSERT INTO advance_teacher (AdvanceID, TeacherID) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(insertAdvanceTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, advance.getAdvanceID());
            preparedStatement.setString(2, advance.getTeacher().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean insertAdvanceStaff(Advance advance) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertLoginStaff = "INSERT INTO advance_staff (AdvanceID, StaffID) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(insertLoginStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, advance.getAdvanceID());
            preparedStatement.setString(2, advance.getStaffReceiver().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateTeacherAdvance(Advance advance) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateTeacherAdvance = "UPDATE advance_teacher_view SET Description = ?, Amount = ?, HandlerStaffID = ? "
                    + "WHERE AdvanceID = ? AND Date = ? AND TeacherID = ?";
            preparedStatement = con.prepareStatement(updateTeacherAdvance, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, advance.getDiscription());
            preparedStatement.setDouble(2, advance.getAdvanceAmount());
            preparedStatement.setString(3, advance.getStaffHandler().getUserID());
            preparedStatement.setString(4, advance.getAdvanceID());
            preparedStatement.setDate(5, SQLDateTimeUtil.parseDate(advance.getAdvanceDate()));
            preparedStatement.setString(6, advance.getTeacher().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateStaffAdvance(Advance advance) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateStaffAdvance = "UPDATE advance_staff_view SET Description = ?, Amount = ?, HandlerStaffID = ? "
                    + "WHERE AdvanceID = ? AND Date = ? AND StaffID = ?";
            preparedStatement = con.prepareStatement(updateStaffAdvance, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, advance.getDiscription());
            preparedStatement.setDouble(2, advance.getAdvanceAmount());
            preparedStatement.setString(3, advance.getStaffHandler().getUserID());
            preparedStatement.setString(4, advance.getAdvanceID());
            preparedStatement.setDate(5, SQLDateTimeUtil.parseDate(advance.getAdvanceDate()));
            preparedStatement.setString(6, advance.getStaffReceiver().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteAdvance(Advance advance) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteAdvance = "DELETE FROM advance WHERE AdvanceID = ?";
            preparedStatement = con.prepareStatement(deleteAdvance, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, advance.getAdvanceID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Advance> getTeacherAdvanceByDate(String date) {
        ArrayList<Advance> advanceList = new ArrayList<Advance>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getTeacherAdvanceByDate = "SELECT * FROM advance_teacher_view WHERE Date = ? ORDER BY advance.AdvanceID DESC";
            preparedStatement = con.prepareStatement(getTeacherAdvanceByDate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, SQLDateTimeUtil.parseDate(date));
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Advance advance = new Advance();
                
                advance.setAdvanceID(resultSet.getString("AdvanceID"));
                advance.setTeacher(new Teacher());
                advance.getTeacher().setUserID(resultSet.getString("TeacherID"));
                advance.getTeacher().setInitial(resultSet.getString("Initial"));
                advance.getTeacher().setFirstName(resultSet.getString("FirstName"));
                advance.getTeacher().setLastName(resultSet.getString("LastName"));
                advance.setDiscription(resultSet.getString("Description"));
                advance.setAdvanceAmount(resultSet.getDouble("Amount"));
                advance.setAdvanceDate(resultSet.getString("Date"));
                advance.setStaffHandler(new Staff());
                advance.getStaffHandler().setUserID(resultSet.getString("HandlerStaffID"));
                advance.getStaffHandler().setFirstName(resultSet.getString("StaffName"));

                advanceList.add(advance);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return advanceList;
    }
    
    public ArrayList<Advance> getStaffAdvanceByDate(String date) {
        ArrayList<Advance> advanceList = new ArrayList<Advance>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStaffAdvanceByDate = "SELECT * FROM advance_staff_view WHERE Date = ? ORDER BY advance.AdvanceID DESC";
            preparedStatement = con.prepareStatement(getStaffAdvanceByDate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setDate(1, SQLDateTimeUtil.parseDate(date));
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Advance advance = new Advance();
                
                advance.setAdvanceID(resultSet.getString("AdvanceID"));
                advance.setStaffReceiver(new Staff());
                advance.getStaffReceiver().setUserID(resultSet.getString("StaffID"));
                advance.getStaffReceiver().setInitial(resultSet.getString("Initial"));
                advance.getStaffReceiver().setFirstName(resultSet.getString("FirstName"));
                advance.getStaffReceiver().setLastName(resultSet.getString("LastName"));
                advance.setDiscription(resultSet.getString("Description"));
                advance.setAdvanceAmount(resultSet.getDouble("Amount"));
                advance.setAdvanceDate(resultSet.getString("Date"));
                advance.setStaffHandler(new Staff());
                advance.getStaffHandler().setUserID(resultSet.getString("HandlerStaffID"));
                advance.getStaffHandler().setFirstName(resultSet.getString("StaffName"));

                advanceList.add(advance);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdvanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return advanceList;
    }
    
}
