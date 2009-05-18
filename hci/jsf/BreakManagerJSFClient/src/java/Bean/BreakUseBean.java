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


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.DataModel;
import cn.m1.hebut.jseduite.technical.breaktime.*;
import java.util.List;
import javax.faces.model.ListDataModel;
import javax.xml.ws.WebServiceRef;
import cn.m1.hebut.jseduite.technical.alarms.*;
/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
public class BreakUseBean {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/client/AlarmManagerService/localhost_8080/jseduite/AlarmManager/AlarmManagerService.wsdl")
    private AlarmManagerService service_2;
    BreakTimeManagerService service = new BreakTimeManagerService();
    //////get break for today
    DataModel todayModel;
    ///////////get break by day
    String day;
    DataModel dayModel;
    //////get all the breaks
    DataModel allModel;
    /////get breaks by promo
    DataModel promoModel;
    String promo;
    ///alarms list
    DataModel alarmForAll,alarmForToday,alarmByDay,alarmByPromo;



    public DataModel getTodayModel() {

        try { // Call Web Service Operation
            BreakTimeManager port = service.getBreakTimeManagerPort();
            List<BreakTime> result = port.getBreakTimeForToday();
            todayModel=new ListDataModel();
            todayModel.setWrappedData(result);
        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return todayModel;
    }

    public DataModel getAlarmForToday() {
        try { // Call Web Service Operation
            AlarmManager port = service_2.getAlarmManagerPort();
            BreakTime b=new BreakTime();
            b=(BreakTime) todayModel.getRowData();
            List<Alarm> result = port.getAlarmByBinding(b.getId());
           alarmForToday=new ListDataModel();
           alarmForToday.setWrappedData(result);
        } catch (AlarmException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alarmForToday;
    }
   ///getter and setter for day
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
   ///getter of dayModel
    public DataModel getDayModel() {

        try { // get breaks by day you input
            BreakTimeManager port = service.getBreakTimeManagerPort();
             List<BreakTime> result = port.getBreakTimeByDay(day);
            dayModel=new ListDataModel();
            dayModel.setWrappedData(result);
        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dayModel;
    }

    public DataModel getAlarmByDay() {

        try {
            cn.m1.hebut.jseduite.technical.alarms.AlarmManager port = service_2.getAlarmManagerPort();
             BreakTime b=new BreakTime();
             b=(BreakTime) dayModel.getRowData();
            List<Alarm> result = port.getAlarmByBinding(b.getId());
            alarmByDay=new ListDataModel();
            alarmByDay.setWrappedData(result);
        } catch (AlarmException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alarmByDay;
    }

  ///////////getter for allModel
    public DataModel getAllModel() {

        try { // get all the breaks
           BreakTimeManager port = service.getBreakTimeManagerPort();
            List<BreakTime> result = port.getAllBreakTime();
           allModel=new ListDataModel();
           allModel.setWrappedData(result);
        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return allModel;
    }

    public DataModel getAlarmForAll() {

        try {
           AlarmManager port = service_2.getAlarmManagerPort();
           BreakTime b=new BreakTime();
           b=(BreakTime) allModel.getRowData();
           List<Alarm> result = port.getAlarmByBinding(b.getId());
           alarmForAll=new ListDataModel();
           alarmForAll.setWrappedData(result);
        } catch (AlarmException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alarmForAll;
    }


    ///////promo for break getting
    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }
    public DataModel getPromoModel() {
        try {
            BreakTimeManager port = service.getBreakTimeManagerPort();
            List<BreakTime> result = port.getBreakByPromo(promo);
            promoModel=new ListDataModel();
            promoModel.setWrappedData(result);
        } catch (BreakTimeException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DALException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return promoModel;
    }

    public DataModel getAlarmByPromo() {
        try {
            AlarmManager port = service_2.getAlarmManagerPort();
            BreakTime b=new BreakTime();
            b=(BreakTime)promoModel.getRowData();
           List<Alarm> result = port.getAlarmByBinding(b.getId());
            alarmByPromo=new ListDataModel();
            alarmByPromo.setWrappedData(result);
        } catch (AlarmException_Exception ex) {
            Logger.getLogger(BreakUseBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return alarmByPromo;
    }



}
