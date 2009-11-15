/**
 * This file is part of jSeduite::ErrorLogger
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ErrorLogger is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ErrorLogger is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ErrorLogger; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor 2008 Clémentine Delerce-Mauris [delercm@polytech.unice.fr]
 * @contributor 2008 Lionel Palacin            [lionel.palacin@gmail.com]
 * @contributor 2008 Stéphane Martarello       [stephane.martarello@gmail.com]
 * @contributor 2009 Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.logger.error;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/** A simple data class to represent a Log inside the database
 * @author mosser
 */
public class ErrorLog {

    private int id;
    private Date stamp;
    private String trigger;
    private String level;
    private String message;

    public ErrorLog() {} ;

    public ErrorLog(Date stamp, String trigger, String level, String message) {
        this.stamp = stamp;
        this.trigger = trigger;
        this.level = level;
        this.message = message;
        this.id = 0;
    }

    public ErrorLog(int id, Date stamp, String trigger, String level,
            String message) {
        this.stamp = stamp;
        this.trigger = trigger;
        this.level = level;
        this.message = message;
        this.id = id;
    }

    public ErrorLog(DalResultSet result) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        this.stamp = format.parse(result.getValue("stamp"));
        this.trigger = result.getValue("trigger");
        this.level = result.getValue("level");
        this.message = result.getValue("message");
        this.id = Integer.parseInt(result.getValue("id"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date stamp) {
        this.stamp = stamp;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

}
