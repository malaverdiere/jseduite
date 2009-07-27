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

/** A set of parameter which should be used to perform an invocation
 */
public class ParameterCallSet {

    private ParameterValue[] callValues;

    /** Default constructor for XML serialization purpose
     */
    public ParameterCallSet() {}

    // Getter 'n Setters
    public ParameterValue[] getValues() {
        return callValues;
    }

    public void setValues(ParameterValue[] values) {
        this.callValues = values;
    }

}
