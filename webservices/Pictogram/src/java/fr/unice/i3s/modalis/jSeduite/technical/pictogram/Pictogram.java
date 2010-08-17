/**
 * This file is part of jSeduite::Pictogram
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::Pictogram is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::Pictogram is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Christophe Desclaux          [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.pictogram;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Date;
import java.text.SimpleDateFormat;


/** A simple data model to reify internal news
 * @author mosser
 */
public class Pictogram {

    private int id;
    private String picture1;
    private String picture2;
    private Date start;
    private Date end;
    private String title;

    public Pictogram() {}

    public Pictogram(DalResultSet rset) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.start = format.parse(rset.getValue("start"));
        this.end  = format.parse(rset.getValue("end"));
        this.title  = rset.getValue("title");
        this.picture1  = rset.getValue("picture1");
        this.picture2  = rset.getValue("picture2");
        this.id = Integer.parseInt(rset.getValue("id"));
    }

    public Pictogram(String picture1, String picture2, Date start, Date end, String title) {
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.start = start;
        this.end = end;
        this.title = title;
        this.id = 0;
    }

    public Pictogram(int id, String author, String picture1, String picture2, Date start,
            Date end, String title) {
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.start = start;
        this.end = end;
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}