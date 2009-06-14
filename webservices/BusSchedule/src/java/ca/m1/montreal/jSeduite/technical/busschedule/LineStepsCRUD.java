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
import java.util.Hashtable;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A web service to manipulate lines
 *
 * @author vincent bonmalais
 * @author yannick tahora
 *
 */


@WebService()
public class LineStepsCRUD {

    private BusScheduleFinder finder = new BusScheduleFinder();

     /** Create CRUD pattern operation
     * @param LineStep id composed of one lineId, and one stopId
     * @return the linestep reference (i.e. its id)
     * @throws BusScheduleException: null object, still persistent
     */
    @WebMethod(operationName = "createLineSteps")
    public int createLineSteps(  @WebParam(name = "lineId") int lineId,
                                 @WebParam(name = "stopId") int stopId)
        throws BusScheduleException {
      if (0 > lineId || 0 > stopId)
          throw new BusScheduleException("Null creation !");
      if (0 > finder.findUniqueLineStep(lineId, stopId))
          throw new BusScheduleException("Re-Creation !");
      DataAccessLayer dal = new DataAccessLayer();
      try{
            String sql = "INSERT INTO `bus_line_steps_lnk` (`line_id`, `stop_id`) VALUES (";
            sql += "'"+lineId+"', '"+stopId+"' );";
            sql += "SELECT * from `bus_line_steps_lnk` WHERE `id` = LAST_INSERT_ID();";

            DalResultSet drs = dal.extractDataSet(sql);
            return Integer.parseInt(drs.getValue("id"));

      } catch (Exception e) {
        throw new BusScheduleException("SQL Exception : " + e.getMessage());
        }
      }

    /**
     * Read CRUD pattern operation
     * @param ref an existing reference (i.e. id) to a persistent lineStep
     *
     * @return ID of line step, line and stop.
     *
     * @throws BusScheduleException: null ref or not binded to persistent object
     */
    @WebMethod(operationName = "readLineStep")
    public Hashtable<String, Integer> readLineStep(@WebParam(name = "id") int id)
       throws BusScheduleException {

        if(0 > id)
           throw new BusScheduleException("Null read!");

        Hashtable<String, Integer> found = finder.findLineStepByID(id);
        if (0 == found.size())
           throw new BusScheduleException("UnexistingRefRead: ");
        return found;
    }

    /** Update CRUD pattern operation
     * @param lineStep the persistent lineStep to update
     * @throws BusScheduleException null object, non persistent object
     */
    @WebMethod(operationName = "updateLineStep")
    public void updateLineStep(     @WebParam(name = "lineId") int lineId,
                                    @WebParam(name = "stopId") int stopId,
                                    @WebParam(name = "id") int id )
            throws BusScheduleException {
        if (0 > lineId || 0 > stopId)
            throw new BusScheduleException("Null update !");
        if (0 > id)
            throw new BusScheduleException("Unreferenced update !");
        String sql = "UPDATE `bus_line_steps_lnk` SET `line_id` = '"+lineId+"', `stop_id` = '"+stopId+"'  ";
        sql += "WHERE `id` = '" +id+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }

    /** Update CRUD pattern operation
     * @param lineStep the persistent lineStep to update
     * @throws BusScheduleException null object, non persistent object
     */
    @WebMethod(operationName = "deleteLineStep")
    public void deleteLineStep(@WebParam(name = "id") int id)
            throws BusScheduleException {
        if (0 > id)
            throw new BusScheduleException("Null delete !");
        String sql = "DELETE FROM `bus_line_steps_lnk` WHERE `id` = '" +id+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }


}
