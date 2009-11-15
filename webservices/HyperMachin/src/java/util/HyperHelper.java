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

package util;
import java.io.*;
import java.net.URL;
import net.fortuna.ical4j.data.*;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.filter.*;

/** A file containing static tools to help others classes implementation.
 * @author mosser
 */
public class HyperHelper {

    /** Retrict an iCal componentList according to a given Period
     * @param cList the initial ComponentList
     * @param p the period used as a Time filter
     * @return a restriction of cList, according to p
     */
    public static ComponentList restrict(ComponentList cList, Period p) {
        Filter filter = new Filter(new PeriodRule(p));
        return (ComponentList) filter.filter(cList);
    }

    /** Retrieve iCal ComponentList from an external iCAL file (http)
     * @param ical the file to read through http
     * @return a ComponentList build using ical file
     */
    public static  ComponentList retrieveComponents(URL ical)
            throws Exception {
        return retrieveCalendar(ical).getComponents(Component.VEVENT);
    }

    public static Calendar retrieveCalendar(URL iCal) throws Exception  {
        StringReader sin = new StringReader(getResourceContent(iCal));
        CalendarBuilder builder = new CalendarBuilder();
        Calendar c = builder.build(sin);
        return c; 
    }

    /** Extract a component property as a date
     * @param p the property name
     * @param c the component to read from
     * @return the expected Date, or the empty Date if a problem occurs
     */
    public static Date readDate(String p, Component c) {
        try {
            DateProperty dp =  (DateProperty) c.getProperty(p);
            return dp.getDate();
        }catch(Exception e) {
            return new Date();
        }
    }

    /** Extract a component property as a String
     * @param p the property name
     * @param c the component to read from
     * @return the expected String, or the empty one if a problem occurs.
     */
    public static String readString(String p, Component c) {
        try {
            return c.getProperty(p).getValue();
        }catch(Exception e) {
            return "";
        }
    }
    
    /** Return the content of an external file, as an UTF-8 encoded string
     * @remarks the UTF-8 should (theoretically) not be explicit,
     *          but in reality, it must.
     * @param url the URL to read from
     * @return a String containing all the line retrieved from the URL
     */
    private static String getResourceContent(URL url) throws Exception  {
       InputStream is = url.openStream();
       BufferedReader bread = new BufferedReader(new InputStreamReader(is));
       String line = "";
       StringBuilder buff = new StringBuilder();
       while((line = bread.readLine()) != null)
           buff.append(line+"\n");
        return new String(buff.toString().getBytes(),"UTF-8");
    }
}
