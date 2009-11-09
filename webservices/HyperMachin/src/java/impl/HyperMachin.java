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
package impl;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import data.*;
import java.util.ArrayList;
import java.util.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Period;
import util.*;

/**
 *
 * @author mosser
 */
@WebService(targetNamespace="http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin")
public class HyperMachin {

    /** Extract ALL relevant HyperEvents for a given HyperPromo
     * @param promo the HyperPromo to handle
     * @return a set of HyperEvents
     * @throws HyperException
     */
    @WebMethod(operationName = "getHyperEvents")
    public HyperEvent[] getHyperEvents(
            @WebParam(name = "promo") HyperPromo promo) throws HyperException {
        return get(promo,null);
    }

    /** Extract current HyperEvents (running now) for a given HyperPromo
     * @param promo the HyperPromo to handle
     * @return a set of HyperEvents
     * @throws data.HyperException
     */
    @WebMethod(operationName = "getHyperEventsRestriction")
    public HyperEvent[] getCurrentHyperEvents(
            @WebParam(name = "promo")   HyperPromo promo)
            throws HyperException {
        Dur now = new Dur(0,0,0,0);
        return get(promo,now);
    }

    /*********************
     ** Private methods **
     *********************/

    /** Factorized HyperPromo -> HyperEvents* transformation
     * @param p the HyperPromo to handle
     * @param delta a filter to restrict the set of extracted HyperEvents
     * @return a set of HyperEvents for 'p' restricted following 'delta'
     */
    private HyperEvent[] get(HyperPromo p, Dur delta) throws HyperException {

        try {
            Calendar now = Calendar.getInstance();
            now.set(java.util.Calendar.HOUR_OF_DAY, 0);
            now.clear(java.util.Calendar.MINUTE);
            now.clear(java.util.Calendar.SECOND);
            Period today = (null == delta? null : new Period(new DateTime(now.getTime()),delta));
            HyperEventBuilder builder = new HyperEventBuilder(p,today);
            builder.transform();
            ArrayList<HyperEvent> result = builder.getResult();
            return result.toArray(new HyperEvent[result.size()]);
        } catch(Exception e) {
            throw new HyperException(e.getMessage());
        }
    }
    
}
