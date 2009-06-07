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

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author vincent bonmalais
 */
public class Schedule {


    private int id;
    private Line schLine;
    private Date schTime;

    /**
     * Overloaded Constructor
     *
     * @param rset  should contain id_schedule and horary
     * @param pLine should be the line associated to the schedule
     */
    public Schedule(DalResultSet rset)
            throws Exception
    {
        this.id = Integer.parseInt(rset.getValue("id"));
        SimpleDateFormat format = new SimpleDateFormat("H:m:s");
        this.schTime = format.parse(rset.getValue("horary"));
    }


    /**
     * Default Constructor
     */
    public Schedule(){}


    /**
     * @return the schLine
     */
    public Line getSchLine() {
        return schLine;
    }

    /**
     * @param schLine the schLine to set
     */
    public void setSchLine(Line schLine) {
        this.schLine = schLine;
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
     * @return the schTime
     */
    public Date getSchTime() {
        return schTime;
    }

    /**
     * @param schTime the schTime to set
     */
    public void setSchTime(Date schTime) {
        this.schTime = schTime;
    }
    
}

