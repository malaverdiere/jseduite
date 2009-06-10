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

/**
 * Object finder for BusSchedule.
 * This class is a singleton and follow the principle of singleton pattern
 * as Bill Pugh has written it.
 *
 *
 * @author vincent bonmalais
 * @author yannick tahora
 * @see BusScheduleFinderHolder
 */
public class BusScheduleFinder {

    /**
     * Private default constructor
     */
    private BusScheduleFinder() {}


    /**
     * Singleton Pattern created by Bill Pugh.
     *
     * @see <a href="http://en.wikipedia.org/wiki/Singleton_pattern">Singleton Pattern</a>
     * @see BusScheduleFinder#getInstance()
     */
    private static class BusScheduleFinderHolder
    {
        private static final BusScheduleFinder INSTANCE = new BusScheduleFinder();
    }

    /**
     * Instance accessor
     *
     * @return      an instance of <code>BusScheduleFinder</code>
     * @see BusScheduleFinderHolder
     */
    public static BusScheduleFinder getInstance()
    {
        return BusScheduleFinderHolder.INSTANCE;
    }

    /**
     * Get line associated to id.
     *
     * @param id        id of the line
     * @return          unique line associated to id
     */
    public Line findLineByID(int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_lines` WHERE `id`= '%"+id+"%';";
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
    public Line[] findLineByName(String name) {
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
    public Line[] findLineByBusStep(String busStep) {
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
    public Stop findStopByID(int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_stops` WHERE `id`= '%"+id+"%';";
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
    public Stop[] findStopByDirection(String direction) {
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
    public Stop[] findStopByName(String name) {
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
     * Get period associated to id.
     *
     * @param id        id of the period
     * @return          unique period associated to id
     */
    public Period findPeriodByID(int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_period` WHERE `id`= '%"+id+"%';";
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
    public Period[] findPeriodByName(String name) {
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
    public Period[] findPeriodByDate(Date beginDate, Date endDate) {
        DataAccessLayer dal = new DataAccessLayer();

        // Format beginDate to yyyy-MM-dd
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatedBeginDate = format.format(beginDate);
        String formatedEndDate = format.format(beginDate);

        try {
            String sql = "SELECT * FROM `bus_periods` " +
                    "WHERE `begin` = '"+formatedBeginDate+"'" +
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
    public Schedule findScheduleByID(int id) {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `bus_schedules` WHERE `id`= '%"+id+"%';";
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
    public Schedule[] findScheduleByHorary(Date horary) {
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
     * Get schedules with line step (a stop id)
     * equals to <code>lineStep</code>
     *
     * @param lineStep  id of a stop
     * @return
     */
    public Schedule[] findScheduleByLineStep(String lineStep) {
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

}
