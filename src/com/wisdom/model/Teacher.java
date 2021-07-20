/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.model;

import java.util.ArrayList;

/**
 *
 * @author Helanka
 */
public class Teacher extends User {
    
    private String title;
    private String qualification;
    private ArrayList<Class> classes;

    public Teacher() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return this.getInitial() + " " + this.getFirstName() + " " + this.getLastName();
    }
    
}
