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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class PartnerKeysFinder {
    /**
     * FindById operation
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findPartnerKeyById")
    public PartnerKey findPartnerKeyById(@WebParam(name = "key") String key)
            throws PartnerKeysException {

        String sql = "SELECT * FROM `partners_key`";
        sql += "WHERE `key` = '" + key + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return new PartnerKey(rSet);
        } catch(Exception e) {
            throw new PartnerKeysException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the PartnerKey business object
     * @return all existing partner keys references
     * @throws PartnerKeysException
     */
    @WebMethod(operationName="getAllPartnerKeysReferences")
    public String[] getAllPartnerKeysReferences() throws PartnerKeysException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `key` FROM `partners_key`;";
            String[] results = dal.extractScalarSet(sql, "key");
            String[] keys = new String[results.length];

            for(int i=0; i<results.length; i++) {
                keys[i] = results[i];
            }

            return keys;
        } catch (Exception e) {
            throw new PartnerKeysException("SQL Exception: " + e.getMessage());
        }
    }
}
