/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangbtt.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Valid {


     public static boolean isValidDate(String date) {
        
             try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date d = format.parse(date);
            String check = format.format(d);
            if (check.equals(date)) {
               if(check.compareTo(getCurrentDate()) > 0 ){
                   return true;
               }
              else if(check.compareTo(getCurrentDate()) < 0){
                   return false;
               }else{
                  return true;
              }
            }
        } catch (ParseException ex) {
            return false;
        }
        return false;
       

    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
