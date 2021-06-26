/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.util;

import java.util.ArrayList;

/**
 *
 * @author Helanka
 */
public class IDGeneratorUtil {
    
    private volatile static IDGeneratorUtil instance;
    private String year;
    private String month;
    private String date;
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
    
    //To generate
    private void generateID(String keyword,ArrayList<String> currentID) {
        int rowCount = currentID.size();
        String id = null;
        
        if (rowCount > 0) {
            int counter = Integer.parseInt(currentID.get(currentID.size() - 1).replaceAll("[\\D]", ""));
            if (counter < 999) {
                if (counter < 9) {
                    id = keyword + "00" + (++counter);
                } else if (counter < 99) {
                    id = keyword + "0" + (++counter);
                } else {
                    id = keyword + (++counter);
                }
            } else {
                System.out.println(keyword +"ID creation limit is exceeded for the system");
            }
        } else if (rowCount == 0) {
            id = keyword + "00" + (++rowCount);
        }
        
        switch (keyword) {
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

}
