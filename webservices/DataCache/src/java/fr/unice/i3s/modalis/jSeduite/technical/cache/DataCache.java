/**
 * This file is part of jSeduite::DataCache
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::DataCache is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::DataCache is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:DataCache; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
 *
 **/
package fr.unice.i3s.modalis.jSeduite.technical.cache;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** A Very Simple Cache service.
 * Defines an entry as a "content" binded to a "key", and use a timestamp to
 * perform temporal validity check.
 * @author mosser
 */
@WebService()
public class DataCache {

    /** Initialize the content of an entry
     * @param key the key entry you want to initialize
     * @remarks this operation MUST be called before using the others on 'key'
     */
    @WebMethod(operationName = "initContent")
    //@Oneway
    public void initContent(@WebParam(name = "key") String key)
            throws DataCacheException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "INSERT INTO `cache` VALUES ('" + key + "',";
        sql += " CURRENT_TIMESTAMP(), \"\");";
        try {
            dal.executeVoid(sql);
        } catch (Exception e) {
            throw new DataCacheException(e.getMessage());
        }
    }

    /** Retrieve the content of an entry
     * @param key the key use to register the content.
     * @return the String content binded to key
     * @throws DataCacheException if 'initContent' wasn't previously called
     */
    @WebMethod(operationName = "getContent")
    public String getContent(@WebParam(name = "key") String key)
            throws DataCacheException {
        String sql = "SELECT `value` FROM `cache` ";
        sql += " WHERE `key` = '" + key + "';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            return dal.extractScalar(sql, "value").replaceAll("\\\\\"", "\\\"");
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataCacheException(e.getMessage());
        }
    }

    /** Perform a temporal validity check for an entry
     * @param key the key to be tested by the operation
     * @param delta validity interval (in minutes)
     * @return true is the entry was stamped in time, false elsewhere
     * @throws DataCacheException if 'initContent' wasn't previously called
     */
    @WebMethod(operationName = "isValid")
    public boolean isValid(@WebParam(name = "key") String key,
            @WebParam(name = "delta") int delta) throws DataCacheException {
        String sql = "SELECT TIME_TO_SEC(TIMEDIFF(DATE_ADD(`stamp`,";
        sql += "INTERVAL " + delta + " MINUTE),CURRENT_TIMESTAMP())) > 0 ";
        sql += "AND `value` != '' AS valid ";
        sql += "FROM `cache` WHERE `key` = '" + key + "';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String ans = dal.extractScalar(sql, "valid");
            return (ans.equals("0") ? false : true);
        } catch (Exception e) {
            this.initContent(key);
            return false;
            //throw new DataCacheException(e.getMessage());
        }
    }

    /** Update the content of the cache for 'key'
     * @param key  the entry key
     * @param content the new value
     * @throws DataCacheException if 'initContent' wasn't previously called
     */
    @WebMethod(operationName = "setContent")
    public void setContent(@WebParam(name = "key") String key,
            @WebParam(name = "content") String content)
            throws DataCacheException {
        content = content.replaceAll("\"", "\\\\\"");
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "UPDATE `cache` SET ";
        sql += "`value` = \"" + content + "\"";
        sql += "WHERE `key` = '" + key + "';";
        try {
            dal.executeVoid(sql);
        } catch (Exception e) {
            throw new DataCacheException(e.getMessage());
        }
    }
}
