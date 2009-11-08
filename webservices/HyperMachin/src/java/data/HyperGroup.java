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
import java.net.URL;

/** A data structure to bind a group name to an URL
 * @author mosser
 */
public class HyperGroup {
    
    private String name; // the group name
    private URL url;     // the associated URL

    /** HyperGroup business constructor
     * @param n the group name
     * @param u the associated URL
     */
    public HyperGroup(String n, URL u) {
        this.name = n;
        this.url = u;
    }

    /** XML Serialisation **/
    public HyperGroup() {}
    public String getName()            { return name; }
    public void   setName(String name) { this.name = name; }
    public URL    getUrl()             { return url; }
    public void   setUrl(URL url)      { this.url = url;}
}
