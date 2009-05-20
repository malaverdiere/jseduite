/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::AlarmManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::AlarmManager is distributed in the hope that it will be useful,
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
package cn.m1.hebut.jSeduite.technical.alarms.admin;

import cn.m1.hebut.jSeduite.technical.alarms.Alarm;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DALException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
@WebService()
public class AlarmAdmin {
    
    
     /**
     * create a new alarm
     * @param breakId,content
     * @return void
     */

   @WebMethod(operationName = "createAlarm")
    public void createAlarm(@WebParam(name = "breakId")
    int breakId, @WebParam(name = "content")
    String content,@WebParam(name = "position")
    String position) throws AlarmsAdminException, DALException{
         DataAccessLayer dal = new DataAccessLayer();
         String sql="select * from alarms where content='"+content+"';";
         DalResultSet rset=dal.extractDataSet(sql);
         if(rset.size()==0){
         sql="insert into `alarms` values(NULL,'"+content+"','"+position+"');";
         dal.executeVoid(sql);}
         sql="select max(alarm_id) as max from alarms;";
         rset=dal.extractDataSet(sql);
         int alarmid=Integer.parseInt(rset.getValue("max"));
         if(breakId>0){
         sql="insert into break_alarm_lnk values('"+breakId+"','"+alarmid+"');";
         dal.executeVoid(sql);
         }
    }
    /**
     * delete an alarm
     * @param alarmId
     * @return void
     */
    @WebMethod(operationName = "deleteAlarm")
    public void deleteAlarm(@WebParam(name = "alarmId")
    int alarmId) throws DALException {
          DataAccessLayer dal=new DataAccessLayer();
            String sql="delete from alarms where alarm_id='"+alarmId+"';";
            dal.executeVoid(sql);
            sql="delete from break_alarm_lnk where binding_alarm='"+alarmId+"';";
            dal.executeVoid(sql);
    }
       /**
         * update an alam
         * @param breakid,Content
         * @return void
         */
      @WebMethod(operationName = "deleteAlarmLnk")
      public void deleteAlarmLnk(@WebParam(name = "alarmId")
       int alarmId) throws DALException{
           DataAccessLayer dal=new DataAccessLayer();
         String sql="delete from break_alarm_lnk where binding_alarm='"+alarmId+"';";
         dal.executeVoid(sql);
      }
       @WebMethod(operationName = "updateAlarms")
       public void updateAlarms(@WebParam(name = "alarmId")
       int alarmId, @WebParam(name = "content")
       String content,@WebParam(name = "position")
       String position,@WebParam(name = "breakId")
       int breakId) throws DALException {
           DataAccessLayer dal=new DataAccessLayer();
         String sql="update alarms set content='"+content+ "',position='"+
                 position+"'where `alarm_id`='"+alarmId+"';";
         dal.executeVoid(sql);
         if(breakId!=0){
             sql="insert into break_alarm_lnk values('"+breakId+"','"+alarmId+"');";
             dal.executeVoid(sql);
         }

    }

    /**
     * get the alarms by break
     * @param breakId
     * @return a list of alarms
     */
    @WebMethod(operationName = "readAlarm")
    public List<Alarm> readAlarm(int breakId) throws  AlarmsAdminException {
           DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT binding_alarm from break_alarm_lnk Where break='"+breakId+"';";
        try {
            DalResultSet rset = dal.extractDataSet(sql);
             ArrayList<Alarm> result = new ArrayList<Alarm>();
            if(rset.size()==0) return null;
             for(int i=0;i<rset.size();i++){
                 int alarmid=Integer.parseInt(rset.getValue("binding_alarm"));
                 sql="select * from alarms where alarm_id='"+alarmid+"';";
                 DalResultSet rsetAlarm = dal.extractDataSet(sql);
                 result.add(new Alarm(rsetAlarm));
                 rset.next();
             }
            return result;

        } catch(Exception e) {
           throw new AlarmsAdminException(e.getMessage());
        }
    }

    /**
     * get all the alarms
     * @return a list of alarms
     */
    @WebMethod(operationName = "readAllAlarms")
    public List<Alarm> readAllAlarms() throws DALException {
        DataAccessLayer dal=new DataAccessLayer();
        String sql="select * from alarms;";
         DalResultSet rset=dal.extractDataSet(sql);
          List<Alarm> list=new ArrayList<Alarm>();
           for(int i=0;i<rset.size();i++){
            list.add(new Alarm(rset));
            rset.next();
        }
        return list;
    }
    
}
