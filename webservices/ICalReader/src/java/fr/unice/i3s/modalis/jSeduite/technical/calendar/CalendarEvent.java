/**
 * This file is part of jSeduite::ICalReader
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ICalReader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ICalReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ICalReader; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.calendar;
import java.util.Date;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.*;
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;


/** A data class to store calendar events in a more simple way than ICal model
 * @author mosser
 */
public class CalendarEvent implements Serializable {
    
    private String summary;
    private String description;
    private String location;
    private Date start;
    private Date end;

    /** Transform an iCal component list into a CalendarEvent array
     * @param cList the list to transform
     * @return the transformed array
     */
    public static CalendarEvent[] transform(ComponentList cList) {
        ArrayList<CalendarEvent> result = new ArrayList<CalendarEvent>();
        Iterator<Component> it = cList.iterator();
        while(it.hasNext()) {
          result.add(new CalendarEvent(it.next()));
        }
        return result.toArray(new CalendarEvent[result.size()]);
    }


    /** Extract a string property from an iCal component (aka Event)
     * @param p the property name
     * @param c the event component
     * @return the content of such a property, the empty string elsewhere
     */
    private String readString(String p, Component c) {
        try {
            return c.getProperty(p).getValue();
        }catch(Exception e) {
            return "";
        }
    }

    /** Ectract a date property from an iCal component (aka Event)
     * @param p the property name
     * @param c the event component
     * @return A Date instance corresponding to the expected date.
     */
    private Date readDate(String p, Component c) {
        try {
            DateProperty dp =  (DateProperty) c.getProperty(p);
            return dp.getDate();
        }catch(Exception e) {
            return new Date();
        }
    }
    public CalendarEvent(Component c) {
        this.summary = readString("SUMMARY",c);
        this.description = readString("DESCRIPTION",c);
        this.start = readDate("DTSTART", c);
        this.end = readDate("DTEND",c);
        this.location = readString("LOCATION", c);
    }

    /** MUST BE DEFINED FOR XML (SOAP) SERIALIZATION PURPOSE THROUGH JAX **/
    public CalendarEvent() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
