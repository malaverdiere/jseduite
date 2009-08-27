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
 * @author      Main Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.plannings;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import java.util.ArrayList;
import java.util.List;


public class Planning {
    private int id;
    private String planning;
    private List<PlanningGroup> groups;

    public Planning() {
    }

    public Planning(DalResultSet rset, DalResultSet rset_groups) {
        this.id = Integer.parseInt(rset.getValue("id"));
        this.planning = rset.getValue("planning");

        this.groups = new ArrayList<PlanningGroup>();

        for(int i=0; i<rset_groups.size(); i++) {
            groups.add(new PlanningGroup(rset_groups));
            rset_groups.next();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public List<PlanningGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<PlanningGroup> groups) {
        this.groups = groups;
    }
}
