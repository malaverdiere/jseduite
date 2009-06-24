/**
 * This file is part of jSeduite::BreakingNews
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakingNews is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakingNews is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BreakingNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.news.breaking;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Finder implementation for the BreakNew business object
 */
@WebService()
public class BreakingNewsFinder {

    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findBreakNewById")
    public BreakNew findBreakNewById(@WebParam(name = "id") int id)
            throws BreakingNewsException {

        String sql = "SELECT * FROM `breaking_news`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }
            
            return new BreakNew(rSet);
        } catch(Exception e) {
            throw new BreakingNewsException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Break New business object
     * @return all existing breaking news references
     * @throws BreakingNewsException
     */
    @WebMethod(operationName="getAllBreakingNewsReferences")
    public int[] getAllBreakingNewsReferences() throws BreakingNewsException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `breaking_news`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new BreakingNewsException("SQL Exception: " + e.getMessage());
        }
    }

}
