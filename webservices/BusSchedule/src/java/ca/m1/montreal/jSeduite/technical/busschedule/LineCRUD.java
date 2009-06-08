/**
 * This file is part of jSeduite::BusScheduleBusiness
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::BusScheduleBusiness is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::BusScheduleBusiness is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::BusScheduleBusiness; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Vincent Bonmalais          [vb.kouno@gmail.com]
 * @author      Main     Yannick Tahora             [ytahora@gmail.com]
 *
 **/


package ca.m1.montreal.jSeduite.technical.busschedule;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A web service to manipulate lines
 *
 * @author vincent bonmalais
 * @author yannick tahora
 * 
 */
@WebService()
public class LineCRUD {

    private BusScheduleFinder finder = BusScheduleFinder.getInstance();


    /**
     * Web service operation
     */
    @WebMethod(operationName = "createLine")
    public int createLine(@WebParam(name = "line")
    Line line) {
        //TODO write your implementation code here:
        return 0;
    }
   
    
}
