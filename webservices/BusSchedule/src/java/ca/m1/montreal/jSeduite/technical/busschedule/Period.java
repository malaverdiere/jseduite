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
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author vincentbonmalais
 */
public class Period {
    private int id;
    private String name;
    private Date begin;
    private Date end;

    public Period(DalResultSet rset)
            throws Exception
    {
        this.id = Integer.parseInt(rset.getValue("id"));
        this.name = rset.getValue("name");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.begin = format.parse(rset.getValue("begin"));
        this.end = format.parse(rset.getValue("end"));
    }


    /**
     * Default constructor
     */
    public Period(){}


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
     * @return the begin
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * @param begin the begin to set
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(Date end) {
        this.end = end;
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


    
}
