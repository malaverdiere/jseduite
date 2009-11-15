/**
 * This file is part of jSeduite::ProfileManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ProfileManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ProfileManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ProfileManager; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles.devices;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class DeviceCRUD {

    private DeviceFinder finder = new DeviceFinder();

    /**
     * Create CRUD pattern operation
     * @param d the transient device to transform in a persistent one
     * @return the device reference (i.e. its name)
     * @throws DeviceException: null object, still persistent
     */
    @WebMethod(operationName = "createDevice")
    public String createDevice(@WebParam(name = "d") Device d)
            throws DeviceException {
        if (null == d)
            throw new DeviceException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `devices`";
        sql += "(`name`, `location`) ";
        sql += "VALUES (";
        sql += "'"+d.getName()+"','"+d.getLocation()+"');";


        try {
            dal.executeVoid(sql);
            return d.getName();
        } catch (Exception e) {
            throw new DeviceException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param name an existing reference (i.e. name) to a persistent device
     * @return the expected device
     * @throws DeviceException: null name or not binded to persistent object
     */
    @WebMethod(operationName = "readDevice")
    public Device readDevice(@WebParam(name = "name") String name)
            throws DeviceException {
        Device found = finder.findDeviceByName(name);
        if (null == found) {
            throw new DeviceException("UnexistingNameRead: " + name);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param d the persistent device to update
     * @throws DeviceException: null object, non persistent object
     */
    @WebMethod(operationName = "updateDevice")
    public void updateDevice(@WebParam(name = "d") Device d)
            throws DeviceException {
        if (null == d) {
            throw new DeviceException("Null update !");
        }
        if (null == d.getName()) {
            throw new DeviceException("Unreferenced update !");
        }

        String sql = "UPDATE `devices`";
        sql += "SET `location` = '"+d.getLocation()+"' ";
        sql += "WHERE `name` = '" + d.getName()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new DeviceException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param d the persistent device to delete
     * @throws DeviceException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteDevice")
    public void deleteDevice(@WebParam(name = "d") Device d)
            throws DeviceException {
        if (null == d)
            throw new DeviceException("Null delete !");
        if (null == d.getName())
            throw new DeviceException("Unreferenced delete !");

        String sql = "DELETE FROM `devices`";
        sql += "WHERE `name` = '" + d.getName()+"';";

        String sql_sub = "DELETE FROM `device_subscription`";
        sql_sub += "WHERE `device` = '" + d.getName()+"';";

        String sql_par = "DELETE FROM `device_parametrization`";
        sql_par += "WHERE `device_id` = '" + d.getName()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql_sub);
            dal.executeVoid(sql_par);
        } catch(Exception e) {
           throw new DeviceException("SQLException: " + e.getMessage());
       }
    }
}
