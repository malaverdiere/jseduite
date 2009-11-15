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

package fr.unice.i3s.modalis.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class AlarmCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private AlarmFinder finder = new AlarmFinder();

    /**
     * Create CRUD pattern operation
     * @param a the transient alarm to transform in a persistent one
     * @return the alarm reference (i.e. its id)
     * @throws AlarmException: null object, still persistent
     */
    @WebMethod(operationName = "createAlarm")
    public int createAlarm(@WebParam(name = "a") Alarm a)
            throws AlarmException {
        if (null == a)
            throw new AlarmException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `alarms` ";
        sql += "(`kind`, `message`, `sound`, `break_id`) VALUES (";
        sql += "'"+a.getKind()+"','"+a.getMessage()+"','"+a.getSound()+"',";
        sql += "'"+a.getBreakTime().getId()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `alarms`";

        try {
            /* Alarm creation */
            dal.executeVoid(sql);
            int id = Integer.parseInt(dal.extractScalar(idGetter, "id"));

            return id;
        } catch (Exception e) {
            throw new AlarmException("SQL Exception: " + e.getMessage());
        }
    }


    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent alarm
     * @return the expected alarm
     * @throws AlarmException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readAlarm")
    public Alarm readAlarm(@WebParam(name = "id") int id)
            throws AlarmException {
        Alarm found = finder.findAlarmById(id);
        if (null == found) {
            throw new AlarmException("UnexistingIdRead: " + id);
        }
        return found;
    }


    /**
     * Update CRUD pattern operation
     * @param a the persistent alarm to update
     * @throws AlarmException: null object, non persistent object
     */
    @WebMethod(operationName = "updateAlarm")
    public void updateAlarm(@WebParam(name = "a") Alarm a)
            throws AlarmException {
        if (null == a) {
            throw new AlarmException("Null update !");
        }
        if (0 == a.getId()) {
            throw new AlarmException("Unreferenced update !");
        }

        String sql = "UPDATE `alarms` ";
        sql += "SET `kind` = '"+a.getKind()+"', ";
        sql += "`message` = '"+a.getMessage()+"', ";
        sql += "`sound` = '"+a.getSound()+"', ";
        sql += "`break_id` = '"+a.getBreakTime().getId()+"' ";
        sql += "WHERE `id` = '" + a.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new AlarmException("SQLException: " + e.getMessage());
       }

    }


    /**
     * Delete CRUD pattern operation
     * @param a the persistent alarm to delete
     * @throws AlarmException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteAlarm")
    public void deleteAlarm(@WebParam(name = "a") Alarm a)
            throws AlarmException {
        if (null == a)
            throw new AlarmException("Null delete !");
        if (0 == a.getId())
            throw new AlarmException("Unreferenced delete !");

        String sql = "DELETE FROM `alarms`";
        sql += "WHERE `id` = '" + a.getId()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new AlarmException("SQLException: " + e.getMessage());
       }
    }
}
