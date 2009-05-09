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

package cn.m1.hebut.jSeduite.technical.alarms;

import cn.m1.hebut.jSeduite.technical.alarms.admin.AlarmsAdminException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DALException;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]f
 */
@WebService()
public class AlarmsManager {

    /**
     * get all the alarms from the database
     */
    @WebMethod(operationName = "getAllAlarms")
    public Alarm[] getAllAlarms() throws AlarmException {
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

           throw new AlarmException(e.getMessage());

        }
    }

    /**
     * get an alarm by the binding
     */
    @WebMethod(operationName = "getAlarmByBinding")
    public Alarm getAlarmByBinding(int breakId) throws AlarmException {
       DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * from `alarms` Where binding='"+breakId+"';";
        try {
            DalResultSet rset = dal.extractDataSet(sql);
            if(rset.size()==0) return null;
             return new Alarm(rset);

        } catch(Exception e) {
           throw new AlarmException(e.getMessage());

        }
    }

    /**
     * get all the binding from alarms table
     */
    @WebMethod(operationName = "getAllBinding")
    public List getAllBinding() throws DALException {
         DataAccessLayer dal = new DataAccessLayer();
        String sql="select `binding` from `alarms`;";
         DalResultSet rset=dal.extractDataSet(sql);
         ArrayList list=new ArrayList();
         for(int i = 0; i < rset.size(); i++){
             list.add(Integer.parseInt(rset.getValue("binding")));
              rset.next();
         }
          return list;
    }
    

}
