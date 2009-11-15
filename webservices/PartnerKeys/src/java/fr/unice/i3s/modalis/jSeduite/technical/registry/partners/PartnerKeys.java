/**
 * This file is part of jSeduite::PartnerKeys
 *
 * Copyright (C) 2008-  Sebastien Mosser
 *
 * jSeduite::PartnerKeys is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jSeduite::PartnerKeys is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite::PartnerKeys; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main     Sebastien Mosser [mosser@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.registry.partners;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/** A web service to store partners keys
 * @author mosser
 */
@WebService()
public class PartnerKeys {

    /** Return the key associated to a given partner
     * @param partner the given partner
     * @return the expected key
     */
    @WebMethod(operationName = "get")
    public String get(@WebParam(name = "partner") String partner)
        throws PartnerKeysException {
        // select `value` from `partners_key` where `key` = "flickr";
        DataAccessLayer dal = new DataAccessLayer();
        String sql = "SELECT `value` FROM `partners_key` where `key` = \"" +
                     partner + "\";";
        try {
            return dal.extractScalar(sql, "value");
        } catch(Exception e) {
            throw new PartnerKeysException(e.getMessage());
        }
    }

}
