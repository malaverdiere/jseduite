/**
 * This file is part of jSeduite::SchoolLife
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::SchoolLife is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.plannings;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class PlanningFinder {
    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findPlanningById")
    public Planning findPlanningById(@WebParam(name = "id") int id)
            throws PlanningException {

        String sql = "SELECT * FROM `promo_planning` ";
        sql += "WHERE `id` = '" + id + "';";

        String sql_groups = "SELECT * FROM `promo_groups` ";
        sql_groups += "WHERE `promo_id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);
            DalResultSet rSet_groups = dal.extractDataSet(sql_groups);

            if (rSet.size() == 0) {
                return null;
            }

            return new Planning(rSet, rSet_groups);
        } catch(Exception e) {
            throw new PlanningException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Summoning business object
     * @return all existing summonings references
     * @throws StudentsSummonException
     */
    @WebMethod(operationName="getAllPlanningReferences")
    public int[] getAllPlanningReferences() throws PlanningException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `promo_planning`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new PlanningException("SQL Exception: " + e.getMessage());
        }
    }
}
