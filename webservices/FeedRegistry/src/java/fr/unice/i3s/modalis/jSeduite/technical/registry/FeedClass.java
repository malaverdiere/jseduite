/**
 * This file is part of jSeduite::FeedRegistry
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::FeedRegistry is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::FeedRegistry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::FeedRegistry; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

public class FeedClass {
    private int id;
    private String name;

    public FeedClass() {
    }

    public FeedClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FeedClass(DalResultSet rSet) {
        this.id = Integer.parseInt(rSet.getValue("id"));
        this.name = rSet.getValue("class");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
