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

package fr.unice.i3s.modalis.jSeduite.technical.accounts;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** This web service deals with jSeduite users account management
 * @author mosser
 */
@WebService()
public class AccountManager {

    /** Check the user right to connect to jSeduite
     * @param login
     * @param password
     * @return true if (login,password) is authorized, false elsewhere
     */
    @WebMethod(operationName = "isAuthorized")
    public boolean isAuthorized(@WebParam(name = "login") String login,
            @WebParam(name = "password") String password) {
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT `password` FROM `accounts` WHERE `login` = \"" +
                login + "\";";
        try {
            String data = dal.extractScalar(sql, "password");
            return data.equals(password);
        }catch(Exception e){ return false; }
    }

}
