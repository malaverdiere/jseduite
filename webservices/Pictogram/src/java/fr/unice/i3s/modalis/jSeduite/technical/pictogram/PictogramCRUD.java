/**
 * This file is part of jSeduite::Pictograms
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Pictograms is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Pictograms is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Christophe Desclaux        [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.pictogram;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class PictogramCRUD {

    private PictogramFinder finder = new PictogramFinder();

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
    @WebMethod(operationName = "createPictogram")
    public int createPictogram(@WebParam(name = "i") Pictogram i)
            throws PictogramException {
        if (null == i)
            throw new PictogramException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlStart = toSql(i.getStart());
        String sqlEnd = toSql(i.getEnd());

        String sql = "INSERT INTO `pictograms`";
        sql += "(`start`, `end`, `picture1`, `picture2`, `title`) ";
        sql += "VALUES (";
        sql += "'"+sqlStart+"','"+sqlEnd+"', '"+i.getPicture1()+"',";
        sql += "'"+i.getPicture2()+"','"+i.getTitle()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `pictograms`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new PictogramException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent internal news
     * @return the expected internal news
     * @throws InternalNewsException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readPictogram")
    public Pictogram readPictogram(@WebParam(name = "id") int id)
            throws PictogramException {
        Pictogram found = finder.findPictogramById(id);
        if (null == found) {
            throw new PictogramException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param i the persistent Pictogram to update
     * @throws PictogramException: null object, non persistent object
     */
    @WebMethod(operationName = "updatePictogram")
    public void updatePictogram(@WebParam(name = "i") Pictogram i)
            throws PictogramException {
        if (null == i) {
            throw new PictogramException("Null update !");
        }
        if (0 == i.getId()) {
            throw new PictogramException("Unreferenced update !");
        }

        String sql = "UPDATE `pictograms`";
        sql += "SET `start` = '"+toSql(i.getStart())+"', ";
        sql += "`end` = '"+toSql(i.getEnd())+"', ";
        sql += "`picture1` = '"+i.getPicture1()+"', ";
        sql += "`picture2` = '"+i.getPicture2()+"', ";
        sql += "`title` = '"+i.getTitle()+"' ";
        sql += "WHERE `id` = '" + i.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PictogramException("SQLException: " + e.getMessage()+"\n"+sql);
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param i the persistent Pictogram to delete
     * @throws PictogramException: null object, non persistent object
     */
    @WebMethod(operationName = "deletePictogram")
    public void deletePictogram(@WebParam(name = "i") Pictogram i)
            throws PictogramException {
        if (null == i)
            throw new PictogramException("Null delete !");
        if (0 == i.getId())
            throw new PictogramException("Unreferenced delete !");

        String sql = "DELETE FROM `pictograms`";
        sql += "WHERE `id` = '" + i.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PictogramException("SQLException: " + e.getMessage());
       }
    }
}
