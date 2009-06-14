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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

/**
 *
 * @author vincentbonmalais
 */
public class LineSteps {
    private int id;
    private int lineId;
    private int stopId;

    public LineSteps() {}

    public LineSteps(DalResultSet drs)
    {
        this.id = Integer.parseInt(drs.getValue("id"));
        this.lineId = Integer.parseInt(drs.getValue("line_id"));
        this.stopId = Integer.parseInt(drs.getValue("stop_id"));
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the lineId
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * @param lineId the lineId to set
     */
    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    /**
     * @return the stopId
     */
    public int getStopId() {
        return stopId;
    }

    /**
     * @param stopId the stopId to set
     */
    public void setStopId(int stopId) {
        this.stopId = stopId;
    }


}
