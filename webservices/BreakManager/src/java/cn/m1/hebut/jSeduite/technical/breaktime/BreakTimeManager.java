/**
 * This file is part of jSeduite::BreakTimeManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BreakManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BreakManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::InternalNews; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
   @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author zhao yichen
   @author qin zhaobo      
 */
@WebService()
public class BreakTimeManager {

     /** Extract all breaks for today
      * @return a set of valid breaks for today
     * @throws BreakTimeException
     */
   

    @WebMethod(operationName = "getBreakTimeForToday")
    public BreakTime[] getBreakTimeForToday() throws BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * from `break_time` where day=dayname(curdate()) AND " +
                "break_id in (select break_id from break_time_promos_lnk);";
        try {
            ArrayList<BreakTime> result = new ArrayList<BreakTime>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new BreakTime(rset));
                rset.next();
            }
             return result.toArray(new BreakTime[result.size()]);

        } catch(Exception e) {

      throw new BreakTimeException(e.getMessage());

        }
    }

    /** Extract all breaks for a given day
     * @param day expected weekday
     * @return a set of valid breaks for a given day identified by 'day'
     * @throws BreakTimeException
     */
    @WebMethod(operationName = "getBreakTimeByDay")
    public BreakTime[] getBreakTimeByDay(@WebParam(name = "day")
   String day) throws BreakTimeException {
       DataAccessLayer dal = new DataAccessLayer();
       String sql ="SELECT * from `break_time` where day='"+day+"' AND " +
               "break_id in (select break_id from break_time_promos_lnk);";
        try {
            ArrayList<BreakTime> result = new ArrayList<BreakTime>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new BreakTime(rset));
                rset.next();
            }
             return result.toArray(new BreakTime[result.size()]);

        } catch(Exception e) {
               throw new BreakTimeException(e.getMessage());
        }
    }


  
   
    /**
     * get all the promos from database
     */
    @WebMethod(operationName = "getAllPromos")
    public List getAllPromos() throws DALException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql="select `name` from `promos`;";
         DalResultSet rset=dal.extractDataSet(sql);
         ArrayList promolist=new ArrayList();
         for(int i = 0; i < rset.size(); i++){
             promolist.add(rset.getValue("name"));
              rset.next();
         }
          return promolist;
       
    }

    /**
     * 
     * @return all the breaks
     * @throws BreakTimeException
     */
    @WebMethod(operationName = "getAllBreakTime")
    public BreakTime[] getAllBreakTime() throws BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
       String sql ="SELECT * from `break_time` Where " +
               "break_id in (select break_id from break_time_promos_lnk);";
        try {
            ArrayList<BreakTime> result = new ArrayList<BreakTime>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new BreakTime(rset));
                rset.next();
            }
             return result.toArray(new BreakTime[result.size()]);

        } catch(Exception e) { 
          throw new BreakTimeException(e.getMessage());
        }
    }

    /**
     * Extract all breaks for a given promo
     * @param promo
     * @return a set of valid breaks for a given promo
     * @throws BreakTimeException
     */
    @WebMethod(operationName = "getBreakByPromo")
    public BreakTime[] getBreakByPromo(@WebParam(name = "promo")
    String promo) throws DALException, BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql ="select * from break_time where break_id in" +
                "(select break_id from break_time_promos_lnk where promo_id in" +
                "(select promos_id from promos where name='"+promo+"'));";
        try {
            ArrayList<BreakTime> result = new ArrayList<BreakTime>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new BreakTime(rset));
                rset.next();
            }
             return result.toArray(new BreakTime[result.size()]);

        } catch(Exception e) {
               throw new BreakTimeException(e.getMessage());
        }
        
    }

    /**
     * get all breaks by an alarm
     * @param alarmId
     * @return a list of breaks
     */
    @WebMethod(operationName = "getBreakByAlarm")
    public List<BreakTime> getBreakByAlarm(@WebParam(name = "alarmId")
    int alarmId) throws DALException, Exception {
         DataAccessLayer dal = new DataAccessLayer();
         String sql="select break from break_alarm_lnk where binding_alarm='"+
                 alarmId+"';";
          DalResultSet rset = dal.extractDataSet(sql);
          if(rset.size()==0) return null;
          ArrayList<BreakTime> result = new ArrayList<BreakTime>();
          for(int i=0;i<rset.size();i++){
              int breakId=Integer.parseInt(rset.getValue("break"));
              sql="SELECT `break_time`.`break_id`,`promos`.`name` AS `promo`," +
               " `break_time`.`start` AS  `start`, `break_time`.`end` AS `end`," +
               "`break_time`.`kind` AS `kind`,`break_time`.`day` FROM " +
               "`break_time`, `promos`,`break_time_promos_lnk`";
              sql += "WHERE `break_time`.`break_id` = '"+breakId+"'";
              sql+="AND `break_time_promos_lnk`.`break_id`=`break_time`.`break_id` " +
                "AND `promos`.`promos_id`=`break_time_promos_lnk`.`promo_id`;";
              DalResultSet rset2 = dal.extractDataSet(sql);
              result.add(new BreakTime(rset2));
              rset.next();
          }
         return result;
    }

 
   

   
   


    


}

