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
import data.*;
import java.util.ArrayList;
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getToday")
    public HyperEvent[] getToday(HyperPromo p) throws Exception {
        java.util.Calendar now = java.util.Calendar.getInstance();
        now.set(java.util.Calendar.HOUR_OF_DAY, 0);
        now.clear(java.util.Calendar.MINUTE);
        now.clear(java.util.Calendar.SECOND);
        Period today = new Period(new DateTime(now.getTime()),new Dur(1,0,0,0));
        HyperEventBuilder builder = new HyperEventBuilder(p,today);
        builder.transform();
        ArrayList<HyperEvent> result = builder.getResult();
        return result.toArray(new HyperEvent[result.size()]);
    }
 
}
