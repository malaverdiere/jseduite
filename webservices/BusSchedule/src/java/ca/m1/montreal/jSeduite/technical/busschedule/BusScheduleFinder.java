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
     * @see http://en.wikipedia.org/wiki/Singleton_pattern
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
     * Get lines containing <code>name</code> whithin their names.
     *
     * @param name      name of the line
     * @return          All lines containing <code>name</code>.
     */
    public Line[] findLineByName(String name) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get lines associated to a Bus Step (stop id).
     *
     * @param busStep   id of the stop associated to the line
     * @return          All lines with <code>busStep</code>
     */
    public Line[] findLineByBusStep(String busStep) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get stops associated to a <code>direction</code>
     *
     * @param direction     stop name (terminus)
     * @return              All stops switch the direction.
     */
    public Stop[] findStopByDirection(String direction) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get stops containing <code>name</code> in their names.
     *
     * @param name     stop name (terminus)
     * @return         All stops containing <code>name</code>.
     *
     */
    public Stop[] findStopByName(String name) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get periods containing <code>name</code> in their names.
     *
     * @param name      name of the period
     * @return          All periods containing <code>name</code>.
     */
    public Period[] findPeriodByName(String name) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get Period by begin date. The finder will return every period before
     * the specified date (<code>beginDate</code>)
     *
     * @param beginDate     beginning date of the period
     * @return              All periods with begin date older than
     *                      <code>beginDate</code>
     */
    public Period[] findPeriodByBeginDate(Date beginDate) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get Period by ending date. The finder will return every period after
     * the specified date (<code>endDate</code>)
     *
     * @param beginDate     ending date of the period
     * @return              All periods with ending date older than
     *                      <code>endDate</code>
     */
    public Period[] findPeriodByEndDate(Date endDate) {
        //TODO write your implementation code here:
        return null;
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
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get schedules with line step (a stop id)
     * equals to <code>lineStep</code>
     *
     * @param lineStep  id of a stop
     * @return
     */
    public Schedule[] findScheduleByLineStep(String lineStep) {
        //TODO write your implementation code here:
        return null;
    }

}
