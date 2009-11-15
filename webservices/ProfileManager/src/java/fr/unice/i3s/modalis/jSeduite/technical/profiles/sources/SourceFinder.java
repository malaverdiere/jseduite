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

package fr.unice.i3s.modalis.jSeduite.technical.profiles.sources;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class SourceFinder {
    /**
     * FindByReference operation
     * @param nickname the element nickname you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findSourceByNickname")
    public Source findSourceByNickname(@WebParam(name = "nickname") String nickname)
            throws SourceException {

        String sql = "SELECT * FROM `sources`";
        sql += "WHERE `nickname` = '" + nickname + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return new Source(rSet);
        } catch(Exception e) {
            throw new SourceException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Source business object
     * @return all existing source references
     * @throws SourceException
     */
    @WebMethod(operationName="getAllSourceReferences")
    public String[] getAllSourceReferences() throws SourceException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `nickname` FROM `sources`;";
            String[] results = dal.extractScalarSet(sql, "nickname");

            return results;
        } catch (Exception e) {
            throw new SourceException("SQL Exception: " + e.getMessage());
        }
    }
}
