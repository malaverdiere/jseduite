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
 * @author      Steve Colombié         [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.technical.breaktime.BreakTime;


public class Alarm {
    private int id;
    private String kind;
    private String message;
    private String sound;
    private BreakTime breakTime;

    public Alarm() {}

    public Alarm(DalResultSet rset, BreakTime breakTime) {
        this.id = Integer.parseInt(rset.getValue("id"));
        this.kind = rset.getValue("kind");
        this.message = rset.getValue("message");
        this.sound = rset.getValue("sound");
        this.breakTime = breakTime;
    }

    public int getId() {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind (String kind) {
        this.kind = kind;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public String getSound() {
        return sound;
    }

    public void setSound (String sound) {
        this.sound = sound;
    }

    public BreakTime getBreakTime() {
        return breakTime;
    }

    public void setBreakTime (BreakTime breakTime) {
        this.breakTime = breakTime;
    }
}
