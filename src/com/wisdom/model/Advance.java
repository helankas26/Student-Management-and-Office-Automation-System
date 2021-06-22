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
public class Advance {
    
    private String advanceID;
    private String discription;
    private double advanceAmount;
    private String advanceDate;
    private Staff staffHandler;
    private Staff staffReceiver;
    private Teacher teacher;

    public Advance() {
    }

    public String getAdvanceID() {
        return advanceID;
    }

    public void setAdvanceID(String advanceID) {
        this.advanceID = advanceID;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public String getAdvanceDate() {
        return advanceDate;
    }

    public void setAdvanceDate(String advanceDate) {
        this.advanceDate = advanceDate;
    }

    public Staff getStaffHandler() {
        return staffHandler;
    }

    public void setStaffHandler(Staff staffHandler) {
        this.staffHandler = staffHandler;
    }

    public Staff getStaffReceiver() {
        return staffReceiver;
    }

    public void setStaffReceiver(Staff staffReceiver) {
        this.staffReceiver = staffReceiver;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
}
