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

package fr.unice.i3s.modalis.jSeduite.technical.news.summon;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Summoning {

    private int id;
    private String student;
    private String promo;
    private Date date;
    private String owner;
    private String level;

    public Summoning() {}

    public Summoning(DalResultSet rset) throws Exception {
        this.student = rset.getValue("student");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.date = format.parse(rset.getValue("date"));
        this.level = rset.getValue("level");
        this.owner = rset.getValue("owner");
        this.promo = rset.getValue("promo");
        this.id = Integer.parseInt(rset.getValue("id"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromo() {
        return promo;
    }


    public void setPromo(String promo) {
        this.promo = promo;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }


}
