/**
 * This file is part of jSeduite::BusSchedule
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BusSchedule is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BusSchedule is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BusSchedule; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Vincent Bonmalais          [vb.kouno@gmail.com]
 * @author      Main     Yannick Tahora             [ytahora@gmail.com]
 *
 **/

package ca.m1.montreal.jSeduite.technical.busschedule;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A Web Service to handle Bus Schedule
 *
 * @author vincent bonmalais
 * @author yannick tahora
 */
@WebService()
public class BusSchedule {

    /**
     * Get all bus lines in database
     *
     * @return      All lines available in database
     */
    @WebMethod(operationName = "getAllLines")
    public Line[] getAllLines()
        throws BusScheduleException {

        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `bus_lines`";

        try{
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                throw new BusScheduleException("No records in database. (bus_lines empty) ");


            Line[] lines = new Line[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                lines[i] = new Line(drs);
                drs.next();
            }

            return lines;
        }catch(Exception e){
            throw new BusScheduleException(e.getMessage());
        }
    }

    /**
     * <p>Get all Schedule associated to a stop.</p>
     * To specify a direction, use : <code>getSchedules</code>
     *
     * @param idStop    id of the stop for which you need to retrieve schedules
     * @return          The schedules associated to the stop.
     *
     * @exception BusScheduleException
     *            If the idStop doesn't exist.
     *
     */
    @WebMethod(operationName = "getSchedulesForStop")
    public Schedule[] getSchedulesForStop(@WebParam(name = "stop") int idStop)
        throws BusScheduleException {

        //TODO Write this operation
        return null;
    }

    /**
     * <p>Get all Schedule for a specified Stop and Time.</p>
     * To specify a direction, use : <code>getSchedulesAtTime</code>
     *
     * @param idStop      id of the stop for which you need to retrieve schedules
     *
     */
    @WebMethod(operationName = "getSchedulesForStopAtTime")
    public Schedule[] getSchedulesForStopAtTime(@WebParam(name = "stop") int idStop,
            @WebParam(name = "line") int idLine,
            @WebParam(name = "time") Date time)
            throws BusScheduleException
    {
        DataAccessLayer dal = new DataAccessLayer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String frmtTime = sdf.format(time);


        String sql =
                "SELECT `bus_schedules`.*" +
                "FROM `bus_schedules`" +
                "INNER JOIN `bus_period_lnk`" +
                "ON `bus_period_lnk`.`schedule_id` = `bus_schedules`.`id`" +
                "WHERE `bus_period_lnk`.`day` = DATE_FORMAT('"+frmtTime+"', '%W')" +
                "AND `bus_period_lnk`.`period_id` =" +
                "   (SELECT `bus_periods`.`id` " +
                "   FROM `bus_periods` " +
                "   WHERE `bus_periods`.`end` >= '"+frmtTime+"'" +
                "   AND `bus_periods`.`begin` <= '"+frmtTime+"')" +
                "AND `bus_schedules`.`line_steps_id` =" +
                "   (SELECT `bus_line_steps_lnk`.`id`" +
                "   FROM `bus_line_steps_lnk`" +
                "   WHERE `bus_line_steps_lnk`.`line_id` = '"+String.valueOf(idLine)+"'" +
                "   AND `bus_line_steps_lnk`.`stop_id` = '"+String.valueOf(idStop)+"'" +
                "   )" +
                "AND `bus_schedules`.`horary` > DATE_FORMAT('"+frmtTime+"', '%H:%i:%S') " +
                "LIMIT 0,5";


        try{
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                throw new BusScheduleException("No schedules available.");


            Schedule[] schedules = new Schedule[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                schedules[i] = new Schedule(drs);
                drs.next();
            }

            return schedules;
        }catch(Exception e){
            throw new BusScheduleException(e.getMessage());
        }


    }

    /**
     * Get all stop for a specified Line
     *
     * @param idLine    Line for which you need to retrieve stops
     *
     *
     */
    @WebMethod(operationName = "getAllStopsForLine")
    public Stop[] getStopsForLine(@WebParam(name = "line") int idLine) 
            throws BusScheduleException {

        DataAccessLayer dal = new DataAccessLayer();
        String sql =
                "SELECT `bus_stops`.*" +
                "FROM `bus_stops`" +
                "INNER JOIN `bus_line_steps_lnk`" +
                "ON `bus_line_steps_lnk`.`stop_id` = `bus_stops`.`id`" +
                "WHERE `bus_line_steps_lnk`.`line_id` = '" + Integer.toString(idLine) + "'";

        try{
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                throw new BusScheduleException("No Stop in bus_stops" +
                        " for line id : "+Integer.toString(idLine));


            Stop[] stops = new Stop[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                stops[i] = new Stop(drs);
                drs.next();
            }

            return stops;

        }catch (Exception e){
            throw new BusScheduleException(e.getMessage());
        }
    }
    

    /**
     * Web service operation
     *
     */
    @WebMethod(operationName = "getDirectionForLine")
    public String[] getDirectionForLine(@WebParam(name = "idLine") int idLine)
            throws BusScheduleException {

        DataAccessLayer dal = new DataAccessLayer();
        String sql = 
                "SELECT DISTINCT `bus_stops`.`direction`" +
                "FROM `bus_stops`" +
                "INNER JOIN `bus_line_steps_lnk`" +
                "ON `bus_line_steps_lnk`.`stop_id` = `bus_stops`.`id`" +
                "WHERE `bus_line_steps_lnk`.`line_id` = '" + Integer.toString(idLine) + "';";

        try{
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                throw new BusScheduleException("No Stop in" +
                        " bus_stops for line id : "+Integer.toString(idLine));


            String[] directions = new String[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                directions[i] = drs.getValue("direction");
                drs.next();
            }

            return directions;

        }catch (Exception e){
            throw new BusScheduleException(e.getMessage());
        }
    }

}
