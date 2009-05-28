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

import java.util.ArrayList;
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
     * @return Line[]   Lines
     */
    @WebMethod(operationName = "getAllLines")
    public Line[] getAllLines()
        throws BusScheduleException {

        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM bus_lines";

        try{
            DalResultSet drs = dal.extractDataSet(sql);

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
     * Get all Schedule corresponding to a stop
     *
     * @param stop
     */
    @WebMethod(operationName = "getSchedulesForStop")
    public Schedule[] getSchedulesForStop(@WebParam(name = "stop") int idStop)
        throws BusScheduleException {

        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get all Schedule for a specified Stop and Time
     */
    @WebMethod(operationName = "getSchedulesForStopAtTime")
    public ArrayList getSchedulesForStopAtTime(@WebParam(name = "stop") String stop,
                                            @WebParam(name = "time") Date time)
    {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Get all stop for a specified Line
     */
    @WebMethod(operationName = "getAllStopsForLine")
    public ArrayList getStopsForLine(@WebParam(name = "line") Line line) {
        //TODO write your implementation code here:
        return null;
    }

}
