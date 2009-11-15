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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class SourceCRUD {
    private SourceFinder finder = new SourceFinder();

    /**
     * Create CRUD pattern operation
     * @param s the transient source to transform in a persistent one
     * @return the source reference (i.e. its nickname)
     * @throws SourceException: null object, still persistent
     */
    @WebMethod(operationName = "createSource")
    public String createSource(@WebParam(name = "s") Source s)
            throws SourceException {
        if (null == s)
            throw new SourceException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `sources`";
        sql += "(`nickname`, `name`) ";
        sql += "VALUES (";
        sql += "'"+s.getNickname()+"','"+s.getName()+"');";

        try {
            dal.executeVoid(sql);
            return s.getNickname();
        } catch (Exception e) {
            throw new SourceException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param nickname an existing reference (i.e. nickname) to a persistent source
     * @return the expected source
     * @throws SourceException: null nickname or not binded to persistent object
     */
    @WebMethod(operationName = "readSource")
    public Source readSource(@WebParam(name = "nickname") String nickname)
            throws SourceException {
        Source found = finder.findSourceByNickname(nickname);
        if (null == found) {
            throw new SourceException("UnexistingNameRead: " + nickname);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param s the persistent source to update
     * @throws SourceException: null object, non persistent object
     */
    @WebMethod(operationName = "updateSource")
    public void updateSource(@WebParam(name = "s") Source s)
            throws SourceException {
        if (null == s) {
            throw new SourceException("Null update !");
        }
        if (null == s.getName()) {
            throw new SourceException("Unreferenced update !");
        }

        String sql = "UPDATE `devices`";
        sql += "SET `nickname` = '"+s.getNickname()+"' ";
        sql += "WHERE `name` = '" + s.getName()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new SourceException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param s the persistent source to delete
     * @throws SourceException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteSource")
    public void deleteSource(@WebParam(name = "s") Source s)
            throws SourceException {
        if (null == s)
            throw new SourceException("Null delete !");
        if (null == s.getName())
            throw new SourceException("Unreferenced delete !");

        String sql = "DELETE FROM `sources`";
        sql += "WHERE `nickname` = '" + s.getNickname()+"';";

        String sql_par = "DELETE FROM `parameters`";
        sql_par += "WHERE `source` = '" + s.getNickname()+"';";

        String sql_sub = "DELETE FROM `device_subscription`";
        sql_sub += "WHERE `source` = '" + s.getNickname()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql_sub);
            dal.executeVoid(sql_par);
        } catch(Exception e) {
           throw new SourceException("SQLException: " + e.getMessage());
       }
    }
}
