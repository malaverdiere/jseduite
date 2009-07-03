/**
 * This file is part of jSeduite::InternalNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::InternalNews is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::InternalNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.internal;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class InternalNewsCRUD {

    private InternalNewsFinder finder = new InternalNewsFinder();

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
     * @param b the transient internal news to transform in a persistent one
     * @return the internal news reference (i.e. its id)
     * @throws InternalNewsException: null object, still persistent
     */
    @WebMethod(operationName = "createInternalNews")
    public int createInternalNews(@WebParam(name = "i") News i)
            throws InternalNewsException {
        if (null == i)
            throw new InternalNewsException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlStart = toSql(i.getStart());
        String sqlEnd = toSql(i.getEnd());

        String sql = "INSERT INTO `internal_news`";
        sql += "(`target`, `author`, `start`, `end`, `title`, `content`) ";
        sql += "VALUES (";
        sql += "'"+i.getTarget()+"','"+i.getAuthor()+"','"+sqlStart+"','";
        sql += sqlEnd+"','"+i.getTitle()+"','"+i.getContent()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `internal_news`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new InternalNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent internal news
     * @return the expected internal news
     * @throws InternalNewsException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readInternalNews")
    public News readInternalNews(@WebParam(name = "id") int id)
            throws InternalNewsException {
        News found = finder.findInternalNewsById(id);
        if (null == found) {
            throw new InternalNewsException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param i the persistent internal news to update
     * @throws InternalNewsException: null object, non persistent object
     */
    @WebMethod(operationName = "updateInternalNews")
    public void updateInternalNews(@WebParam(name = "i") News i)
            throws InternalNewsException {
        if (null == i) {
            throw new InternalNewsException("Null update !");
        }
        if (0 == i.getId()) {
            throw new InternalNewsException("Unreferenced update !");
        }

        String sql = "UPDATE `internal_news`";
        sql += "SET `target` = '"+i.getTarget()+"', ";
        sql += "`author` = '"+i.getAuthor()+"', ";
        sql += "`start` = '"+toSql(i.getStart())+"', ";
        sql += "`end` = '"+toSql(i.getEnd())+"', ";
        sql += "`title` = '"+i.getTitle()+"', ";
        sql += "`content` = '"+i.getContent()+"' ";
        sql += "WHERE `id` = '" + i.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new InternalNewsException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param i the persistent internal news to delete
     * @throws InternalNewsException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteInternalNews")
    public void deleteInternalNews(@WebParam(name = "i") News i)
            throws InternalNewsException {
        if (null == i)
            throw new InternalNewsException("Null delete !");
        if (0 == i.getId())
            throw new InternalNewsException("Unreferenced delete !");

        String sql = "DELETE FROM `internal_news`";
        sql += "WHERE `id` = '" + i.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new InternalNewsException("SQLException: " + e.getMessage());
       }
    }
}
