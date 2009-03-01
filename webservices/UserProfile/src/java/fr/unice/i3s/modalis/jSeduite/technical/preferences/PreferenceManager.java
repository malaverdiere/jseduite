/**
 * This file is part of jSeduite::UserProfile
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::UserProfile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::UserProfile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::UserProfile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Sébastien Mosser          [mosser@polytech.unice.fr]
 *
 **/
package fr.unice.i3s.modalis.jSeduite.technical.preferences;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author mosser
 */
@WebService()
public class PreferenceManager {

    /** Extract all the call we have to do from the database
     * @param service
     * @param operation
     * @return
     */
    @WebMethod(operationName = "getCallIdentifiers")
    public int[] getCallIdentifiers(@WebParam(name = "service") String service,
            @WebParam(name = "operation") String operation,
            @WebParam(name = "login") String login) {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT DISTINCT `callId` FROM `settings` WHERE ";
        sql += "`service` = \"" + service + "\" AND ";
        sql += "`operation` = \"" + operation + "\" AND ";
        sql += "`login` = \"" + login + "\";";
        try {
            String[] data = dal.extractScalarSet(sql, "callId");
            int[] result = new int[data.length];
            for(int i = 0; i < data.length; i++)
                result[i] = Integer.parseInt(data[i]);
            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    /** Extract parameter's value of a given service::operation call
     * @param service
     * @param operation
     * @param call
     * @return
     */
    @WebMethod(operationName = "getParameterValue")
    public String getParameterValue(@WebParam(name = "service") String service,
            @WebParam(name = "operation") String operation,
            @WebParam(name = "login") String login,
            @WebParam(name = "param") String param,
            @WebParam(name = "call") String call) {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT `value` FROM `settings` WHERE ";
        sql += "`service` = \"" + service + "\" AND ";
        sql += "`operation` = \"" + operation + "\" AND ";
        sql += "`login` = \"" + login + "\" AND ";
        sql += "`parameter` = \""+ param + "\" AND ";
        sql += "`callId` = " + call + ";";
        try {
            return dal.extractScalar(sql, "value");
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
