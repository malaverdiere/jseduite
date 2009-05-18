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
import cn.m1.hebut.jseduite.technical.breaktime.Exception_Exception;
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
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/AlarmAdminService/localhost_8080/jseduite/AlarmManager/AlarmAdminService.wsdl")
    private AlarmAdminService service;

   DataModel allModel,editModel,noLnkModel;
   Break breakTime=new Break();
   int breakid=0;
   String content;
   Alarm delAlarm,editAlarm;
    public DataModel getAllModel() throws BreakTimeException_Exception {

           allModel=new ListDataModel();
           allModel.setWrappedData(breakTime.getbreak());
           return allModel;
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

    //add a new Alarm
      public String addAlarm(){
          try {
              AlarmAdmin port = service.getAlarmAdminPort();
              List<Break> blist=(List<Break>)allModel.getWrappedData();
              Iterator<Break> bint=blist.iterator();
              while(bint.hasNext()){
                Break b=bint.next();
                 if(b.isBreakChecked()){
                     breakid=b.getId();
                     port.createAlarm(breakid, content);
              }
            }
            if(breakid==0){
                port.createAlarm(breakid, content);
            }

          } catch (Exception ex) {
             ex.getMessage();
          }
          return "listAllBreaks";
      }

    public DataModel getEditModel() {
        editModel=new ListDataModel();
        try {
            editModel.setWrappedData(breakTime.listBreak());
        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(AlarmAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (cn.m1.hebut.jseduite.technical.breaktime.DALException_Exception ex) {
            Logger.getLogger(AlarmAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception_Exception ex) {
            Logger.getLogger(AlarmAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editModel;
    }
    //edit an alarm
    public String editAlarm(){
        try {
           AlarmAdmin port = service.getAlarmAdminPort();
           int alarmId=BreakAdminBean.editAlarm.getAlarmId();
           List<Break> blist=(List<Break>)editModel.getWrappedData();
            Iterator<Break> bint=blist.iterator();
            while(bint.hasNext()){
                Break b=bint.next();
                 if(b.isBreakChecked()){
                     int id=b.getId();
                     port.updateAlarm(alarmId, id, BreakAdminBean.editAlarm.getAlarmContent());
                 }
               }
        } catch (Exception ex) {
           ex.getMessage();
        }
       return "listAllBreaks";
   }
    ////get all alarms no linked with breaks
    public DataModel getNoLnkModel() {

        try { // Call Web Service Operation
           AlarmAdmin port = service.getAlarmAdminPort();
           List<Alarm> result = port.getNoLnkAlarm();
           noLnkModel=new ListDataModel();
           noLnkModel.setWrappedData(result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return noLnkModel;
    }
    public String deleteNoLnkAlarm(){
       
        try { // delete
            AlarmAdmin port = service.getAlarmAdminPort();
            delAlarm=(Alarm)noLnkModel.getRowData();
            port.deleteAlarm(delAlarm.getAlarmId());
        } catch (Exception ex) {
            ex.getMessage();
        }
        return null;
    }
    public String goToEdit(){
        editAlarm=(Alarm)noLnkModel.getRowData();
        return "editNotLnkAlarm";
    }
    public String editNoLnkAlarm(){

        try { // edit
            AlarmAdmin port = service.getAlarmAdminPort();
             List<Break> blist=(List<Break>)allModel.getWrappedData();
              Iterator<Break> bint=blist.iterator();
              while(bint.hasNext()){
                Break b=bint.next();
                 if(b.isBreakChecked()){
                     breakid=b.getId();
                      port.updateAlarm(editAlarm.getAlarmId(), breakid, editAlarm.getAlarmContent());
              }
            }
            if(breakid==0){
                port.updateAlarm(editAlarm.getAlarmId(), breakid, content);
            }

        } catch (Exception ex) {
           ex.getMessage();
        }
        return "listAllBreaks";
    }


}

