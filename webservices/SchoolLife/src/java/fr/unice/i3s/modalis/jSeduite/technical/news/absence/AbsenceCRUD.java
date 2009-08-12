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
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.absence;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class AbsenceCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private AbsenceFinder finder = new AbsenceFinder();

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
     * @param a the transient absence to transform in a persistent one
     * @return the absence reference (i.e. its id)
     * @throws AbsenceTeacherException: null object, still persistent
     */
    @WebMethod(operationName = "createAbsence")
    public int createAbsence(@WebParam(name = "a") Absence a)
            throws TeacherAbsenceException {
        if (null == a)
            throw new TeacherAbsenceException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();
        String sqlFrom = toSql(a.getFrom());
        String sqlUntil = toSql(a.getUntil());

        String sql = "INSERT INTO `teacher_absences`";
        sql += "(`teacher`, `reason`, `from`, `until`) VALUES (";
        sql += "'"+a.getTeacher()+"','"+a.getReason()+"','"+sqlFrom+"','"+sqlUntil+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `teacher_absences`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new TeacherAbsenceException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent absence
     * @return the expected absence
     * @throws TeacherAbsenceException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readAbsence")
    public Absence readAbsence(@WebParam(name = "id") int id)
            throws TeacherAbsenceException {
        Absence found = finder.findAbsenceById(id);
        if (null == found) {
            throw new TeacherAbsenceException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param a the persistent absence to update
     * @throws TeacherAbsenceException: null object, non persistent object
     */
    @WebMethod(operationName = "updateAbsence")
    public void updateAbsence(@WebParam(name = "a") Absence a)
            throws TeacherAbsenceException {
        if (null == a) {
            throw new TeacherAbsenceException("Null update !");
        }
        if (0 == a.getId()) {
            throw new TeacherAbsenceException("Unreferenced update !");
        }

        String sql = "UPDATE `teacher_absences` ";
        sql += "SET `teacher` = '"+a.getTeacher()+"', ";
        sql += "`reason` = '"+a.getReason()+"', ";
        sql += "`from` = '"+toSql(a.getFrom())+"', ";
        sql += "`until` = '"+toSql(a.getUntil())+"' ";
        sql += "WHERE `id` = '" + a.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new TeacherAbsenceException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param a the persistent absence to delete
     * @throws TeacherAbsenceException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteAbsence")
    public void deleteAbsence(@WebParam(name = "a") Absence a)
            throws TeacherAbsenceException {
        if (null == a)
            throw new TeacherAbsenceException("Null delete !");
        if (0 == a.getId())
            throw new TeacherAbsenceException("Unreferenced delete !");

        String sql = "DELETE FROM `teacher_absences`";
        sql += "WHERE `id` = '" + a.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new TeacherAbsenceException("SQLException: " + e.getMessage());
       }
    }
}
