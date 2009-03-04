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
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.net.URL;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** A web service to store Syndication Feed adresses using nicknames
 * @author mosser
 */
@WebService()
public class FeedRegistry {

    /** Retrieve an URL for a feed base on its nickname
     * @param name the nickname of the feed
     * @return the URL use to retrieve this feed
     * @throws FeedRegistryException
     */
    @WebMethod(operationName = "getURL")
    public URL getURL(@WebParam(name = "name") String name)
            throws FeedRegistryException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT CONCAT('http://',provider_dns,feed_url) as url ";
        sql += "FROM  feed_registry WHERE nickname = '" + name + "';";
        try {
            return new URL(dal.extractScalar(sql,"url"));
        } catch (Exception e) {
            throw new FeedRegistryException(e.getMessage());
        }
    }


    /** Retrieve available feeds categories
     * @return all categor
     */
    @WebMethod(operationName = "getCategories")
    public String[] getCategories()
            throws FeedRegistryException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT class FROM feed_class";
        try {
            return dal.extractScalarSet(sql,"class");
        } catch (Exception e) {
            throw new FeedRegistryException(e.getMessage());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getNicknames")
    public String[] getNicknames(@WebParam(name = "category") String category)
            throws FeedRegistryException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT nickname FROM feed_class, feed_registry";
        sql += " WHERE class_id = id AND class = '"+category+"';";
        try {
            return dal.extractScalarSet(sql,"nickname");
        } catch (Exception e) {
            throw new FeedRegistryException(e.getMessage());
        }
    }

}
