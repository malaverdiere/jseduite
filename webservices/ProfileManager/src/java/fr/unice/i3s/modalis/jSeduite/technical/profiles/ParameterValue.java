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
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles;

/** This data structure binds a value (as a string) to a parameter name;
 * @author mosser
 */
public class ParameterValue {
    // The name of the parameter
    private String name;
    // The expected value
    private String value;

    /** Default constructor, for XML serialization purpose
     */
    public ParameterValue() {}

    /** Usual constructor for parameter value stuff
     * @param name the name of the parameter
     * @param value the expected value which should be used
     */
    public ParameterValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /** Getters 'n Setters **/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
