/**
 * This file is part of jSeduite::breakTime
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::breakTime is free software; you can redistribute it and/or modify
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

package fr.unice.i3s.modalis.jSeduite.technical.breaktime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class BreakTimeBusiness {
    /** Return next menu
     * @return the menu the next menu
     * @param delta the delta time for writing last menu
     * @throws RestaurantException (see findMenuByDate @MenuFinder
     */
    @WebMethod(operationName = "getTodayBreakTimes")
    public  BreakTime[] getTodayBreakTimes() throws BreakTimeException {
        Calendar now = Calendar.getInstance();
        BreakTimeFinder finder = new BreakTimeFinder();
        int [] breaks = finder.getADateBreakTimesIds(now.get(Calendar.DAY_OF_WEEK));
        ArrayList<BreakTime> items = new ArrayList<BreakTime>();

        for (int cur : breaks){
            BreakTime curBreak = finder.findBreakTimeById(cur);
            if(curBreak != null){
                Calendar start = Calendar.getInstance();
                start.setTime(curBreak.getStart());
                start.set(Calendar.YEAR,now.get(Calendar.YEAR));
                start.set(Calendar.MONTH,now.get(Calendar.MONTH));
                start.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH));
                if(start.after(now)){
                    curBreak.setStart(start.getTime());
                    items.add(curBreak);
                }
            }
        }
        return items.toArray(new BreakTime[items.size()]);
    }
}
