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
public class Line {

    /**
     * Line identifier
     */
    private int id;

    /**
     * Line name should be unique
     */
    private String name;

    /**
     * Stop where the schedules have been mesured
     *
     */
    private int busSteps;


    /**
     * Constructor using the Data Access Layer object
     *
     * @param rset  should contain id, name and information about the stop
     */
    public Line(DalResultSet rset)
            throws Exception
    {
        this.id = Integer.parseInt(rset.getValue("id"));
        this.name = rset.getValue("name");
        this.busSteps = Integer.parseInt(rset.getValue("bus_steps"));
    }



    /**
     * Default Constructor
     */
    public Line(){}

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the busSteps
     */
    public int getBusSteps() {
        return busSteps;
    }

    /**
     * @param busSteps the busSteps to set
     */
    public void setBusSteps(int busSteps) {
        this.busSteps = busSteps;
    }


}
