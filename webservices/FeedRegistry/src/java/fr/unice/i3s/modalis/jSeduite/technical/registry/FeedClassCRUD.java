/**
 * This file is part of jSeduite::FeedRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FeedRegistry is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FeedRegistry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::FeedRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class FeedClassCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private FeedClassFinder finder = new FeedClassFinder();

    /**
     * Create CRUD pattern operation
     * @param c the transient feed class to transform in a persistent one
     * @return the feed class reference (i.e. its id)
     * @throws FeedRegistryException: null object, still persistent
     */
    @WebMethod(operationName = "createFeedClass")
    public int createFeedClass(@WebParam(name = "c") FeedClass c)
            throws FeedRegistryException {
        if (null == c)
            throw new FeedRegistryException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `feed_class` ";
        sql += "(`id`, `class`) VALUES (";
        sql += "'"+c.getId()+"', '"+c.getName()+"');";

        String idGetter = "SELECT max(`id`) AS `id` FROM `feed_class`";

        try {
            dal.executeVoid(sql);
            return Integer.parseInt(dal.extractScalar(idGetter, "id"));
        } catch (Exception e) {
            throw new FeedRegistryException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param id an existing reference (i.e. id) to a persistent feed class
     * @return the expected feed class
     * @throws FeedRegistryException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readFeedClass")
    public FeedClass readFeedClass(@WebParam(name = "id") int id)
            throws FeedRegistryException {
        FeedClass found = finder.findFeedClassById(id);
        if (null == found) {
            throw new FeedRegistryException("UnexistingIdRead: " + id);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param c the persistent feed class to update
     * @throws FeedRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "updateFeedClass")
    public void updateFeedClass(@WebParam(name = "c") FeedClass c)
            throws FeedRegistryException {
        if (null == c) {
            throw new FeedRegistryException("Null update !");
        }
        if (0 == c.getId()) {
            throw new FeedRegistryException("Unreferenced update !");
        }

        String sql = "UPDATE `feed_class`";
        sql += "SET `class` = '"+c.getName()+"' ";
        sql += "WHERE `id` = '" + c.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new FeedRegistryException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param c the persistent feed class to delete
     * @throws FeedRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteFeedClass")
    public void deleteFeedClass(@WebParam(name = "c") FeedClass c)
            throws FeedRegistryException {
        if (null == c)
            throw new FeedRegistryException("Null delete !");
        if (0 == c.getId())
            throw new FeedRegistryException("Unreferenced delete !");

        String sql = "DELETE FROM `feed_class`";
        sql += "WHERE `id` = '" + c.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new FeedRegistryException("SQLException: " + e.getMessage());
       }
    }
}
