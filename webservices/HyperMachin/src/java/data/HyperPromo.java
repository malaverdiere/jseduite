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

/** A data structure to deal with Promotions
 * @author mosser
 */
public class HyperPromo {

    private String name;         // the name of the promotion
    private String code;         // the promo identifier
    private HyperGroup[] groups; // the associated hyper groups

    /** XML Serialization **/
    public HyperPromo() {}
    public HyperGroup[] getGroups()                    { return groups; }
    public void         setGroups(HyperGroup[] groups) { this.groups = groups; }
    public String       getName()                      { return name; }
    public void         setName(String name)           { this.name = name; }
    public String       getCode()                      { return code; }
    public void         setCode(String code)           { this.code = code; }
}
