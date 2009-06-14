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
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A web service to manipulate links between Schedules and Periods.
 *
 * @author vincent bonmalais
 * @author yannick tahora
 *
 */
@WebService()
public class PeriodLinkCRUD {

    private BusScheduleFinder finder = new BusScheduleFinder();

    /**
     * Create CRUD pattern operation
     *
     * @param periodLink    link between period and schedule
     *
     * @return id of the inserted PeriodLink
     */
    @WebMethod(operationName = "createPeriodLink")
    public int createPeriodLink(@WebParam(name = "periodLink")
    PeriodLink periodLink) {
        if (null == periodLink)
            throw new RuntimeException("Null creation !");
        if (null != finder.findUniquePeriodLink(periodLink))
            throw new RuntimeException("Re-Creation !");

        DataAccessLayer dal = new DataAccessLayer();
        try {
            // Insert Period Link
            String sql = "INSERT INTO `bus_period_lnk` (`period_id`,`schedule_id`,`day`) " +
                    "VALUES ('"+ periodLink.getPeriod() +"'," +
                    "       '"+ periodLink.getSchedule() +"'," +
                    "       '"+ periodLink.getDay() +"');";

            dal.executeVoid(sql);

            // Retrieve the inserted id
            sql =   "SELECT * FROM `bus_period_lnk` " +
                    "WHERE `id` = LAST_INSERT_ID();";

            DalResultSet drs = dal.extractDataSet(sql);

            PeriodLink createdPeriodLnk = new PeriodLink(drs);
            return createdPeriodLnk.getId();
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Retrieve a period link.
     *
     * @param id    id of the period link
     * @return      a period link
     */
    @WebMethod(operationName = "readPeriodLink")
    public PeriodLink readPeriodLink(@WebParam(name = "id")
    int id) {
        
        PeriodLink found = finder.findPeriodLinkByID(id);
        if (null == found)
            throw new RuntimeException("UnexistingIDRead: " + id);
        return found;
    }

    /**
     * Update a period link (period, schedule, day)
     *
     * @param periodLink     period to update
     */
    @WebMethod(operationName = "updatePeriodLink")
    @Oneway
    public void updatePeriodLink(@WebParam(name = "periodLink")
    PeriodLink periodLink) {

        if(null == periodLink)
            throw new RuntimeException("Null update !");
        if (null == finder.findPeriodByID(periodLink.getId()))
            throw new RuntimeException("Unreferenced update !");

        String sql = "UPDATE `bus_period_lnk` SET `period_id` = '"+ periodLink.getPeriod()+"', " +
                "`schedule_id` = '"+ periodLink.getSchedule() +"', " +
                "`day` = '"+ periodLink.getDay()+"' " +
                "WHERE `id` = '" + periodLink.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }

    /**
     * Delete a period link.
     *
     * @param pl     period link which will be deleted
     */
    @WebMethod(operationName = "deletePeriodLink")
    @Oneway
    public void deletePeriodLink(@WebParam(name = "periodLink")
    PeriodLink pl) {
        if (null == pl)
            throw new RuntimeException("Null delete !");
        if (null == finder.findPeriodByID(pl.getId()))
            throw new RuntimeException("Unreferenced delete !");

        String sql = "DELETE FROM `bus_period_lnk` WHERE `id` = '" +pl.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new RuntimeException("SQLException: " + e.getMessage());
       }
    }
}
