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
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeException_Exception;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManager;
import cn.m1.hebut.jseduite.technical.breaktime.BreakTimeManagerService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 */

public class BreakManagedBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/BreakTimeManagerService/localhost_8080/BreakManager/BreakTimeManagerService.wsdl")
    private BreakTimeManagerService service;
    private DataModel model,tmodel;//the model for getting the break according to the day you have input and for today
    private String day;
    private List<BreakTime> list;
    private BreakTimeManager port;


    public BreakManagedBean() {
       }

    public DataModel getModel() {
        try {
            port = service.getBreakTimeManagerPort();
            list = port.getBreakTime(day);
            model = new ListDataModel();
            model.setWrappedData(list);

        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
          return model;
    }

    public DataModel getTmodel() {
        
             try {
            port = service.getBreakTimeManagerPort();
            list = port.getBreakTimeForToday();
            tmodel = new ListDataModel();
            tmodel.setWrappedData(list);

        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
          return tmodel;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


   


   
   

}
