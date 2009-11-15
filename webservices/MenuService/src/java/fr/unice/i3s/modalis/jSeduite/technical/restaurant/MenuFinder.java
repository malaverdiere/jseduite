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
**/
package fr.unice.i3s.modalis.jSeduite.technical.restaurant;

import java.util.Date;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.GregorianCalendar;

/** Finder implementation for the Menu business object
 */
@WebService()
public class MenuFinder {

    /** FindByDate operation from the Finder pattern
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
            sql += " AND `menu`.`course` = `course`.`name`;";
            DalResultSet rSet = dal.extractDataSet(sql);
            if (rSet.size() == 0)
                return null;
            Menu result = new Menu();
            GregorianCalendar cal = new GregorianCalendar();
            String[] items = rSet.getValue("date").split("-");
            cal.set(Integer.parseInt(items[0]),Integer.parseInt(items[1]),
                    Integer.parseInt(items[2]));
            result.setDate(cal.getTime());
            ArrayList<Course> courses = new ArrayList<Course>(rSet.size());
            for(int i = 0; i < rSet.size(); i++){
                courses.add(new Course(rSet));
                rSet.next();
            }
            result.setCourses(courses.toArray(new Course[courses.size()]));
            return result;
        } catch(Exception e) {
            throw new RestaurantException(e.getMessage());
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
                GregorianCalendar cal = new GregorianCalendar();
                String[] items = date.split("-");
                cal.set(Integer.parseInt(items[0]),Integer.parseInt(items[1]),
                        Integer.parseInt(items[2]));
                result.add(cal.getTime());
            }
            return result.toArray(new Date[result.size()]);
        } catch(Exception e) {
            throw new RestaurantException(e.getMessage());
        }
    }
}
