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
import java.util.Date;


public class HyperLocation {

    private Date start;         
    private Date end;           
    private String kind;        
    private String teacher;
    private String[] rooms;
    private String building;

    public static HyperLocation[] build(HyperEvent source) {
        ArrayList<HyperLocation> result = new ArrayList<HyperLocation>();
        for(String t: source.getTeachers()) {
            HyperLocation loc = new HyperLocation();
            loc.setEnd(source.getEnd());
            loc.setStart(source.getStart());
            loc.setBuilding(source.getBuilding());
            loc.setRooms(source.getRooms());
            loc.setKind(source.getKind());
            loc.setTeacher(t);
            result.add(loc);
        }
        return result.toArray(new HyperLocation[result.size()]);
    }


    @Override
    public boolean equals(Object o) {
        HyperLocation other = (HyperLocation) o;
        return this.building.equals(other.getBuilding())
                &&  this.end.equals(other.getEnd())
                &&  this.kind.equals(other.getKind())
                && this.start.equals(other.getStart())
                && this.teacher.equals(other.getTeacher())
                && Arrays.equals(this.rooms, other.getRooms());
    }

    /** XML Serialization **/
    public HyperLocation() {}
    public Date getEnd() { return end; }
    public void setEnd(Date end) {  this.end = end; }
    public String getKind() { return kind; }
    public void setKind(String kind) { this.kind = kind; }
    public String[] getRooms() {  return rooms; }
    public void setRooms(String[] rooms) { this.rooms = rooms; }
    public Date getStart() { return start; }
    public void setStart(Date start) { this.start = start; }
    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
}
