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
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 *
 **/

package cn.m1.hebut.jSeduite.technical.alarms.admin;

import cn.m1.hebut.jSeduite.technical.alarms.Alarm;
import cn.m1.hebut.jSeduite.technical.alarms.AlarmException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DALException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
@WebService()
public class AlarmsAdmin {

    /**
     * add a new alarm
     */
    @WebMethod(operationName = "createAlarm")
    public void createAlarm(@WebParam(name = "breakId")
    int breakId, @WebParam(name = "content")
    String content) throws DALException {
         DataAccessLayer dal = new DataAccessLayer();
         String sql="insert into `alarms` value(NULL,'"+breakId+"','"+content+"');";
         dal.executeVoid(sql);
    }

    /**
     * get all the alarms
     */
    @WebMethod(operationName = "readAlarm")
    public Alarm[] readAlarm() throws  AlarmsAdminException {
           DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * from `alarms`;";
        try {
            ArrayList<Alarm> result = new ArrayList<Alarm>();
            DalResultSet rset = dal.extractDataSet(sql);
            for(int i = 0; i < rset.size(); i++){
                result.add(new Alarm(rset));
                rset.next();
            }
             return result.toArray(new Alarm[result.size()]);

        } catch(Exception e) {

           throw new AlarmsAdminException(e.getMessage());

        }
    }

    /**
     * update an alarm
     */
    @WebMethod(operationName = "updateAlarms")

    public void updateAlarms(@WebParam(name = "breakId")
    int breakId, @WebParam(name = "content")
    String content) throws DALException {
         DataAccessLayer dal=new DataAccessLayer();
         String sql="update `alarms` set `content`='"+content+
                 "'where `binding`='"+breakId+"';";
         dal.executeVoid(sql);

    }

    /**
     * delete an alarm
     */
    @WebMethod(operationName = "deleteAlarm")
    public void deleteAlarm(@WebParam(name = "breakId")
    int breakId) throws DALException {
          DataAccessLayer dal=new DataAccessLayer();
            String sql="delete from `alarms` ";
            sql+="where `binding`='"+breakId+"';";
            dal.executeVoid(sql);
    }

    /**
     * get all the break id
     */
    @WebMethod(operationName = "getAllBreakId")
    public List getAllBreakId() throws DALException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql="select `break_id` from `break_time`;";
         DalResultSet rset=dal.extractDataSet(sql);
         ArrayList list=new ArrayList();
         for(int i = 0; i < rset.size(); i++){
             list.add(Integer.parseInt(rset.getValue("break_id")));
              rset.next();
         }
          return list;

    }
    
    
}
