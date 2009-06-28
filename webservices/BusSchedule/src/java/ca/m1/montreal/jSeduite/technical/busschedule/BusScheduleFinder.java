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
 * Object finder for BusSchedule.
 *
 * @author vincent bonmalais
 * @author yannick tahora
 * @see BusScheduleFinderHolder
 */
@WebService()
public class BusScheduleFinder {

    /**
     * Default constructor
     */
    public BusScheduleFinder() {}


    /**
     * Get line associated to id.
     *
     * @param id        id of the line
     * @return          unique line associated to id
     */
    @WebMethod(operationName = "fineLineByID")
    public Line findLineByID(@WebParam(name = "id") int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_lines` WHERE `id`= '"+id+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Line(drs);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get lines containing <code>name</code> whithin their names.
     *
     * @param name      name of the line
     * @return          All lines containing <code>name</code>.
     */
    @WebMethod(operationName="findLineByName")
    public Line[] findLineByName(@WebParam(name="name") String name) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_lines` WHERE `name`= '%"+name+"%';";
            DalResultSet drs = dal.extractDataSet(sql);

            Line[] lines = new Line[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                lines[i] = new Line(drs);
                drs.next();
            }

            return lines;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get lines associated to a Bus Step (stop id).
     *
     * @param busStep   id of the stop associated to the line
     * @return          All lines with <code>busStep</code>
     */
    @WebMethod(operationName="findLineByBusStep")
    public Line[] findLineByBusStep(@WebParam(name="busStep") String busStep) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_lines` WHERE `bus_steps`= '"+busStep+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Line[] lines = new Line[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                lines[i] = new Line(drs);
                drs.next();
            }

            return lines;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get stop associated to id.
     *
     * @param id        id of the line
     * @return          unique line associated to id
     */
    @WebMethod(operationName="findStopByID")
    public Stop findStopByID(@WebParam(name="id") int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_stops` WHERE `id`= '"+id+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Stop(drs);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get stops associated to a <code>direction</code>
     *
     * @param direction     stop name (terminus)
     * @return              All stops switch the direction.
     */
    @WebMethod(operationName="findStopByDirection")
    public Stop[] findStopByDirection(@WebParam(name = "direction") String direction) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_stops` WHERE `direction`= '"+direction+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Stop[] stops = new Stop[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                stops[i] = new Stop(drs);
                drs.next();
            }

            return stops;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get stops containing <code>name</code> in their names.
     *
     * @param name     stop name (terminus)
     * @return         All stops containing <code>name</code>.
     *
     */
    @WebMethod(operationName="findStopByName")
    public Stop[] findStopByName(@WebParam(name = "name") String name) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_stops` WHERE `name`= '"+name+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Stop[] stops = new Stop[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                stops[i] = new Stop(drs);
                drs.next();
            }

