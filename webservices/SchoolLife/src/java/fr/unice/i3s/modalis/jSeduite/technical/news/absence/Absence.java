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
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2009 Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/
package fr.unice.i3s.modalis.jSeduite.technical.news.absence;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author mosser
 */
public class Absence {

    private int id;
    private String teacher;
    private String reason;
    private Date from;
    private Date until;

    public Absence() { }

    public Absence(DalResultSet rset) throws Exception {
        this.teacher = rset.getValue("teacher");
        this.reason = rset.getValue("reason");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.from = format.parse(rset.getValue("from"));
        this.until = format.parse(rset.getValue("until"));
        this.id = Integer.parseInt(rset.getValue("id"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    

}
