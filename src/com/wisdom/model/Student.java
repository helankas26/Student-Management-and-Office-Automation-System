/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model;

/**
 *
 * @author Helanka
 */
public class Student extends User {
    
    private String grade;
    private String medium;
    private String school;
    private Parent parent;

    public Student() {
        parent = new Parent();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Parent getParent() {
        return parent;
    }

//    public void setParent(Parent parent) {
//        this.parent = parent;
//    }

    @Override
    public String toString() {
        return this.getInitial() + " " + this.getFirstName() + " " + this.getLastName();
    }
    
}
