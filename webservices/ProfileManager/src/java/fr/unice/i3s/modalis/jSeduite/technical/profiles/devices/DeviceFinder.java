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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class DeviceFinder {
    /**
     * FindByReference operation
     * @param name the element name you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findDeviceByName")
    public Device findDeviceByName(@WebParam(name = "name") String name)
            throws DeviceException {

        String sql = "SELECT * FROM `devices`";
        sql += "WHERE `name` = '" + name + "';";

        //TODO : sources + parametrization

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return new Device(rSet);
        } catch(Exception e) {
            throw new DeviceException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Device business object
     * @return all existing device references
     * @throws DeviceException
     */
    @WebMethod(operationName="getAllDeviceReferences")
    public String[] getAllDeviceReferences() throws DeviceException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `name` FROM `devices`;";
            String[] results = dal.extractScalarSet(sql, "name");

            return results;
        } catch (Exception e) {
            throw new DeviceException("SQL Exception: " + e.getMessage());
        }
    }
}
