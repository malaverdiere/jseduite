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
 * GNU General Public License for more detailp.
 *
 * You should have received a copy of the GNU General Public License
 * along with jSeduite:SchoolLife; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * @author      Main Steve Colombié            [colombie@polytech.unice.fr]
 *
 **/

package fr.unice.i3s.modalis.jSeduite.technical.plannings;

import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DalResultSet;
import fr.unice.i3s.modalis.jSeduite.libraries.mysql.DataAccessLayer;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService()
public class Plannings {

    public Plannings() {
    }


    @WebMethod(operationName = "getPlannings")
    public String[] getAlbumContent(@WebParam(name = "id") int id)
            throws PlanningException {
        String[] result;

        String sql = "SELECT * FROM `promo_planning` as `p`, `promo_groups` as `g` ";
        sql += "WHERE `p`.`id` = '" + id + "' ";
        sql += "AND `g`.`promo_id` = `p`.`id`;";

        DataAccessLayer dal = new DataAccessLayer();
        try {
            DalResultSet rSet = dal.extractDataSet(sql);

            result = new String[rSet.size()+1];
            result[0] = rSet.getValue("p.planning");

            for(int i=1; i<=rSet.size(); i++){
                result[i] = rSet.getValue("g.planning");
            }

            return result;
        }
        catch (Exception e) {
            throw new PlanningException("SQL Exception: " + e.getMessage());
        }
    }
}
