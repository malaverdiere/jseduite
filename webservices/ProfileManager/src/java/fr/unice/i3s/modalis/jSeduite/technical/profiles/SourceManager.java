/**
 * This file is part of jSeduite::ProfileManager
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::ProfileManager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::ProfileManager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::ProfileManager; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import java.util.Hashtable;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;
import java.util.ArrayList;


/** the source manager service helps source to determine how they should be called
 * @author mosser
 */
@WebService()
public class SourceManager {

    /** Should a source be called for a given device ?
     * @param device the device whose asking
     * @param source a source of information
     * @return true of the device should call the source, false elsewhere
     */
    @WebMethod(operationName = "shouldCall")
    public boolean shouldCall(@WebParam(name="device")String device,
            @WebParam(name="source") String source) {
        int identifiers = getParameterSetsIdentifiers(source, device);
        return identifiers > 0;
    }

    /** Build the 'parametr space' for a given source and a given device.
     * @remarks what an ugly code ... do not reproduce such a crap at home ;)
     * @param device the device whose asking
     * @param source  a source of information
     * @return the expected parameterspace
     */
    @WebMethod(operationName = "getParameterSpace")
    public ParameterSpace getParameterSpace(@WebParam(name="device")String device,
            @WebParam(name="source") String source) {
        int maxId = getParameterSetsIdentifiers(source, device);
        if (maxId <= 0)
            return new ParameterSpace(source);

        // Building parameters name - default value binding
        String sql = "SELECT `name` AS `k`, `default_value` AS `v`, `id`";
        sql += " FROM `parameters` WHERE `source` = '"+source+"' ;";
        DataAccessLayer dal = new DataAccessLayer();
        Hashtable<String,String> paramDefault = new Hashtable<String, String>();
        Hashtable<String,String> paramIds =  new Hashtable<String, String>();
        try {
            DalResultSet rset = dal.extractDataSet(sql);

            for(int i = 0; i < rset.size(); i++) {
                paramDefault.put(rset.getValue("k"), rset.getValue("v"));
                paramIds.put(rset.getValue("k"), rset.getValue("id"));
                rset.next();
            }
        } catch(Exception e) {
            System.err.println(e);
            return new ParameterSpace(source);
        }

        // Building each parameter space
        ArrayList<ParameterCallSet> calls = new ArrayList<ParameterCallSet>();
        for(int i = 0; i < maxId; i++) {
            ParameterCallSet aCall = new ParameterCallSet();
            ArrayList<ParameterValue> values = new ArrayList<ParameterValue>();
            for(String name: paramIds.keySet()){
                ParameterValue aValue = new ParameterValue();
                aValue.setName(name);
                sql = "SELECT `value` AS `v` FROM `device_parametrization`";
                sql += " WHERE `param_id` = "+  paramIds.get(name) +" AND ";
                sql += " `set_id` = " + (i+1) + ";";
                try {
                    aValue.setValue(dal.extractScalar(sql,"v"));
                }catch (Exception e){
                    aValue.setValue(paramDefault.get(name));
                }
                values.add(aValue);
            }
            aCall.setValues(values.toArray(new ParameterValue[values.size()]));
            calls.add(aCall);
        }
        
        // Building the parameter space
        ParameterSpace p = new  ParameterSpace(source);
        p.setSets(calls.toArray(new ParameterCallSet[calls.size()]));
        return p; 
    }


    /** Code helpers **/

    private int getParameterSetsIdentifiers(String source, String device) {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT `parameter_sets` AS `c` from `device_subscription` ";
        sql += "WHERE `device` = '"+device+"' AND ";
        sql += "`source` = '"+ source+"';";
        try {
            return Integer.parseInt(dal.extractScalar(sql, "c"));
        } catch (Exception e) {
            return -1;
        }
    }

}
