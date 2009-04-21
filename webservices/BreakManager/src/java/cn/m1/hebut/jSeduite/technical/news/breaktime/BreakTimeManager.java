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
 * @author      Main Zhao Yichen  Qin Zhaobo         [yichenzhao18@gmail.com&Bienvenueqin@gmail.com]
 **/
package cn.m1.hebut.jSeduite.technical.news.breaktime;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Administrator
 */
@WebService()
public class BreakTimeManager {

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "getAllBreakTime")
    public BreakTime[] GetAllBreakTime() throws BreakTimeException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT`promos`.`code` AS `p_code`, `promos`.`name` AS `promo`, `break_time`.`start` AS  `start_time`, `break_time`.`end` AS `end_time`,`break_time`.`kind` AS `break_type`,`day`" +
                "FROM`break_time`, `promos`,`break_time_days_lnk`" +
                " WHERE" +
                " `break_time_days_lnk`.`break_id`=`break_time`.`break_id` AND" +
                "`promos`.`promos_id`=`break_time`.`promo`;";
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
     * Web 服务操作
     */
    @WebMethod(operationName = "getBreakTimeForToday")
    public BreakTime[] GetBreakNewsForToday() throws BreakTimeException {
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

    /**
     * Web 服务操作
     */
    @WebMethod(operationName = "getBreakTime")
    public BreakTime[] getBreakTime(@WebParam(name = "day")
   String day) throws BreakTimeException {
             DataAccessLayer dal = new DataAccessLayer();
      String sql = "SELECT`promos`.`code` AS `p_code`, `promos`.`name` AS `promo`, `break_time`.`start` AS  `start_time`, `break_time`.`end` AS `end_time`,`break_time`.`kind` AS `break_type`,`day`" +
                "FROM`break_time`, `promos`,`break_time_days_lnk`" +
                " WHERE" +
                " `break_time_days_lnk`.`break_id`=`break_time`.`break_id` AND" +
                "`promos`.`promos_id`=`break_time`.`promo` AND ";
         sql+= "day='"+day+"';";
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

