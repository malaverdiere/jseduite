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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class FeedFinder {
    @WebMethod(operationName = "findFeedById")
    public Feed findFeedById(@WebParam(name = "provider_dns") String providerDNS,
                             @WebParam(name = "feed_url") String feedURL)
            throws FeedRegistryException {

        String sql = "SELECT * FROM `feed_registry` AS `r`, `feed_class` as `c`";
        sql += "WHERE `r`.`provider_dns` = '" + providerDNS + "' ";
        sql += "AND `r`.`feed_url` = '" + feedURL + "' ";
        sql += "AND `c`.`id` = `r`.`class_id`;";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return new Feed(rSet);
        } catch(Exception e) {
            throw new FeedRegistryException("SQL Exception: " + e.getMessage());
        }
    }

    @WebMethod(operationName="getAllFeeds")
    public Feed[] getAllFeeds() throws FeedRegistryException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `feed_registry` AS `r`, `feed_class` as `c`";
            sql += "WHERE `c`.`id` = `r`.`class_id`;";

            ArrayList<Feed> result = new ArrayList<Feed>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++) {
                result.add(new Feed(rset));
                rset.next();
            }

            return result.toArray(new Feed[result.size()]);
        } catch (Exception e) {
            throw new FeedRegistryException("SQL Exception: " + e.getMessage());
        }
    }

}
