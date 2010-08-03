/**
 * This file is part of jSeduite::SchoolLife
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::SchoolLife is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Christophe Desclaux         [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaks;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BreakScreen {
    private int id;
    private Date start;
    private Date end;
    private String building;
    private String content;

    private String[] days;


    public BreakScreen() {}


    public BreakScreen(DalResultSet rset, String[] days)
            throws Exception {
        this.id = Integer.parseInt(rset.getValue("id"));
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        this.start = format.parse(rset.getValue("start"));
        this.end = format.parse(rset.getValue("end"));
        this.building = rset.getValue("building");
        this.content = rset.getValue("content");
        this.days = days;
    }

    public BreakScreen(int id, Date start, Date end, String building, 
                       String content, String[] days) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.building = building;
        this.content = content;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }
}
