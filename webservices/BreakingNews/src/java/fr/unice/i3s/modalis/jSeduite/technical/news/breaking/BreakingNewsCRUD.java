/**
 * This file is part of jSeduite::BreakingNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakingNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BreakingNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.breaking;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A Web service for BreakNew business object data handling
 */
@WebService()
public class BreakingNewsCRUD {

    /// A finder, to explore the persistences layer in an OO way
    private BreakingNewsFinder finder = new BreakingNewsFinder();

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
     * @param b the transient breaking news to transform in a persistent one
     * @return the breaking news reference (i.e. its id)
     * @throws BreakingNewsException: null object, still persistent
     */
    @WebMethod(operationName = "createBreakingNews")
    public int createBreakingNews(@WebParam(name = "b") BreakNew b)
            throws BreakingNewsException {
        if (null == b)
            throw new BreakingNewsException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlDate = toSql(b.getStamp());

        String sql = "INSERT INTO `breaking_news`";
        sql += "(`author`, `stamp`, `content`) VALUES (";
        sql += "'"+b.getAuthor()+"','"+sqlDate+"','"+b.getContent()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `breaking_news`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new BreakingNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent breaking news
     * @return the expected breaking news
     * @throws BreakingNewsException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readBreakingNews")
    public BreakNew readBreakingNews(@WebParam(name = "id") int id)
            throws BreakingNewsException {
        BreakNew found = finder.findBreakNewById(id);
        if (null == found) {
            throw new BreakingNewsException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param b the persistent breaking news to update
     * @throws BreakingNewsException: null object, non persistent object
     */
    @WebMethod(operationName = "updateBreakingNews")
    public void updateBreakingNews(@WebParam(name = "b") BreakNew b)
            throws BreakingNewsException {
        if (null == b) {
            throw new BreakingNewsException("Null update !");
        }
        if (0 == b.getId()) {
            throw new BreakingNewsException("Unreferenced update !");
        }

        String sql = "UPDATE `breaking_news`";
        sql += "SET `author` = '"+b.getAuthor()+"', ";
        sql += "`stamp` = '"+toSql(b.getStamp())+"', ";
        sql += "`content` = '"+b.getContent()+"' ";
        sql += "WHERE `id` = '" + b.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BreakingNewsException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param b the persistent breaking news to delete
     * @throws BreakingNewsException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteBreakingNews")
    public void deleteBreakingNews(@WebParam(name = "b") BreakNew b)
            throws BreakingNewsException {
        if (null == b)
            throw new BreakingNewsException("Null delete !");
        if (0 == b.getId())
            throw new BreakingNewsException("Unreferenced delete !");

        String sql = "DELETE FROM `breaking_news`";
        sql += "WHERE `id` = '" + b.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BreakingNewsException("SQLException: " + e.getMessage());
       }
    }
}
