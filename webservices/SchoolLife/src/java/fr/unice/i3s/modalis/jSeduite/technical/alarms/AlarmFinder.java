/**
 * This file is part of jSeduite::SchoolLife
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::SchoolLife is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::SchoolLife is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Steve Colombié         [colombie@polytech.unice.fr]
 **/
package fr.unice.i3s.modalis.jSeduite.technical.alarms;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import fr.unice.i3s.modalis.jSeduite.technical.breaktime.BreakTime;
import fr.unice.i3s.modalis.jSeduite.technical.breaktime.BreakTimeFinder;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class AlarmFinder {
    /**
     * FindById operation from the Finder pattern
     * @param id the element id you're looking for
     * @return null if no persistent object, such an object if exists
     */
    @WebMethod(operationName = "findAlarmById")
    public Alarm findAlarmById(@WebParam(name = "id") int id)
            throws AlarmException {

        BreakTimeFinder breakTimeFinder = new BreakTimeFinder();

        String sql = "SELECT * FROM `alarms`";
        sql += "WHERE `id` = '" + id + "';";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            if (rSet.size() == 0) {
                return null;
            }

            int breakId = Integer.parseInt(rSet.getValue("break_id"));
            BreakTime breakTime = breakTimeFinder.findBreakTimeById(breakId);

            return new Alarm(rSet, breakTime);

        } catch(Exception e) {
            throw new AlarmException("SQL Exception: " + e.getMessage());
        }
    }


    /** Extract the alarms corresponding to the given break time id
     * @return The corresponding alarms
     * @throws AlarmException
     */
    @WebMethod(operationName = "getAlarmsByBreakTimeId")
    public Alarm[] getAlarmsByBreakTimeId(@WebParam(name = "id") int id)
            throws AlarmException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `alarms` ";
        sql += "WHERE `break_id` = '"+id+"';";
        try {
            ArrayList<Alarm> result = new ArrayList<Alarm>();
            DalResultSet rset = dal.extractDataSet(sql);
             for (int i = 0; i < rset.size(); i++ ) {
                result.add(findAlarmById(Integer.parseInt(rset.getValue("id"))));
                rset.next();
            }
            return result.toArray(new Alarm[result.size()]);
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }
    
    /** Extract all the alarm ids
     * @return The alarm IDs
     * @throws AlarmException
     */
    @WebMethod(operationName = "getAllAlarmIds")
    public Integer[] getAllAlarmIds()
            throws AlarmException {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT id FROM `alarms`; ";

        try {
            ArrayList<Integer> result = new ArrayList<Integer>();
            DalResultSet rset = dal.extractDataSet(sql);
             for (int i = 0; i < rset.size(); i++ ) {
                result.add(Integer.parseInt(rset.getValue("id")));
                rset.next();
            }
            return result.toArray(new Integer[result.size()]);
        } catch (Exception e) {
            throw new AlarmException(e.getMessage());
        }
    }


}
