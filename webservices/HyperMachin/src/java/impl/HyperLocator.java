/**
 * This file is part of jSeduite::HyperMachin
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::HyperMachin is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::HyperMachin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::HyperMachin; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main   Sébastien Mosser          [mosser@polytech.unice.fr]
 * @contributor [2009] Claudine Peyrat           [claudine@polytech.unice.fr]
 *
 **/

package impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import data.*;

/**
 *
 * @author mosser
 */
@WebService()
public class HyperLocator {

    @WebMethod(operationName = "getAll")
    public HyperEvent[] getAll(@WebParam(name="identifiers") String[] identifiers) {
        
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getByBuilding")
    public String getByBuilding(
            @WebParam(name="identifiers") String[] identifiers, 
            @WebParam(name = "building") String building) {
        //TODO write your implementation code here:
        return null;
    }

}
