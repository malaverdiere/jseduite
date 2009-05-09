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
import  cn.m1.hebut.jseduite.technical.alarms.admin.*;
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
public class AlarmAdminBean {
   AlarmsAdminService service = new AlarmsAdminService();
    DataModel allAlarms;
    int alarmId;
    int breakId;
    String alarmContent;
    private List<SelectItem> blist;
    private Alarm editAlarm,delAlarm;

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getBreakId() {
        return breakId;
    }

    public void setBreakId(int breakId) {
        this.breakId = breakId;
    }

    public Alarm getDelAlarm() {
        if(delAlarm==null)
            delAlarm=new Alarm();
        return delAlarm;
    }

    public Alarm getEditAlarm() {
        if(editAlarm==null)
            editAlarm=new Alarm();
        return editAlarm;
    }

    public DataModel getAllAlarms() {

        try { // get all the alarms
          
            AlarmsAdmin port = service.getAlarmsAdminPort();
           List<Alarm> result = port.readAlarm();
           allAlarms=new ListDataModel();
           allAlarms.setWrappedData(result);
        } catch (Exception ex) {
           ex.getMessage();
        }

        return allAlarms;
    }
    //add a new alarm
    public String addNewAlarm(){

        try { // Call Web Service Operation
            cn.m1.hebut.jseduite.technical.alarms.admin.AlarmsAdmin port = service.getAlarmsAdminPort();
            port.createAlarm(breakId, alarmContent);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
       return "showAllAlarms";
    }
    //edit the alarm you have choosen
    public String editAlarm(){
         editAlarm=(Alarm) allAlarms.getRowData();
         return "editAlarm";
    }
    public String updateAlarm(){
        
        try { // update alarm
           AlarmsAdmin port = service.getAlarmsAdminPort();
            port.updateAlarms(editAlarm.getBreakId(), editAlarm.getAlarmContent());
        } catch (Exception ex) {
            ex.getMessage();
        }
       return  "showAllAlarms";
    }
    ////////////delete the alarm
    public void deleteAlarm(){
       
        try { // Call Web Service Operation
           AlarmsAdmin port = service.getAlarmsAdminPort();
            delAlarm=(Alarm) allAlarms.getRowData();
            port.deleteAlarm(delAlarm.getBreakId());
        } catch (Exception ex) {
           ex.getMessage();
        }
    }
    ////////get breakid from the web services
    public List<SelectItem> getBlist() {
         blist=new LinkedList<SelectItem>();
        try { // Call Web Service Operation
            AlarmsAdmin port = service.getAlarmsAdminPort();
            List<java.lang.Object> result = port.getAllBreakId();
              Iterator i=result.iterator();
           while(i.hasNext()){
                Object b= i.next();
                SelectItem si=new SelectItem(b.hashCode(),b.toString());
                blist.add(si);
           }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return blist;
    }




}
