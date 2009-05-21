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
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakTimeManagerService/localhost_8080/BreakManager/BreakTimeManagerService.wsdl")
    private BreakTimeManagerService service_1;
    BreakManagerAdminService service = new BreakManagerAdminService();
     DataModel allBreakModel,promoModel;
     ////////the properties for adding a break
      List<String> promo;
      String start;
      String end;
      String kind;
      String day;
      private List<SelectItem> plist=null;
      private List<SelectItem> plistbybreak;//show the promo list corresponding to a break
      private BreakTime delBreak,editBreak;//for deleting and editing break
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

    public BreakTime getDelBreak() {
        if(delBreak==null)
        {
            delBreak=new BreakTime();
        }
        return delBreak;
    }

    public BreakTime getEditBreak() {
        if(editBreak==null)
        {
            editBreak=new BreakTime();
        }
        return editBreak;
    }

   // get all the promos from promos table
    public List<SelectItem> getPlist() {
        plist=new LinkedList<SelectItem>();
        try {
           BreakTimeManager port = service_1.getBreakTimeManagerPort();
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
    public DataModel getPromoModel() {

        return promoModel;
    }


    public List<SelectItem> getPlistbybreak() {
        plistbybreak=new LinkedList<SelectItem>();
        BreakTime show=new BreakTime();
        show=(BreakTime)allBreakModel.getRowData();
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
               port.create(promo, start, end, kind, day);
        } catch (Exception ex) {
          ex.getMessage();
        }


        return "listAllBreaks";
    }
    /////delete a break
    public void deleteBreak(){

        try { // delete
           BreakManagerAdmin port = service.getBreakManagerAdminPort();
           delBreak=(BreakTime)allBreakModel.getRowData();
            port.delete(delBreak.getId());
        } catch (Exception ex) {
            ex.getMessage();
        }

    }
   ////edit break
    public String getBreakForEdit(){
        editBreak=(BreakTime)allBreakModel.getRowData();
        return "edit";
    }

    public String editABreak(){
         try { // edit a break
           BreakManagerAdmin port = service.getBreakManagerAdminPort();
           port.deleteAllLnkByBreak(editBreak.getId());
           if(promo.size()==0)
           {
               port.update(editBreak.getId(),null, editBreak.getStart(),
                       editBreak.getEnd(), editBreak.getKind(), editBreak.getDay());
           }
            port.update(editBreak.getId(), promo,editBreak.getStart(),
                       editBreak.getEnd(), editBreak.getKind(), editBreak.getDay());
        } catch (Exception ex) {
            ex.getMessage();
        }
          return "listAllBreaks";
    }
   /////get all the breaks in the break_time table
    public DataModel getAllBreakModel() {

        try { // Call Web Service Operation
            BreakManagerAdmin port = service.getBreakManagerAdminPort();
            List<BreakTime> result = port.getAllBreaks();
             allBreakModel=new ListDataModel();
             allBreakModel.setWrappedData(result);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return allBreakModel;
    }

}
