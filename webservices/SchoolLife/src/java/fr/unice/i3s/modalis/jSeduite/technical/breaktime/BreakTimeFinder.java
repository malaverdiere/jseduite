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
 * @author      Steve Colombié         [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import fr.unice.i3s.modalis.jSeduite.technical.promos.Promo;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class BreakTimeFinder {
    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findBreakTimeById")
    public BreakTime findBreakTimeById(@WebParam(name = "id") int id)
            throws BreakTimeException {

        String sql = "SELECT * FROM `break_time`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            /* Promotions extraction */
            String sql_promos = "SELECT * FROM `break_time_promotions`, `promos`";
            sql_promos += "WHERE `break_time_promotions`.`break_id` = '" + id + "' ";
            sql_promos += "AND `break_time_promotions`.`promo_id` = `promos`.`id`;";
            DalResultSet rSet_promos = dal.extractDataSet(sql_promos);
            Promo[] promos = new Promo[rSet_promos.size()];
            int i = 0;

            while(i<rSet_promos.size()) {
                promos[i] = new Promo(rSet_promos);
                rSet_promos.next();
                i++;
            }

            /* Days extraction */
            String sql_days = "SELECT * FROM `break_time_days`";
            sql_days += "WHERE `break_id` = '" + id + "';";
            DalResultSet rSet_days = dal.extractDataSet(sql_days);
            String[] days = new String[rSet_days.size()];
            i = 0;

            while(i<rSet_days.size()) {
                days[i] = rSet_days.getValue("day");
                rSet_days.next();
                i++;
            }

            return new BreakTime(rSet, promos, days);

        } catch(Exception e) {
            throw new BreakTimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Break Time business object
     * @return all existing internal news references
     * @throws BreakTimeException
     */
    @WebMethod(operationName="getAllBreakTimeReferences")
    public int[] getAllBreakTimeReferences() throws BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `break_time`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new BreakTimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get all the buildings
     */
    @WebMethod(operationName="getAllBuildings")
    public String[] getAllBuildings() throws BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT DISTINCT `building` FROM `break_time`;";
            String[] results = dal.extractScalarSet(sql, "building");

            return results;
        } catch (Exception e) {
            throw new BreakTimeException("SQL Exception: " + e.getMessage());
        }
    }
}
