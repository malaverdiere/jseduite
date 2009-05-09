/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakTimeManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakTimeManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *
 **/

package ManagedBean;

import javax.faces.model.DataModel;
import cn.m1.hebut.jseduite.technical.alarms.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class AlarmManagedBean {
    DataModel allAlarmModel,alarmBindModel;
    AlarmsManagerService service = new AlarmsManagerService();
    int breakId;
    private List<SelectItem> blist;


    public int getBreakId() {
        return breakId;
    }

    public void setBreakId(int breakId) {
        this.breakId = breakId;
    }
    
    public DataModel getAllAlarmModel() {

        try { //get all the alarms

            AlarmsManager port = service.getAlarmsManagerPort();
            List<Alarm> result = port.getAllAlarms();
            allAlarmModel=new ListDataModel();
            allAlarmModel.setWrappedData(result);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return allAlarmModel;
    }

    public DataModel getAlarmBindModel() {

        try { // Call Web Service Operation
           AlarmsManager port = service.getAlarmsManagerPort();
           Alarm result = port.getAlarmByBinding(breakId);
           List<Alarm> alarmlist=new ArrayList<Alarm>();
           alarmlist.add(result);
           alarmBindModel=new ListDataModel();
           alarmBindModel.setWrappedData(alarmlist);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return alarmBindModel;
    }

    public List<SelectItem> getBlist() {
        blist=new LinkedList<SelectItem>();

        try { // Call Web Service Operation
               AlarmsManager port = service.getAlarmsManagerPort();
                // TODO process result here
                List result = port.getAllBinding();
               Iterator i=result.iterator();
           while(i.hasNext()){
                Object b= i.next();
                SelectItem si=new SelectItem(b.hashCode(),b.toString());
                blist.add(si);
           }
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        return blist;
    }



}
