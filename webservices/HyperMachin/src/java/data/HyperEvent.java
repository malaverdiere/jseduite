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
import java.util.Arrays;
import net.fortuna.ical4j.model.*;
import java.util.Date;
import java.util.ArrayList;
import util.*;

/** A class to represent timetable Events
 * @author mosser
 */
public class HyperEvent {

    private String[] groups;     // groups following the HyperEvent
    private Date start;          // start date
    private Date end;            // end date
    private String kind;         // kind (lab, lecture, ...)
    private String course;       // course name
    private String[] teachers;   // associated teachers
    private String[] rooms;      // associated rooms
    private String building;     // location inside the campus
    private String promo = "??"; // associated promotion

    /** Build a HyperEvent for a given promotion
     * @param p th promotion name
     */
    public HyperEvent(String p) { this.promo = p; }

    /** Fill the current instance according to an iCal component
     * @param c  a component (generated by HyperPlanning)
     * @param group name of the group associated to this component
     */
    public void fill(Component c, String group) {
        this.start    = HyperHelper.readDate("DTSTART", c);
        this.end      = HyperHelper.readDate("DTEND",c);
        this.kind     = extractKind(c);
        this.course   = extractCourse(c);
        this.teachers = extractTeachers(c);
        this.rooms    = extractRooms(c);
        this.building = extractBuilding(c);
        this.groups   = new String[] {group};
    }

    /** An equality method for HyperEvents
     *  The equality os based on ALL the attirbute, excepted the group array
     * @remark this method is used to determine if a new HyperEvent should be
     *         created or if an existing one can be reused for a given group.
     * @param o The object to test (must be a castable as a Component)
     * @return true if equals (excepting groups), false elsewhere.
     */
    @Override
    public boolean equals(Object o) { // ALL excepting groups
        HyperEvent other = (HyperEvent) o;
        return  start.equals(other.start) && end.equals(other.end)
                && kind.equals(other.kind) && course.equals((other.course))
                && building.equals(other.building)
                && Arrays.equals(teachers,other.teachers) 
                && Arrays.equals(rooms,other.rooms);
    }

    /** Add a group name into the group array.
     * @param g the group to add
     */
    public void addGroup(String g) {
        ArrayList<String> tmp = new ArrayList<String>(Arrays.asList(this.groups));
        tmp.add(g);
        this.groups = tmp.toArray(new String[tmp.size()]);
    }

    /*********************
     ** Data extraction **
     *********************/
    
    private String extractBuilding(Component c) {
        try { // works as a misunderstanding (aka SHAZAM)
            String s = HyperHelper.readString("LOCATION", c);
            return s.split(" ")[1].trim();
            //return s.substring(s.lastIndexOf(" ")+1,s.length()).trim();
        } catch (Exception e) { return "??"; }
    }
    private String[] extractRooms(Component c) {
        try {
            String s = HyperHelper.readString("LOCATION", c);
            String[] raw = extractCommaSeparated(s);
            ArrayList<String> result = new ArrayList<String>();
            for(String str: raw){
                try {result.add(str.split(" ")[0]);}
                catch(Exception e){continue;}
            }
            return result.toArray(new String[result.size()]);
            //return extractCommaSeparated(s.substring(0,s.lastIndexOf("-")));
        } catch (Exception e) { return new String[] {"??"}; }
    }

    private String[] extractTeachers(Component c) {
        try {
            String s = HyperHelper.readString("DESCRIPTION", c);
            s = s.split("\n")[1]; // Information is located line #2
            return extractCommaSeparated(s.substring(s.lastIndexOf(":")+1));
        } catch (Exception e) { return new String[] {"??"}; }
    }

    private String extractKind(Component c) {
        try {
           String s = HyperHelper.readString("SUMMARY", c);
           if (s.lastIndexOf("-") == -1)
                return "TD";
           return s.substring(s.lastIndexOf("-")+1,s.length()).trim();
        } catch (Exception e) { return "??"; }
    }

    private String extractCourse(Component c) {
        String s = HyperHelper.readString("SUMMARY", c);
        try {
            return s.substring(0,s.lastIndexOf("-")).trim();
        } catch (Exception e) { return s; }
    }


    private String[] extractCommaSeparated(String raw) {
        String[] tmp = raw.split(",");
        for(int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].trim();
        }
        return tmp;
    }

    /***********************
     ** XML Serialization **
     ***********************/

    public HyperEvent() {}
    public String   getBuilding()                  { return building; }
    public void     setBuilding(String building)   { this.building = building; }
    public String   getCourse()                    { return course; }
    public void     setCourse(String course)       { this.course = course; }
    public Date     getEnd()                       { return end; }
    public void     setEnd(Date end)               { this.end = end; }
    public String[] getGroups()                    { return groups; }
    public void     setGroups(String[] groups)     { this.groups = groups; }
    public String   getKind()                      { return kind; }
    public void     setKind(String kind)           { this.kind = kind; }
    public String[] getRooms()                     { return rooms; }
    public void     setRooms(String[] rooms)       { this.rooms = rooms; }
    public Date     getStart()                     { return start; }
    public void     setStart(Date start)           { this.start = start; }
    public String[] getTeachers()                  { return teachers; }
    public void     setTeachers(String[] teachers) { this.teachers = teachers; }
    public String   getPromo()                     { return promo; }
    public void     setPromo(String promo)         { this.promo = promo; }
}
