/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Helanka
 */
public final class IDGeneratorUtil {
    
    private volatile static IDGeneratorUtil instance;
    private final String LOGIN = "LOGIN";
    private final String STAFF = "STAFF";
    private final String TEACHER = "TECHR";
    private final String CLASS = "CLASS";
    private final String CATEGORY = "CTGRY";
    private final String SUBJECT = "SUBJT";
    private String loginID;
    private String staffID;
    private String teacherID;
    private String classID;
    private String categoryID;
    private String subjectID;
    private String studentID;

    private IDGeneratorUtil() {
    }
    
    public static IDGeneratorUtil getInstance() {
        if (instance == null) {
            synchronized (IDGeneratorUtil.class) {
                if (instance == null) {
                    instance = new IDGeneratorUtil();
                }
            }
        }
        return instance;
    }

    public String getLoginID() {
        generateID(LOGIN,IDGeneratorUtilDAO.getCurrentLoginID());
        return loginID;
    }

    public String getStaffID() {
        generateID(STAFF,IDGeneratorUtilDAO.getCurrentStaffID());
        return staffID;
    }

    public String getTeacherID() {
        generateID(TEACHER,IDGeneratorUtilDAO.getCurrentTeacherID());
        return teacherID;
    }

    public String getClassID() {
        generateID(CLASS,IDGeneratorUtilDAO.getCurrentClassID());
        return classID;
    }

    public String getCategoryID() {
        generateID(CATEGORY,IDGeneratorUtilDAO.getCurrentCategoryID());
        return categoryID;
    }

    public String getSubjectID() {
        generateID(SUBJECT,IDGeneratorUtilDAO.getCurrentSubjectID());
        return subjectID;
    }
    
    public String getStudentID(String joiningDate) {
        generateStudentID(joiningDate, IDGeneratorUtilDAO.getCurrentStudentID());
        return studentID;
    }
    
    //To generate unique ID
    private void generateID(String prefix,ArrayList<String> currentID) {
        int rowCount = currentID.size();
        String id = null;
        
        if (rowCount > 0) {
            int counter = Integer.parseInt(currentID.get(currentID.size() - 1).replaceAll("[\\D]", ""));
            if (counter < 999) {
                if (counter < 9) {
                    id = prefix + "00" + (++counter);
                } else if (counter < 99) {
                    id = prefix + "0" + (++counter);
                } else {
                    id = prefix + (++counter);
                }
            } else {
                System.out.println(prefix +"ID creation limit is exceeded for the system");
            }
        } else if (rowCount == 0) {
            id = prefix + "00" + (++rowCount);
        }
        
        switch (prefix) {
            case LOGIN:
                loginID = id;
                break;
            case STAFF:
                staffID = id;
                break;
            case TEACHER:
                teacherID = id;
                break;
            case CLASS:
                classID = id;
                break;
            case CATEGORY:
                categoryID = id;
                break;
            case SUBJECT:
                subjectID = id;
                break;    
        }
    }

    //To generate studentID
    private void generateStudentID(String joiningDate, ArrayList<String> currentID) {
        LocalDate joinDate = LocalDate.parse(joiningDate);
        DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yy");
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");
        String year = joinDate.format(yearFormat);
        String month = joinDate.format(monthFormat);
        String date = joinDate.format(dateFormat);
        String prefix = year + month + date;
        
        int rowCount = currentID.size();
        
        if (rowCount > 0) {
            String lastID = currentID.get(currentID.size() - 1);
            String previousPrefix = lastID.substring(0, 6);
            int counter = Integer.parseInt(lastID.substring(6, 8));
            
            DateTimeFormatter stringParseTODate = DateTimeFormatter.ofPattern("yyMMdd");
            LocalDate parsedDate = LocalDate.parse(previousPrefix, stringParseTODate);
            
            if (joinDate.isEqual(parsedDate)) {
                if (counter < 99) {
                    if (counter < 9) {
                        studentID = previousPrefix + "0" + (++counter);
                    } else {
                        studentID = previousPrefix + (++counter);
                    }
                } else {
                    System.out.println("Student ID creation limit is exceeded for the system today");
                }
            } else if (joinDate.isAfter(parsedDate)) {
                studentID = prefix + "00";
            } else if (joinDate.isBefore(parsedDate)) {
                generateStudentID(joiningDate, IDGeneratorUtilDAO.getCurrentStudentID(joinDate.toString()));
            }

        } else if (rowCount == 0) {
            studentID = prefix + "00";
        } 
    }
    
}
