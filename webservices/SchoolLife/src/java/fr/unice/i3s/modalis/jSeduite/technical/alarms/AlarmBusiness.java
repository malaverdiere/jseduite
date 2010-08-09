/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.unice.i3s.modalis.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.technical.breaktime.BreakTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author descl
 */
@WebService()
public class AlarmBusiness {
    /** Return next menu
     * @return the menu the next menu
     * @param delta the delta time for writing last menu
     * @throws RestaurantException (see findMenuByDate @MenuFinder
     */
    @WebMethod(operationName = "getTodayAlarms")
    public  Alarm[] getTodayAlarms() throws AlarmException {
        AlarmFinder finder = new AlarmFinder();
        Calendar now = Calendar.getInstance();
        try{
            Alarm [] alarms = finder.getADayAlarms(now.get(Calendar.DAY_OF_WEEK));

            ArrayList<Alarm> items = new ArrayList<Alarm>();

            for (Alarm curAlarm : alarms){
                Calendar start = Calendar.getInstance();
                start.setTime(curAlarm.getDateRing());
                start.set(Calendar.YEAR,now.get(Calendar.YEAR));
                start.set(Calendar.MONTH,now.get(Calendar.MONTH));
                start.set(Calendar.DAY_OF_MONTH,now.get(Calendar.DAY_OF_MONTH));
            
                if(start.after(now)){
                    curAlarm.setDateRing(start.getTime());
                    items.add(curAlarm);
                }
            }
        return items.toArray(new Alarm[items.size()]);
        }catch(Exception e){
            throw new AlarmException(e.toString());
        }
    }
}
