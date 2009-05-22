/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakManagerJSFClient is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakManagerJSFClient is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *
 **/

package Bean;

import cn.m1.hebut.jseduite.technical.alarms.admin.*;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeException_Exception;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManagerService;
import cn.m1.hebut.jseduite.technical.breaktime.Exception_Exception;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManager;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTime;
import com.sun.istack.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.ws.WebServiceRef;
/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class AlarmAdminBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakTimeManagerService/localhost_8080/BreakManager/BreakTimeManagerService.wsdl")
    private BreakTimeManagerService service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/AlarmAdminService/localhost_8080/jseduite/AlarmManager/AlarmAdminService.wsdl")
    private AlarmAdminService service;

   DataModel allBreakModel,editModel,breakByAlarmModel;//delete nolnk
   DataModel allAlarmModel;
   Break breakTime=new Break();
   int breakid=0;
   String content,position;
   Alarm delAlarm,editAlarm,currentAlarm;
    public DataModel getAllBreakModel() {
        try {
            allBreakModel = new ListDataModel();
            allBreakModel.setWrappedData(breakTime.getbreak());
        } catch (Exception ex) {
            Logger.getLogger(AlarmAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
          return allBreakModel;
    }

    public DataModel getAllAlarmModel() {

        try { // get all the alarms
            AlarmAdmin port = service.getAlarmAdminPort();
            List<Alarm> result = port.readAllAlarms();
            allAlarmModel=new ListDataModel();
            allAlarmModel.setWrappedData(result);
        } catch (Exception ex) {
           ex.getMessage();
        }
        return allAlarmModel;
    }

    public DataModel getBreakByAlarmModel() {

        try { // get breaks by alarm
           BreakTimeManager port = service_1.getBreakTimeManagerPort();
            currentAlarm=(Alarm)allAlarmModel.getRowData();
            List<BreakTime> result = port.getBreakByAlarm(currentAlarm.getAlarmId());
            breakByAlarmModel=new ListDataModel();
            breakByAlarmModel.setWrappedData(result);
        } catch (Exception ex) {
           ex.getMessage();
        }
        return breakByAlarmModel;
    }

    public int getBreakid() {
        return breakid;
    }

    public void setBreakid(int breakid) {
        this.breakid = breakid;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Alarm getDelAlarm() {
        if(delAlarm==null)
        {
           delAlarm=new Alarm();
        }
        return delAlarm;
    }

    public Alarm getEditAlarm() {
        if(editAlarm==null){
            editAlarm=new Alarm();
        }
        return editAlarm;
    }

    public Alarm getCurrentAlarm() {
        if(currentAlarm==null)
            currentAlarm=new Alarm();
        return currentAlarm;
    }

    //add a new Alarm
      public String addAlarm(){
          try {
              AlarmAdmin port = service.getAlarmAdminPort();
              List<Break> blist=(List<Break>)allBreakModel.getWrappedData();
              Iterator<Break> bint=blist.iterator();
              int id=0;
              while(bint.hasNext()){
                Break b=bint.next();
                 if(b.isBreakChecked()){
                     id=b.getId();
                     port.createAlarm(id, content,position);
              }
            }
            if(id==0){
                port.createAlarm(id, content,position);
            }

          } catch (Exception ex) {
             ex.getMessage();
          }
          return "listAllAlarms";
      }
      //delete an alarm
      public String deleteAlarm(){
          try {  //delete an alarm
             AlarmAdmin port = service.getAlarmAdminPort();
             delAlarm=(Alarm)allAlarmModel.getRowData();
              port.deleteAlarm(delAlarm.getAlarmId());
          } catch (Exception ex) {
              ex.getMessage();
          }
         return null;
      }
       public String goToEdit(){
        editAlarm=(Alarm)allAlarmModel.getRowData();
        return "editAlarm";
    }
    public DataModel getEditModel() {
        editModel=new ListDataModel();
        try {
            editModel.setWrappedData(breakTime.listBreak(editAlarm.getAlarmId()));
        } catch (Exception ex) {
            Logger.getLogger(AlarmAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editModel;
    }
    //edit an alarm
    public String editAlarm(){
        try {
           AlarmAdmin port = service.getAlarmAdminPort();
           int alarmId=editAlarm.getAlarmId();
           List<Break> blist=(List<Break>)editModel.getWrappedData();
            Iterator<Break> bint=blist.iterator();
            port.deleteAlarmLnk(alarmId);
            int id=0;
            while(bint.hasNext()){
                Break b=bint.next();
                 if(b.isBreakChecked()){
                      id=b.getId();
                     port.updateAlarms(alarmId,editAlarm.getAlarmContent(),editAlarm.getPosition() ,id);
                 }
               }
              if(id==0){
                port.updateAlarms(alarmId, editAlarm.getAlarmContent(),editAlarm.getPosition(), 0);
              }
        } catch (Exception ex) {
           ex.getMessage();
        }
       return "listAllAlarms";
   }
   


}

