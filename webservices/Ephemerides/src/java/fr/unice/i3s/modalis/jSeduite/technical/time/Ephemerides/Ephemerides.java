/**
 * This file is part of jSeduite::util
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::util is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::util; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @contributor 2010     Main Christophe Desclaux    [desclaux@polytech.unice.fr]
 **/

package fr.unice.i3s.modalis.jSeduite.technical.time.Ephemerides;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;



@WebService()
public class Ephemerides {

    @WebMethod(operationName = "getEphemerides")
    public NamesOfTheDay getEphemerides(@WebParam(name="day") int day,
            @WebParam(name="month") int month) throws EphemeridesException{
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT * FROM `ephemerides` WHERE day = '"+day+"'";
        sql += " AND month = '"+month+"';";
        try {
            String[] names = dal.extractScalarSet(sql, "name");
            return new NamesOfTheDay(names, month, day);
        } catch(Exception e) {
            throw new EphemeridesException(e.getMessage());
        }
    }
}