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

import java.util.Date;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

/** Finder implementation for the Menu business object
 */
@WebService()
public class MenuFinder {

    /** FindNextDateMenu return the Date of a futur  Menu
     * @param date the element you're looking for
     * @param delta the delta time for saying old menu in minutes
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findNextDateMenu")
    public Date findNextDateMenu(@WebParam(name = "date") Date date,
                                 @WebParam(name = "delta") int delta)
            throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            Date searchDate = new Date(date.getTime() - delta*60*1000);


            String sql = "SELECT date FROM `menu` WHERE";
            sql += " `date` >= '" + MenuCRUD.toSql(searchDate) + "'";
            sql += "ORDER BY date ASC Limit 1;";
            DalResultSet rSet = dal.extractDataSet(sql);
            String nextDate  = rSet.getValue("date");
            if (rSet.size() == 0)
                return null;
            return MenuCRUD.toDate(nextDate);
        } catch(Exception e) {
            throw new RestaurantException("SQL Exception:" + e.getMessage());
        }
    }


   /** Return all available type of menus
     * @return a String[] containing all those types
     */
    @WebMethod(operationName="findAvailableMenuType")
    public String[] findAvailableMenuType() throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT DISTINCT `typeMenu` FROM  `menu`;";
            return dal.extractScalarSet(sql, "typeMenu");
        } catch (Exception e) {
            throw new RestaurantException("SQL Exception: " + e.getMessage());
        }
    }


    /** FindMenuByDate operation from the Finder pattern
     * @param date the element you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findMenuByDate")
    public Menu findMenuByDate(@WebParam(name = "date") Date date)
            throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String kDate = MenuCRUD.toSql(date);
            String sql = "SELECT * FROM `menu`, `course` WHERE";
            sql += " `date` = '" + kDate + "'";
            sql += " AND `menu`.`courseId` = `course`.`id`;";
            DalResultSet rSet = dal.extractDataSet(sql);
            if (rSet.size() == 0)
                return null;
            Menu result = new Menu();
            result.setDate(MenuCRUD.toDate(rSet.getValue("date")));
            result.setTypeMenu(rSet.getValue("typeMenu"));
            ArrayList<Course> courses = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                courses.add(new Course(rSet));
                rSet.next();
            }
            result.setCourses(courses.toArray(new Course[courses.size()]));
            return result;
        } catch(Exception e) {
            throw new RestaurantException("SQL error:" + e.getMessage());
        }
    }

    /** Retrieves all persistent references
     * @return Extract all menu references (i.e. dates)
     * @throws RestaurantException
     */
    @WebMethod(operationName = "getAllMenuReferences")
    public Date[] getAllMenuReferences() throws RestaurantException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT DISTINCT `date` from `menu`;";
            String[] dates = dal.extractScalarSet(sql,"date");
            ArrayList<Date> result = new ArrayList<Date>();
            for(String date: dates) {
                result.add(MenuCRUD.toDate(date));
            }
            return result.toArray(new Date[result.size()]);
        } catch(Exception e) {
            throw new RestaurantException("SQL error:" + e.getMessage());
        }
    }
}
