/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Helanka
 */
public final class DBConnectionUtil {
    
    private volatile static DBConnectionUtil instance;
    private Connection connection;
    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    private DBConnectionUtil() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wisdom/config/config.xml");
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
        XPath xpath = XPathFactory.newInstance().newXPath();
        this.url = (String) xpath.compile("//config//jdbc//url").evaluate(document, XPathConstants.STRING);
        this.driver = (String) xpath.compile("//config//jdbc//driver").evaluate(document, XPathConstants.STRING);
        this.username = (String) xpath.compile("//config//jdbc//username").evaluate(document, XPathConstants.STRING);
        this.password = (String) xpath.compile("//config//jdbc//password").evaluate(document, XPathConstants.STRING);
    }
    
    public static DBConnectionUtil getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                synchronized (DBConnectionUtil.class) {
                    if (instance == null || instance.getConnection().isClosed()) {
                        instance = new DBConnectionUtil();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }
    
    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in registering the driver");
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Error in getting connection");
            Logger.getLogger(DBConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
    
    
}
