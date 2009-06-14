/**
 * This file is part of jSeduite::BusScheduleBusiness
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BusScheduleBusiness is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BusScheduleBusiness is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BusScheduleBusiness; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Vincent Bonmalais          [vb.kouno@gmail.com]
 * @author      Main     Yannick Tahora             [ytahora@gmail.com]
 *
 **/

package ca.m1.montreal.jSeduite.technical.busschedule;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * A web service to manipulate periods
 *
 * @author vincent bonmalais
 * @author yannick tahora
 *
 */
@WebService()
public class PeriodCRUD {

    private BusScheduleFinder finder = new BusScheduleFinder();


    /**
     * Format Date to a String (yyyy-MM-dd)
     *
     * @param d     Date which will be processed
     * @return      the formatted date
     */
    public static String toSql(Date d)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(d);
    }

    /**
     * Insert a new period.
     *
     * @param p     period which will be inserted
     * @return      The id of the inserted period.
     */
    @WebMethod(operationName = "createPeriod")
    public int createPeriod(@WebParam(name = "p") Period p)
    {
        if (null == p)
            throw new RuntimeException("Null creation !");
        if (null != finder.findPeriodByDate(p.getBegin(),p.getEnd()))
            throw new RuntimeException("Re-Creation !");

        DataAccessLayer dal = new DataAccessLayer();
        try {
            // Insert Period
            String sql = "INSERT INTO `bus_periods` (`begin`,`end`,`name`) " +
                    "VALUES ('"+toSql(p.getBegin())+"'," +
                    "       '"+toSql(p.getEnd())+"'," +
                    "       '"+p.getName()+"');";

            dal.executeVoid(sql);

            // Retrieve the inserted id
            sql =   "SELECT * FROM `bus_periods` " +
                    "WHERE `id` = LAST_INSERT_ID();";

            DalResultSet drs = dal.extractDataSet(sql);

            Period createdPeriod = new Period(drs);
            return createdPeriod.getId();
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Retrieve a period.
     *
     * @param id    id of the period
     * @return      a period
     */
    @WebMethod(operationName = "readPeriod")
    public Period readPeriod(@WebParam(name = "id") int id)
    {
        Period found = finder.findPeriodByID(id);
        if (null == found)
            throw new RuntimeException("UnexistingIDRead: " + id);
        return found;
    }

    /**
     * Update a period (name, begin date, end date)
     * 
     * @param p     period to update
     */
    @WebMethod(operationName = "updatePeriod")
    public void updatePeriod(@WebParam(name = "p") Period p)
    {
        if(null == p)
            throw new RuntimeException("Null update !");
        if (null == finder.findPeriodByID(p.getId()))
            throw new RuntimeException("Unreferenced update !");

        String sql = "UPDATE `bus_periods` SET `begin` = '"+toSql(p.getBegin())+"', " +
                "`end` = '"+toSql(p.getEnd())+"', " +
                "`name` = '"+p.getName()+"' " +
                "WHERE `name` = '" + p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete a period.
     *
     * @param p     period which will be deleted
     */
    @WebMethod(operationName = "deletePeriod")
    public void deletePeriod(@WebParam(name = "p") Period p)
    {
        if (null == p)
            throw new RuntimeException("Null delete !");
        if (null == finder.findPeriodByID(p.getId()))
            throw new RuntimeException("Unreferenced delete !");

        String sql = "DELETE FROM `bus_periods` WHERE `id` = '" +p.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }
   
}
