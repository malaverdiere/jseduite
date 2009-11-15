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

package fr.unice.i3s.modalis.jSeduite.technical.news.summon;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import fr.unice.i3s.modalis.jSeduite.technical.promos.Promo;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class SummoningFinder {

    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findSummoningById")
    public Summoning findSummoningById(@WebParam(name = "id") int id)
            throws StudentsSummonException {

        String sql = "SELECT * FROM `summonings` ";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            String sql_promo = "SELECT * FROM `promos` ";
            sql_promo += "WHERE `id` = '" + rSet.getValue("promo") +"';";

            DalResultSet rSet_promo = dal.extractDataSet(sql_promo);

            return new Summoning(rSet, new Promo(rSet_promo));
        } catch(Exception e) {
            throw new StudentsSummonException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Extract all persistent references for the Summoning business object
     * @return all existing summonings references
     * @throws StudentsSummonException
     */
    @WebMethod(operationName="getAllSummoningReferences")
    public int[] getAllSummoningReferences() throws StudentsSummonException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `id` FROM `summonings`;";
            String[] results = dal.extractScalarSet(sql, "id");
            int[] ids = new int[results.length];

            for(int i=0; i<results.length; i++) {
                ids[i] = Integer.parseInt(results[i]);
            }

            return ids;
        } catch (Exception e) {
            throw new StudentsSummonException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * FindById operation
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findLevelById")
    public String findLevelById(@WebParam(name = "id") int id)
            throws StudentsSummonException {

        String sql = "SELECT * FROM `summon_levels`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            return rSet.getValue("name");
        } catch(Exception e) {
            throw new StudentsSummonException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLevelIds")
    public int[] getLevelIds() throws StudentsSummonException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `summon_levels`;";
        try {
            String[] sSet = dal.extractScalarSet(sql, "id");
            int[] result = new int[sSet.length];
            int i =0;

            for(String id : sSet) {
                result[i] = Integer.parseInt(id);
                i++;
            }
            return result;
        } catch(Exception e) {
            throw new StudentsSummonException(e.getMessage());
        }
    }
}
