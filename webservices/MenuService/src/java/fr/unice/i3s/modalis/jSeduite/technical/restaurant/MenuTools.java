/** This file is part of jSeduite::MenuService
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::MenuService is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::MenuService is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::MenuService; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author 2010 Desclaux Christophe[desclaux@polytech.unice.fr]
**/

package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
