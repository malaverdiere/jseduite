/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author descl
 */
public class MenuTools {
    /** A static method to transform a SQL entry into a java Date
     * @param dateString the SQL date entry to transform
     * @return a java Date
     */
    public static Date toDate(String dateString)throws RestaurantException {
        Date  result;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        try{
            result = format.parse(dateString);
        } catch(Exception e){
               throw new RestaurantException("Date translation Exception: " +
                       "element parse:" + dateString +"\n error: " +
                       "" + e.getMessage() +"");
        }
       return result;
    }

   /** A static method to transform a java Date into a valid SQL entry
     * @param date the date to transform
     * @return a string formatted as YYYY-MM-DD HH:MM:SS
     */
    public static String toSql(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.YEAR);
        d += "-" + (cal.get(Calendar.MONTH) + 1);
        d += "-" + cal.get(Calendar.DAY_OF_MONTH);
        d += " " + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        d += ":00";
        return d;
    }
}
