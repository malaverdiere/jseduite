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
 * @author      Mireille Blay-Fornarino [blay@polytech.unice.fr]
 * @contributor 2009 Mosser Sebastien   [mosser@polytech.unice.fr]
 * @contributor 2010 Desclaux Christophe[desclaux@polytech.unice.fr]
 **/
package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/** CRUD pattern implementation for Menu business object
 *  Create~Read~Update~Delete 'automagic' data access layer
 */
@WebService()
public class MenuCRUD {

    // A finder to retrieves menu whan needed
    private MenuFinder finder = new MenuFinder();

    /** A static method to transform a SQL entry into a java Date
     * @param dateString the SQL date entry to transform
     * @return a java Date
     */
    public static Date toDate(String dateString)throws RestaurantException {
        GregorianCalendar cal;
        Date result = null;
        try{
            cal = new GregorianCalendar();
            String[] items = dateString.split("-");
            String[] items2 = items[2].split(" ");
            String[] items3 = items2[1].split(":");
            cal.set(Integer.parseInt(items[0]) , Integer.parseInt(items[1]),
                    Integer.parseInt(items2[0]), Integer.parseInt(items3[0]),
                    Integer.parseInt(items3[1]), 0);
            result = cal.getTime();
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
        d += "-" + cal.get(Calendar.MONTH);
        d += "-" + cal.get(Calendar.DAY_OF_MONTH);
        d += " " + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        d += ":00";
        return d;
    }

    /** Create CRUD pattern operation
     * @param m the transient menu to transform as a peristent one
     * @return the menu reference (i.e. its date)
     * @throws RestaurantException: null object, still persistent
     */
    @WebMethod(operationName = "createMenu")
    public Date createMenu(@WebParam(name = "m") Menu m)
            throws RestaurantException {
        if (null == m) {
            throw new RestaurantException("Null creation !");
        }
        if (null != finder.findMenuByDate(m.getDate())) {
            throw new RestaurantException("Re-Creation !");
        }
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String d = toSql(m.getDate());
            for (Course c : m.getCourses()) {
                String sql = "INSERT INTO `menu` (`date`, `courseId`, `typeMenu`) VALUES (";
                sql += "'" + d + "','" + c.getId()+ "', '" + m.getTypeMenu() + "');";
                dal.executeVoid(sql);
            }
            return m.getDate();
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }

    /** Read CRUD pattern operation
     * @param ref an existing reference (i.e. date) to a persistent menu
     * @return the expected persistent menu
     * @throws RestaurantException: null ref or not binded to persistent object
     */
    @WebMethod(operationName = "readMenu")
    public Menu readMenu(@WebParam(name = "ref") Date ref)
            throws RestaurantException {
        if (null == ref) {
            throw new RestaurantException("Null read !");
        }
        Menu found = finder.findMenuByDate(ref);
        if (null == found) {
            throw new RestaurantException("UnexistingRefRead: " + ref);
        }
        return found;
        }

    /** Update CRUD pattern operation
     * @param m the persistent menu to update
     * @throws RestaurantException: null object, non persistent object
     */
    @WebMethod(operationName = "updateMenu")
    public void updateMenu(@WebParam(name = "m") Menu m)
            throws RestaurantException {
        if (null == m) {
            throw new RestaurantException("Null update !");
        }
        if (null == m.getDate()) {
            throw new RestaurantException("Unreferenced update !");
        }
        this.deleteMenu(m); // Ouch ... An ugly shortcut (but simple)
        this.createMenu(m); // => should be improved in next releases
    }

    /** Delete CRUD pattern operation
     * @param m the persistent menu to delete
     * @throws RestaurantException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteMenu")
    public void deleteMenu(@WebParam(name = "m") Menu m)
            throws RestaurantException {
        if (null == m) {
            throw new RestaurantException("Null delete !");
        }
        if (null == m.getDate()) {
            throw new RestaurantException("Unreferenced delete !");
        }
        String k = toSql(m.getDate());
        String sql = "DELETE FROM `menu` WHERE `date` = '" + k + "';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch (Exception e) {
            throw new RestaurantException("SQLException: " + e.getMessage());
        }
    }
}
