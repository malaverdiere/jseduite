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
 * @author      Main Steve Colombié          [colombie@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.profiles.params;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class ParamFinder {
    @WebMethod(operationName="getParameters")
    public Param[] getParameters(@WebParam(name = "source") String source,
                                 @WebParam(name = "device") String device)
                                 throws ParamException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `parameters` AS p, ";
            sql += "`device_parametrization` AS d ";
            sql += "WHERE p.`id` = d.`param_id` ";
            sql += "AND d.`device_id` = '"+device+"' ";
            sql += "AND p.`source` = '"+source+"';";

            ArrayList<Param> result = new ArrayList<Param>();
            DalResultSet rset = dal.extractDataSet(sql);

            for (int i = 0; i < rset.size(); i++ ) {
                result.add(new Param(rset));
                rset.next();
            }
            return result.toArray(new Param[result.size()]);

        } catch (Exception e) {
            throw new ParamException("SQL Exception: " + e.getMessage());
        }
    }

    @WebMethod(operationName="getParametersForSource")
    public Param[] getParametersForSource(@WebParam(name = "source") String source)
            throws ParamException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT * FROM `parameters` ";
            sql += "WHERE `source` = '"+source+"';";

            ArrayList<Param> result = new ArrayList<Param>();
            DalResultSet rset = dal.extractDataSet(sql);

            for (int i = 0; i < rset.size(); i++ ) {
                result.add(new Param(rset.getValue("name"), rset.getValue("default_value"), rset.getValue("pretty_name"), Integer.parseInt(rset.getValue("id"))));
                rset.next();
            }
            return result.toArray(new Param[result.size()]);

        } catch (Exception e) {
            throw new ParamException("SQL Exception: " + e.getMessage());
        }
    }

    @WebMethod(operationName="getParameterCard")
    public int getParameterCard(@WebParam(name = "source") String source,
                                 @WebParam(name = "device") String device)
                                 throws ParamException {
        DataAccessLayer dal = new DataAccessLayer();
        try {
            String sql = "SELECT `parameter_sets` FROM `device_subscription` ";
            sql += "WHERE `device` = '"+device+"' ";
            sql += "AND `source` = '"+source+"';";

           DalResultSet rset = dal.extractDataSet(sql);

           if(rset.size() == 0) {
               return 0;
           }

            return Integer.parseInt(rset.getValue("parameter_sets"));

        } catch (Exception e) {
            throw new ParamException("SQL Exception: " + e.getMessage());
        }
    }
}
