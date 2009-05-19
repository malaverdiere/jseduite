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

package cn.m1.hebut.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author      Qin Zhaobo          [Bienvenueqin@gmail.com]
 * @author      Zhao Yichen         [yichenzhao18@gmail.com]
 */
@WebService()
public class AlarmManager {
     /**
     * get alarms by a given break
      * @param  breakId
      * @return  a list of alarms
     */
   @WebMethod(operationName = "getAlarmByBinding")
    public List<Alarm> getAlarmByBinding(int breakId) throws AlarmException {
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
           throw new AlarmException(e.getMessage());
        }
    }

}
