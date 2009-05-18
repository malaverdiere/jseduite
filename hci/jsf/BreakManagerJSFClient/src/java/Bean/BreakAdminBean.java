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


import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManager;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManagerService;
import javax.faces.model.DataModel;
import  cn.m1.hebut.jseduite.technical.breaktime.admin.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;
import cn.m1.hebut.jseduite.technical.alarms.admin.*;
/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class BreakAdminBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/AlarmAdminService/localhost_8080/jseduite/AlarmManager/AlarmAdminService.wsdl")
    private AlarmAdminService service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakTimeManagerService/localhost_8080/BreakManager/BreakTimeManagerService.wsdl")
    private BreakTimeManagerService service_1;
    BreakManagerAdminService service = new BreakManagerAdminService();
     DataModel allBreakModel,alarmModel;
     ////////the properties for adding a break
      List<String> promo;
      String start;
      String end;
      String kind;
      String day;
      private List<SelectItem> plist=null;
      private List<SelectItem> plistbybreak;//show the promo list corresponding to a break
      private BreakTimeForAdmin delBreak,editBreak;//for deleting and editing break
      private Alarm delAlarm;
            static Alarm editAlarm;
    //getter and setter
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getPromo() {
        return promo;
    }

    public void setPromo(List<String> promo) {
        this.promo = promo;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public BreakTimeForAdmin getDelBreak() {
        if(delBreak==null)
        {
            delBreak=new BreakTimeForAdmin();
        }
        return delBreak;
    }

    public BreakTimeForAdmin getEditBreak() {
        if(editBreak==null)
        {
            editBreak=new BreakTimeForAdmin();
        }
        return editBreak;
    }
       public Alarm getDelAlarm() {
         if(delAlarm==null){
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

   // get all the promos from promos table
    public List<SelectItem> getPlist() {
        plist=new LinkedList<SelectItem>();
        try {
           BreakTimeManager port = service_1.getBreakTimeManagerPort();
            // TODO process result here
           List<java.lang.Object> result = port.getAllPromos();
            Iterator i=result.iterator();
            while(i.hasNext())
            {
                String p=(String) i.next();
                SelectItem si=new SelectItem(p,p);
                plist.add(si);

            }
        } catch (Exception ex) {
           ex.getMessage();
        }

        return plist;
    }
      //get a list of promo by a selected break
    public List<SelectItem> getPlistbybreak() {
        plistbybreak=new LinkedList<SelectItem>();
        BreakTimeForAdmin show=new BreakTimeForAdmin();
        show=(BreakTimeForAdmin)allBreakModel.getRowData();
        try {
            BreakManagerAdmin port = service.getBreakManagerAdminPort();
            List<java.lang.Object> result = port.getPromosByBreak(show.getId());
            Iterator i=result.iterator();
            while(i.hasNext())
            {
                String p=(String) i.next();
                SelectItem si=new SelectItem(p,p);
                plistbybreak.add(si);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        return plistbybreak;
    }


    //create a new break
    public String addNewBreak(){
      try { // Call Web Service Operation
            BreakManagerAdmin port = service.getBreakManagerAdminPort();
            if(promo.size()==0)
            {
                port.create(null, start, end, kind, day);
            }
            for(int i=0;i<promo.size();i++)
            {
                String p=promo.get(i);
               port.create(p, start, end, kind, day);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }


        return "listAllBreaks";
    }
    /////delete a break
    public void deleteBreak(){

        try { // delete
           BreakManagerAdmin port = service.getBreakManagerAdminPort();
           delBreak=(BreakTimeForAdmin)allBreakModel.getRowData();
            port.delete(delBreak.getId());
        } catch (Exception ex) {
            ex.getMessage();
        }

    }
   ////edit break
    public String getBreakForEdit(){
        editBreak=(BreakTimeForAdmin)allBreakModel.getRowData();
        return "edit";
    }
    public String editABreak(){
         try { // edit a break
           BreakManagerAdmin port = service.getBreakManagerAdminPort();
           port.deleteAllLnkByBreak(editBreak.getId());
           if(promo.size()==0)
           {
               port.update(editBreak.getId(), null, editBreak.getBreakPromoLnk());
           }
           for(int i=0;i<promo.size();i++)
           {
            port.update(editBreak.getId(), promo.get(i), editBreak.getBreakPromoLnk());
           }
        } catch (Exception ex) {
            ex.getMessage();
        }
          return "listAllBreaks";
    }
   /////get all the breaks in the break_time table
    public DataModel getAllBreakModel() {
        try { // Call Web Service Operation
           BreakManagerAdmin port = service.getBreakManagerAdminPort();
           List<BreakTimeForAdmin> result = port.getAllBreaks();
            allBreakModel=new ListDataModel();
            allBreakModel.setWrappedData(result);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return allBreakModel;
    }

    public DataModel getAlarmModel() {
        try { 
              AlarmAdmin port = service_2.getAlarmAdminPort();
              BreakTimeForAdmin b=new BreakTimeForAdmin();
              b=(BreakTimeForAdmin)allBreakModel.getRowData();
              List<Alarm> result = port.readAlarm(b.getId());
              alarmModel=new ListDataModel();
             alarmModel.setWrappedData(result);
        } catch (Exception ex) {
           ex.getMessage();
        }
        return alarmModel;
    }
    ///delete an alarm I have to do it in BreakAdmin
   public String deleteAlarm(){
        try {
            AlarmAdmin port = service_2.getAlarmAdminPort();
             delAlarm=(Alarm)alarmModel.getRowData();
              port.deleteAlarm(delAlarm.getAlarmId());
        } catch (Exception ex) {
           ex.getMessage();
        }
          return null;
      }
    ///go to edit an alarm
    public String goToEdit(){
        editAlarm=(Alarm)alarmModel.getRowData();
        return "editAlarm";
    }
}
