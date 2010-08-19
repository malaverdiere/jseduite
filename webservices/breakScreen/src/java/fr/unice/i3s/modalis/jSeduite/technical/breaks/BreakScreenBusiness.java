/**
 * This file is part of jSeduite::breakScreen
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::breakScreen is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Christophe Desclaux         [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.breaks;

import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class BreakScreenBusiness {

    /** Return next Break Screens for today
     * @return the list of next Break Screens
     * @throws RestaurantException (see findMenuByDate @MenuFinder
     */
    @WebMethod(operationName = "getTodayBreakScreens")
    public  BreakScreen[] getTodayBreakScreens() throws BreakScreenException {
        Calendar now = Calendar.getInstance();
        //now.set(Calendar.DAY_OF_MONTH,8);
        BreakScreenFinder finder = new BreakScreenFinder();
        int [] breaks = finder.getADateBreakScreensIds(now.get(Calendar.DAY_OF_WEEK));
        ArrayList<BreakScreen> items = new ArrayList<BreakScreen>();

        for (int cur : breaks){
            BreakScreen curBreak = finder.findBreakScreenById(cur);
            Calendar start = Calendar.getInstance();
            start.setTime(curBreak.getStart());
            start.set(Calendar.YEAR,now.get(Calendar.YEAR));
            start.set(Calendar.MONTH,now.get(Calendar.MONTH));
            start.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH));
            Calendar end = Calendar.getInstance();
            end.setTime(curBreak.getEnd());
            end.set(Calendar.YEAR,now.get(Calendar.YEAR));
            end.set(Calendar.MONTH,now.get(Calendar.MONTH));
            end.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH));
            if(end.after(now)){
                
                curBreak.setEnd(end.getTime());
                curBreak.setStart(start.getTime());
                items.add(curBreak);
            }
        }
        return items.toArray(new BreakScreen[items.size()]);
    }

}
