/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model.dao;

import com.wisdom.model.Category;
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
public class CategoryDAO {
    
    private DBConnectionUtil dbConnectionUtil = DBConnectionUtil.getInstance();
    private IDGeneratorUtil idGeneratorUtil = IDGeneratorUtil.getInstance();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con;
    
    public boolean insertCategory(Category category) {
        category.setCategoryID(idGeneratorUtil.getCategoryID());
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String insertCategory = "INSERT INTO category (CategoryID, CategoryName) VALUES (?, ?)";
            preparedStatement = con.prepareStatement(insertCategory, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, category.getCategoryID());
            preparedStatement.setString(2, category.getCategoryName());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
                valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
    public ArrayList<Category> getCategory() {
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try {
            con = dbConnectionUtil.getConnection();
            
            String getCategory = "SELECT * FROM category ORDER BY CategoryID ASC";
            preparedStatement = con.prepareStatement(getCategory, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                Category category = new Category();
                
                category.setCategoryID(resultSet.getString("CategoryID"));
                category.setCategoryName(resultSet.getString("CategoryName"));
                
                categoryList.add(category);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return categoryList;
    }
    
    public boolean getCategory(Category category) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String getCategory = "SELECT * FROM category WHERE CategoryID = ?";
            preparedStatement = con.prepareStatement(getCategory, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, category.getCategoryID());
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                category.setCategoryName(resultSet.getString("CategoryName"));
                valid = true;
                
            } else {
                valid = false;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
     public boolean updateCategory(Category category) {
        boolean valid = false;
        try {
            con = dbConnectionUtil.getConnection();
            
            String updateCategory = "UPDATE category SET CategoryName = ? WHERE CategoryID = ?";
            preparedStatement = con.prepareStatement(updateCategory, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryID());
            int rowAffected  = preparedStatement.executeUpdate();
            
            if (rowAffected  == 1) {
               valid = true;
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return valid;
    }
    
}
