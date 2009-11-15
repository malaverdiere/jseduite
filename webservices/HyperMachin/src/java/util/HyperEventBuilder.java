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
import data.*;
import java.util.ArrayList;
import net.fortuna.ical4j.model.*;

/** This builder construct a set of relevant HyperEvents
 *  according to a given HyperPromo
 * @author mosser
 */
public class HyperEventBuilder {

    // the promo to handle
    private HyperPromo promo;
    // The time restriction, if any
    private Period restriction;
    // The result of the transformation
    private ArrayList<HyperEvent> result;

    /** construct a Builder
     * @param p the HyperPromo to handle
     * @param r the time restriction to apply when filtering HyperEvents
     */
    public HyperEventBuilder(HyperPromo p, Period r) {
        this.promo = p;
        this.restriction = r;
        this.result = new ArrayList<HyperEvent>();
    }

    /** construct a Builder without any time restriction
     * @param p the HyperPromo to handle
     */
    public HyperEventBuilder(HyperPromo p) { this(p,null); }

    /** Getter for the set of builded HyperEvents
     * @return the result of the build
     */
    public ArrayList<HyperEvent> getResult() { return result; }

    /** Perform the transformation
     * @throws java.lang.Exception
     */
    public void transform() throws Exception {
        for(HyperGroup g: promo.getGroups()) {
            ComponentList cList = HyperHelper.retrieveComponents(g.getUrl());
            if (null != this.restriction)
                cList = HyperHelper.restrict(cList,this.restriction);
            this.process(g.getName(), cList);
        }
    }

    /** Process a set of ICAL components retrieved from a given HyperGroup
     * @param groupName the name of the HyperGroup to handle
     * @param cList the component List to transform
     */
    private void process(String groupName, ComponentList cList) {
        for(Object o: cList) 
            this.process(groupName,(Component)o);
    }

    /** Process an iCAL component for a given group
     * @param groupName the name of the HyperGroup to handle
     * @param c the component List to transform
     */
    private void process(String groupName, Component c) {
        HyperEvent e = new HyperEvent(this.promo.getName());
        e.fill((Component) c, groupName);
        if (! this.result.contains(e)) {
            // The HyperEvent should be inserted into the result
            this.result.add(e);
        } else {
            // The existing HyperEvent should be modified
            this.result.get(this.result.indexOf(e)).addGroup(groupName);
        }
    }


}
