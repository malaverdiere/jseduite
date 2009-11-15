/**
 * This file is part of jSeduite::InternalNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::InternalNews is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::InternalNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.internal;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class InternalNewsFinder {

    /**
     * FindById operation
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findInternalNewsById")
    public News findInternalNewsById(@WebParam(name = "id") int id)
            throws InternalNewsException {

        String sql = "SELECT * FROM `internal_news`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return new News(rSet);
        } catch(Exception e) {
            throw new InternalNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Internal News business object
     * @return all existing internal news references
     * @throws InternalNewsException
     */
    @WebMethod(operationName="getAllInternalNewsReferences")
    public int[] getAllInternalNewsReferences() throws InternalNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `internal_news`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new InternalNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * FindById operation
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findTargetById")
    public String findTargetById(@WebParam(name = "id") int id)
            throws InternalNewsException {

        String sql = "SELECT * FROM `internal_news_target`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return rSet.getValue("name");
        } catch(Exception e) {
            throw new InternalNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTargetsIds")
    public String[] getTargetsIds() throws InternalNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `internal_news_target`;";
        try {
            return dal.extractScalarSet(sql, "id");
        } catch(Exception e) {
            throw new InternalNewsException(e.getMessage());
        }
    }
}
