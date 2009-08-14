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
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.summon;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class SummoningCRUD {

    /// A finder, to explore the persistences layer in an OO way
    private SummoningFinder finder = new SummoningFinder();

    /**
     * A static method to transform a java Date into a valid SQL entry
     * @param date the date to transform
     * @return a string formatted as YYYY-MM-DD HH:MM
     */
    public static String toSql(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        String d = "" + cal.get(Calendar.YEAR);
        d += "-" + (cal.get(Calendar.MONTH)+1);
        d += "-" + cal.get(Calendar.DAY_OF_MONTH);
        d += " " + cal.get(Calendar.HOUR_OF_DAY);
        d += ":" + cal.get(Calendar.MINUTE);
        d += ":" + cal.get(Calendar.SECOND);
        return d;
    }

    /**
     * Create CRUD pattern operation
     * @param s the transient summoning to transform in a persistent one
     * @return the summoning reference (i.e. its id)
     * @throws StudentsSummonException: null object, still persistent
     */
    @WebMethod(operationName = "createSummoning")
    public int createSummoning(@WebParam(name = "s") Summoning s)
            throws StudentsSummonException {
        if (null == s)
            throw new StudentsSummonException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlDate = toSql(s.getDate());

        int valid;
        if(s.getValid()) {
            valid = 1;
        }
        else {
            valid = 0;
        }

        String sql = "INSERT INTO `summonings`";
        sql += "(`student` , `promo`, `owner`, `stamp`, `level`, `valid`) ";
        sql += "VALUES ('"+s.getStudent()+"','"+s.getPromo().getId()+"','"+s.getOwner();
        sql += "','"+sqlDate+"','"+s.getLevel()+"','"+valid+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `summonings`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new StudentsSummonException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent summoning
     * @return the expected summoning
     * @throws StudentsSummonException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readSummoning")
    public Summoning readSummoning(@WebParam(name = "id") int id)
            throws StudentsSummonException {
        Summoning found = finder.findSummoningById(id);
        if (null == found) {
            throw new StudentsSummonException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param s the persistent summoning to update
     * @throws StudentsSummonException: null object, non persistent object
     */
    @WebMethod(operationName = "updateSummoning")
    public void updateSummoning(@WebParam(name = "s") Summoning s)
            throws StudentsSummonException {
        if (null == s) {
            throw new StudentsSummonException("Null update !");
        }
        if (0 == s.getId()) {
            throw new StudentsSummonException("Unreferenced update !");
        }

        int valid;
        if(s.getValid()) {
            valid = 1;
        }
        else {
            valid = 0;
        }

        String sql = "UPDATE `summonings`";
        sql += "SET `student` = '"+s.getStudent()+"', ";
        sql += "`promo` = '"+s.getPromo().getId()+"', ";
        sql += "`owner` = '"+s.getOwner()+"', ";
        sql += "`stamp` = '"+toSql(s.getDate())+"', ";
        sql += "`owner` = '"+s.getOwner()+"', ";
        sql += "`valid` = '"+valid+"', ";
        sql += "`level` = '"+s.getLevel()+"' ";
        sql += "WHERE `id` = '" + s.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new StudentsSummonException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param s the persistent summoning to delete
     * @throws StudentsSummonException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteSummoning")
    public void deleteSummoning(@WebParam(name = "s") Summoning s)
            throws StudentsSummonException {
        if (null == s)
            throw new StudentsSummonException("Null delete !");
        if (0 == s.getId())
            throw new StudentsSummonException("Unreferenced delete !");

        String sql = "DELETE FROM `summonings`";
        sql += "WHERE `id` = '" + s.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new StudentsSummonException("SQLException: " + e.getMessage());
       }
    }
}
