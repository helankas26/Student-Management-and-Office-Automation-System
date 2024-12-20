/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wisdom.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Helanka
 */
public class SQLDateTimeUtil {
    
    private static Date sqlDate;
    
    public static Date parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        sqlDate = Date.valueOf(localDate);

        return sqlDate;
    }
    
    public static String toStringTime(String time) {
        LocalTime localTime = LocalTime.parse(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        return localTime.format(formatter);
    }

}
