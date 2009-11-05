/**
 * This file is part of jSeduite::HyperMachin
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::HyperMachin is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::HyperMachin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::HyperMachin; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main   Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor [2009] Claudine Peyrat           [claudine@polytech.unice.fr]
 *
 **/
package data;
import java.util.ArrayList;
import java.util.Arrays;
import net.fortuna.ical4j.model.*;
import java.util.Date;
import util.*;

/**
 *
 * @author mosser
 */
public class HyperEvent {

    private String[] groups;

    private Date start;
    private Date end;
    private String kind;
    private String course;
    private String[] teachers;
    private String[] rooms;
    private String building;
        

    public void fill(Component c, String group) {
        this.start = HyperHelper.readDate("DTSTART", c);
        this.end = HyperHelper.readDate("DTEND",c);
        this.kind = extractKind(HyperHelper.readString("SUMMARY", c));
        this.course = extractCourse(HyperHelper.readString("SUMMARY", c));
        this.teachers = extractTeachers(HyperHelper.readString("DESCRIPTION", c));
        this.rooms = extractRooms(HyperHelper.readString("LOCATION", c));
        this.building = extractBuilding(HyperHelper.readString("LOCATION", c));
        this.groups = new String[] {group};
    }

    @Override
    public boolean equals(Object o) { // ALL excepting groups
        HyperEvent other = (HyperEvent) o;
        return  start.equals(other.start) && end.equals(other.end)
                && kind.equals(other.kind) && course.equals((other.course))
                && building.equals(other.building)
                && Arrays.equals(teachers,other.teachers) 
                && Arrays.equals(rooms,other.rooms);
    }

    public void addGroup(String g) {
        ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(this.groups));
        tmp.add(g);
        this.groups = tmp.toArray(new String[tmp.size()]);
    }

    private String extractBuilding(String s) {
        try {
            return s.substring(s.lastIndexOf("-")+1,s.length());
        } catch (Exception e) { return "??"; }
    }
    private String[] extractRooms(String s) {
        try {
            return extractMultiple(s.substring(0,s.lastIndexOf("-")));
        } catch (Exception e) { return new String[] {"??"}; }
    }

    private String[] extractTeachers(String s) {
        try {
            return extractMultiple(s.split(":")[1]);
        } catch (Exception e) { return new String[] {"??"}; }
    }

    private String extractKind(String s) {
        try {
           return s.substring(s.lastIndexOf("-")+1,s.length()).trim();
        } catch (Exception e) { return "??"; }
    }

    private String extractCourse(String s) {
        try {
            return s.substring(0,s.lastIndexOf("-")).trim();
        } catch (Exception e) { return "??"; }
    }


    private String[] extractMultiple(String raw) {
        String[] tmp = raw.split(",");
        for(int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].trim();
        }
        return tmp;
    }


    
    /** XML Serialization **/

    public HyperEvent() {}

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String[] getRooms() {
        return rooms;
    }

    public void setRooms(String[] rooms) {
        this.rooms = rooms;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String[] getTeachers() {
        return teachers;
    }

    public void setTeachers(String[] teachers) {
        this.teachers = teachers;
    }
}
