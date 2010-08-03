/**
 * This file is part of jSeduite::breakScreen
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::breakScreen is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Christophe Desclaux         [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaks;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class BreakScreenCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private BreakScreenFinder finder = new BreakScreenFinder();

    /**
     * A static method to transform a java Date into a valid SQL entry
     * @param date the date to transform
     * @return a string formatted as HH:MM
     */
    public static String toSql(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        return d;
    }

    /**
     * Create CRUD pattern operation
     * @param b the transient break screen to transform in a persistent one
     * @return the break screen reference (i.e. its id)
     * @throws BreakScreenException: null object, still persistent
     */
    @WebMethod(operationName = "createBreakScreen")
    public int createBreakScreen(@WebParam(name = "b") BreakScreen b)
            throws BreakScreenException {
        if (null == b)
            throw new BreakScreenException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlStart = toSql(b.getStart());
        String sqlEnd = toSql(b.getEnd());

        String sql_bt = "INSERT INTO `break_screen` ";
        sql_bt += "(`start`, `end`, `building`, `content`) VALUES (";
        sql_bt += "'" + sqlStart + "','" + sqlEnd + "','" + b.getBuilding() + "','" + b.getContent() + "');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `break_screen`";

        try {
            /* Break screen creation */
            dal.executeVoid(sql_bt);
            int id = Integer.parseInt(dal.extractScalar(idGetter, "id"));


            /* Days associations */
            String[] days = b.getDays();

            String sql_days;

            for(String day : days) {
                sql_days = "INSERT INTO `break_screen_days`";
                sql_days += "(`break_id`, `day`) VALUES (";
                sql_days += "'"+id+"','"+day+"');";

                dal.executeVoid(sql_days);
            }

            return id;
        } catch (Exception e) {
            throw new BreakScreenException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent break screen
     * @return the expected break screen
     * @throws BreakScreenException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readBreakScreen")
    public BreakScreen readBreakScreen(@WebParam(name = "id") int id)
            throws BreakScreenException {
        BreakScreen found = finder.findBreakScreenById(id);
        if (null == found) {
            throw new BreakScreenException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param b the persistent break screen to update
     * @throws BreakScreenException: null object, non persistent object
     */
    @WebMethod(operationName = "updateBreakScreen")
    public void updateBreakScreen(@WebParam(name = "b") BreakScreen b)
            throws BreakScreenException {
        if (null == b) {
            throw new BreakScreenException("Null update !");
        }
        if (0 == b.getId()) {
            throw new BreakScreenException("Unreferenced update !");
        }

        String sql = "UPDATE `break_screen`";
        sql += "SET `start` = '"+toSql(b.getStart())+"', ";
        sql += "`end` = '"+toSql(b.getEnd())+"', ";
        sql += "`building` = '"+b.getBuilding()+"', ";
        sql += "`content` = '"+b.getContent()+"' ";
        sql += "WHERE `id` = '" + b.getId()+"';";

        String sql_day_deletion = "DELETE FROM `break_screen_days`";
        sql_day_deletion += " WHERE `break_id` = '" + b.getId()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql_day_deletion);
            dal.executeVoid(sql);

            /* Days associations */
            String[] days = b.getDays();

            String sql_days = "";

            for(String day : days) {
                sql_days = "INSERT INTO `break_screen_days`";
                sql_days += "(`break_id`, `day`) VALUES (";
                sql_days += "'"+b.getId()+"','"+day+"');";

                dal.executeVoid(sql_days);
            }


        } catch(Exception e) {
           throw new BreakScreenException("SQLException: " + e.getMessage());
       }

    }

    /**
     * Delete CRUD pattern operation
     * @param b the persistent break screen to delete
     * @throws BreakScreenException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteBreakScreen")
    public void deleteBreakScreen(@WebParam(name = "b") BreakScreen b)
            throws BreakScreenException {
        if (null == b)
            throw new BreakScreenException("Null delete !");
        if (0 == b.getId())
            throw new BreakScreenException("Unreferenced delete !");

        String sql = "DELETE FROM `break_screen`";
        sql += "WHERE `id` = '" + b.getId()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
         } catch(Exception e) {
           throw new BreakScreenException("SQLException: " + e.getMessage());
       }
    }
}
