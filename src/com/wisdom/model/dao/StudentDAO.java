/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Admission;
import com.wisdom.model.Student;
import com.wisdom.util.SQLDateUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Helanka
 */
public class StudentDAO extends UserDAO {
    
    public boolean insertStudent(Student student, Admission admission) {
        student.setUserID(idGeneratorUtil.getStudentID(student.getJoinedDate()));
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertStudent = "INSERT INTO student (StudentID, Initial, FirstName, LastName, DoB, Sex, Grade, Medium, School, "
                    + "TelNo, Address, Email, JoinedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertStudent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getUserID());
            preparedStatement.setString(2, student.getInitial());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.setDate(5, SQLDateUtil.parseDate(student.getDateOfBirth()));
            preparedStatement.setString(6, student.getSex());
            preparedStatement.setString(7, student.getGrade());
            preparedStatement.setString(8, student.getMedium());
            preparedStatement.setString(9, student.getSchool());
            preparedStatement.setString(10, student.getTelNo());
            preparedStatement.setString(11, student.getAddress());
            preparedStatement.setString(12, student.getEmail());
            preparedStatement.setDate(13, SQLDateUtil.parseDate(student.getJoinedDate()));
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                admission.setStudent(student);
                valid = new AdmissionDAO().insertAdmission(admission) && new ParentDAO().insertParent(student);
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Student> getStudentActive() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStudentActive = "SELECT * FROM student_active";
            preparedStatement = con.prepareStatement(getStudentActive, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Student student = new Student();
                
                student.setUserID(resultSet.getString("StudentID"));
                student.setInitial(resultSet.getString("Initial"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setGrade(resultSet.getString("Grade"));
                student.setDateOfBirth(resultSet.getString("DoB"));
                student.setSchool(resultSet.getString("School"));
                student.setSex(resultSet.getString("Sex"));
                student.setMedium(resultSet.getString("Medium"));
                student.setEmail(resultSet.getString("Email"));
                student.getParent().setTitle(resultSet.getString("Title"));
                student.getParent().setParentName(resultSet.getString("ParentName"));
                student.setTelNo(resultSet.getString("TelNo"));
                student.setAddress(resultSet.getString("Address"));
                student.setJoinedDate(resultSet.getString("JoinedDate"));
                
                studentList.add(student);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return studentList;
    }
    
    public boolean getStudent(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getStudent = "SELECT * FROM student_active WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(getStudent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getUserID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                student.setInitial(resultSet.getString("Initial"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setGrade(resultSet.getString("Grade"));
                student.setDateOfBirth(resultSet.getString("DoB"));
                student.setSchool(resultSet.getString("School"));
                student.setSex(resultSet.getString("Sex"));
                student.setMedium(resultSet.getString("Medium"));
                student.setEmail(resultSet.getString("Email"));
                student.getParent().setTitle(resultSet.getString("Title"));
                student.getParent().setParentName(resultSet.getString("ParentName"));
                student.setTelNo(resultSet.getString("TelNo"));
                student.setAddress(resultSet.getString("Address"));
                student.setJoinedDate(resultSet.getString("JoinedDate"));
                
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean changeStudentStatus(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String makePastStudent = "UPDATE student SET Status = ? WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(makePastStudent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getStatus());
            preparedStatement.setString(2, student.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateStudent(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertStudent = "UPDATE student SET Initial = ?, FirstName = ?, LastName = ?, DoB = ?, Sex = ?, "
                    + "Grade = ?, Medium = ?, School = ?, TelNo = ?, Address = ?, Email = ? WHERE StudentID = ?";
            preparedStatement = con.prepareStatement(insertStudent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getInitial());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDate(4, SQLDateUtil.parseDate(student.getDateOfBirth()));
            preparedStatement.setString(5, student.getSex());
            preparedStatement.setString(6, student.getGrade());
            preparedStatement.setString(7, student.getMedium());
            preparedStatement.setString(8, student.getSchool());
            preparedStatement.setString(9, student.getTelNo());
            preparedStatement.setString(10, student.getAddress());
            preparedStatement.setString(11, student.getEmail());
            preparedStatement.setString(12, student.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = new ParentDAO().updateParent(student);
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean deleteStudent(Student student) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String deleteStudent = "DELETE FROM student WHERE Status = ? AND StudentID = ?";
            preparedStatement = con.prepareStatement(deleteStudent, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, student.getStatus());
            preparedStatement.setString(2, student.getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
}
