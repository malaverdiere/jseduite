/**
 * This file is part of jSeduite::ProfileManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ProfileManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ProfileManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ProfileManager; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles.params;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

public class Param {
    
    private int setId;
    private int id;
    private String name;
    private String value;
    private String defaultValue;

    public Param() {
    }

    public Param(DalResultSet rset) {
        this.id = Integer.parseInt(rset.getValue("id"));
        this.name = rset.getValue("name");
        this.value = rset.getValue("value");
        this.setId = Integer.parseInt(rset.getValue("set_id"));
        this.defaultValue = rset.getValue("default_value");
    }

    public Param(String name, String defaultValue, int id) {
        this.id = id;
        this.setId = 0;
        this.name = name;
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public int getSetId() {
        return setId;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
