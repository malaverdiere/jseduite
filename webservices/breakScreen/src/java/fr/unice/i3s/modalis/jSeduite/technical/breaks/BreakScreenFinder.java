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
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Christophe Desclaux         [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaks;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class BreakScreenFinder {
    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findBreakScreenById")
    public BreakScreen findBreakScreenById(@WebParam(name = "id") int id)
            throws BreakScreenException {

        String sql = "SELECT * FROM `break_screen`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            /* Days extraction */
            String sql_days = "SELECT * FROM `break_screen_days`";
            sql_days += "WHERE `break_id` = '" + id + "';";
            DalResultSet rSet_days = dal.extractDataSet(sql_days);
            String[] days = new String[rSet_days.size()];
            int i = 0;

            while(i<rSet_days.size()) {
                days[i] = rSet_days.getValue("day");
                rSet_days.next();
                i++;
            }

            return new BreakScreen(rSet, days);

        } catch(Exception e) {
            throw new BreakScreenException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Break Screen business object
     * @return all existing internal news references
     * @throws BreakScreenException
     */
    @WebMethod(operationName="getAllBreakScreenReferences")
    public int[] getAllBreakScreenReferences() throws BreakScreenException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `break_screen`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new BreakScreenException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get all the buildings
     */
    @WebMethod(operationName="getAllBuildings")
    public String[] getAllBuildings() throws BreakScreenException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT DISTINCT `building` FROM `break_screen`;";
            String[] results = dal.extractScalarSet(sql, "building");

            return results;
        } catch (Exception e) {
            throw new BreakScreenException("SQL Exception: " + e.getMessage());
        }
    }
}
