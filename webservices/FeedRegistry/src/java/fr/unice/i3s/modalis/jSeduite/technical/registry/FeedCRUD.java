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
 * Foundation, Inf., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
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
public class FeedCRUD {
    /// A finder, to explore the persistences layer in an OO way
    private FeedFinder finder = new FeedFinder();

    /**
     * Create CRUD pattern operation
     * @param f the transient feed to transform in a persistent one
     * @throws FeedRegistryException: null object, still persistent
     */
    @WebMethod(operationName = "createFeed")
    public void createFeed(@WebParam(name = "f") Feed f)
            throws FeedRegistryException {
        if (null == f)
            throw new FeedRegistryException("Null creation !");

        DataAccessLayer dal = new DataAccessLayer();

        String sql = "INSERT INTO `feed_registry` ";
        sql += "(`provider_dns`, `class_id`, `nickname`, `feed_url`) VALUES (";
        sql += "'"+f.getProviderDNS()+"', '"+f.getFeedClass().getId()+"', '";
        sql += f.getNickname() + "', '" + f.getFeedURL() + "'); \n";

        String sql_cache = "INSERT INTO `cache` VALUES ('";
        sql_cache += f.getNickname() + "',CURRENT_TIMESTAMP(), '');";

        try {
            dal.executeVoid(sql);
            dal.executeVoid(sql_cache);
        } catch (Exception e) {
            throw new FeedRegistryException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Read CRUD pattern operation
     * @param providerDNS the provider dns
     * @param feedURL the feed url
     * @return the expected feed
     * @throws FeedRegistryException: null id or not binded to persistent object
     */
    @WebMethod(operationName = "readFeed")
    public Feed readFeed(@WebParam(name = "provider_dns") String providerDNS,
                         @WebParam(name = "feed_url") String feedURL)
                         throws FeedRegistryException {
        Feed found = finder.findFeedById(providerDNS, feedURL);
        if (null == found) {
            throw new FeedRegistryException("UnexistingRefRead: " + providerDNS + " " + feedURL);
        }
        return found;
    }

    /**
     * Update CRUD pattern operation
     * @param f the persistent feed to update
     * @throws FeedRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "updateFeed")
    public void updateFeed(@WebParam(name = "f") Feed f)
            throws FeedRegistryException {
        if (null == f) {
            throw new FeedRegistryException("Null update !");
        }
        if (f.getProviderDNS().equals(null) || f.getFeedURL().equals(null)) {
            throw new FeedRegistryException("Unreferenced update !");
        }

        String sql = "UPDATE `feed_registry`";
        sql += "SET `class_id` = '"+f.getFeedClass().getId()+"', ";
        sql += "`nickname` = '"+f.getNickname()+"' ";
        sql += "WHERE `provider_dns` = '" + f.getProviderDNS()+"' ";
        sql += "AND `feed_url` = '"+ f.getFeedURL() +"';";

        String class_cleaner = "DELETE FROM `feed_class` ";
        class_cleaner += "WHERE `id` NOT IN ";
        class_cleaner += "(SELECT `class_id` FROM `feed_registry`);";


        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(class_cleaner);
        } catch(Exception e) {
           throw new FeedRegistryException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete CRUD pattern operation
     * @param f the persistent feed to delete
     * @throws FeedRegistryException: null object, non persistent object
     */
    @WebMethod(operationName = "deleteFeed")
    public void deleteFeed(@WebParam(name = "f") Feed f)
            throws FeedRegistryException {
        if (null == f)
            throw new FeedRegistryException("Null delete !");
        if (f.getProviderDNS().equals(null) || f.getFeedURL().equals(null))
            throw new FeedRegistryException("Unreferenced delete !");

        String sql = "DELETE FROM `feed_registry` ";
        sql += "WHERE `provider_dns` = '" + f.getProviderDNS()+"' ";
        sql += "AND `feed_url` = '"+ f.getFeedURL() +"';";

        String class_cleaner = "DELETE FROM `feed_class` ";
        class_cleaner += "WHERE `id` NOT IN ";
        class_cleaner += "(SELECT `class_id` FROM `feed_registry`);";

        String cache_cleaner = "DELETE FROM `cache` ";
        cache_cleaner += "WHERE `key` = '"+f.getNickname()+"';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
            dal.executeVoid(class_cleaner);
            dal.executeVoid(cache_cleaner);
        } catch(Exception e) {
           throw new FeedRegistryException("SQLException: " + e.getMessage());
       }
    }
}
