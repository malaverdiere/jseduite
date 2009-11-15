/**
 * This file is part of jSeduite::PartnerKeys
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PartnerKeys is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PartnerKeys is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PartnerKeys; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry.partners;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class PartnerKeysCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private PartnerKeysFinder finder = new PartnerKeysFinder();

    /**
     * Create CRUD pattern operation
     * @param p the transient partner key to transform in a persistent one
     * @return the partner key reference (i.e. its key)
     * @throws PartnerKeysException: null object, still persistent
     */
    @WebMethod(operationName = "createPartnerKey")
    public String createPartnerKey(@WebParam(name = "p") PartnerKey p)
            throws PartnerKeysException {
        if (null == p)
            throw new PartnerKeysException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `partners_key`";
        sql += "(`key`, `value`) VALUES (";
        sql += "'"+p.getKey()+"','"+p.getValue()+"');";

        try {
            dal.executeVoid(sql);
            return p.getKey();
        } catch (Exception e) {
            throw new PartnerKeysException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param key an existing reference (i.e. key) to a persistent partner key
     * @return the expected partner key
     * @throws PartnerKeysException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readPartnerKey")
    public PartnerKey readPartnerKey(@WebParam(name = "key") String key)
            throws PartnerKeysException {
        PartnerKey found = finder.findPartnerKeyById(key);
        if (null == found) {
            throw new PartnerKeysException("UnexistingIdRead: " + key);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param p the persistent partner key to update
     * @throws PartnerKeysException: null object, non persistent object
     */
    @WebMethod(operationName = "updatePartnerKey")
    public void updatePartnerKey(@WebParam(name = "p") PartnerKey p)
            throws PartnerKeysException {
        if (null == p) {
            throw new PartnerKeysException("Null update !");
        }
        if (p.getKey() == null) {
            throw new PartnerKeysException("Unreferenced update !");
        }

        String sql = "UPDATE `partners_key`";
        sql += "SET `value` = '"+p.getValue()+"' ";
        sql += "WHERE `key` = '" + p.getKey()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PartnerKeysException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param p the persistent partner key to delete
     * @throws PartnerKeysException: null object, non persistent object
     */
    @WebMethod(operationName = "deletePartnerKey")
    public void deletePartnerKey(@WebParam(name = "p") PartnerKey p)
            throws PartnerKeysException {
        if (null == p)
            throw new PartnerKeysException("Null delete !");
        if (p.getKey() == null)
            throw new PartnerKeysException("Unreferenced delete !");

        String sql = "DELETE FROM `partners_key`";
        sql += "WHERE `key` = '" + p.getKey()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new PartnerKeysException("SQLException: " + e.getMessage());
       }
    }
}
