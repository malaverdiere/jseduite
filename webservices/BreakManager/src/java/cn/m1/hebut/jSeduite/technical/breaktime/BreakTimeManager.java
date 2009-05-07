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
   @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;
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
        String sql = "SELECT * from `break_time_today`;";
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
    @WebMethod(operationName = "getBreakTime")
    public BreakTime[] getBreakTime(@WebParam(name = "day")
   String day) throws BreakTimeException {
             DataAccessLayer dal = new DataAccessLayer();
       String sql = "SELECT `break_time`.`break_id` AS `break_id`," +
               "`promos`.`name` AS `promo`,`break_time`.`start` AS  `start`," +
               " `break_time`.`end` AS `end`,`break_time`.`kind` AS `kind`," +
               "`break_time`.`day` FROM `break_time`,`promos` WHERE ";
        sql += " day = '"+day+"'";
        sql+="AND `promos`.`promos_id`=`break_time`.`promo`;";
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

    /** Extract all breaks
     * @return a set of valid breaks
     * @throws BreakTimeException
     */
    @WebMethod(operationName = "getAllBreak")
    public BreakTime[] getAllBreak() throws BreakTimeException {
          DataAccessLayer dal = new DataAccessLayer();
       String sql = "SELECT * FROM `break_time`";
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

}

