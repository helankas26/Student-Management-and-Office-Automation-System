/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Login;
import com.wisdom.model.User;
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
public class LoginDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private IDGeneratorUtil idGeneratorUtil = IDGeneratorUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public void verifyLogin(Login login) {
        try {
            con = dbConnectionUtil.getConnection();
            
            String loginData = "SELECT * FROM login WHERE UserName = ?";
            preparedStatement = con.prepareStatement(loginData, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getUsername());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                login.setLoginID(resultSet.getString("LoginID"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setStatus(resultSet.getString("Status"));
                
                if (login.getPassword().equals(resultSet.getString("Password"))) {
                    login.setPassword("");
                    String loginStaff = "SELECT * FROM login_staff WHERE LoginID = ?";
                    preparedStatement = con.prepareStatement(loginStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setString(1, login.getLoginID());
                    resultSet = preparedStatement.executeQuery();
                    
                    if (resultSet.next()) {
                        login.setUser(new User());
                        login.getUser().setUserID(resultSet.getString("StaffID"));
                        new UserDAO().getLoginUser(login, "Staff");
                    } else {
                        String loginTeacher = "SELECT * FROM login_teacher WHERE LoginID = ?";
                        preparedStatement = con.prepareStatement(loginTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        preparedStatement.setString(1, login.getLoginID());
                        resultSet = preparedStatement.executeQuery();
                        
                        if (resultSet.next()) {
                            login.setUser(new User());
                            login.getUser().setUserID(resultSet.getString("TeacherID"));
                            new UserDAO().getLoginUser(login, "Teacher");
                        }
                    }
                    
                } else {
                    login.setPassword(null);
                }

            } else {
                login.setLoginID(null);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void recordLogin(Login login) {
        try {
            con = dbConnectionUtil.getConnection();
            
            String loginRecord = "INSERT INTO login_record (LoginID) VALUES (?)";
            preparedStatement = con.prepareStatement(loginRecord, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean verifyPassword(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String password = "SELECT Password FROM login WHERE LoginID = ?";
            preparedStatement = con.prepareStatement(password, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                if (login.getPassword().equals(resultSet.getString("Password"))) {
                    login.setPassword(null);
                    valid = true;
                }
            } else {
                valid = false;
                login.setPassword(null);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean insertLogin(Login login, String loginFor) {
        login.setLoginID(idGeneratorUtil.getLoginID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertLogin = "INSERT INTO login (LoginID, UserName, Password, Privilege) VALUES (?, ?, ?, ?)";
            preparedStatement = con.prepareStatement(insertLogin, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            preparedStatement.setString(2, login.getUsername());
            preparedStatement.setString(3, login.getPassword());
            preparedStatement.setString(4, login.getPrivilege());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                if (loginFor.equals("Staff")) {
                    valid = insertLoginStaff(login);
                } else if (loginFor.equals("Teacher")) {
                    valid = insertLoginTeacher(login);
                }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean insertLoginStaff(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertLoginStaff = "INSERT INTO login_staff (LoginID, StaffID) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(insertLoginStaff, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            preparedStatement.setString(2, login.getUser().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean insertLoginTeacher(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertLoginTeacher = "INSERT INTO login_teacher (LoginID, TeacherID) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(insertLoginTeacher, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getLoginID());
            preparedStatement.setString(2, login.getUser().getUserID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Login> getLoginStaffActive() {
        ArrayList<Login> logintList = new ArrayList<Login>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getLoginStaffActive = "SELECT * FROM login_active_staff";
            preparedStatement = con.prepareStatement(getLoginStaffActive, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Login login = new Login();
                
                login.setLoginID(resultSet.getString("LoginID"));
                login.setUserName(resultSet.getString("UserName"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setUser(new User());
                login.getUser().setUserID(resultSet.getString("StaffID"));
                login.getUser().setFirstName(resultSet.getString("FirstName"));
                
                logintList.add(login);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return logintList;
    }
    
    public ArrayList<Login> getLoginStaffDeactivate() {
        ArrayList<Login> logintList = new ArrayList<Login>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getLoginStaffDeactivate = "SELECT * FROM login_deactivate_staff";
            preparedStatement = con.prepareStatement(getLoginStaffDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Login login = new Login();
                
                login.setLoginID(resultSet.getString("LoginID"));
                login.setUserName(resultSet.getString("UserName"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setUser(new User());
                login.getUser().setUserID(resultSet.getString("StaffID"));
                login.getUser().setFirstName(resultSet.getString("FirstName"));
                
                logintList.add(login);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return logintList;
    }
    
    public ArrayList<Login> getLoginTeacherActive() {
        ArrayList<Login> logintList = new ArrayList<Login>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getLoginTeacherActive = "SELECT * FROM login_active_teacher";
            preparedStatement = con.prepareStatement(getLoginTeacherActive, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Login login = new Login();
                
                login.setLoginID(resultSet.getString("LoginID"));
                login.setUserName(resultSet.getString("UserName"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setUser(new User());
                login.getUser().setUserID(resultSet.getString("TeacherID"));
                login.getUser().setFirstName(resultSet.getString("FirstName"));
                
                logintList.add(login);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return logintList;
    }
    
    public ArrayList<Login> getLoginTeacherDeactivate() {
        ArrayList<Login> logintList = new ArrayList<Login>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getLoginTeacherDeactivate = "SELECT * FROM login_deactivate_teacher";
            preparedStatement = con.prepareStatement(getLoginTeacherDeactivate, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Login login = new Login();
                
                login.setLoginID(resultSet.getString("LoginID"));
                login.setUserName(resultSet.getString("UserName"));
                login.setPrivilege(resultSet.getString("Privilege"));
                login.setUser(new User());
                login.getUser().setUserID(resultSet.getString("TeacherID"));
                login.getUser().setFirstName(resultSet.getString("FirstName"));
                
                logintList.add(login);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return logintList;
    }
    
    public boolean getLogin(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getLogin = "SELECT * FROM login WHERE Status = ? AND LoginID = ?";
            preparedStatement = con.prepareStatement(getLogin, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getStatus());
            preparedStatement.setString(2, login.getLoginID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                login.setUserName(resultSet.getString("UserName"));
                login.setPrivilege(resultSet.getString("Privilege"));
                
                valid = true;
                
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean changeLoginStatus(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String changeLoginStatus = "UPDATE login SET Status = ? WHERE LoginID = ?";
            preparedStatement = con.prepareStatement(changeLoginStatus, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getStatus());
            preparedStatement.setString(2, login.getLoginID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public boolean updateLogin(Login login) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateLogin = "UPDATE login SET Password = ?, Privilege = ?  WHERE Status = ? AND LoginID = ?";
            preparedStatement = con.prepareStatement(updateLogin, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, login.getPassword());
            preparedStatement.setString(2, login.getPrivilege());
            preparedStatement.setString(3, login.getStatus());
            preparedStatement.setString(4, login.getLoginID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    
}