            return stops;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get unique stop for the specified parameters.
     *
     * @param direction     direction for the line
     * @param name          name of the stop
     *
     * @return  The Stop object with his id, name and direction.
     *
     */
    @WebMethod(operationName="findUniqueStop")
    public Stop findUniqueStop(@WebParam(name = "name") String name,
            @WebParam(name = "direction") String direction) {
        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM bus_stops" +
                    "WHERE `name` = '"+ name +"' " +
                    "AND `direction` = '"+ direction +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Stop(drs);
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get period associated to id.
     *
     * @param id        id of the period
     * @return          unique period associated to id
     */
    @WebMethod(operationName="findPeriodByID")
    public Period findPeriodByID(@WebParam(name = "id") int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_period` WHERE `id`= '"+id+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Period(drs);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get periods containing <code>name</code> in their names.
     *
     * @param name      name of the period
     * @return          All periods containing <code>name</code>.
     */
    @WebMethod(operationName="findPeriodByName")
    public Period[] findPeriodByName(@WebParam(name = "name") String name) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_periods` WHERE `name`= '"+name+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Period[] periods = new Period[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                periods[i] = new Period(drs);
                drs.next();
            }

            return periods;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get Period by date. The finder will return every periods within
     * the specified dates (<code>beginDate</code> and <code>endDate</code>)
     *
     * @param beginDate     beginning date of the period
     * @param endDate       ending date of the period
     * @return              All periods with begin date equals to
     *                      <code>beginDate</code>
     *                      and with end date equals to
     *                      <code>endDate</code>
     */
    @WebMethod(operationName="findPeriodByDate")
    public Period[] findPeriodByDate(@WebParam(name="beginDate") Date beginDate,
            @WebParam(name = "endDate") Date endDate) {
        DataAccessLayer dal = new DataAccessLayer();

        // Format beginDate to yyyy-MM-dd
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatedBeginDate = format.format(beginDate);
        String formatedEndDate = format.format(beginDate);

        try {
            String sql = "SELECT * FROM `bus_periods` " +
                    "WHERE `begin` = '"+formatedBeginDate+"' " +
                    "AND `end` = '"+formatedEndDate+"';";

            DalResultSet drs = dal.extractDataSet(sql);

            Period[] periods = new Period[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                periods[i] = new Period(drs);
                drs.next();
            }

            return periods;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get schedule associated to id.
     *
     * @param id        id of the schedule
     * @return          unique schedule associated to id
     */
    @WebMethod(operationName="findScheduleByID")
    public Schedule findScheduleByID(@WebParam(name = "id") int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_schedules` WHERE `id`= '"+id+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Schedule(drs);
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /** 
     * Get all schedules by horary.
     *
     * @param horary        date associated to a schedule.
     *                      Only the "HH:mm:yy" part is important,
     *                      the rest of the date will not be processed.
     * @return              Schedules with the specified <code>horary</code>
     */
    @WebMethod(operationName="findScheduleByHorary")
    public Schedule[] findScheduleByHorary(@WebParam(name = "horary") Date horary) {
        DataAccessLayer dal = new DataAccessLayer();

        // Format horary to H:m:s
        SimpleDateFormat format = new SimpleDateFormat("H:m:s");
        String formatedDate = format.format(horary);

        try {
            String sql = "SELECT * FROM `bus_schedules` WHERE `horary`= '"+formatedDate+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Schedule[] schedules = new Schedule[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                schedules[i] = new Schedule(drs);
                drs.next();
            }

            return schedules;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get schedules with line step (association of a stop and line)
     * equals to <code>lineStep</code>
     *
     * @param lineStep  id of a stop
     * @return
     */
    @WebMethod(operationName="findScheduleByLineStep")
    public Schedule[] findScheduleByLineStep(@WebParam(name = "lineStep") String lineStep) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_schedules` WHERE `line_steps_id`= '"+lineStep+"';";
            DalResultSet drs = dal.extractDataSet(sql);

            Schedule[] schedules = new Schedule[drs.size()];

            for (int i = 0; i < drs.size(); i++) {
                schedules[i] = new Schedule(drs);
                drs.next();
            }

            return schedules;
        } catch (Exception e) {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get a unique line step id for the specified parameters.
     *
     * @param id      id of a line step
     * @return  A line Step 
     */
    @WebMethod(operationName="findLineStepByID")
    public LineSteps findLineStepByID (
            @WebParam(name = "id") int id) {
        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM `bus_line_steps_lnk` " +
                    "WHERE `id` = '"+ id +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;

            LineSteps ls = new LineSteps(drs);
            return ls;
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get unique schedule for the specified parameters.
     *
     * @param line_steps_id     id of a stop
     * @param horary            date associated to a schedule.
     *                          Only the "HH:mm:yy" part is important,
     *                          the rest of the date will not be processed.
     * @return  Schedule associated to <code>line_steps_id</code> and
     *          <code>horary</code>
     *          Can return null if the schedule do not exist.
     */
    @WebMethod(operationName="findUniqueSchedule")
    public Schedule findUniqueSchedule (@WebParam(name = "line_step_id") int line_steps_id,
            @WebParam(name = "horary") Date horary) {
        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM `bus_schedules` " +
                    "WHERE `line_steps_id` = '"+ line_steps_id +"' " +
                    "AND `horary` = '"+ horary +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new Schedule(drs);
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }


    /**
     * Get a unique line step id for the specified parameters.
     *
     * @param line_id      id of a line
     * @param stop_id      id of a stop
     * @return  a line step id
     */
    @WebMethod(operationName="findUniqueLineStep")
    public LineSteps findUniqueLineStep (@WebParam(name = "line_id") int line_id,
            @WebParam(name = "stop_id") int stop_id) {
        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM `bus_line_steps_lnk` " +
                    "WHERE `line_id` = '"+ line_id +"' " +
                    "AND `stop_id` = '"+ stop_id +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;
            return new LineSteps(drs);
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }


    /**
     * Get a unique PeriodLink for the specified paramaters
     *
     * @param pl    period link on which the research will be executed.
     *              Should contains the period id, schedule id and day.
     *
     * @return The period link completed with his id
     */
    @WebMethod(operationName = "findUniquePeriodLink")
    public PeriodLink findUniquePeriodLink(
            @WebParam(name = "periodLink") PeriodLink pl)
    {
        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM `bus_period_lnk` " +
                    "WHERE `period_id` = '"+ pl.getPeriod() +"' " +
                    "AND `schedule_id` = '"+ pl.getSchedule() +"' " +
                    "AND `day` = '"+ pl.getDay() +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;

            return new PeriodLink(drs);
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }

    /**
     * Get Period Link by ID
     *
     * @param id    id of the period link
     *
     * @return The specified Period Link
     */
    @WebMethod(operationName = "findPeriodLinkByID")
    public PeriodLink findPeriodLinkByID(@WebParam(name = "id")
    int id) {

        try
        {
            DataAccessLayer dal = new DataAccessLayer();

            String sql = "SELECT * FROM `bus_period_lnk` " +
                    "WHERE `id` = '"+ id +"'";

            DalResultSet drs = dal.extractDataSet(sql);

            if(drs.size() == 0)
                return null;

            return new PeriodLink(drs);
        }
        catch(Exception e)
        {
            throw new RuntimeException("SQL Exception: " + e.getMessage());
        }
    }
    

}
