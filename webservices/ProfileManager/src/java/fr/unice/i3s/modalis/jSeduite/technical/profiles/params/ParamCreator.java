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

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class ParamCreator {
    @WebMethod(operationName="subscription")
    public void subscription(@WebParam(name = "device") String device,
                                @WebParam(name = "source") String source,
                                @WebParam(name = "sets") int sets)
                                 throws ParamException {

        if(sets > 0) {
            DataAccessLayer dal = new DataAccessLayer();
            try {

                String sql = "INSERT INTO `device_subscription`";
                sql += "(`device`, `source`, `parameter_sets`) ";
                sql += "VALUES (";
                sql += "'"+device+"','"+source+"','"+sets+"');";

                dal.executeVoid(sql);

            } catch (Exception e) {
                throw new ParamException("SQL Exception: " + e.getMessage());
            }
        }
    }

    @WebMethod(operationName="parametrization")
    public void parametrization(@WebParam(name = "device") String device,
                                @WebParam(name = "param") Param param,
                                @WebParam(name = "set_id") int setId)
                                 throws ParamException {

        if(!param.getValue().equals("") && !param.getValue().equals(param.getDefaultValue())) {
            DataAccessLayer dal = new DataAccessLayer();

            try {
                String sql = "INSERT INTO `device_parametrization`";
                sql += "(`device_id`, `param_id`, `set_id`, `value`) ";
                sql += "VALUES ('"+device+"','"+param.getId()+"','";
                sql += setId+"','"+param.getValue()+"');";

                dal.executeVoid(sql);

            } catch (Exception e) {
                throw new ParamException("SQL Exception: " + e.getMessage());
            }
        }
    }
}
