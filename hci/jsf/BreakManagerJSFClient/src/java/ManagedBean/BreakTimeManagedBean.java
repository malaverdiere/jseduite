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
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *  @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/

package ManagedBean;



import cn.m1.hebut.jseduite.technical.breaktime.BreakTime;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManagerService;
import cn.m1.hebut.jseduite.technical.breaktime.admin.BreakManagerAdminService;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *  @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 */

public class BreakTimeManagedBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakTimeManagerService/localhost_8080/BreakManager/BreakTimeManagerService.wsdl")
    private BreakTimeManagerService service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakManagerAdminService/localhost_8080/BreakManager/BreakManagerAdminService.wsdl")
    private BreakManagerAdminService service;
    private DataModel model;
    private BreakTime editItem,delItem;
    

    /** Creates a new instance of BreakTimeManagedBean */
    public BreakTimeManagedBean() {
           
    }
      private String day;
      private String promo;
      private String start;
      private String end;
      private String kind;
      private int id;
      private List<SelectItem> plist=null;
     
    
   
    public int getId() {
        return id;
    }
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
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
    
    public List<SelectItem> getPlist() {

       plist=new LinkedList<SelectItem>();
        try { // Call Web Service Operation
            cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManager port = service_1.getBreakTimeManagerPort();
            // TODO process result here
            java.util.List result = port.getAllPromos();
            Iterator i=result.iterator();
            while(i.hasNext())
            {
            
                String p=(String) i.next();
                SelectItem si=new SelectItem(Integer.parseInt(p),p);
                plist.add(si);
              
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return plist;
    }

    
     public String insert(){
         try { // Call Web Service Operation
             cn.m1.hebut.jseduite.technical.breaktime.admin.BreakManagerAdmin port
                     = service.getBreakManagerAdminPort();
             port.create(promo, start, end, kind, day);
         } catch (Exception ex) {
           ex.getMessage();
         }
        return "listAllBreak";


     }
     public String edit(){
         editItem=(BreakTime)model.getRowData();

             return "edit";

     }
    public DataModel getModel() {

        try { // Call Web Service Operation
            cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManager port = service_1.getBreakTimeManagerPort();
            // TODO process result here
            java.util.List<cn.m1.hebut.jseduite.technical.breaktime.BreakTime> list = port.getAllBreak();
           model = new ListDataModel();
            model.setWrappedData(list);
        } catch (Exception ex) {
            ex.getMessage();
        }
return model;
      
    }

    public BreakTime getEditItem() {
     if(editItem==null){
     editItem=new BreakTime();

     }
        return editItem;
    }
    public String update(){

        try { // Call Web Service Operation
            cn.m1.hebut.jseduite.technical.breaktime.admin.BreakManagerAdmin port = service.getBreakManagerAdminPort();
            port.update(promo, start, end, kind,day,editItem.getId());
        } catch (Exception ex) {
            ex.getMessage();
        }
    return "listAllBreak";
    }
    public BreakTime getDelItem() {
        if(delItem==null){
        delItem=new BreakTime();
        }
        return delItem;
    }
    public void delete(){
        try { // Call Web Service Operation
            cn.m1.hebut.jseduite.technical.breaktime.admin.BreakManagerAdmin port = service.getBreakManagerAdminPort();
            // TODO initialize WS operation arguments here
           delItem=(BreakTime)model.getRowData();
            port.delete(delItem.getId());
        } catch (Exception ex) {
        ex.getMessage();
        }
    }
}
