/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Staff;
import com.wisdom.util.SQLDateTimeUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class StaffDAO extends UserDAO {
    
    public boolean insertStaff(Staff staff) {
        staff.setUserID(idGeneratorUtil.getStaffID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertStaff = "INSERT INTO staff (StaffID, Title, Initial, FirstName, LastName, DoB, Sex, TelNo, "
                    + "Address, Email, JoinedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getUserID());
            preparedStatement.setString(2, staff.getTitle());
            preparedStatement.setString(3, staff.getInitial());
            preparedStatement.setString(4, staff.getFirstName());
            preparedStatement.setString(5, staff.getLastName());
            preparedStatement.setDate(6, SQLDateTimeUtil.parseDate(staff.getDateOfBirth()));
            preparedStatement.setString(7, staff.getSex());
            preparedStatement.setString(8, staff.getTelNo());
            preparedStatement.setString(9, staff.getAddress());
            preparedStatement.setString(10, staff.getEmail());
            preparedStatement.setDate(11, SQLDateTimeUtil.parseDate(staff.getJoinedDate()));
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Staff> getStaffActive() {
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStaffActive = "SELECT * FROM staff_active";
            preparedStatement = con.prepareStatement(getStaffActive, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Staff staff = new Staff();
                
                staff.setUserID(resultSet.getString("StaffID"));
                staff.setTitle(resultSet.getString("Title"));
                staff.setInitial(resultSet.getString("Initial"));
                staff.setFirstName(resultSet.getString("FirstName"));
                staff.setLastName(resultSet.getString("LastName"));
                staff.setDateOfBirth(resultSet.getString("DoB"));
                staff.setSex(resultSet.getString("Sex"));
                staff.setTelNo(resultSet.getString("TelNo"));
                staff.setAddress(resultSet.getString("Address"));
                staff.setEmail(resultSet.getString("Email"));
                staff.setJoinedDate(resultSet.getString("JoinedDate"));
                
                staffList.add(staff);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return staffList;
    }
    
    public boolean getStaff(Staff staff) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStaff = "SELECT * FROM staff_active WHERE StaffID = ?";
            preparedStatement = con.prepareStatement(getStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getUserID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                staff.setTitle(resultSet.getString("Title"));
                staff.setInitial(resultSet.getString("Initial"));
                staff.setFirstName(resultSet.getString("FirstName"));
                staff.setLastName(resultSet.getString("LastName"));
                staff.setDateOfBirth(resultSet.getString("DoB"));
                staff.setSex(resultSet.getString("Sex"));
                staff.setTelNo(resultSet.getString("TelNo"));
                staff.setAddress(resultSet.getString("Address"));
                staff.setEmail(resultSet.getString("Email"));
                staff.setJoinedDate(resultSet.getString("JoinedDate"));
                
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean changeStaffStatus(Staff staff) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String changeStaffStatus = "UPDATE staff SET Status = ? WHERE StaffID = ?";
            preparedStatement = con.prepareStatement(changeStaffStatus, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getStatus());
            preparedStatement.setString(2, staff.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateStaff(Staff staff) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateStaff = "UPDATE staff SET Title = ?, Initial = ?, FirstName = ?, LastName = ?, DoB = ?, Sex = ?, "
                    + "TelNo = ?, Address = ?, Email = ? WHERE Status = ? AND StaffID = ?";
            preparedStatement = con.prepareStatement(updateStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getTitle());
            preparedStatement.setString(2, staff.getInitial());
            preparedStatement.setString(3, staff.getFirstName());
            preparedStatement.setString(4, staff.getLastName());
            preparedStatement.setDate(5, SQLDateTimeUtil.parseDate(staff.getDateOfBirth()));
            preparedStatement.setString(6, staff.getSex());
            preparedStatement.setString(7, staff.getTelNo());
            preparedStatement.setString(8, staff.getAddress());
            preparedStatement.setString(9, staff.getEmail());
            preparedStatement.setString(10, staff.getStatus());
            preparedStatement.setString(11, staff.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteStaff(Staff staff) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteStaff = "DELETE FROM staff WHERE Status = ? AND StaffID = ?";
            preparedStatement = con.prepareStatement(deleteStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getStatus());
            preparedStatement.setString(2, staff.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Staff> getStaffDeactivate() {
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStaffDeactivate = "SELECT * FROM staff_deactivate";
            preparedStatement = con.prepareStatement(getStaffDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Staff staff = new Staff();
                
                staff.setUserID(resultSet.getString("StaffID"));
                staff.setTitle(resultSet.getString("Title"));
                staff.setInitial(resultSet.getString("Initial"));
                staff.setFirstName(resultSet.getString("FirstName"));
                staff.setLastName(resultSet.getString("LastName"));
                staff.setDateOfBirth(resultSet.getString("DoB"));
                staff.setSex(resultSet.getString("Sex"));
                staff.setTelNo(resultSet.getString("TelNo"));
                staff.setAddress(resultSet.getString("Address"));
                staff.setEmail(resultSet.getString("Email"));
                staff.setJoinedDate(resultSet.getString("JoinedDate"));
                
                staffList.add(staff);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return staffList;
    }
    
    public boolean insertStaffDeactivate(Staff staff) {
        staff.setUserID(idGeneratorUtil.getStaffID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertStaffDeactivate = "INSERT INTO staff (StaffID, Title, Initial, FirstName, LastName, DoB, Sex, TelNo, "
                    + "Address, Email, Status, JoinedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertStaffDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getUserID());
            preparedStatement.setString(2, staff.getTitle());
            preparedStatement.setString(3, staff.getInitial());
            preparedStatement.setString(4, staff.getFirstName());
            preparedStatement.setString(5, staff.getLastName());
            preparedStatement.setDate(6, SQLDateTimeUtil.parseDate(staff.getDateOfBirth()));
            preparedStatement.setString(7, staff.getSex());
            preparedStatement.setString(8, staff.getTelNo());
            preparedStatement.setString(9, staff.getAddress());
            preparedStatement.setString(10, staff.getEmail());
            preparedStatement.setString(11, staff.getStatus());
            preparedStatement.setDate(12, SQLDateTimeUtil.parseDate(staff.getJoinedDate()));
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean getDeactivateStaff(Staff staff) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getDeactivateStaff = "SELECT * FROM staff_deactivate WHERE StaffID = ?";
            preparedStatement = con.prepareStatement(getDeactivateStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, staff.getUserID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                staff.setTitle(resultSet.getString("Title"));
                staff.setInitial(resultSet.getString("Initial"));
                staff.setFirstName(resultSet.getString("FirstName"));
                staff.setLastName(resultSet.getString("LastName"));
                staff.setDateOfBirth(resultSet.getString("DoB"));
                staff.setSex(resultSet.getString("Sex"));
                staff.setTelNo(resultSet.getString("TelNo"));
                staff.setAddress(resultSet.getString("Address"));
                staff.setEmail(resultSet.getString("Email"));
                staff.setJoinedDate(resultSet.getString("JoinedDate"));
                
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Staff> getStaffNotInLogin() {
        ArrayList<Staff> staffList = new ArrayList<Staff>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStaffNotInLogin = "SELECT * FROM staff_active WHERE StaffID NOT IN (SELECT StaffID  FROM login_staff)";
            preparedStatement = con.prepareStatement(getStaffNotInLogin, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Staff staff = new Staff();
                
                staff.setUserID(resultSet.getString("StaffID"));
                staff.setTitle(resultSet.getString("Title"));
                staff.setInitial(resultSet.getString("Initial"));
                staff.setFirstName(resultSet.getString("FirstName"));
                staff.setLastName(resultSet.getString("LastName"));
                staff.setDateOfBirth(resultSet.getString("DoB"));
                staff.setSex(resultSet.getString("Sex"));
                staff.setTelNo(resultSet.getString("TelNo"));
                staff.setAddress(resultSet.getString("Address"));
                staff.setEmail(resultSet.getString("Email"));
                staff.setJoinedDate(resultSet.getString("JoinedDate"));
                
                staffList.add(staff);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return staffList;
    }
    
}
