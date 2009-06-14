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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A web service to manipulate Stops
 *
 * @author vincent bonmalais
 * @author yannick tahora
 * 
 */
@WebService()
public class StopCRUD {

     private BusScheduleFinder finder = new BusScheduleFinder();

    /** Create CRUD pattern operation
     * @param stop the trasient stop to transform in a persistent one
     * @return the stop reference (i.e. its id)
     * @throws BusScheduleException
     *          null object, still persistent
     */
    @WebMethod(operationName = "createStop")
    public int createStop(@WebParam(name = "stop") Stop stop)
        throws BusScheduleException {
      if (null == stop)
          throw new BusScheduleException("Null creation !");
      if (null != finder.findUniqueStop(stop.getName(), stop.getDirection()))
          throw new BusScheduleException("Re-Creation !");
      DataAccessLayer dal = new DataAccessLayer();
      try{
            String sql = "INSERT INTO `bus_stop` (`name`, `direction`) VALUES (";
            sql += "'"+stop.getName()+"','"+stop.getDirection()+"');";
            dal.executeVoid(sql);
            return stop.getId();
      } catch (Exception e) {
        throw new BusScheduleException("SQL Exception : " + e.getMessage());
        }
      }

     /** Read CRUD pattern operation
     * @param ref an existing reference (i.e. id) to a persistent stop
     * @return the expected stop
     * @throws BusScheduleException
      *         null ref or not binded to persistent object
     */
        @WebMethod(operationName = "readStop")
    public Stop readStop(@WebParam(name = "ref") int ref)
            throws BusScheduleException {
       if(0 > ref)
           throw new BusScheduleException("Null read !");
        Stop found = finder.findStopByID(ref);
        if (null == found)
           throw new BusScheduleException("UnexistingRefRead: " + ref);
       return found;
    }


    /** Update CRUD pattern operation
     * @param stop the persistent stop to update
     * @throws BusScheduleException null object, non persistent object
     */
        @WebMethod(operationName = "updateStop")
    public void updateStop(@WebParam(name = "stop") Stop stop)
            throws BusScheduleException {
        if (null == stop)
            throw new BusScheduleException("Null update !");
        if (null == stop.getName())
            throw new BusScheduleException("Unreferenced update !");
        String sql = "UPDATE `bus_stop` SET `bus_direction` = '"+stop.getDirection()+"' ";
        sql += "WHERE `id` = '" + stop.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }


        /**  Delete CRUD pattern operation
        * @param stop the persistent stop to delete
        * @throws BusScheduleException null object, non persistent object
        */
        @WebMethod(operationName = "deleteStop")
    public void deleteStop(@WebParam(name = "stop") Stop stop)
            throws BusScheduleException {
        if (null == stop)
            throw new BusScheduleException("Null delete !");
        if (null == stop.getName())
            throw new BusScheduleException("Unreferenced delete !");
        String sql = "DELETE FROM `bus_stop` WHERE `id` = '" + stop.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }

}
