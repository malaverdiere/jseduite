/**
 * This file is part of jSeduite::SchoolLife
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::SchoolLife is free software; you can redistribute it and/or modify
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
 * @author      Steve Colombié         [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import fr.unice.i3s.modalis.jSeduite.technical.promos.Promo;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class BreakTimeCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private BreakTimeFinder finder = new BreakTimeFinder();

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
     * @param b the transient break time to transform in a persistent one
     * @return the break time reference (i.e. its id)
     * @throws BreakTimeException: null object, still persistent
     */
    @WebMethod(operationName = "createBreakTime")
    public int createBreakTime(@WebParam(name = "b") BreakTime b)
            throws BreakTimeException {
        if (null == b)
            throw new BreakTimeException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlStart = toSql(b.getStart());
        String sqlEnd = toSql(b.getEnd());

        String sql_bt = "INSERT INTO `break_time`";
        sql_bt += "(`start`, `end`, `building`, `kind`) VALUES (";
        sql_bt += "'"+sqlStart+"','"+sqlEnd+"','"+b.getBuilding()+"','"+b.getKind()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `break_time`";

        try {
            /* Break time creation */
            dal.executeVoid(sql_bt);
            int id = Integer.parseInt(dal.extractScalar(idGetter, "id"));

            /* Promotions associations */
            Promo[] promos = b.getPromos();
            String sql_promos;

            for(Promo promo : promos) {
                sql_promos = "INSERT INTO `break_time_promotions`";
                sql_promos += "(`break_id`, `promo_id`) VALUES (";
                sql_promos += "'"+id+"','"+promo.getId()+"');";

                dal.executeVoid(sql_promos);
            }

            /* Days associations */
            String[] days = b.getDays();

            String sql_days;

            for(String day : days) {
                sql_days = "INSERT INTO `break_time_days`";
                sql_days += "(`break_id`, `day`) VALUES (";
                sql_days += "'"+id+"','"+day+"');";

                dal.executeVoid(sql_days);
            }

            return id;
        } catch (Exception e) {
            throw new BreakTimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent break time
     * @return the expected break time
     * @throws BreakTimeException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readBreakTime")
    public BreakTime readBreakTime(@WebParam(name = "id") int id)
            throws BreakTimeException {
        BreakTime found = finder.findBreakTimeById(id);
        if (null == found) {
            throw new BreakTimeException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param b the persistent break time to update
     * @throws BreakTimeException: null object, non persistent object
     */
    @WebMethod(operationName = "updateBreakTime")
    public void updateBreakTime(@WebParam(name = "b") BreakTime b)
            throws BreakTimeException {
        if (null == b) {
            throw new BreakTimeException("Null update !");
        }
        if (0 == b.getId()) {
            throw new BreakTimeException("Unreferenced update !");
        }

        this.deleteBreakTime(b); // Ouch ... An ugly shortcut (but simple)
        this.createBreakTime(b); // => should be improved in next releases

    }

    /**
     * Delete CRUD pattern operation
     * @param b the persistent break time to delete
     * @throws BreakTimeException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteBreakTime")
    public void deleteBreakTime(@WebParam(name = "b") BreakTime b)
            throws BreakTimeException {
        if (null == b)
            throw new BreakTimeException("Null delete !");
        if (0 == b.getId())
            throw new BreakTimeException("Unreferenced delete !");

        String sql = "DELETE FROM `break_time`";
        sql += "WHERE `id` = '" + b.getId()+"';";

        String sql_promos = "DELETE FROM `break_time_promotions`";
        sql_promos += "WHERE `break_id` = '" + b.getId()+"';";

        String sql_days = "DELETE FROM `break_time_days`";
        sql_days += "WHERE `break_id` = '" + b.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql_promos);
            dal.executeVoid(sql_days);
        } catch(Exception e) {
           throw new BreakTimeException("SQLException: " + e.getMessage());
       }
    }
}
