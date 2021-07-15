/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Teacher;
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
public class TeacherDAO extends UserDAO {
    
    public boolean insertTeacher(Teacher teacher) {
        teacher.setUserID(idGeneratorUtil.getTeacherID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertTeacher = "INSERT INTO teacher (TeacherID, Title, Initial, FirstName, LastName, DoB, Sex, TelNo, "
                    + "Address, Email, Qualification, JoinedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getUserID());
            preparedStatement.setString(2, teacher.getTitle());
            preparedStatement.setString(3, teacher.getInitial());
            preparedStatement.setString(4, teacher.getFirstName());
            preparedStatement.setString(5, teacher.getLastName());
            preparedStatement.setDate(6, SQLDateTimeUtil.parseDate(teacher.getDateOfBirth()));
            preparedStatement.setString(7, teacher.getSex());
            preparedStatement.setString(8, teacher.getTelNo());
            preparedStatement.setString(9, teacher.getAddress());
            preparedStatement.setString(10, teacher.getEmail());
            preparedStatement.setString(11, teacher.getQualification());
            preparedStatement.setDate(12, SQLDateTimeUtil.parseDate(teacher.getJoinedDate()));
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Teacher> getTeacherActive() {
        ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getTeacherActive = "SELECT * FROM teacher_active";
            preparedStatement = con.prepareStatement(getTeacherActive, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                
                teacher.setUserID(resultSet.getString("TeacherID"));
                teacher.setTitle(resultSet.getString("Title"));
                teacher.setInitial(resultSet.getString("Initial"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setDateOfBirth(resultSet.getString("DoB"));
                teacher.setSex(resultSet.getString("Sex"));
                teacher.setTelNo(resultSet.getString("TelNo"));
                teacher.setAddress(resultSet.getString("Address"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setQualification(resultSet.getString("Qualification"));
                teacher.setJoinedDate(resultSet.getString("JoinedDate"));
                
                teacherList.add(teacher);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return teacherList;
    }
    
    public boolean getTeacher(Teacher teacher) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getTeacher = "SELECT * FROM teacher_active WHERE TeacherID = ?";
            preparedStatement = con.prepareStatement(getTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getUserID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                teacher.setTitle(resultSet.getString("Title"));
                teacher.setInitial(resultSet.getString("Initial"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setDateOfBirth(resultSet.getString("DoB"));
                teacher.setSex(resultSet.getString("Sex"));
                teacher.setTelNo(resultSet.getString("TelNo"));
                teacher.setAddress(resultSet.getString("Address"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setQualification(resultSet.getString("Qualification"));
                teacher.setJoinedDate(resultSet.getString("JoinedDate"));
                
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean changeTeacherStatus(Teacher teacher) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String changeTeacherStatus = "UPDATE teacher SET Status = ? WHERE TeacherID = ?";
            preparedStatement = con.prepareStatement(changeTeacherStatus, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getStatus());
            preparedStatement.setString(2, teacher.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateTeacher(Teacher teacher) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateTeacher = "UPDATE teacher SET Title = ?, Initial = ?, FirstName = ?, LastName = ?, DoB = ?, Sex = ?, "
                    + "TelNo = ?, Address = ?, Email = ?, Qualification= ? WHERE Status = ? AND TeacherID = ?";
            preparedStatement = con.prepareStatement(updateTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getTitle());
            preparedStatement.setString(2, teacher.getInitial());
            preparedStatement.setString(3, teacher.getFirstName());
            preparedStatement.setString(4, teacher.getLastName());
            preparedStatement.setDate(5, SQLDateTimeUtil.parseDate(teacher.getDateOfBirth()));
            preparedStatement.setString(6, teacher.getSex());
            preparedStatement.setString(7, teacher.getTelNo());
            preparedStatement.setString(8, teacher.getAddress());
            preparedStatement.setString(9, teacher.getEmail());
            preparedStatement.setString(10, teacher.getQualification());
            preparedStatement.setString(11, teacher.getStatus());
            preparedStatement.setString(12, teacher.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteTeacher(Teacher teacher) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteTeacher = "DELETE FROM teacher WHERE Status = ? AND TeacherID = ?";
            preparedStatement = con.prepareStatement(deleteTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getStatus());
            preparedStatement.setString(2, teacher.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Teacher> getTeacherDeactivate() {
        ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getTeacherDeactivate = "SELECT * FROM teacher_deactivate";
            preparedStatement = con.prepareStatement(getTeacherDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                
                teacher.setUserID(resultSet.getString("TeacherID"));
                teacher.setTitle(resultSet.getString("Title"));
                teacher.setInitial(resultSet.getString("Initial"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setDateOfBirth(resultSet.getString("DoB"));
                teacher.setSex(resultSet.getString("Sex"));
                teacher.setTelNo(resultSet.getString("TelNo"));
                teacher.setAddress(resultSet.getString("Address"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setQualification(resultSet.getString("Qualification"));
                teacher.setJoinedDate(resultSet.getString("JoinedDate"));
                
                teacherList.add(teacher);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return teacherList;
    }
    
    public boolean insertTeacherDeactivate(Teacher teacher) {
        teacher.setUserID(idGeneratorUtil.getTeacherID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertTeacherDeactivate = "INSERT INTO teacher (TeacherID, Title, Initial, FirstName, LastName, DoB, Sex, TelNo, "
                    + "Address, Email, Qualification, Status, JoinedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertTeacherDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getUserID());
            preparedStatement.setString(2, teacher.getTitle());
            preparedStatement.setString(3, teacher.getInitial());
            preparedStatement.setString(4, teacher.getFirstName());
            preparedStatement.setString(5, teacher.getLastName());
            preparedStatement.setDate(6, SQLDateTimeUtil.parseDate(teacher.getDateOfBirth()));
            preparedStatement.setString(7, teacher.getSex());
            preparedStatement.setString(8, teacher.getTelNo());
            preparedStatement.setString(9, teacher.getAddress());
            preparedStatement.setString(10, teacher.getEmail());
            preparedStatement.setString(11, teacher.getQualification());
            preparedStatement.setString(12, teacher.getStatus());
            preparedStatement.setDate(13, SQLDateTimeUtil.parseDate(teacher.getJoinedDate()));
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean getDeactivateTeacher(Teacher teacher) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getDeactivateTeacher = "SELECT * FROM teacher_deactivate WHERE TeacherID = ?";
            preparedStatement = con.prepareStatement(getDeactivateTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, teacher.getUserID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                teacher.setTitle(resultSet.getString("Title"));
                teacher.setInitial(resultSet.getString("Initial"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setDateOfBirth(resultSet.getString("DoB"));
                teacher.setSex(resultSet.getString("Sex"));
                teacher.setTelNo(resultSet.getString("TelNo"));
                teacher.setAddress(resultSet.getString("Address"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setQualification(resultSet.getString("Qualification"));
                teacher.setJoinedDate(resultSet.getString("JoinedDate"));
                
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Teacher> getTeacherNotInLogin() {
        ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getTeacherNotInLogin = "SELECT * FROM teacher_active WHERE TeacherID NOT IN (SELECT TeacherID  FROM login_teacher)";
            preparedStatement = con.prepareStatement(getTeacherNotInLogin, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                
                teacher.setUserID(resultSet.getString("TeacherID"));
                teacher.setTitle(resultSet.getString("Title"));
                teacher.setInitial(resultSet.getString("Initial"));
                teacher.setFirstName(resultSet.getString("FirstName"));
                teacher.setLastName(resultSet.getString("LastName"));
                teacher.setDateOfBirth(resultSet.getString("DoB"));
                teacher.setSex(resultSet.getString("Sex"));
                teacher.setTelNo(resultSet.getString("TelNo"));
                teacher.setAddress(resultSet.getString("Address"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setQualification(resultSet.getString("Qualification"));
                teacher.setJoinedDate(resultSet.getString("JoinedDate"));
                
                teacherList.add(teacher);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return teacherList;
    }
         
}
