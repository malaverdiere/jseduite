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
 * A web service to manipulate lines
 *
 * @author vincent bonmalais
 * @author yannick tahora
 * 
 */
@WebService()
public class LineCRUD {

    private BusScheduleFinder finder = new BusScheduleFinder();

    /** Create CRUD pattern operation
     * @param line the trasient line to transform in a persistent one
     * @return the line reference (i.e. its id)
     * @throws BusScheduleException: null object, still persistent
     */
        @WebMethod(operationName = "createLine")
    public int createLine(@WebParam(name = "line") Line line)
        throws BusScheduleException {
      if (null == line)
          throw new BusScheduleException("Null creation !");
      if (null != finder.findLineByName(line.getName()))
          throw new BusScheduleException("Re-Creation !");
      DataAccessLayer dal = new DataAccessLayer();
      try{
            String sql = "INSERT INTO `bus_lines` (`name`) VALUES (";
            sql += "'"+line.getName()+"');";
            dal.executeVoid(sql);
            return line.getId();
      } catch (Exception e) {
        throw new BusScheduleException("SQL Exception : " + e.getMessage());
        }
      }
     
     /** Read CRUD pattern operation 
     * @param ref an existing reference (i.e. id) to a persistent line
     * @return the expected line
     * @throws BusScheduleException: null ref or not binded to persistent object
     */
        @WebMethod(operationName = "readLine")
    public Line readLine(@WebParam(name = "ref") int ref)
            throws BusScheduleException {
       if(0 > ref)
           throw new BusScheduleException("Null read !");
        Line found = finder.findLineByID(ref);
        if (null == found)
           throw new BusScheduleException("UnexistingRefRead: " + ref);
       return found;
    }


    /** Update CRUD pattern operation
     * @param line the persistent line to update
     * @throws BusScheduleException null object, non persistent object
     */
        @WebMethod(operationName = "updateLine")
    public void updateLine(@WebParam(name = "line") Line line)
            throws BusScheduleException {
        if (null == line)
            throw new BusScheduleException("Null update !");
        if (null == finder.findLineByID(line.getId()))
            throw new BusScheduleException("Unreferenced update !");
        String sql = "UPDATE `bus_lines` SET `bus_steps` = '"+line.getBusSteps()+"', `name` = '"+line.getName()+"'";
        sql += "WHERE `id` = '" + line.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }


        /**  Delete CRUD pattern operation
        * @param line the persistent line to delete
        * @throws BusScheduleException null object, non persistent object
        */
        @WebMethod(operationName = "deleteLine")
    public void deleteLine(@WebParam(name = "line") Line line)
            throws BusScheduleException {
        if (null == line)
            throw new BusScheduleException("Null delete !");
        if (null == finder.findLineByID(line.getId()))
            throw new BusScheduleException("Unreferenced delete !");
        String sql = "DELETE FROM `bus_lines` WHERE `id` = '" + line.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }
}
