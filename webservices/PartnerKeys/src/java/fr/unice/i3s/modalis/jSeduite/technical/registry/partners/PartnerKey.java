/**
 * This file is part of jSeduite::PartnerKeys
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PartnerKeys is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PartnerKeys is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PartnerKeys; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Steve Colombié [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry.partners;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;

public class PartnerKey {
    private String key;
    private String value;

    public PartnerKey() {
        
    }

    public PartnerKey(DalResultSet rset) throws Exception {
        this.key = rset.getValue("key");
        this.value = rset.getValue("value");
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
