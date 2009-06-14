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
 * A web service to manipulate schedules
 *
 * @author vincent bonmalais
 * @author yannick tahora
 *
 */
@WebService()
public class ScheduleCRUD {

    private BusScheduleFinder finder = new BusScheduleFinder();


    /**
    * Format Date to a String (yyyy-MM-dd)
    *
    * @param d     Date which will be processed
    * @return      the formatted date
    */
   public static String toSql(Date d)
   {
       SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
       return format.format(d);
   }


    /** Create CRUD pattern operation
     * @param horary will only take the hour of the date.
     * @return the schedule reference (i.e. its id)
     * @throws BusScheduleException: null object, still persistent
     */
    @WebMethod(operationName = "createSchedule")
    public int createSchedule(   @WebParam(name = "horary") Date horary,
                                 @WebParam (name = "line_step_id") int lineStepId)
        throws BusScheduleException {
      if (null == horary)
          throw new BusScheduleException("Null creation !");
      if (null != finder.findUniqueSchedule(lineStepId, horary))
              throw new BusScheduleException("Re-Creation !");
      DataAccessLayer dal = new DataAccessLayer();
      try{
            String sql = "INSERT INTO `bus_schedules` (`line_step_id`, `horary`) VALUES (";
                   sql += "'"+lineStepId+"','"+toSql(horary)+"');";
                   sql += "SELECT * from `bus_schedules` WHERE `id` = LAST_INSERT_ID();";
                   
                   DalResultSet drs = dal.extractDataSet(sql);
                   
                   Schedule createdSchedule = new Schedule(drs);
                   
                   return createdSchedule.getId();
                   
      } catch (Exception e) {
        throw new BusScheduleException("SQL Exception : " + e.getMessage());
        }
      }

     /** Read CRUD pattern operation
     * @param ref an existing reference (i.e. id) to a persistent schedule
     * @return the expected schedule
     * @throws BusScheduleException: null ref or not binded to persistent object
     */
        @WebMethod(operationName = "readSchedule")
    public Schedule readSchedule(@WebParam(name = "ref") int ref)
            throws BusScheduleException {
       if(0 > ref)
           throw new BusScheduleException("Null read !");
        Schedule found = finder.findScheduleByID(ref);
        if (null == found)
           throw new BusScheduleException("UnexistingRefRead: " + ref);
       return found;
    }


    /** Update CRUD pattern operation
     * @param schedule the persistent schedule to update
     * @throws BusScheduleException null object, non persistent object
     */
        @WebMethod(operationName = "updateSchedule")
    public void updateSchedule(@WebParam(name = "schedule") Schedule schedule)
            throws BusScheduleException {
        if (null == schedule)
            throw new BusScheduleException("Null update !");
        if (null == finder.findScheduleByID(schedule.getId()))
            throw new BusScheduleException("Unreferenced update !");
        String sql = "UPDATE `bus_schedules` SET `line_steps_id` = '"+schedule.getIdLineSteps()+"', `horary` = '"+schedule.getHorary()+"' ";
        sql += "WHERE `id` = '" + schedule.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }


        /**  Delete CRUD pattern operation
        * @param schedule the persistent schedule to delete
        * @throws BusScheduleException null object, non persistent object
        */
        @WebMethod(operationName = "deleteSchedule")
    public void deleteSchedule(@WebParam(name = "schedule") Schedule schedule)
            throws BusScheduleException {
        if (null == schedule)
            throw new BusScheduleException("Null delete !");
        if (null == finder.findScheduleByID(schedule.getId()))
            throw new BusScheduleException("Unreferenced delete !");
        String sql = "DELETE FROM `bus_schedules` WHERE `id` = '" + schedule.getId()+"';";
        DataAccessLayer dal = new DataAccessLayer();
        try {
            dal.executeVoid(sql);
        } catch(Exception e) {
           throw new BusScheduleException("SQLException: " + e.getMessage());
       }
    }


}
